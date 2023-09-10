package com.timepass.notesapp
/* 
Disclaimer:
This app has been designed and developed by Archit Anant
as a personal project
*/
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.timepass.notesapp.data.Notes
import com.timepass.notesapp.data.generateTimeStamp
import com.timepass.notesapp.ui.main.component.FabButton
import com.timepass.notesapp.ui.main.NoteUi
import com.timepass.notesapp.ui.main.component.dialogBox
import com.timepass.notesapp.ui.navigation.MainNavigation
import com.timepass.notesapp.ui.theme.NotesTheme
import com.timepass.notesapp.utils.backgroundColor

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm = ViewModel()
            val navController = rememberNavController()
            NotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = backgroundColor
                ) {
                    MainNavigation(navController = navController, viewModel = vm )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotesTheme {
    }
}
