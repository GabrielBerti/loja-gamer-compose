package com.example.loja_gamer_compose.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.material.Surface
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loja_gamer_compose.model.Produto
import com.example.loja_gamer_compose.ui.components.CampoDeTextoDeBusca
import com.example.loja_gamer_compose.ui.components.CardProductItem
import com.example.loja_gamer_compose.ui.components.SecaoProdutos
import com.example.loja_gamer_compose.ui.components.sections
import com.example.loja_gamer_compose.ui.components.todosProdutos
import com.example.loja_gamer_compose.ui.theme.LojagamercomposeTheme

class HomeScreenUiState(
    val secoes: Map<String, List<Produto>> = emptyMap(),
    val produtosFiltrados: List<Produto> = emptyList(),
    val searchText: String = "",
    val onSearchChange: (String) -> Unit = {}
) {
    fun isMostraSecoes(): Boolean = searchText.isBlank()
}

@Composable
fun HomeScreen(produtos: List<Produto>) {
    val secoes = sections.plus("Todos produtos" to produtos)

    var texto by remember {
        mutableStateOf("")
    }

    fun containsInNameOrDescription() = { produto: Produto ->
        produto.nome.contains(texto, ignoreCase = true) ||
                produto.descricao?.contains(texto, ignoreCase = true) ?: false
    }

    val produtosFiltrados = remember(texto, produtos) {
        if (texto.isNotBlank()) {
            todosProdutos().filter(containsInNameOrDescription()) + produtos.filter(
                containsInNameOrDescription()
            )
        } else {
            emptyList()
        }
    }

    val state = remember(produtos, texto) {
        HomeScreenUiState(secoes,
            produtosFiltrados,
            texto,
            onSearchChange = {
                texto = it
            })
    }

    HomeScreen(state = state)
}

@Composable
fun HomeScreen(
    state: HomeScreenUiState = HomeScreenUiState()
) {
    Column {
        val secoes = state.secoes
        val produtosFiltrados = state.produtosFiltrados

        CampoDeTextoDeBusca(searchText = state.searchText, onSearchChange = state.onSearchChange)

        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {

            if (state.isMostraSecoes()) {
                for (section in secoes) {
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
private fun HomeScreenPreview() {
    LojagamercomposeTheme {
        Surface {
            HomeScreen(HomeScreenUiState(sections))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenComCampoDeBuscaPreenchidoPreview() {
    HomeScreen(state = HomeScreenUiState(searchText = "a"))
}