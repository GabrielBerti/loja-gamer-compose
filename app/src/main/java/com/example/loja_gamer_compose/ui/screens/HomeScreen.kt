package com.example.loja_gamer_compose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loja_gamer_compose.ui.components.*

@Composable
fun HomeScreen(
    searchText: String = ""
) {
    Column {

        var novoTexto by remember {
            mutableStateOf(searchText)
        }
        CampoDeTextoDeBusca(searchText = novoTexto, onSearchChange = {
            novoTexto = it
        })

        val produtosFiltrados = remember(novoTexto) {
            if(novoTexto.isNotBlank()) {
                todosProdutos().filter { produto ->
                    produto.nome.contains(novoTexto, ignoreCase = true) ||
                            produto.descricao?.contains(novoTexto, ignoreCase = true) ?: false
                }
            } else {
                emptyList()
            }
        }

        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {

            if (novoTexto.isBlank()) {
                for (section in sections) {
                    item {
                        SecaoProdutos(titulo = section.key, produtos = section.value)
                    }
                }
            } else {
                items(produtosFiltrados) { p ->
                    CardProductItem(
                        produto = p,
                        Modifier.padding(bottom = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenComCampoDeBuscaPreenchidoPreview() {
    HomeScreen("a")
}