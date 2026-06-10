package pt.clara.foodflow.model

import java.util.UUID

// Representa o Restaurante na lista principal
data class Restaurante(
    val id: String = UUID.randomUUID().toString(),
    val nome: String,
    val imageUrl: String, // URL para usar com a biblioteca Coil, por exemplo
    val avaliacao: Double, // Ex: 4.5
    val tempoEntregaEstimado: String, // Ex: "20-30 min"
    val taxaEntrega: Double,
    val categorias: List<String> = emptyList() // Ex: listOf("Burgers", "Fast Food")
)

// Representa um prato ou produto dentro da ementa do restaurante
data class ItemDoMenu(
    val id: String = UUID.randomUUID().toString(),
    val restauranteId: String, // Relacionamento com o restaurante
    val nome: String,
    val descricao: String,
    val preco: Double,
    val imageUrl: String
)

// Representa o que está atualmente no cesto de compras do utilizador
data class ItemDoCarrinho(
    val itemDoMenu: ItemDoMenu,
    val quantidade: Int,
    val observacoes: String = "" // Ex: "Sem cebola"
) {
    // Propriedade calculada: facilita a exibição do total por item na UI
    val precoTotal: Double
        get() = itemDoMenu.preco * quantidade
}