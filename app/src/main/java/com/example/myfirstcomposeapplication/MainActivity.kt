package com.example.myfirstcomposeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfirstcomposeapplication.ui.theme.MyFirstComposeApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyScreenContent()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    MyFirstComposeApplicationTheme {
        Surface(color = Color.Yellow) {
            content()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        modifier = Modifier.padding(24.dp)
    )
}

@Composable
fun Counter(count: Int, updateCount: (newCount: Int) -> Unit) {
    Button(onClick = { updateCount(count + 1) }) {
        Text("I've been clicked $count times")
    }
}

@Composable
fun MyScreenContent() {
    val count = remember {
        mutableStateOf(0)
    }

    Column {
        Greeting(name = "Arnold")
        Divider(color = Color.Black)
        Divider(color = Color.Transparent, thickness = 32.dp)
        Counter(count.value) {
            count.value = it
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent()
    }
}