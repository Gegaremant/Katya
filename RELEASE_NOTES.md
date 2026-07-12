# Release Notes
## [Unreleased]

## [1.0.3]
### Added
- Added Root access confirmation dialog on first launch.
- Implemented SSH Tunnel via JSch library for connecting to local models on srv-llm.
- Added Battery Optimization explanation dialog in MainActivity.
- Translated Quick Actions to Russian and added configuration examples.
- Updated Local API AI description with reference to the Servers tab for SSH tunnels.

### Fixed
- Fixed black text on dark theme in GeneralSettings (Dropdowns/Inputs).
### Fixed
- Fixed an issue causing `ScreenshotTest` to crash by gracefully bypassing Koin initialization for STT and `AudioPermissionController` components when running in Compose `LocalInspectionMode`.
- Resolved unresolved references to `SttController` by ensuring Kotlin safe calls (`?.`) are correctly used within `QuestionInput.kt` when STT is disabled in preview mode.
- CI/CD Unit Test pipeline is now restored and `screenshotTests` run successfully alongside unit tests.
