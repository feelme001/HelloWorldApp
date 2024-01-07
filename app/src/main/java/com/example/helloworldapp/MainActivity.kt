package com.example.helloworldapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.helloworldapp.ui.theme.HelloWorldAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloWorldAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        ResponsiveColumn()
                        Greeting("Android", modifier = Modifier.padding(30.dp))
                        Divider()  // Optional: Adds a visual separator
                        MyForm()
                    }
                }
            }
        }
    }
}

@Composable
fun ResponsiveColumn() {
    Column {
        Text(
            "Header",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(16.dp)
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                "Left Pane",
                modifier = Modifier
                    .weight(1f)
                    .background(Color.LightGray)
                    .padding(16.dp)
            )
            Text(
                "Right Pane",
                modifier = Modifier
                    .weight(1f)
                    .background(Color.DarkGray)
                    .padding(16.dp)
            )
        }
        Text(
            "Footer",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(16.dp)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // State to hold the greeting message
    var greetingText by remember { mutableStateOf("Welcome to Kotlin, $name!") }
    var text by remember { mutableStateOf("")}
    var checked by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        Text(text = greetingText)
        Button(onClick = { greetingText = "Hello again, $name!" }) {
            Text("Change Greeting")
        }
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.padding(16.dp),
            label = { Text("Enter something")}
        )
        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it }
        )
        Card(
            modifier = Modifier.padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Text("This is a card", modifier = Modifier.padding(16.dp))
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Item 1")
            Text("Item 2")
        }
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(Color.LightGray), // Apply a background color to the Box
            contentAlignment = Alignment.Center // Align the content to the center
        ) {
            Text("Centered text") // This text will be centered within the Box
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyForm() {
    // First, we'll define some state variables to hold the input data for our form fields.
    var name by remember { mutableStateOf("")}
    var email by remember { mutableStateOf("")}
    var isSubscribed by remember { mutableStateOf(false)}

    // Define isFormValid here to use it within the Composable function
    val isFormValid = name.isNotEmpty() && email.contains("@")

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        TextField(
            value = name,
            onValueChange = { name = it},
            label = { Text("Name")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        TextField(
            value = email,
            onValueChange = { name = it},
            label = { Text("Email")}
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isSubscribed,
                onCheckedChange = { isSubscribed = it}
            )
            Text(text = "Subscribe to newsletter")
        }
        Button(
            onClick = { /* Handle form submission */ },
            modifier = Modifier.align(Alignment.End),
            enabled = isFormValid // Button is enabled only if the form is valid
        ) {
            Text("Submit")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloWorldAppTheme {
        Greeting("Android", modifier = Modifier.padding(16.dp))
    }
}

@Preview(name = "Default Greeting Preview")
@Composable
fun DefaultGreetingPreview() {
    HelloWorldAppTheme {
        Greeting("Android")
    }
}

@Preview(name = "Padded Greeting Preview")
@Composable
fun PaddedGreetingPreview() {
    HelloWorldAppTheme {
        Greeting("Android", modifier = Modifier.padding(32.dp))
    }
}

@Preview(name = "Custom Name Greeting Preview")
@Composable
fun CustomNameGreetingPreview() {
    HelloWorldAppTheme {
        Greeting("Jetpack Compose")
    }
}

@Preview(device = Devices.PIXEL_C, showBackground = true)
@Composable
fun TabletPreview() {
    HelloWorldAppTheme {
        MyForm()
    }
}

@Preview(device = Devices.PIXEL_4, showBackground = true)
@Composable
fun PhonePreview() {
    HelloWorldAppTheme {
        MyForm()
    }
}

@Preview(uiMode = Configuration.UI_MODE_TYPE_NORMAL or Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun DefaultPreview() {
    MyForm()
}

@Preview(uiMode = Configuration.UI_MODE_TYPE_NORMAL or Configuration.UI_MODE_NIGHT_NO, showBackground = true, device = Devices.DEFAULT, widthDp = 480, heightDp = 320)
@Composable
fun LandscapePreview() {
    MyForm()
}
