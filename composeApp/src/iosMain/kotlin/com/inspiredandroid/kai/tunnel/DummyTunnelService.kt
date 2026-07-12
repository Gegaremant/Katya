package com.inspiredandroid.kai.tunnel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DummyTunnelService : SshTunnelService {
    override val tunnelState: StateFlow<TunnelState> = MutableStateFlow(TunnelState(error = "Tunnel is not supported on this platform"))

    override suspend fun startTunnel(
        localPort: Int,
        remotePort: Int,
        sshIp: String,
        sshPort: Int,
        sshUser: String,
        sshPass: String,
    ) {
        // No-op
    }

    override suspend fun stopTunnel() {
        // No-op
    }
}

actual fun createTunnelService(): SshTunnelService = DummyTunnelService()
