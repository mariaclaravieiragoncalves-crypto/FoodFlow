package pt.clara.foodflow.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.clara.foodflow.model.DummyData
import pt.clara.foodflow.components.RestaurantCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onRestaurantClick: (String) -> Unit = {}
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Restaurantes Disponíveis",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(DummyData.restaurantes) { restaurante ->
                RestaurantCard(
                    restaurante = restaurante,
                    onClick = { onRestaurantClick(restaurante.id) }
                )
            }
        }
    }
}

@Preview(
    name = "Ecrã Principal",
    showBackground = true,
    backgroundColor = 0xFFF5F5F5
)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(
            onRestaurantClick = { id ->
                println("Clicou no restaurante: $id")
            }
        )
    }
}