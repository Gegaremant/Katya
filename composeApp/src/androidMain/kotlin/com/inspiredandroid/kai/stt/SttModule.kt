package com.inspiredandroid.kai.stt

import org.koin.dsl.module

actual val sttModule = module {
    single<WakeWordPlatform> { VoskWakeWordManager(get()) }
}
