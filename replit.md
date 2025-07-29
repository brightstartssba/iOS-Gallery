# iOS Gallery Android App

## Project Overview
An Android mobile application that mimics the iOS Photos app interface, built with Jetpack Compose. The app features an iOS-style photo grid layout with sections for Recent Days and People & Pets, along with automated GitHub CI/CD pipeline for continuous deployment.

## Stack
- **Android SDK 34** (API Level 34)
- **Kotlin 1.9.25** with Jetpack Compose (latest stable in 1.9.x series)
- **Material Design 3** (Material3)
- **GitHub Actions** for CI/CD
- **Gradle 8.6** build system
- **Java 11** compilation target

## Recent Changes (2025-07-29)

### ✅ Android Build Configuration Fixed - FINAL VERSION
- **Updated Gradle versions**: 
  - Android Gradle Plugin: 8.1.4 → 8.2.2
  - Kotlin: 1.9.21 → **1.9.25** (latest stable in 1.9.x series)  
  - Gradle Wrapper: 8.6 → **8.4** (rollback for stability)
- **CRITICAL FIX**: Kotlin 1.9.22 không tồn tại → Changed to 1.9.25
- **CRITICAL FIX**: Removed `org.jetbrains.kotlin.plugin.compose` plugin (only exists in Kotlin 2.0+)
- **Compose Configuration**: Using standard Kotlin 1.9.x approach với `kotlinCompilerExtensionVersion`
- **Updated Java target**: 1.8 → 11 (modern compatibility)
- **Compose Compiler Extension**: 1.5.8 → **1.5.15** (compatible with Kotlin 1.9.25)
- **Updated dependencies to latest stable versions**:
  - Compose BOM: 2024.02.00 → 2024.12.01
  - AndroidX Lifecycle: 2.7.0 → 2.8.7
  - Activity Compose: 1.8.2 → 1.9.3
  - Navigation Compose: 2.7.5 → 2.8.5
  - Coil: 2.5.0 → 2.7.0
  - Accompanist Permissions: 0.32.0 → 0.36.0
- **Enhanced GitHub Actions workflow**: Added `./gradlew clean` step before building
- **Material 3 theme fix**: Final AppCompat fallback approach - changed XML theme parent from `Theme.Material3.DayNight.NoActionBar` (not found) to `Theme.AppCompat.DayNight.NoActionBar` (guaranteed compatibility), added AppCompat dependency, maintained Material 3 Compose functionality
- **Launcher icon compatibility fix**: Fixed Vector Drawable circle attributes errors by replacing `<circle>` with `<path>`, implemented adaptive icons only for API 26+ với bitmap fallback for API 24-25, ensuring universal launcher icon support across all target Android versions
- **Kotlin compilation fix**: Added `@OptIn(ExperimentalMaterial3Api::class)` annotation và compiler flags to suppress experimental Material 3 API warnings, fixed smart cast error by using local variable for error property handling, resolved missing import for `ExperimentalMaterial3Api` class

### Build Configuration Details
- **Compile SDK**: 34 (Android 14)
- **Target SDK**: 34 (Android 14) 
- **Min SDK**: 24 (Android 7.0)
- **JVM Target**: 11
- **Kotlin Compiler Extension**: 1.5.15 (compatible with Kotlin 1.9.25)

## User Preferences
- **Language**: Vietnamese (project documentation and communication)
- **Build Priority**: Focus on resolving Android build errors and ensuring GitHub Actions success
- **Target**: Successful APK generation in `app/build/outputs/apk/debug/`

## Project Architecture

### Android Application Structure
```
app/
├── src/main/
│   ├── java/com/gallery/iosstyle/
│   │   ├── MainActivity.kt              # Main activity with Compose
│   │   ├── data/
│   │   │   ├── Photo.kt                 # Photo data model
│   │   │   └── PhotoRepository.kt       # Repository pattern
│   │   ├── ui/
│   │   │   ├── screens/                 # Compose screens
│   │   │   ├── components/              # Reusable UI components
│   │   │   └── theme/                   # Material3 theme
│   │   └── viewmodel/
│   │       └── PhotoViewModel.kt        # ViewModel with StateFlow
│   ├── res/                             # Android resources
│   └── AndroidManifest.xml             # App manifest with permissions
└── build.gradle.kts                    # App module build config
```

### GitHub Actions CI/CD
- **Workflow**: `.github/workflows/android.yml`
- **Triggers**: Push to main/master, PRs, manual dispatch
- **Build Outputs**: Debug and Release APK artifacts (30-day retention)
- **Features**: Gradle caching, lint checks, JDK 17 setup, Android SDK auto-installation

## Key Features Implemented
- iOS-style photo grid with 3-column layout
- Recent Days section with thumbnail images
- People & Pets section with circular avatars
- Photo detail viewer with zoom and swipe gestures
- Permission handling for media access
- Material Design 3 theming
- MVVM architecture with Compose

## Next Steps
- Verify the build fixes resolve the original `lStar not found` error
- Test GitHub Actions workflow with updated configuration
- Ensure APK artifacts are generated successfully
- Validate app functionality on Android devices