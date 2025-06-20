package com.example.baskher_frontend.ui.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.baskher_frontend.R
import com.example.baskher_frontend.ui.theme.PurpleBack
import com.example.baskher_frontend.ui.theme.PurpleDark
import com.example.baskher_frontend.ui.theme.SelectedField
import com.example.baskher_frontend.ui.theme.UnselectedField
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SignUpScreen(
    auth: FirebaseAuth,
    onSignUpSuccess:() -> Unit,
    navController: NavHostController)
{
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(PurpleBack, PurpleDark)))
            .padding(horizontal = 32.dp, vertical = 32.dp),

        ) {

        Row{
            Icon(
                painter = painterResource(id = R.drawable.ic_back_24),
                contentDescription = "",
                tint = White,
                modifier = Modifier
                    .padding(top = 30.dp, bottom = 30.dp)
                    .size(24.dp)
                    .clickable {
                        navController.navigate("initial") {
                            popUpTo("initial") { inclusive = true }
                        }
                    }
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        Text("Email",
            color = White,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp)
        TextField(
            value = email,
            onValueChange = { email = it },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = androidx.compose.ui.text.TextStyle(
                color = White,
                fontSize = 16.sp
            ),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Spacer(Modifier.height(48.dp))
        Text("Contraseña",
            color = White,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp)
        TextField(
            value = password, onValueChange = { password = it },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = androidx.compose.ui.text.TextStyle(
                color = White,
                fontSize = 16.sp
            ),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(Modifier.height(48.dp))
        Button(
            onClick = {
                  auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
                      if(task.isSuccessful){
                          onSignUpSuccess()
                      }else{
                          Log.i("registro", "LOGIN KO")
                      }
                  }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 32.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PurpleBack)) {
            Text(text = "Regístrate")

        }
    }
}