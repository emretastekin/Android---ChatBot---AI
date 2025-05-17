package com.emretastekin.chatbotaibootcamp.model

data class ChatGptRequestModel (
    val model: String,
    val messages: List<Message>
)

data class Message(
    val role: String,
    val content: String
)