package com.diegocal.tunechat.data.network

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Interfaz de Retrofit para la API de OpenAI
 */
interface OpenAIService {

    @Headers(
        "Content-Type: application/json"
    )
    @POST("chat/completions")
    suspend fun getChatCompletion(
        @Body request: OpenAIRequest
    ): OpenAIResponse
}

/**
 * Constantes para la API
 */
object OpenAIConstants {
    const val BASE_URL = "https://api.openai.com/v1/"
    const val MUSIC_SYSTEM_PROMPT = """
        Eres un asistente experto en recomendaciones musicales. 
        Tu nombre es TuneChat y tu objetivo es ayudar a los usuarios a descubrir nueva música 
        basándote en sus gustos, estado de ánimo o cualquier criterio que mencionen.
        
        Proporciona recomendaciones específicas de canciones, artistas o álbumes.
        Sé conversacional, amigable y entusiasta sobre la música.
        Si el usuario pregunta sobre algo que no está relacionado con música, 
        redirige amablemente la conversación hacia temas musicales.
    """
}