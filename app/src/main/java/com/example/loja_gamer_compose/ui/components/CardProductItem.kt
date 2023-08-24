package com.example.loja_gamer_compose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.loja_gamer_compose.extensions.toMoedaBrasileira
import com.example.loja_gamer_compose.model.Produto
import com.example.loja_gamer_compose.ui.theme.LojagamercomposeTheme
import java.math.BigDecimal

@Composable
fun CardProductItem(
    produto: Produto,
    modifier: Modifier = Modifier,
    elevation: Dp = 4.dp,
    isExpanded: Boolean = false
) {
    var expanded by remember {
        mutableStateOf(isExpanded)
    }

    Card(
        modifier
            .fillMaxWidth()
            .heightIn(150.dp)
            .clickable {
                expanded = !expanded
            },
        elevation = elevation
    ) {
        Column {
            Image(
                painter = painterResource(id = produto.imagem),
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.primaryVariant)
                    .padding(16.dp)
            ) {
                Text(
                    text = produto.nome
                )
                Text(
                    text = produto.preco.toMoedaBrasileira()
                )
            }

            if (expanded) {
                produto.descricao?.let {
                    Text(
                        text = produto.descricao,
                        Modifier
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CardProductItemSemDescricaoPreview() {
    LojagamercomposeTheme {
        Surface {
            CardProductItem(
                produto = Produto(
                    nome = "teste",
                    preco = BigDecimal("9.99")
                )
            )
        }
    }
}

@Preview
@Composable
private fun CardProductItemComDescricaoPreview() {
    LojagamercomposeTheme {
        Surface {
            CardProductItem(
                produto = Produto(
                    nome = "teste",
                    preco = BigDecimal("9.99"),
                    descricao = LoremIpsum(50).values.first()
                ),
                isExpanded = true
            )
        }
    }
}