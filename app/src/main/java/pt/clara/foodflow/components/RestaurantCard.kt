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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import pt.clara.foodflow.model.Restaurante

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantCard(
    restaurante: Restaurante,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {} // Permite passar uma ação de clique para navegação
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
                contentScale = ContentScale.Crop // Corta a imagem para preencher o espaço mantendo a proporção
            )

            // 2. Detalhes em Texto (Nome, Avaliação, Tempo, Taxa)
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Linha superior: Nome e Avaliação
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = restaurante.nome,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f) // Evita que nomes grandes empurrem a avaliação para fora
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    // Avaliação com ícone de estrela
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Avaliação",
                            tint = Color(0xFFFFC107), // Cor amarela/dourada
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

                // Linha inferior: Tempo de entrega e Taxa
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

                    // Ponto separador
                    Text(
                        text = " • ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )

                    // Lógica para mostrar "Grátis" a verde ou o preço da taxa
                    if (restaurante.taxaEntrega == 0.0) {
                        Text(
                            text = "Entrega Grátis",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF4CAF50), // Verde
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