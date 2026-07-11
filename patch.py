import sys

content = open('C:/Projects/LLM_SRV_FULL/android_agent/Katya/composeApp/src/commonMain/kotlin/com/inspiredandroid/kai/data/RemoteDataRepository.kt', 'r', encoding='utf-8').read()

replacement = '''    override fun isWakeWordEnabled(): Boolean {
        return appSettings.isWakeWordEnabled()
    }

    override fun setWakeWordEnabled(enabled: Boolean) {
        appSettings.setWakeWordEnabled(enabled)
    }

    override fun getWakeWordTrigger(): String {
        return appSettings.getWakeWordTrigger()
    }

    override fun setWakeWordTrigger(trigger: String) {
        appSettings.setWakeWordTrigger(trigger)
    }

    override fun getAppStartupCounter(): Int {'''
content = content.replace('    override fun getAppStartupCounter(): Int {', replacement)

open('C:/Projects/LLM_SRV_FULL/android_agent/Katya/composeApp/src/commonMain/kotlin/com/inspiredandroid/kai/data/RemoteDataRepository.kt', 'w', encoding='utf-8').write(content)
