package com.example.coin_api_as.ui.theme.Coin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.coin_api_as.data.remote.dto.CoinDto
import com.example.coin_api_as.model.CoinViewModel

//TODO Realizar ventana del registo de la clase correspondiente
@Composable
fun RegistroCoinsScreen(
    navHostController: NavHostController,
    viewModel: CoinViewModel = hiltViewModel()
    ) {

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
                    imageVector = Icons.Default.Person,
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
                    imageVector = Icons.Default.Email,
                    contentDescription = null)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedButton(
            onClick = {
                if (validateNumber(viewModel.valor)){
                    viewModel.makePost(CoinDto(null, viewModel.descripcion, viewModel.valor.toDouble(), ""))
                    navHostController.navigateUp()
                }else{

                }

            }
        ) {
            Text(text = "Guardar")
        }
    }
    }
}

fun validateNumber(number:String): Boolean {
    val validation = number.toDouble()

    if (validation >= 0){
        return true
    }else{
        return false
    }
}