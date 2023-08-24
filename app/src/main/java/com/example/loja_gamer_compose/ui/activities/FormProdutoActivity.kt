package com.example.loja_gamer_compose.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.loja_gamer_compose.ui.screens.ProductFormScreen
import com.example.loja_gamer_compose.ui.states.ProductFormUiState
import com.example.loja_gamer_compose.ui.theme.LojagamercomposeTheme
import com.example.loja_gamer_compose.ui.viewmodels.ProdutoFormScreenViewModel

class FormProdutoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LojagamercomposeTheme {
                Surface {
                    val viewModel: ProdutoFormScreenViewModel by viewModels()
                    FormProdutoScreen(
                        viewModel = viewModel,
                        onSaveClick = {
                            finish()
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun FormProdutoScreen(
        viewModel: ProdutoFormScreenViewModel,
        onSaveClick: () -> Unit = {}
    ) {
        ProductFormScreen(
            viewModel,
            onSaveClick
        )
    }
}

@Preview
@Composable
fun ProductFormPreview() {
    LojagamercomposeTheme {
        Surface {
            ProductFormScreen(state = ProductFormUiState())
        }
    }
}