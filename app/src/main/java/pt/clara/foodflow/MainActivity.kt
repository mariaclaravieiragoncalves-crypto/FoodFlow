package pt.clara.foodflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import pt.clara.foodflow.screens.HomeScreen
import pt.clara.foodflow.ui.theme.FoodFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // O Tema dá as cores base à tua app
            FoodFlowTheme {
                // Aqui dizemos à app: "Assim que abrires, mostra o HomeScreen!"
                HomeScreen()
            }
        }
    }
}