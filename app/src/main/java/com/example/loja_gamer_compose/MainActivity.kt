package com.example.loja_gamer_compose
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.loja_gamer_compose.ui.screens.HomeScreen
import com.example.loja_gamer_compose.ui.theme.LojagamercomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }

    @Composable
    fun App() {
        LojagamercomposeTheme {
            Surface {
                HomeScreen()
            }
        }
    }
}

