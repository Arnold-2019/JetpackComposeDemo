package com.example.myfirstcomposeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScreenContent()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ScreenContent()
}

@Composable
fun ScreenContent() {
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
            CustomTextField("Email Address", "Input your email address")

            Divider(color = Color.Transparent, thickness = 32.dp)

            CustomOutlinedTextField("User Name", "Input your name")
            Divider(color = Color.Transparent, thickness = 16.dp)
            CustomOutlinedTextField("Email Address", "Input your email address")
        }
    }
}

@Composable
private fun CustomTextField(label: String, placeHolderString: String) {
    val textState = remember { mutableStateOf("") }
    val focusedState = remember { mutableStateOf(false) }

    val textColor01 = Color(0xff333F48)
    val textColor02 = Color(0xff697684)
    val transparentColor = Color.Transparent

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
            cursorColor = textColor01,
            focusedLabelColor = textColor02,
            unfocusedLabelColor = textColor02,
            textColor = textColor01,
            placeholderColor = textColor02,
            focusedBorderColor = transparentColor,
            unfocusedBorderColor = transparentColor,
            disabledBorderColor = transparentColor,
            errorBorderColor = transparentColor
        )
    )
}

@Composable
fun CustomOutlinedTextField(label: String, placeHolderString: String) {
    val textState = remember { mutableStateOf("") }

    val textColor01 = Color(0xff333F48)
    val textColor02 = Color(0xff697684)
    val unfocusedBorderColor = Color(0xffd2d5da)

    OutlinedTextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        label = { Text(text = label) },
        textStyle = TextStyle(
            fontSize = 16.sp
        ),
        placeholder = { Text(text = placeHolderString) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = textColor01,
            focusedLabelColor = textColor02,
            unfocusedLabelColor = textColor02,
            textColor = textColor01,
            placeholderColor = textColor02,
            focusedBorderColor = textColor02,
            unfocusedBorderColor = unfocusedBorderColor,
        )
    )
}
