package com.example.myfirstcomposeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstcomposeapplication.ui.theme.MyFirstComposeApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            MyApp {
//                MyScreenContent()
//            }
            AnotherScreenContent()
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
    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) Color.Red else Color.Yellow
    )

    Text(
        text = "Hello $name!",
        modifier = Modifier
            .padding(24.dp)
            .background(color = backgroundColor)
            .clickable { isSelected = !isSelected }
    )
}

@Composable
fun Counter(count: Int, updateCount: (newCount: Int) -> Unit) {
    Button(onClick = { updateCount(count + 1) }) {
        Text("I've been clicked $count times")
    }
}

@Composable
fun MyScreenContent(names: List<String> = List(1000) { "Alina & Arnold" }) {
    val count = remember {
        mutableStateOf(0)
    }

    Column {
        NameList(
            names = names,
            modifier = Modifier.weight(1f)
        )
        Divider(color = Color.Transparent, thickness = 32.dp)
        Counter(count.value) {
            count.value = it
        }
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = names) { name ->
            Greeting(name = name)
            Divider(color = Color.Black)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
//    MyApp {
//        MyScreenContent()
//    }
    AnotherScreenContent()
}

@Composable
fun AnotherScreenContent() {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextField("User Name", "Input your name")
            Divider(color = Color.Transparent, thickness = 16.dp)
            CustomTextField("Password", "Input your password")
        }
    }
}

@Composable
private fun CustomTextField(label: String, placeHolderString: String) {
    val textState = remember { mutableStateOf("") }
    val focusedState = remember { mutableStateOf(false) }


    TextField(
        modifier = Modifier.border(
            width = 1.dp,
            color = if (focusedState.value) Color(0xff333F48) else Color(0xffd2d5da),
            shape = RoundedCornerShape(4.dp)
        ),
        value = textState.value,
        onValueChange = { textState.value = it },
        label = {
            Text(
                text = label
            )
        },
        placeholder = {
            Text(
                text = placeHolderString,
                fontSize = 16.sp,
            )
        },
        textStyle = TextStyle(
            fontSize = 16.sp
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Color(0xff333F48),
            focusedLabelColor = Color(0xff697684),
            unfocusedLabelColor = Color(0xff697684),
            textColor = Color(0xff333F48),
            placeholderColor = Color(0xff697684),
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent
        )
    )
}