package com.example.loja_gamer_compose.dao

import com.example.loja_gamer_compose.model.Produto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProdutoDao {

    companion object {
        private val produtos = MutableStateFlow<List<Produto>>(emptyList())
    }

    fun produtos() = produtos.asStateFlow()

    fun salvar(produto: Produto) {
        produtos.value = produtos.value + produto
    }
}