package com.emretastekin.chatbotaibootcamp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.emretastekin.chatbotaibootcamp.ui.theme.ChatBotAIBootcampTheme
import com.emretastekin.chatbotaibootcamp.ui.theme.ChatBotAIBootcampTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatBotAIBootcampTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, viewModel: ChatGptViewModel = hiltViewModel()) {

    val generatedMessage = viewModel.generatedMessage.value

    Column(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)) {
        generatedMessage?.takeIf { it.isNotEmpty() }?.let {
            
            LazyColumn{
                items(it) { message ->
                    Text(
                        text = message
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                }
                
            }
            
        }

        Spacer(modifier = Modifier.weight(1f))

        var inputText by remember {
            mutableStateOf("")
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            
            TextField(modifier = Modifier.weight(1f), value = inputText, onValueChange = {
                inputText = it
            })

            Button(modifier = Modifier.weight(1f), onClick = { viewModel.createMessage(inputText) }) {
                Text(text = "Gönder")
            }
        }

    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChatBotAIBootcampTheme {
        Greeting("Android")
    }
}