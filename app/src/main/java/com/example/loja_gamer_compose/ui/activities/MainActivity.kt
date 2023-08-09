package com.example.loja_gamer_compose.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.loja_gamer_compose.dao.ProdutoDao
import com.example.loja_gamer_compose.ui.components.sections
import com.example.loja_gamer_compose.ui.screens.HomeScreen
import com.example.loja_gamer_compose.ui.screens.HomeScreenUiState
import com.example.loja_gamer_compose.ui.theme.LojagamercomposeTheme

class MainActivity : ComponentActivity() {

    private val dao = ProdutoDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(onFabClick = {
                startActivity(Intent(this, FormProdutoActivity::class.java))
            }) {
                val produtos = dao.produtos()
                HomeScreen(produtos = produtos)
            }
        }
    }

    @Composable
    fun App(
        onFabClick: () -> Unit = {}, content: @Composable () -> Unit = {}
    ) {
        LojagamercomposeTheme {
            Surface {
                Scaffold(floatingActionButton = {
                    FloatingActionButton(onClick = onFabClick) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    }
                }) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        content()
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun AppPreview() {
        App {
            HomeScreen(HomeScreenUiState(secoes = sections))
        }
    }
}