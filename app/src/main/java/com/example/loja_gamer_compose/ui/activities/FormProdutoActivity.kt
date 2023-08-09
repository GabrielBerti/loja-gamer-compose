package com.example.loja_gamer_compose.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.loja_gamer_compose.R
import com.example.loja_gamer_compose.dao.ProdutoDao
import com.example.loja_gamer_compose.model.Produto
import com.example.loja_gamer_compose.ui.theme.LojagamercomposeTheme
import java.math.BigDecimal
import java.text.DecimalFormat

class FormProductUiState(
    val onSaveClick: (Produto) -> Unit = {}
) {
    fun converterPreco(preco: String): String {
        val formatter = DecimalFormat("#.##")
        val precoFormatado = try {
            formatter.format(BigDecimal(preco))
        } catch (e: IllegalArgumentException) {
            if (preco.isBlank()) {
                preco
            } else {
                ""
            }
        }

        return precoFormatado
    }
}

class FormProdutoActivity : ComponentActivity() {

    private val dao = ProdutoDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FormProdutoScreen(
                onSaveClick = { produto ->
                    dao.salvar(produto)
                    finish()
                }
            )
        }
    }

    @Composable
    fun FormProdutoScreen(onSaveClick: (Produto) -> Unit = {}) {
        FormProdutoScreen(
            FormProductUiState(
                onSaveClick
            )
        )
    }
}

@Composable
fun FormProdutoScreen(state: FormProductUiState = FormProductUiState()) {
    var url by remember {
        mutableStateOf("")
    }

    var nome by remember {
        mutableStateOf("")
    }

    var preco by remember {
        mutableStateOf("")
    }

    var descricao by remember {
        mutableStateOf("")
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier)
        Text(
            text = "Criando o produto",
            Modifier.fillMaxWidth(),
            fontSize = 28.sp
        )

        if (url.isNotBlank()) {
            AsyncImage(
                model = url,
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder)
            )
        }

        TextField(
            value = url,
            onValueChange = {
                url = it
            },
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "Url da imagem")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            )
        )

        TextField(
            value = nome,
            onValueChange = {
                nome = it
            },
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "Nome")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words
            )
        )

        TextField(
            value = preco,
            onValueChange = {
                preco = state.converterPreco(it)
            },
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "Preço")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            )
        )

        TextField(
            value = descricao,
            onValueChange = {
                descricao = it
            },
            Modifier
                .fillMaxWidth()
                .heightIn(100.dp),
            label = {
                Text(text = "Descrição")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words
            )
        )

        Button(
            onClick = {
                val precoConvertido = try {
                    BigDecimal(preco)
                } catch (e: NumberFormatException) {
                    BigDecimal.ZERO
                }

                val produto = Produto(
                    nome = nome,
                    imagem = R.drawable.placeholder,
                    imagem2 = url,
                    preco = precoConvertido,
                    descricao = descricao
                )

                Log.i("tag", "produto: $produto")
                state.onSaveClick(produto)
            },
            Modifier.fillMaxWidth()
        ) {
            Text(text = "Salvar")
        }

        Spacer(modifier = Modifier)
    }
}

@Composable
@Preview
fun FormProdutoActivityPreview() {
    LojagamercomposeTheme {
        Surface {
            FormProdutoScreen()
        }
    }
}
