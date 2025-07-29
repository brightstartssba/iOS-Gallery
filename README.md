# iOS Style Gallery - Android App

Ứng dụng Android Gallery mô phỏng giao diện của iOS Photos app, được xây dựng bằng Kotlin và Jetpack Compose.

## 📱 Tính năng

- **Grid Layout**: Hiển thị ảnh dạng lưới giống iOS Photos
- **Recent Days**: Phân nhóm ảnh theo ngày
- **People & Pets**: Nhóm ảnh theo người và thú cưng (mô phỏng)
- **Zoom & Swipe**: Xem chi tiết và vuốt giữa các ảnh
- **Permissions**: Yêu cầu quyền truy cập ảnh một cách an toàn
- **iOS-style UI**: Thiết kế mô phỏng giao diện iOS

## 🛠️ Tech Stack

- **Kotlin** - Ngôn ngữ lập trình chính
- **Jetpack Compose** - UI Framework hiện đại
- **MVVM Architecture** - ViewModel + StateFlow
- **Coil** - Thư viện load ảnh
- **MediaStore API** - Truy cập ảnh từ thiết bị
- **Material Design 3** - Design system

## 🚀 Build & Run

### Yêu cầu
- Android Studio Flamingo (2022.2.1) hoặc mới hơn
- JDK 17
- Android SDK API 34
- Gradle 8.4

### Local Build
```bash
git clone <repository>
cd ios-gallery-android
./gradlew assembleDebug
```

### GitHub Actions Build
- Push code lên GitHub
- GitHub Actions sẽ tự động build APK
- Download APK từ tab "Actions" → "Artifacts"

## 📦 APK Download

APK sẽ được build tự động qua GitHub Actions. Kiểm tra:
1. Tab **Actions** để xem quá trình build
2. Tab **Releases** hoặc **Artifacts** để tải APK
3. File APK: `ios-gallery-debug-apk` hoặc `ios-gallery-release-apk`

## 🔒 Permissions

App yêu cầu các quyền sau:
- `READ_MEDIA_IMAGES` (Android 13+)
- `READ_EXTERNAL_STORAGE` (Android 12 trở xuống)

## 🎨 Screenshots

App mô phỏng giao diện iOS Photos với:
- Grid ảnh 3 cột với bo góc
- Header "Photos" với số lượng ảnh
- Section "Recent Days" với ảnh thumbnail
- Section "People & Pets" với avatar tròn
- Xem chi tiết ảnh với zoom và swipe

## 🏗️ Cấu trúc Project

```
app/
├── src/main/
│   ├── java/com/gallery/iosstyle/
│   │   ├── MainActivity.kt              # Activity chính
│   │   ├── data/
│   │   │   ├── Photo.kt                 # Data models
│   │   │   └── PhotoRepository.kt       # Repository pattern
│   │   ├── ui/
│   │   │   ├── screens/                 # Compose screens
│   │   │   ├── components/              # UI components
│   │   │   └── theme/                   # Material theme
│   │   └── viewmodel/
│   │       └── PhotoViewModel.kt        # ViewModel với StateFlow
│   ├── res/                             # Resources (colors, strings, icons)
│   └── AndroidManifest.xml
├── build.gradle.kts                     # App build config
└── proguard-rules.pro                   # ProGuard rules
```

## 🔧 GitHub Actions

File `.github/workflows/android.yml` cấu hình:
- Build cả debug và release APK
- Upload artifacts với retention 30 ngày
- Run lint checks
- Cache Gradle dependencies

## 🎯 Compatibility

- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)
- **Compile SDK**: 34

## 📄 License

MIT License - Xem file LICENSE để biết thêm chi tiết.