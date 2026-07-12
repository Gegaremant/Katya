import re
def fix(path):
    with open(path, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # SettingsViewModel.kt
    content = content.replace('        onToggleWakeWord = ::onToggleWakeWord,\n        onChangeWakeWordTrigger = ::onChangeWakeWordTrigger,\n        onDownloadVosk = ::onDownloadVosk,\n', '        onDownloadVosk = ::onDownloadVosk,\n')
    
    with open(path, 'w', encoding='utf-8') as f:
        f.write(content)
        
fix('C:/Projects/LLM_SRV_FULL/android_agent/Katya/composeApp/src/commonMain/kotlin/com/inspiredandroid/kai/ui/settings/SettingsViewModel.kt')
