package com.example.loja_gamer_compose.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.loja_gamer_compose.R
import com.example.loja_gamer_compose.model.Produto
import com.example.loja_gamer_compose.ui.screens.ProductDetailScreen
import com.example.loja_gamer_compose.ui.theme.LojagamercomposeTheme
import java.math.BigDecimal

class ProductDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val produto = intent.getParcelableExtra<Produto>("produto")

        setContent {
            LojagamercomposeTheme {
                Surface {
                    FormProductScreen(
                        produto = Produto(
                            nome = produto?.nome ?: "",
                            produto?.preco ?: BigDecimal.ZERO,
                             produto?.imagem ?: R.drawable.placeholder,
                            "",
                            produto?.descricao ?: ""
                        )
                    )
                }
            }
        }
    }

    @Composable
    fun FormProductScreen(
        produto: Produto
    ) {
        ProductDetailScreen(
            produto
        )
    }
}

@Preview
@Composable
fun ProductDetailPreview() {
    LojagamercomposeTheme {
        Surface {
            ProductDetailScreen(
                produto = Produto(
                    nome = "Controle Elite Xbox One",
                    BigDecimal.TEN,
                    R.drawable.controle,
                    "",
                    "Controle muito bom, com garantia de 2 anos"
                )
            )
        }
    }
}