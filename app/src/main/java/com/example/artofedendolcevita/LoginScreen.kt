package com.example.artofedendolcevita

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.systemBarsPadding

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    // Cały interfejs będzie umieszczony wewnątrz jednej kolumny
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding() // dodaje odstęp na górze, aby nie nachodzić na status bar
            .padding(16.dp)
    ) {
        // Pole tekstowe do wprowadzenia emaila
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Pole tekstowe do wprowadzenia hasła
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Hasło") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Jeżeli istnieje komunikat błędu, wyświetl go
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Przycisk logowania
        Button(
            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    onLoginSuccess()
                } else {
                    errorMessage = "Wprowadź email i hasło"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Zaloguj się")
        }
    }
}