package com.inspiredandroid.kai.monitor

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DummyMonitorService : MonitorService {
    private val _stats = MutableStateFlow(MonitorStats())
    override val stats: StateFlow<MonitorStats> = _stats

    override suspend fun startMonitoring(
        host: String,
        port: Int,
        user: String,
        pass: String,
        isFullMode: Boolean
    ) {
        _stats.value = MonitorStats(isRunning = true, locShort = "Loc: N/A", srvShort = "Srv: Not supported on this platform")
    }

    override fun stopMonitoring() {
        _stats.value = MonitorStats(isRunning = false)
    }
}

actual fun createMonitorService(): MonitorService = DummyMonitorService()
