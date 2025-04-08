package com.example.business_card

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(bottom = 10.dp)
        ){
            AppleLogo()
            TitleText(name = name, title = title)
        }
        ContactText(phone = phone, social = social, email = email)
    }
}// end function

@Composable
fun TitleText(name: String, title: String) {
    Column {
        Text(text = name, style = MaterialTheme.typography.headlineMedium)
        Text(text = title, style = MaterialTheme.typography.bodyLarge)
    }
}// end function

@Composable
fun ContactText(phone: String, social: String, email: String) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            PhoneLogo()
            Text(text = "Phone: $phone")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            SocialLogo()
            Text(text = "Social: $social")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            MailLogo()
            Text(text = "Email: $email")
        }
    }// end column
}// end function

@Composable
fun AppleLogo(){
    val image = painterResource(R.drawable.apple_icon)
    Image(
        painter = image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(45.dp)
    )
}// end function

@Composable
fun PhoneLogo() {
    val image = painterResource(R.drawable.phone_icon)
    Image(
        painter = image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(16.dp)
    )
}//end function

@Composable
fun SocialLogo(){
    val image = painterResource(R.drawable.x_icon)
    Image(
        painter = image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(16.dp)
    )
}// end function

@Composable
fun MailLogo() {
    val image = painterResource(R.drawable.main_icon)
    Image(
        painter = image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(16.dp)
    )
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