package com.example.baskher_frontend.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baskher_frontend.R
import com.example.baskher_frontend.ui.theme.PurpleBack
import com.example.baskher_frontend.ui.theme.PurpleDark

@Preview
@Composable
fun InitialScreen(navigateToLogin:() -> Unit = {}, navigateToSignUp:() -> Unit = {}){
   Column (
       modifier = Modifier
           .fillMaxSize()
           .background(Brush.verticalGradient(listOf(PurpleBack, PurpleDark))),
       horizontalAlignment = Alignment.CenterHorizontally
   ) {
       Image(painter = painterResource(id = R.drawable.logo),
           contentDescription ="logo",
           modifier = Modifier
               .size(500.dp)
               .clip(CircleShape)
       )
       Spacer(modifier = Modifier.weight(1f))
       Text("Encuentra a tu jugadora.",
           color = Color.White,
           fontSize = 30.sp,
           fontWeight = Bold
       )
       Text("Compara.",
           color = Color.White,
           fontSize = 28.sp,
           )
       Text("Aprende.",
           color = Color.White,
           fontSize = 26.sp,
           )
       Spacer(modifier = Modifier.weight(1f))

       Button(
           onClick = { navigateToLogin() },
           modifier = Modifier
               .fillMaxWidth()
               .height(48.dp)
               .padding(horizontal = 32.dp),
           colors = ButtonDefaults.buttonColors(containerColor = PurpleBack)) {
           Text(text = "Inicia sesión")
       }

       Text (
           text = "Regístrate",
           color = Color.White,
           modifier = Modifier.padding(24.dp).clickable { navigateToSignUp() },
           fontWeight = Bold
       )
       Spacer(modifier = Modifier.weight(1f))

   }
}

