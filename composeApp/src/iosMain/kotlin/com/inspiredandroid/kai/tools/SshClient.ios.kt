package com.inspiredandroid.kai.tools

actual class SshClient actual constructor() {
    actual fun executeCommand(
        host: String,
        port: Int,
        user: String,
        pass: String,
        command: String,
    ): String = "SSH not supported on iOS yet."
}
