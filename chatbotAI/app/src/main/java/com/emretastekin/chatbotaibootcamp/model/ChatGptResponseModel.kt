package com.emretastekin.chatbotaibootcamp.model

import com.google.gson.annotations.SerializedName

data class ChatGptResponseModel (
    val id: String,
    @SerializedName("object")
    val objectType: String,
    val created: Long,
    val model: Long,
    val choices: List<Choices>,
    val usage: Usage
)

data class Choices(
    val index: Int,
    val message: Message,
    @SerializedName("finish reason")
    val finishReason: String
)

data class Usage(
    @SerializedName("prompt token")
    val promptToken: Int,
    @SerializedName("completion_token")
    val completionToken: Int,
    @SerializedName("total_tokens")
    val totalTokens: Int
)