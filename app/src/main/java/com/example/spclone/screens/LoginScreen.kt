package com.example.spclone.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.spclone.ui.theme.SpotifyBlack
import com.example.spclone.ui.theme.SpotifyGreen
import com.example.spclone.ui.theme.SpotifyWhite
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SpotifyBlack),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Iniciar Sesión",
                style = MaterialTheme.typography.headlineLarge,
                color = SpotifyWhite
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo Electrónico", color = SpotifyWhite) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = SpotifyWhite,
                    unfocusedBorderColor = SpotifyWhite,
                    cursorColor = SpotifyWhite,
                    focusedLabelColor = SpotifyWhite,
                    unfocusedLabelColor = SpotifyWhite,
                    focusedTextColor = SpotifyWhite,
                    unfocusedTextColor = SpotifyWhite
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña", color = SpotifyWhite) },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = SpotifyWhite,
                    unfocusedBorderColor = SpotifyWhite,
                    cursorColor = SpotifyWhite,
                    focusedLabelColor = SpotifyWhite,
                    unfocusedLabelColor = SpotifyWhite,
                    focusedTextColor = SpotifyWhite,
                    unfocusedTextColor = SpotifyWhite
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (email.isNotBlank() && password.isNotBlank()) {
                        FirebaseAuth.getInstance()
                            .signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    errorMessage = null
                                    onLoginSuccess()
                                } else {
                                    errorMessage = "Error: ${task.exception?.message}"
                                }
                            }
                    } else {
                        errorMessage = "Todos los campos son obligatorios"
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SpotifyGreen,
                    contentColor = SpotifyBlack
                )
            ) {
                Text("Iniciar Sesión", color = SpotifyWhite)
            }

            errorMessage?.let {
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = it, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = onNavigateToRegister) {
                Text("¿No tienes una cuenta? Regístrate", color = SpotifyGreen)
            }
        }
    }
}


