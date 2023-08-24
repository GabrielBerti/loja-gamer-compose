package com.example.loja_gamer_compose.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.loja_gamer_compose.R
import com.example.loja_gamer_compose.dao.ProdutoDao
import com.example.loja_gamer_compose.model.Produto
import com.example.loja_gamer_compose.ui.states.ProductFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.math.BigDecimal
import java.text.DecimalFormat

class ProdutoFormScreenViewModel : ViewModel() {

    private val dao = ProdutoDao()

    private val _uiState: MutableStateFlow<ProductFormUiState> =
        MutableStateFlow(ProductFormUiState())
    val uiState get() = _uiState.asStateFlow()

    val formatter = DecimalFormat("#.##")

    init {
        _uiState.update { estadoAtual ->
            estadoAtual.copy(
                onUrlChange = {
                    _uiState.value = _uiState.value.copy(
                        url = it
                    )
                },
                onNameChange = {
                    _uiState.value = _uiState.value.copy(
                        name = it
                    )
                },
                onPriceChange = {
                    val price = try {
                        formatter.format(BigDecimal(it))
                    } catch (e: IllegalArgumentException) {
                        if (it.isEmpty()) {
                            it
                        } else {
                            null
                        }
                    }

                    price.let {
                        _uiState.value = _uiState.value.copy(
                            price = price ?: ""
                        )
                    }

                },
                onDescriptionChange = {
                    _uiState.value = _uiState.value.copy(
                        description = it
                    )
                },
            )
        }
    }

    fun save() {
        _uiState.value.run {
            val convertedPrice = try {
                BigDecimal(price)
            } catch (e: NumberFormatException) {
                BigDecimal.ZERO
            }
            val product = Produto(
                nome = name,
                imagem = R.drawable.placeholder,
                imagem2 = url,
                preco = convertedPrice,
                descricao = description
            )
            dao.salvar(product)
        }
    }
}