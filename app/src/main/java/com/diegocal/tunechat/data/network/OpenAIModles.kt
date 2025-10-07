package com.diegocal.tunechat.data.network

import com.google.gson.annotations.SerializedName

/**
 * Modelos de datos para la API de OpenAI
 */

data class OpenAIRequest(
    val model: String = "gpt-3.5-turbo",
    val messages: List<ChatMessage>,
    @SerializedName("max_tokens")
    val maxTokens: Int = 500,
    val temperature: Double = 0.7
)

data class ChatMessage(
    val role: String, // "system", "user", o "assistant"
    val content: String
)

data class OpenAIResponse(
    val id: String,
    val choices: List<Choice>,
    val usage: Usage
)

data class Choice(
    val message: ChatMessage,
    val index: Int,
    @SerializedName("finish_reason")
    val finishReason: String
)

data class Usage(
    @SerializedName("prompt_tokens")
    val promptTokens: Int,
    @SerializedName("completion_tokens")
    val completionTokens: Int,
    @SerializedName("total_tokens")
    val totalTokens: Int
)