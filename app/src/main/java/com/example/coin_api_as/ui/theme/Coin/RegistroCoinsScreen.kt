package com.example.coin_api_as.ui.theme.Coin

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.coin_api_as.data.remote.dto.CoinDto
import com.example.coin_api_as.model.CoinViewModel

@Composable
fun RegistroCoinsScreen(
    navHostController: NavHostController,
    viewModel: CoinViewModel = hiltViewModel()
    ) {

    val context = LocalContext.current
    var error by rememberSaveable{ mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Registro de Monedas") }) }
    ){it


    Column(modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = viewModel.descripcion,
            onValueChange = {viewModel.descripcion = it},
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Nombre de la Moneda")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Input,
                    contentDescription = null)
            }
        )

        OutlinedTextField(
            value = viewModel.valor,
            onValueChange = {viewModel.valor = it},
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Valor")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AttachMoney,
                    contentDescription = null)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(
            onClick = {
                error = viewModel.descripcion.isBlank()
                if(!error){
                    if (viewModel.valor.toDouble() > 0)
                        viewModel.makePost()
                    navHostController.navigate("CoinList")
                }
                else{
                    Toast.makeText(context, "Transaccion Fallida", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ) {
            Text("Guardar")
        }

    }
    }
}

/*
fun validateNumber(number:String): Boolean {
    val validation = number.toDouble()

    if (validation >= 0){
        return true
    }else{
        return false
    }
}*/
