package com.example.loja_gamer_compose.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loja_gamer_compose.model.Produto
import java.math.BigDecimal
import com.example.loja_gamer_compose.R

@Composable
fun SecaoProdutos(titulo: String, produtos: List<Produto>) {
    Column {
        Text(
            text = titulo,
            Modifier.padding(start = 16.dp, end = 16.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight(400)
        )
        Row(
            Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(Modifier)
            produtos.forEach {
                ProdutoItem(produto = it)
            }
            Spacer(Modifier)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SecaoProdutosPreview() {
    SecaoProdutos("Games", sampleProdutosGames)
}

val sampleProdutosPromocoes = listOf(
    Produto(
        nome = "PS5",
        preco = BigDecimal("4000.00"),
        imagem = R.drawable.ps5
    )
)

val sampleProdutosGames = listOf(
    Produto(
        nome = "PS5",
        preco = BigDecimal("4000.00"),
        imagem = R.drawable.ps5
    ),
    Produto(
        nome = "Xbox One",
        preco = BigDecimal("1799.99"),
        imagem = R.drawable.xbox
    ),
    Produto(
        nome = "Nintendo Switch",
        preco = BigDecimal("800.90"),
        imagem = R.drawable.nintendo
    )
)

val sampleProdutosAcessorios = listOf(
    Produto(
        nome = "Head Set Logitech",
        preco = BigDecimal("300.00"),
        imagem = R.drawable.h7
    ),
    Produto(
        nome = "Controle Elite Xbox",
        preco = BigDecimal("999.99"),
        imagem = R.drawable.controle
    )
)