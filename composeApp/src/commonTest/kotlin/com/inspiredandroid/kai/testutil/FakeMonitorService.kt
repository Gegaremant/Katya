package com.inspiredandroid.kai.testutil

import com.inspiredandroid.kai.monitor.MonitorService
import com.inspiredandroid.kai.monitor.MonitorStats
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeMonitorService : MonitorService {
    override val stats: StateFlow<MonitorStats> = MutableStateFlow(MonitorStats())
    override suspend fun startMonitoring(host: String, port: Int, user: String, pass: String, isFullMode: Boolean) {}
    override fun stopMonitoring() {}
}
