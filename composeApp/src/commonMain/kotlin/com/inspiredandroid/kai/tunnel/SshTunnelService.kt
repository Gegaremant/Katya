package com.inspiredandroid.kai.tunnel

import kotlinx.coroutines.flow.StateFlow

data class TunnelState(
    val isRunning: Boolean = false,
    val error: String? = null,
    val message: String = ""
)

interface SshTunnelService {
    val tunnelState: StateFlow<TunnelState>

    suspend fun startTunnel(
        localPort: Int,
        remotePort: Int,
        sshIp: String,
        sshPort: Int,
        sshUser: String,
        sshPass: String
    )

    suspend fun stopTunnel()
}

expect fun createTunnelService(): SshTunnelService
