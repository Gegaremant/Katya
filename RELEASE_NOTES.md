# Release Notes — v0.2.0-alpha (2026-07-12)

## What's New

### Wake Word (Offline Voice Activation)
- **Vosk-based offline speech recognition** — the app can now listen for a custom trigger phrase
  and launch the assistant hands-free, entirely on-device.
- **Multi-language model support** — built-in options for Russian (`vosk-model-small-ru-0.22`)
  and English (`vosk-model-small-en-us-0.15`). Users can also browse the full
  [Vosk model catalog](https://alphacephei.com/vosk/models) and download any model directly.
- **Portal integration** — selecting "Изменить на портале" in the dropdown opens the Vosk
  models page in a browser; tapping any `.zip` link is intercepted by the app via an
  `ACTION_VIEW` intent filter and the model is downloaded/extracted automatically.
- **Configurable trigger phrase** — default is "привет катя", editable in Settings.
- **Vibration & sound feedback** — toggles in Settings control whether a haptic pulse and/or
  notification sound fire when the wake word is detected.
- **Background download with notification** — model download runs in a foreground service with
  a progress notification (reuses `ModelDownloadService`).
- **Dynamic model storage** — each model is stored in its own directory derived from the URL,
  so switching between languages doesn't overwrite previous downloads.

### System Permissions (Android)
- On first launch, the app now sequentially requests:
  1. Root access (if available)
  2. Battery optimization exclusion
  3. Unused app restrictions pause
- Uses `ActivityResultContracts` for a proper sequential chain — each permission prompt
  waits for user response before showing the next.

### UI / Branding
- Removed "Sponsors" section and documentation link from the bottom bar (temporarily).
- Updated footer with project GitHub link.
- Localization improvements across settings screens.

## Files Changed

### New Files
- `composeApp/src/commonMain/kotlin/.../stt/WakeWordPlatform.kt` — common interface
- `composeApp/src/androidMain/kotlin/.../stt/WakeWordPlatform.android.kt` — Android Vosk implementation
- `composeApp/src/androidMain/kotlin/.../stt/SttModule.kt` — Koin DI module (Android)
- `composeApp/src/iosMain/kotlin/.../stt/WakeWordPlatform.ios.kt` — iOS stub
- `composeApp/src/iosMain/kotlin/.../stt/SttModule.kt` — Koin DI module (iOS)
- `composeApp/src/jvmShared/kotlin/.../stt/WakeWordPlatform.jvm.kt` — JVM stub
- `composeApp/src/jvmShared/kotlin/.../stt/SttModule.kt` — Koin DI module (JVM)
- `composeApp/src/commonMain/kotlin/.../ui/settings/WakeWordToggle.kt` — Wake Word settings UI

### Modified Files
- `AndroidManifest.xml` — added `VIBRATE` permission, `ACTION_VIEW` intent filter for `.zip` on alphacephei.com
- `MainActivity.kt` — sequential permission requests + deep link interception for Vosk model downloads
- `AppSettings.kt` — new keys for wake word settings (enabled, lang, trigger, vibration, sound)
- `DataRepository.kt` / `RemoteDataRepository.kt` — wake word getter/setter methods
- `SettingsViewModel.kt` — wake word state management, model URL resolution, triggerWakeWordResponse
- `SettingsUiState.kt` — new state fields for wake word
- `SettingsActions.kt` — new action callbacks for wake word
- `GeneralSettings.kt` — integrated WakeWordToggle composable
- `README.md` — rebranded to "Katya AI Assistant", added Features section
