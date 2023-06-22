package com.example.loja_gamer_compose.model

import androidx.annotation.DrawableRes
import com.example.loja_gamer_compose.R
import java.math.BigDecimal

class Produto(
    val nome: String,
    val preco: BigDecimal,
    @DrawableRes val imagem: Int = R.drawable.ic_launcher_background,
    val descricao: String? = null
)