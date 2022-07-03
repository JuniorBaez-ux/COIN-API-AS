package com.example.coin_api_as

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coin_api_as.ui.theme.Coin.CoinListScreen
import com.example.coin_api_as.ui.theme.Coin.RegistroCoinsScreen
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
                    MyApp()
            }
        }
    }
}

    @Composable
    fun MyApp() {
        val navHostController = rememberNavController()

        NavHost(navController = navHostController, startDestination = "CoinList"){
            composable("CoinList"){
                CoinListScreen(navHostController = navHostController)
            }
            composable("RegistroCoins"){
                RegistroCoinsScreen(navHostController = navHostController)
            }
        }
    }

/*@Composable
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

}*/


}