package com.inspiredandroid.kai.stt

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

actual fun createSttController(): SttController = DummySttController()

class DummySttController : SttController {
    override val isListening: StateFlow<Boolean> = MutableStateFlow(false)
    override val partialResults: StateFlow<String> = MutableStateFlow("")
    override val error: StateFlow<String?> = MutableStateFlow(null)

    override fun startListening(onResult: (String) -> Unit) {}
    override fun stopListening() {}
}
