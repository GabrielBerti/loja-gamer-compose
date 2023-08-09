package com.example.loja_gamer_compose.dao

import androidx.compose.runtime.mutableStateListOf
import com.example.loja_gamer_compose.model.Produto

class ProdutoDao {

    companion object {
        private val produtos = mutableStateListOf<Produto>()
    }

    fun produtos() = produtos.toList()

    fun salvar(produto: Produto) {
        produtos.add(produto)
    }
}