package cat.alexgluque.sqliteerror.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import cat.alexgluque.sqliteerror.AppViewModel
import cat.alexgluque.sqliteerror.DatabaseDriverFactory
import cat.alexgluque.sqliteerror.Persons
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var appViewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appViewModel = AppViewModel(DatabaseDriverFactory(applicationContext))

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GreetingView(appViewModel)
                }
            }
        }
    }
}

@Composable
fun GreetingView(appViewModel: AppViewModel) {
    val state: Persons by appViewModel.state.collectAsState(Persons(0, 0, ""))

    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "${state.name}'s age:")

            Button(
                onClick = { appViewModel.updateAge(state.copy(age = state.age + 1)) }
            ) {
                Text(text = "+")
            }

            Text(text = "${state.age}")

            Button(
                onClick = { appViewModel.updateAge(state.copy(age = state.age - 1)) }
            ) {
                Text(text = "-")
            }
        }
    }
}
