package pt.clara.foodflow.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import pt.clara.foodflow.model.DummyData
import pt.clara.foodflow.model.ItemDoMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantDetailScreen(
    restauranteId: String,
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val restaurante = DummyData.restaurantes.find { it.id == restauranteId }
    val menuDoRestaurante = DummyData.getMenuParaRestaurante(restauranteId)

    // Variáveis de "Memória" para a nossa simulação interativa
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }

    if (restaurante == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Restaurante não encontrado.")
        }
        return
    }

    // Se o utilizador clicou em Finalizar Pedido, mostramos este Pop-up (AlertDialog)
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Pedido Confirmado! 🎉") },
            text = { Text("O teu pedido no ${restaurante.nome} foi recebido e a cozinha já começou a prepará-lo.") },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    onBackClick() // Volta magicamente à página inicial depois da compra!
                }) {
                    Text("Fantástico")
                }
            }
        )
    }

    Scaffold(
        // Onde aparecem os avisos rápidos (Snackbar)
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(restaurante.nome, maxLines = 1, overflow = TextOverflow.Ellipsis) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar atrás")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
            )
        },
        // O Botão Flutuante (Carrinho de Compras)
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { showDialog = true },
                icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = "Carrinho") },
                text = { Text("Finalizar Pedido") },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier.fillMaxSize().padding(paddingValues),
            // Adicionamos 80.dp no fundo para o Botão Flutuante não esconder o último prato da lista
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            // --- CABEÇALHO ---
            item {
                AsyncImage(
                    model = restaurante.imageUrl,
                    contentDescription = "Imagem de ${restaurante.nome}",
                    modifier = Modifier.fillMaxWidth().height(220.dp),
                    contentScale = ContentScale.Crop
                )

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = restaurante.nome,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${restaurante.avaliacao} ★ • ${restaurante.tempoEntregaEstimado}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }

                HorizontalDivider(thickness = 8.dp, color = Color(0xFFF0F0F0))
            }

            // --- TÍTULO DA EMENTA ---
            item {
                Text(
                    text = "Ementa",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }

            // --- LISTA DE PRATOS ---
            items(menuDoRestaurante) { prato ->
                MenuItemRow(
                    item = prato,
                    onAddToCart = {
                        // Dispara a pequena mensagem a avisar que o prato foi adicionado
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("${prato.nome} adicionado!")
                        }
                    }
                )
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), color = Color(0xFFEEEEEE))
            }
        }
    }
}

@Composable
fun MenuItemRow(item: ItemDoMenu, onAddToCart: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f).padding(end = 16.dp)) {
            Text(
                text = item.nome,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.descricao,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Fila com o Preço e o botão "+"
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "€${item.preco}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.width(16.dp))
                FilledTonalIconButton(
                    onClick = onAddToCart,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Adicionar", modifier = Modifier.size(16.dp))
                }
            }
        }

        AsyncImage(
            model = item.imageUrl,
            contentDescription = "Imagem de ${item.nome}",
            modifier = Modifier.size(90.dp).clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun RestaurantDetailScreenPreview() {
    MaterialTheme {
        RestaurantDetailScreen(restauranteId = "rest_1")
    }
}