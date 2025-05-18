package com.emretastekin.chatbotaibootcamp.di

import com.emretastekin.chatbotaibootcamp.model.ChatGptRequestModel
import com.emretastekin.chatbotaibootcamp.model.ChatGptResponseModel
import retrofit2.Response
import javax.inject.Inject

class ChatGptRepository @Inject constructor(private val apiServices: ApiServices) {
    suspend fun createMessage(apiKey: String, requestBody: ChatGptRequestModel): Response<ChatGptResponseModel> = apiServices.createMessage(apiKey, requestBody)
}