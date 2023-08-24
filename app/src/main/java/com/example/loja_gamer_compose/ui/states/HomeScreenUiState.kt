package com.example.loja_gamer_compose.ui.states

import com.example.loja_gamer_compose.model.Produto

data class HomeScreenUiState(
    val secoes: Map<String, List<Produto>> = emptyMap(),
    val produtosFiltrados: List<Produto> = emptyList(),
    val searchText: String = "",
    val onSearchChange: (String) -> Unit = {}
) {
    fun isMostraSecoes(): Boolean = searchText.isBlank()
}