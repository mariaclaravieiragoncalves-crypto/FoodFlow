package pt.clara.foodflow.model


import java.util.UUID

object DummyData {

    // Lista com 3 restaurantes de categorias diferentes para testar filtros na UI
    val restaurantes = listOf(
        Restaurante(
            id = "rest_1",
            nome = "Burger House Artisanal",
            imageUrl = "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=600&auto=format&fit=crop&q=80",
            avaliacao = 4.7,
            tempoEntregaEstimado = "15-25 min",
            taxaEntrega = 1.99,
            categorias = listOf("Hambúrgueres", "Fast Food", "Americano")
        ),
        Restaurante(
            id = "rest_2",
            nome = "Sushi Corner Premium",
            imageUrl = "https://images.unsplash.com/photo-1579871494447-9811cf80d66c?w=600&auto=format&fit=crop&80",
            avaliacao = 4.9,
            tempoEntregaEstimado = "30-45 min",
            taxaEntrega = 2.99,
            categorias = listOf("Sushi", "Japonês", "Saudável")
        ),
        Restaurante(
            id = "rest_3",
            nome = "Pizzeria Bella Vita",
            imageUrl = "https://images.unsplash.com/photo-1513104890138-7c749659a591?w=600&auto=format&fit=crop&80",
            avaliacao = 4.5,
            tempoEntregaEstimado = "20-35 min",
            taxaEntrega = 0.0, // Grátis para testar badges de entrega gratuita
            categorias = listOf("Pizzas", "Italiano", "Massas")
        )
    )

    // Lista global de itens de menu mapeados pelo 'restauranteId'
    val itensDoMenu = listOf(
        // --- Itens do Burger House (rest_1) ---
        ItemDoMenu(
            id = "menu_1_1",
            restauranteId = "rest_1",
            nome = "Hambúrguer Smash Clássico",
            descricao = "Dois hambúrgueres smash de 90g, queijo cheddar derretido, alface, tomate e o nosso molho secreto no pão brioche torrado.",
            preco = 8.50,
            imageUrl = "https://images.unsplash.com/photo-1550547660-d9450f859349?w=500&auto=format&fit=crop&q=80"
        ),
        ItemDoMenu(
            id = "menu_1_2",
            restauranteId = "rest_1",
            nome = "Batatas Rústicas com Alecrim",
            descricao = "Batatas fritas cortadas à mão, crocantes por fora e macias por dentro, temperadas com sal marinho e alecrim fresco.",
            preco = 2.90,
            imageUrl = "https://images.unsplash.com/photo-1573080496219-bb080dd4f877?w=500&auto=format&fit=crop&q=80"
        ),
        ItemDoMenu(
            id = "menu_1_3",
            restauranteId = "rest_1",
            nome = "Double Bacon Cheese",
            descricao = "Para os verdadeiros amantes de carne: duplo hambúrguer de novilho, triplo queijo cheddar e muito bacon fumado crocante.",
            preco = 10.90,
            imageUrl = "https://images.unsplash.com/photo-1594212699903-ec8a3eca50f5?w=500&auto=format&fit=crop&q=80"
        ),

        // --- Itens do Sushi Corner (rest_2) ---
        ItemDoMenu(
            id = "menu_2_1",
            restauranteId = "rest_2",
            nome = "Combo Sushi Familiar (16 pcs)",
            descricao = "Variedade escolhida pelo sushiman: 4 Hossomakis, 4 Uramakis, 4 Niguiris e 4 fatias de Sashimi de Salmão fresco.",
            preco = 18.90,
            imageUrl = "https://images.unsplash.com/photo-1611143669185-af224c5e3252?w=500&auto=format&fit=crop&q=80"
        ),
        ItemDoMenu(
            id = "menu_2_2",
            restauranteId = "rest_2",
            nome = "Temaki de Salmão Completo",
            descricao = "Cone de alga nori crocante recheado com arroz de sushi, cubos de salmão, cream cheese e cebolinho.",
            preco = 6.50,
            imageUrl = "https://images.unsplash.com/photo-1617196034796-73dfa7b1fd56?w=500&auto=format&fit=crop&q=80"
        ),

        // --- Itens da Pizzeria Bella Vita (rest_3) ---
        ItemDoMenu(
            id = "menu_3_1",
            restauranteId = "rest_3",
            nome = "Pizza Margherita D.O.C.",
            descricao = "Molho de tomate italiano caseiro, mozzarella de búfala fiordilatte, manjericão fresco e um fio de azeite virgem extra.",
            preco = 9.50,
            imageUrl = "https://images.unsplash.com/photo-1604068549290-dea0e4a305ca?w=500&auto=format&fit=crop&q=80"
        ),
        ItemDoMenu(
            id = "menu_3_2",
            restauranteId = "rest_3",
            nome = "Pizza Diavola (Pepperoni)",
            descricao = "Molho de tomate, mozzarella, salame picante (pepperoni) fatiado artesanalmente e um toque leve de orégãos.",
            preco = 11.90,
            imageUrl = "https://images.unsplash.com/photo-1628840042765-356cda07504e?w=500&auto=format&fit=crop&q=80"
        )
    )

    /**
     * Função utilitária para simular uma consulta à base de dados ou API.
     * Retorna apenas os pratos pertencentes a um restaurante específico.
     */
    fun getMenuParaRestaurante(restauranteId: String): List<ItemDoMenu> {
        return itensDoMenu.filter { it.restauranteId == restauranteId }
    }
}