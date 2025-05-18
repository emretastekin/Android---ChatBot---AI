package com.emretastekin.chatbotaibootcamp.usecase

import com.emretastekin.chatbotaibootcamp.di.ChatGptRepository
import com.emretastekin.chatbotaibootcamp.model.ChatGptRequestModel
import com.emretastekin.chatbotaibootcamp.model.ChatGptResponseModel
import retrofit2.Response
import javax.inject.Inject

class ChatGptUseCase @Inject constructor(private val repository: ChatGptRepository) {
    suspend operator fun invoke(
        apiKey: String,
        requestBody: ChatGptRequestModel
    ): Response<ChatGptResponseModel> = repository.createMessage(apiKey, requestBody)
}