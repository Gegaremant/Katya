package com.inspiredandroid.kai.stt

import kotlinx.coroutines.flow.StateFlow

interface WakeWordPlatform {
    val isDownloading: StateFlow<Boolean>
    val downloadProgress: StateFlow<Float?>
    fun isModelReady(modelUrl: String): Boolean
    fun startDownload(modelUrl: String)
    fun startListening(modelUrl: String, triggerWord: String, onWakeWordDetected: () -> Unit)
    fun stopListening()
    fun triggerWakeWordResponse(vibrate: Boolean, sound: Boolean)
}

expect val sttModule: org.koin.core.module.Module
