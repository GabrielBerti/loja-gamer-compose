package com.example.loja_gamer_compose.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

fun BigDecimal.toMoedaBrasileira(): String {
    return NumberFormat.getCurrencyInstance(Locale("pt", "br")).format(this)
}
