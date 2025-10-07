package com.diegocal.tunechat.model

/**
 * Modelo de datos para un mensaje en el chat de música
 * @param id Identificador único del mensaje
 * @param content Contenido del mensaje
 * @param isUser true si el mensaje es del usuario, false si es de la IA
 * @param timestamp Marca de tiempo del mensaje
 */
data class MusicMessage(
    val id: String = java.util.UUID.randomUUID().toString(),
    val content: String,
    val isUser: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)

/**
 * Estados posibles del chat
 */
sealed class ChatUiState {
    object Initial : ChatUiState()
    object Loading : ChatUiState()
    data class Success(val messages: List<MusicMessage>) : ChatUiState()
    data class Error(val message: String) : ChatUiState()
}