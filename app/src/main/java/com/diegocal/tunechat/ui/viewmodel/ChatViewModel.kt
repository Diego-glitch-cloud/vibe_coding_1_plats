package com.diegocal.tunechat.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegocal.tunechat.data.network.ChatMessage
import com.diegocal.tunechat.data.repository.ChatRepository
import com.diegocal.tunechat.model.ChatUiState
import com.diegocal.tunechat.model.MusicMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para manejar la lógica del chat
 */
class ChatViewModel : ViewModel() {

    private val repository = ChatRepository()

    private val _uiState = MutableStateFlow<ChatUiState>(ChatUiState.Initial)
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    private val _messages = MutableStateFlow<List<MusicMessage>>(emptyList())
    val messages: StateFlow<List<MusicMessage>> = _messages.asStateFlow()

    /**
     * Envía un mensaje del usuario y obtiene la respuesta
     */
    fun sendMessage(userMessage: String) {
        if (userMessage.isBlank()) return

        viewModelScope.launch {
            try {
                // Agregar mensaje del usuario
                val userMsg = MusicMessage(
                    content = userMessage,
                    isUser = true
                )
                _messages.value = _messages.value + userMsg
                _uiState.value = ChatUiState.Loading

                // Construir historial para la API
                val messageHistory = _messages.value.map { msg ->
                    ChatMessage(
                        role = if (msg.isUser) "user" else "assistant",
                        content = msg.content
                    )
                }

                // Enviar al repositorio
                val result = repository.sendMessage(messageHistory)

                result.onSuccess { response ->
                    val assistantMsg = MusicMessage(
                        content = response,
                        isUser = false
                    )
                    _messages.value = _messages.value + assistantMsg
                    _uiState.value = ChatUiState.Success(_messages.value)
                }

                result.onFailure { error ->
                    _uiState.value = ChatUiState.Error(
                        error.message ?: "Error al enviar mensaje"
                    )
                }

            } catch (e: Exception) {
                _uiState.value = ChatUiState.Error(
                    e.message ?: "Error desconocido"
                )
            }
        }
    }

    /**
     * Limpia el chat
     */
    fun clearChat() {
        _messages.value = emptyList()
        _uiState.value = ChatUiState.Initial
    }

    /**
     * Inicializa el chat con un mensaje de bienvenida
     */
    fun initializeChat() {
        if (_messages.value.isEmpty()) {
            val welcomeMessage = MusicMessage(
                content = "¡Hola! Soy TuneChat 🎵\n\n" +
                        "Estoy aquí para ayudarte a descubrir nueva música. " +
                        "Cuéntame qué tipo de música te gusta, cómo te sientes hoy, " +
                        "o simplemente pídeme recomendaciones.",
                isUser = false
            )
            _messages.value = listOf(welcomeMessage)
            _uiState.value = ChatUiState.Success(_messages.value)
        }
    }
}