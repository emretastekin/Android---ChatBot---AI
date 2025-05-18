package com.emretastekin.chatbotaibootcamp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emretastekin.chatbotaibootcamp.Constants.Companion.API_KEY
import com.emretastekin.chatbotaibootcamp.model.ChatGptRequestModel
import com.emretastekin.chatbotaibootcamp.model.Choices
import com.emretastekin.chatbotaibootcamp.model.Message
import com.emretastekin.chatbotaibootcamp.usecase.ChatGptUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatGptViewModel @Inject constructor(private val useCase: ChatGptUseCase): ViewModel() {

    private val _generatedMessage = mutableStateOf<List<String>>(emptyList())
    val generatedMessage: State<List<String>> = _generatedMessage;

    fun createMessage(text: String) {

        addMessage("Emre: $text")

        viewModelScope.launch {
           val response = useCase.invoke(
                apiKey = "Bearer $API_KEY",
                requestBody = ChatGptRequestModel(
                    model = "gpt-4o-mini",
                    messages = listOf(
                        Message(
                            role = "system",
                            content = "You are a helpful assistant"
                        ),
                        Message(
                            role = "user",
                            content = text
                        )
                    ),
                    maxTokens = 2500
                )
            )

            if(response.isSuccessful){
                response.body()?.let {
                    addMessage("ChatBot: " +it.choices.first().message.content)
                }
            }
        }
    }

    private fun addMessage(message: String) {
        _generatedMessage.value += message
    }





















}