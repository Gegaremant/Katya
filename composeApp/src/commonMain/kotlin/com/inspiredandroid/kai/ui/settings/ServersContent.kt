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

    var tunnelLocalPort by remember { mutableStateOf("8080") }
    var tunnelRemotePort by remember { mutableStateOf("80") }

    var showSavedMessage by remember { mutableStateOf(false) }
    var showTunnelMessage by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text("Настройки мониторинга серверов", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        Text(
            "Настройте SSH доступы для удаленного мониторинга и управления серверами. Укажите IP-адрес, порт и учетные данные для подключения.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = ip,
            onValueChange = { ip = it },
            label = { Text("IP-адрес / Имя хоста") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = port,
            onValueChange = { port = it.filter { char -> char.isDigit() } },
            label = { Text("SSH Порт") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = user,
            onValueChange = { user = it },
            label = { Text("Имя пользователя SSH") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Пароль SSH") },
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
            Text("Сохранить настройки")
        }

        if (showSavedMessage) {
            Spacer(Modifier.height(8.dp))
            Text("Данные успешно сохранены!", color = MaterialTheme.colorScheme.primary)
        }

        Spacer(Modifier.height(32.dp))
        HorizontalDivider()
        Spacer(Modifier.height(16.dp))

        Text("Настройка SSH-туннеля", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        Text(
            "Создание локального перенаправления портов через настроенный сервер.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(16.dp))
        
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = tunnelLocalPort,
                onValueChange = { tunnelLocalPort = it.filter { char -> char.isDigit() } },
                label = { Text("Локальный порт") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = tunnelRemotePort,
                onValueChange = { tunnelRemotePort = it.filter { char -> char.isDigit() } },
                label = { Text("Удаленный порт") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(Modifier.height(16.dp))
        
        Button(onClick = {
            // Placeholder logic for raising a tunnel
            showTunnelMessage = true
        }) {
            Text("Поднять туннель")
        }

        if (showTunnelMessage) {
            Spacer(Modifier.height(8.dp))
            Text("Запрос на создание туннеля отправлен (в разработке)", color = MaterialTheme.colorScheme.primary)
        }
    }
}
