package com.inspiredandroid.kai.tunnel

import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import java.util.Properties

class JschTunnelService : SshTunnelService {
    private val _tunnelState = MutableStateFlow(TunnelState())
    override val tunnelState: StateFlow<TunnelState> = _tunnelState

    private var session: Session? = null

    override suspend fun startTunnel(
        localPort: Int,
        remotePort: Int,
        sshIp: String,
        sshPort: Int,
        sshUser: String,
        sshPass: String
    ) {
        withContext(Dispatchers.IO) {
            try {
                if (session != null && session!!.isConnected) {
                    stopTunnel()
                }

                _tunnelState.value = TunnelState(isRunning = true, message = "Connecting to $sshIp...")

                val jsch = JSch()
                session = jsch.getSession(sshUser, sshIp, sshPort)
                session?.setPassword(sshPass)

                val config = Properties()
                config["StrictHostKeyChecking"] = "no"
                session?.setConfig(config)

                // Try to connect
                session?.connect(10000)

                // Set port forwarding: bind local port to loopback, forward to 127.0.0.1 on the server
                session?.setPortForwardingL(localPort, "127.0.0.1", remotePort)

                _tunnelState.value = TunnelState(isRunning = true, message = "Tunnel established: localhost:$localPort -> $sshIp:$remotePort")

                // Keep connection alive
                while (isActive && session?.isConnected == true) {
                    delay(5000)
                }

                if (isActive) {
                    _tunnelState.value = TunnelState(isRunning = false, error = "Connection lost")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _tunnelState.value = TunnelState(isRunning = false, error = e.message ?: "Failed to establish tunnel")
                stopTunnel()
            }
        }
    }

    override suspend fun stopTunnel() {
        withContext(Dispatchers.IO) {
            try {
                session?.disconnect()
                session = null
                _tunnelState.value = TunnelState(isRunning = false, message = "Tunnel stopped")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

actual fun createTunnelService(): SshTunnelService = JschTunnelService()
