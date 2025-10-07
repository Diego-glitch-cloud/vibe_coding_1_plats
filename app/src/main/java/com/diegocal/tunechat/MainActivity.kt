package com.diegocal.tunechat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.diegocal.tunechat.data.network.RetrofitClient
import com.diegocal.tunechat.ui.screens.ChatScreen
import com.diegocal.tunechat.ui.theme.TuneChatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar API Key (debe reemplazarse con la key real)
        // IMPORTANTE: En producción, usar una forma más segura de almacenar la API key
        RetrofitClient.setApiKey(BuildConfig.OPENAI_API_KEY)

        enableEdgeToEdge()
        setContent {
            TuneChatTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ChatScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}