package com.example.coin_api_as

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.coin_api_as.data.remote.dto.CoinDto
import com.example.coin_api_as.model.CoinViewModel
import com.example.coin_api_as.ui.theme.COINAPIASTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            COINAPIASTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CoinListScreen()
            }
        }
    }
}

@Composable
fun CoinListScreen(
    viewModel: CoinViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items( state.exchange){ exchange ->
                CoinItem(coin = exchange, {})
            }
        }

        if (state.isLoading)
            CircularProgressIndicator()

    }

}

@Composable
fun CoinItem(
    coin:CoinDto,
    onClick : (CoinDto) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick(coin) }
        .padding(16.dp)
    ) {
        Text(
            text = "${coin.description}",
            style = MaterialTheme.typography.h5,
            overflow = TextOverflow.Ellipsis
        )

        Row(
            modifier = Modifier.fillMaxWidth()
                .height(30.dp).padding(2.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${coin.valor}",
                color = Color.Green,
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.body2,
            )
            Image(
                painter = rememberAsyncImagePainter(coin.imageUrl),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
        }

    }
}
}