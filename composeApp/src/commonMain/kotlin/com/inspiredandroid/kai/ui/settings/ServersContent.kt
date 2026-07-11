package com.inspiredandroid.kai.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.inspiredandroid.kai.data.AppSettings
import org.koin.compose.koinInject

@Composable
fun ServersContent(appSettings: AppSettings = koinInject()) {
    var ip by remember { mutableStateOf(appSettings.getServerIp()) }
    var port by remember { mutableStateOf(appSettings.getServerPort().toString()) }
    var user by remember { mutableStateOf(appSettings.getServerUser()) }
    var password by remember { mutableStateOf(appSettings.getServerPassword()) }

    var showSavedMessage by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text("Server Monitoring Configuration", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        Text(
            "Configure SSH credentials for Katya to monitor the main LLM server (srv-llm).",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = ip,
            onValueChange = { ip = it },
            label = { Text("Server IP / Hostname") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = port,
            onValueChange = { port = it.filter { char -> char.isDigit() } },
            label = { Text("SSH Port") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = user,
            onValueChange = { user = it },
            label = { Text("SSH Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("SSH Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            appSettings.setServerIp(ip)
            appSettings.setServerPort(port.toIntOrNull() ?: 22)
            appSettings.setServerUser(user)
            appSettings.setServerPassword(password)
            showSavedMessage = true
        }) {
            Text("Save Credentials")
        }

        if (showSavedMessage) {
            Spacer(Modifier.height(8.dp))
            Text("Credentials saved successfully!", color = MaterialTheme.colorScheme.primary)
        }
    }
}
