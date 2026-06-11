package pt.clara.foodflow.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import pt.clara.foodflow.model.DummyData
import pt.clara.foodflow.model.Restaurante

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantCard(
    restaurante: Restaurante,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = onClick
    ) {
        Column {
            // 1. Imagem do Restaurante usando Coil
            AsyncImage(
                model = restaurante.imageUrl,
                contentDescription = "Imagem do restaurante ${restaurante.nome}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentScale = ContentScale.Crop
            )

            // 2. Detalhes em Texto
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = restaurante.nome,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Avaliação",
                            tint = Color(0xFFFFC107),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = restaurante.avaliacao.toString(),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = restaurante.tempoEntregaEstimado,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )

                    Text(
                        text = " • ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )

                    if (restaurante.taxaEntrega == 0.0) {
                        Text(
                            text = "Entrega Grátis",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF4CAF50),
                            fontWeight = FontWeight.Medium
                        )
                    } else {
                        Text(
                            text = "€${restaurante.taxaEntrega} entrega",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    name = "Cartão de Restaurante",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun RestaurantCardPreview() {
    val restauranteTeste = DummyData.restaurantes[0]

    RestaurantCard(
        restaurante = restauranteTeste,
        modifier = Modifier.padding(16.dp),
        onClick = {}
    )
}