package com.example.loja_gamer_compose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loja_gamer_compose.ui.theme.LojagamercomposeTheme

class TestComponents {

    @Composable
    fun MeuPrimeiroComposable(text: String) {
        Text(text = text)
    }

    @Preview(
        name = "TextPreview",
        showBackground = true
    )
    @Composable
    fun MeuPrimeiroComposablePreview() {
        LojagamercomposeTheme {
            Column {
                MeuPrimeiroComposable(text = "testeee")
                MeuPrimeiroComposable(text = "testeee 222")
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ColumnRowBoxPreview() {
        Column(
            Modifier
                .padding(8.dp)
                .background(color = Color.Blue)
                .padding(all = 8.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Text(text = "Texto 1")
            Text(text = "Texto 2")
            Row(
                modifier = Modifier
                    .padding(
                        horizontal = 8.dp,
                        vertical = 16.dp
                    )
                    .background(Color.Green)
            ) {
                Text(text = "Texto 3")
                Text(text = "Texto 4")
            }
            Box(
                Modifier
                    .padding(8.dp)
                    .background(color = Color.Red)
                    .padding(all = 8.dp)
            ) {
                Row(
                    Modifier
                        .padding(8.dp)
                        .background(color = Color.Cyan)
                        .padding(all = 8.dp)
                        .fillMaxWidth(0.5f)
                ) {
                    Text(text = "Texto 5")
                    Text(text = "Texto 6")
                }
                Column(
                    Modifier
                        .padding(8.dp)
                        .background(color = Color.Yellow)
                        .padding(all = 8.dp)
                ) {
                    Text(text = "Texto 7")
                    Text(text = "Texto 8")
                }
            }
        }
    }

}