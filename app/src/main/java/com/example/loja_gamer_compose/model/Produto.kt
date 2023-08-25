package com.example.loja_gamer_compose.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.example.loja_gamer_compose.R
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Parcelize
data class Produto(
    val nome: String,
    val preco: BigDecimal,
    @DrawableRes val imagem: Int = R.drawable.ic_launcher_background,
    val imagem2: String? = null,
    val descricao: String? = null
) : Parcelable