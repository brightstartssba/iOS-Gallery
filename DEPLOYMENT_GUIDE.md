# 🚀 Hướng dẫn Deploy APK qua GitHub Actions

## 📋 Tóm tắt

Dự án đã được cấu hình đầy đủ để build APK tự động qua GitHub Actions. File APK sẽ được build mỗi khi có commit mới và có thể tải xuống từ GitHub.

## 🔧 Cách thức hoạt động

### 1. GitHub Actions Workflow
- File `.github/workflows/android.yml` được cấu hình để:
  - Build APK debug và release
  - Upload artifacts với retention 30 ngày  
  - Run lint checks
  - Cache Gradle dependencies

### 2. Build Configuration
- **JDK**: Java 17 (Temurin distribution)
- **Android SDK**: Tự động setup
- **Gradle**: 8.4 với wrapper
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)

## 📱 Cách tải APK

### Bước 1: Push code lên GitHub
```bash
git add .
git commit -m "Initial iOS Gallery Android app"
git push origin main
```

### Bước 2: Kiểm tra build
1. Vào repository GitHub
2. Click tab **Actions**
3. Xem workflow "Build Android APK" đang chạy
4. Đợi build hoàn thành (khoảng 5-10 phút)

### Bước 3: Tải APK
1. Vào tab **Actions** 
2. Click vào build run mới nhất
3. Scroll xuống section **Artifacts**
4. Tải file:
   - `ios-gallery-debug-apk` (cho testing)
   - `ios-gallery-release-apk` (cho production)

## 🔒 Signing APK (Tuỳ chọn)

Để tạo signed APK cho production:

### 1. Tạo keystore
```bash
keytool -genkey -v -keystore my-release-key.keystore -alias alias_name -keyalg RSA -keysize 2048 -validity 10000
```

### 2. Thêm secrets vào GitHub
Vào Repository Settings → Secrets and variables → Actions:
- `KEYSTORE_BASE64`: Base64 của keystore file
- `KEYSTORE_PASSWORD`: Mật khẩu keystore
- `KEY_ALIAS`: Alias của key
- `KEY_PASSWORD`: Mật khẩu của key

### 3. Update workflow
Uncomment phần signing trong `.github/workflows/android.yml`

## 🐛 Troubleshooting

### Build fails
- Kiểm tra logs trong Actions tab
- Thường gặp: dependency conflicts, syntax errors

### APK không install được
- Enable "Unknown sources" trên Android
- Kiểm tra architecture compatibility (ARM vs x86)

### Permission issues
- App yêu cầu quyền READ_EXTERNAL_STORAGE
- Grant permission khi app yêu cầu

## 📈 Optimization Tips

1. **Reduce APK size**: Enable ProGuard/R8
2. **Faster builds**: Use build cache
3. **Multiple variants**: Configure product flavors
4. **Auto updates**: Integrate with Firebase App Distribution

## 🏗️ Project Structure Verified

✅ All required files created:
- Android manifest with permissions
- Gradle build scripts 
- Kotlin source code (12 files)
- Resources (strings, colors, themes)
- GitHub Actions workflow
- Gradle wrapper

✅ Features implemented:
- iOS-style photo grid
- Recent Days section  
- People & Pets section
- Photo detail viewer with zoom/swipe
- Permission handling
- MVVM architecture

## 🎯 Next Steps

1. Push code to GitHub repository
2. Wait for Actions to build APK
3. Download and test APK on Android device
4. Optionally set up signed releases with keystore

Dự án đã sẵn sàng để deploy! 🚀