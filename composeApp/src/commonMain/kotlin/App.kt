import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import home.ui.HomeScreen

@Composable
fun App() {
    MaterialTheme {
        Navigator(HomeScreen)
    }
}