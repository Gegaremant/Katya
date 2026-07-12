package com.inspiredandroid.kai.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.unit.dp
import com.inspiredandroid.kai.data.AppSettings
import com.inspiredandroid.kai.tunnel.SshTunnelService
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun ServersContent(
    appSettings: AppSettings = koinInject(),
    tunnelService: SshTunnelService = koinInject(),
) {
    var ip by remember { mutableStateOf(appSettings.getServerIp()) }
    var port by remember { mutableStateOf(appSettings.getServerPort().toString()) }
    var user by remember { mutableStateOf(appSettings.getServerUser()) }
    var password by remember { mutableStateOf(appSettings.getServerPassword()) }

    var tunnelLocalPort by remember { mutableStateOf("11434") }
    var tunnelRemotePort by remember { mutableStateOf("11434") }

    var showSavedMessage by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    val tunnelState by tunnelService.tunnelState.collectAsState()
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text("Настройки мониторинга серверов", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onSurface)
        Spacer(Modifier.height(8.dp))
        Text(
            "Настройте SSH доступы для удаленного мониторинга и управления серверами. Укажите IP-адрес, порт и учетные данные для подключения.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = ip,
            onValueChange = { ip = it },
            label = { Text("IP-адрес / Имя хоста") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = port,
            onValueChange = { port = it.filter { char -> char.isDigit() } },
            label = { Text("SSH Порт") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = user,
            onValueChange = { user = it },
            label = { Text("Имя пользователя SSH") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Пароль SSH") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = if (passwordVisible) "Hide password" else "Show password")
                }
            },
            modifier = Modifier.fillMaxWidth(),
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

        Text("Настройка SSH-туннеля", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
        Spacer(Modifier.height(8.dp))
        Text(
            "Создание локального перенаправления портов через настроенный сервер.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = tunnelLocalPort,
                onValueChange = { tunnelLocalPort = it.filter { char -> char.isDigit() } },
                label = { Text("Локальный порт") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f),
                enabled = !tunnelState.isRunning,
            )
            OutlinedTextField(
                value = tunnelRemotePort,
                onValueChange = { tunnelRemotePort = it.filter { char -> char.isDigit() } },
                label = { Text("Удаленный порт") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f),
                enabled = !tunnelState.isRunning,
            )
        }
        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            scope.launch {
                if (tunnelState.isRunning) {
                    tunnelService.stopTunnel()
                } else {
                    val local = tunnelLocalPort.toIntOrNull() ?: 11434
                    val remote = tunnelRemotePort.toIntOrNull() ?: 11434
                    val sshPort = port.toIntOrNull() ?: 22
                    tunnelService.startTunnel(local, remote, ip, sshPort, user, password)
                }
            }
        }) {
            Text(if (tunnelState.isRunning) "Остановить туннель" else "Поднять туннель")
        }

        if (tunnelState.message.isNotEmpty()) {
            Spacer(Modifier.height(8.dp))
            Text(tunnelState.message, color = MaterialTheme.colorScheme.primary)
        }
        if (tunnelState.error != null) {
            Spacer(Modifier.height(8.dp))
            Text("Ошибка: ${tunnelState.error}", color = MaterialTheme.colorScheme.error)
        }
    }
}
