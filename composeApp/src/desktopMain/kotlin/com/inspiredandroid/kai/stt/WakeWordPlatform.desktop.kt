@file:Suppress("ktlint:standard:filename")

package com.inspiredandroid.kai.stt

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DummyWakeWordPlatform : WakeWordPlatform {
    override val isDownloading: StateFlow<Boolean> = MutableStateFlow(false)
    override val downloadProgress: StateFlow<Float?> = MutableStateFlow(null)
    override fun isModelReady(modelUrl: String): Boolean = false
    override fun startDownload(modelUrl: String) {}
    override val wakeWordTriggered = kotlinx.coroutines.flow.MutableSharedFlow<Unit>()
    override fun startListening(modelUrl: String, triggerWord: String) {}
    override fun stopListening() {}
    override fun triggerWakeWordResponse(vibrate: Boolean, sound: Boolean) {}
}
