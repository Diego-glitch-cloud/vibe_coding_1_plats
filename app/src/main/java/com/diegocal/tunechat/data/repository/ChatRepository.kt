package com.diegocal.tunechat.data.repository

import com.diegocal.tunechat.data.network.ChatMessage
import com.diegocal.tunechat.data.network.OpenAIConstants
import com.diegocal.tunechat.data.network.OpenAIRequest
import com.diegocal.tunechat.data.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repositorio para manejar las operaciones de chat con OpenAI
 */
class ChatRepository {

    private val apiService = RetrofitClient.openAIService

    /**
     * Envía un mensaje al chat y obtiene la respuesta de OpenAI
     * @param messageHistory Historial completo de mensajes del chat
     * @return Respuesta del asistente o mensaje de error
     */
    suspend fun sendMessage(messageHistory: List<ChatMessage>): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                // Construir el request con el sistema prompt y el historial
                val messages = mutableListOf<ChatMessage>().apply {
                    add(ChatMessage(
                        role = "system",
                        content = OpenAIConstants.MUSIC_SYSTEM_PROMPT
                    ))
                    addAll(messageHistory)
                }

                val request = OpenAIRequest(
                    messages = messages
                )

                val response = apiService.getChatCompletion(request)

                // Extraer la respuesta del asistente
                val assistantMessage = response.choices.firstOrNull()?.message?.content
                    ?: throw Exception("No se recibió respuesta del asistente")

                Result.success(assistantMessage)

            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}