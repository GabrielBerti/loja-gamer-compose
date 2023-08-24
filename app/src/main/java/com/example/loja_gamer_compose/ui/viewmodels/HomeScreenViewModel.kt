package com.example.loja_gamer_compose.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loja_gamer_compose.dao.ProdutoDao
import com.example.loja_gamer_compose.model.Produto
import com.example.loja_gamer_compose.ui.components.sections
import com.example.loja_gamer_compose.ui.components.todosProdutos
import com.example.loja_gamer_compose.ui.states.HomeScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val dao = ProdutoDao()

    private val _uiState: MutableStateFlow<HomeScreenUiState> = MutableStateFlow(HomeScreenUiState())
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.update { estadoAtual ->
            estadoAtual.copy(
                onSearchChange = {
                    _uiState.value = _uiState.value.copy(
                        searchText = it,
                        produtosFiltrados = produtosFiltrados(it)
                    )
                }
            )
        }

        viewModelScope.launch {
            dao.produtos().collect { produtos ->
                _uiState.value = _uiState.value.copy(
                    secoes = sections.plus(
                        "Todos produtos" to produtos
                    ),
                    produtosFiltrados = produtosFiltrados(_uiState.value.searchText)
                )
            }
        }
    }

    private fun containsInNameOrDescription(texto: String) = { produto: Produto ->
        produto.nome.contains(texto, ignoreCase = true) ||
                produto.descricao?.contains(texto, ignoreCase = true) ?: false
    }

    private fun produtosFiltrados(texto: String): List<Produto> {
        return if (texto.isNotBlank()) {
            todosProdutos().filter(containsInNameOrDescription(texto)) + dao.produtos().value.filter(
                containsInNameOrDescription(texto)
            )
        } else {
            emptyList()
        }
    }
}