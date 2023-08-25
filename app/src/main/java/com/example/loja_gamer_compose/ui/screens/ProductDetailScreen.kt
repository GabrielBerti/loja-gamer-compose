package com.example.loja_gamer_compose.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loja_gamer_compose.R
import com.example.loja_gamer_compose.extensions.toMoedaBrasileira
import com.example.loja_gamer_compose.model.Produto
import com.example.loja_gamer_compose.ui.theme.LojagamercomposeTheme
import java.math.BigDecimal

@Composable
fun ProductDetailScreen(produto: Produto) {
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Card(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            elevation = 4.dp
        ) {
            Column {
                Image(
                    painter = painterResource(id = produto.imagem),
                    contentDescription = null,
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = produto.nome,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = produto.preco.toMoedaBrasileira(),
                        color = Color.Green
                    )

                    produto.descricao?.let {
                        Text(
                            text = it,
                            Modifier
                                .padding(top = 16.dp)
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.weight(1f))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = { (context as? ComponentActivity)?.finish() },
                            border = BorderStroke(4.dp, Color.Black),
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .padding(end = 4.dp)
                        ) {
                            Text(text = "VOLTAR")
                        }

                        Button(
                            onClick = { },
                            border = BorderStroke(4.dp, Color.Black),
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .padding(start = 4.dp)
                        ) {
                            Text(text = "COMPRAR")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ProductDetailScreenPreview() {
    LojagamercomposeTheme {
        Surface {
            ProductDetailScreen(
                Produto(
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