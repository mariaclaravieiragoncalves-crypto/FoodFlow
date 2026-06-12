package pt.clara.foodflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import pt.clara.foodflow.navigation.AppNavigation
import pt.clara.foodflow.ui.theme.FoodFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodFlowTheme {
                // Troquei a HomeScreen pela AppNavigation.
                AppNavigation()
            }
        }
    }
}