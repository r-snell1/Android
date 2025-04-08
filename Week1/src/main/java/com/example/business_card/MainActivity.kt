package com.example.business_card

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.business_card.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessCard(
                        name = "Ryan A. Snell",
                        title = "Senior Student",
                        phone = "404-441-3523",
                        social = "@r.snell.Student",
                        email = "rasnell01@gmail.com"
                    )
                }
            }
        }
    }// end function
}// end class

@Composable
fun BusinessCard(
    name: String,
    title: String,
    phone: String,
    social: String,
    email: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
        .background(Color.Black)
        .padding(16.dp)
        .background(Color.Gray)
        .padding(16.dp)
    ) {
        TitleText(name = name, title = title)
        ContactText(phone = phone, social = social, email = email)
    }
}// end function

@Composable
fun TitleText(name: String, title: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = name, style = MaterialTheme.typography.headlineMedium)
        Text(text = title, style = MaterialTheme.typography.bodyLarge)
    }
}// end function

@Composable
fun ContactText(phone: String, social: String, email: String) {
    Column {
        Text(text = "Phone: $phone")
        Text(text = "Social: $social")
        Text(text = "Email: $email")
    }
}// end function

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    BusinessCardTheme {
        BusinessCard(
            name = "Ryan A. Snell",
            title = "Senior Student",
            phone = "404-441-3523",
            social = "@r.snell.Student",
            email = "rasnell01@gmail.com"
        )
    }// end theme
}// end function