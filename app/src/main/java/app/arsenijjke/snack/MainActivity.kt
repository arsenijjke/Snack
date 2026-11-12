package app.arsenijjke.snack

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import app.arsenijjke.home.ui.HomeScreen
import app.arsenijjke.home.viewmodel.HomeViewModel
import app.arsenijjke.snack.ui.theme.SnackTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SnackTheme {
                Scaffold { innerPadding ->
                    val vm: HomeViewModel = hiltViewModel()
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = vm
                    )
                }

            }
        }
    }
}
