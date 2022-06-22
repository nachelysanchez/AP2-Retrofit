package com.example.apiretrofit


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.apiretrofit.data.remote.dto.Exchanges
import com.example.apiretrofit.ui.exchanges.ExchangesViewModel
import com.example.apiretrofit.ui.theme.ApiRetrofitTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiRetrofitTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ExchangeListScreen()
                }
            }
        }
    }
}

@Composable
fun ExchangeListScreen(
    viewModel: ExchangesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()){

            items( state.exchanges){ exchanges ->
                ExchangeItem(exchanges = exchanges, {})
            }
        }

        if (state.isLoading)
            CircularProgressIndicator()


    }

}

@Composable
fun ExchangeItem(
    exchanges : Exchanges,
    onClick : (Exchanges) -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick(exchanges) }
        .padding(16.dp)
    ) {
        Text(
            text = "${exchanges.name} (${exchanges.last_updated})",
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = if(exchanges.active) "Activa" else "Inactiva",
            color = if(exchanges.active) Color.Green else Color.Red,
            fontStyle = Italic,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.align(CenterVertically)
        )


    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ApiRetrofitTheme {
        ExchangeListScreen()
    }
}