package com.example.loja_gamer_compose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loja_gamer_compose.R
import com.example.loja_gamer_compose.model.Produto
import java.math.BigDecimal

@Composable
fun SecaoProdutos(
    titulo: String,
    produtos: List<Produto>,
    modifier: Modifier = Modifier,
    onClickItem: (Produto) -> Unit = {}
) {
    Column(modifier) {
        Text(
            text = titulo,
            Modifier.padding(start = 16.dp, end = 16.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight(400)
        )
        LazyRow(
            Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(produtos) { p ->
                ProdutoItem(produto = p, onClickItem = onClickItem)
            }
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
        imagem = R.drawable.ps5,
        descricao = LoremIpsum(20).values.first()
    )
)

val sampleProdutosGames = listOf(
    Produto(
        nome = "PS5",
        preco = BigDecimal("4000.00"),
        imagem = R.drawable.ps5,
        descricao = LoremIpsum(20).values.first()
    ),
    Produto(
        nome = "Xbox One",
        preco = BigDecimal("1799.99"),
        imagem = R.drawable.xbox
    ),
    Produto(
        nome = "Nintendo Switch",
        preco = BigDecimal("800.90"),
        imagem = R.drawable.nintendo,
        descricao = LoremIpsum(20).values.first()
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

val sections = mapOf(
    "Promoções" to sampleProdutosPromocoes,
    "Games" to sampleProdutosGames,
    "Acessórios" to sampleProdutosAcessorios
)

fun todosProdutos(): List<Produto> {
    val produtos = mutableListOf<Produto>()
    produtos.addAll(sampleProdutosGames)
    produtos.addAll(sampleProdutosAcessorios)

    return produtos
}