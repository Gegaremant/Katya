$files = Get-ChildItem -Path "c:\Projects\LLM_SRV_FULL\android_agent\Katya\composeApp\src" -Filter "ChatViewModel*Test.kt" -Recurse
foreach ($file in $files) {
    if ($file.Name -eq "ChatViewModelFileAttachmentTest.kt") {
        $content = Get-Content $file.FullName -Raw
        $content = $content -replace "ChatViewModel\(appSettings, fakeRepository, noOpScheduler, unconfinedDispatcher\)", "ChatViewModel(fakeRepository, noOpScheduler, com.inspiredandroid.kai.testutil.FakeMonitorService(), com.inspiredandroid.kai.testutil.FakeWakeWordPlatform(), appSettings, unconfinedDispatcher)"
        Set-Content -Path $file.FullName -Value $content -NoNewline
    } else {
        $content = Get-Content $file.FullName -Raw
        
        $content = $content -replace "private lateinit var fakeRepository: FakeDataRepository\r?\n", "private lateinit var fakeRepository: FakeDataRepository`n    private lateinit var appSettings: com.inspiredandroid.kai.data.AppSettings`n"
        
        $content = $content -replace "fakeRepository = FakeDataRepository\(\)\r?\n", "fakeRepository = FakeDataRepository()`n        appSettings = com.inspiredandroid.kai.data.AppSettings(com.russhwolf.settings.MapSettings())`n"
        
        $content = $content -replace "ChatViewModel\(fakeRepository, noOpScheduler, unconfinedDispatcher\)", "ChatViewModel(fakeRepository, noOpScheduler, com.inspiredandroid.kai.testutil.FakeMonitorService(), com.inspiredandroid.kai.testutil.FakeWakeWordPlatform(), appSettings, unconfinedDispatcher)"
        
        $content = $content -replace "ChatViewModel\(fakeRepository, noOpScheduler, backgroundDispatcher\)", "ChatViewModel(fakeRepository, noOpScheduler, com.inspiredandroid.kai.testutil.FakeMonitorService(), com.inspiredandroid.kai.testutil.FakeWakeWordPlatform(), appSettings, backgroundDispatcher)"
        
        Set-Content -Path $file.FullName -Value $content -NoNewline
    }
}
