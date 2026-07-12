# Release Notes
## [Unreleased]
### Fixed
- Fixed an issue causing `ScreenshotTest` to crash by gracefully bypassing Koin initialization for STT and `AudioPermissionController` components when running in Compose `LocalInspectionMode`.
- Resolved unresolved references to `SttController` by ensuring Kotlin safe calls (`?.`) are correctly used within `QuestionInput.kt` when STT is disabled in preview mode.
- CI/CD Unit Test pipeline is now restored and `screenshotTests` run successfully alongside unit tests.
