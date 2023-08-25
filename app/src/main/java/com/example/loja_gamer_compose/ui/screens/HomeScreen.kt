package com.example.loja_gamer_compose.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loja_gamer_compose.model.Produto
import com.example.loja_gamer_compose.ui.components.CampoDeTextoDeBusca
import com.example.loja_gamer_compose.ui.components.CardProductItem
import com.example.loja_gamer_compose.ui.components.SecaoProdutos
import com.example.loja_gamer_compose.ui.components.sections
import com.example.loja_gamer_compose.ui.components.todosProdutos
import com.example.loja_gamer_compose.ui.states.HomeScreenUiState
import com.example.loja_gamer_compose.ui.theme.LojagamercomposeTheme
import com.example.loja_gamer_compose.ui.viewmodels.HomeScreenViewModel

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel, onClickItemSection: (Produto) -> Unit = {}) {
    val state by viewModel.uiState.collectAsState()
    HomeScreen(state = state, onClickItemSection = onClickItemSection)
}

@Composable
fun HomeScreen(
    state: HomeScreenUiState = HomeScreenUiState(),
    onClickItemSection: (Produto) -> Unit = {}
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
                        SecaoProdutos(titulo = section.key, produtos = section.value, onClickItem = onClickItemSection)
                    }

                }

            } else {
                items(produtosFiltrados) { p ->
                    CardProductItem(
                        produto = p,
                        Modifier.padding(bottom = 16.dp),
                        isExpanded = false
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
    HomeScreen(
        state = HomeScreenUiState(
            produtosFiltrados = todosProdutos(), searchText = "a"
        )
    )
}