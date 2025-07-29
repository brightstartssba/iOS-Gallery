# 📤 Hướng dẫn Push Code lên GitHub

Do Replit environment có giới hạn với git operations, bạn cần thực hiện push code theo cách thủ công.

## 🔄 Cách 1: Sử dụng Git Commands (Khuyên dùng)

### Bước 1: Tải code về máy tính
1. Download toàn bộ project từ Replit
2. Giải nén vào folder local

### Bước 2: Git commands
```bash
# Navigate to project folder
cd iOS-Gallery

# Initialize git repository
git init

# Configure git user
git config user.name "Your Name"
git config user.email "your.email@gmail.com"

# Add remote repository
git remote add origin https://github.com/brightstartssba/iOS-Gallery.git

# Add all files
git add .

# Create commit
git commit -m "🎉 Initial commit: Android iOS-style Gallery App

✨ Features:
- iOS Photos app UI with Jetpack Compose
- Photo grid with Recent Days & People sections  
- Zoom & swipe photo viewer
- MediaStore API integration
- Permission handling for Android 7.0+
- MVVM architecture with ViewModel + StateFlow

🚀 CI/CD:
- GitHub Actions for automatic APK builds
- Debug & release variants
- Artifacts upload with 30-day retention

📱 Ready to build APK via GitHub Actions!"

# Set main branch
git branch -M main

# Push to GitHub (sử dụng token)
git push -u origin main
```

**Khi được hỏi username/password:**
- Username: `brightstartssba`
- Password: `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`

## 🔄 Cách 2: Upload trực tiếp qua GitHub Web

### Bước 1: Tạo repository mới
1. Vào https://github.com/brightstartssba/iOS-Gallery
2. Nếu repo chưa tồn tại, tạo mới với tên "iOS-Gallery"

### Bước 2: Upload files
1. Click "uploading an existing file"
2. Drag & drop tất cả files từ Replit
3. Hoặc click "choose your files" để select manually

### Bước 3: Commit
- Commit message: "Initial commit: Android iOS-style Gallery App"
- Click "Commit new files"

## 🔄 Cách 3: GitHub CLI (Nếu có cài đặt)

```bash
# Login với token
gh auth login --with-token <<< "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"

# Clone repository
gh repo clone brightstartssba/iOS-Gallery

# Copy files vào repo folder
cp -r /path/to/replit/files/* iOS-Gallery/

# Push changes
cd iOS-Gallery
git add .
git commit -m "Initial commit: Android iOS-style Gallery App"
git push origin main
```

## 📋 Danh sách Files cần upload

Đảm bảo upload tất cả các files sau:

### 🏗️ Root files:
- `build.gradle.kts`
- `settings.gradle.kts` 
- `gradle.properties`
- `gradlew` *(executable)*
- `gradlew.bat`
- `README.md`
- `DEPLOYMENT_GUIDE.md`
- `.gitignore`

### 📁 Folders:
- `app/` *(toàn bộ folder)*
- `gradle/wrapper/` *(bao gồm gradle-wrapper.jar)*
- `.github/workflows/` *(GitHub Actions)*

### ⚠️ Lưu ý quan trọng:
- **KHÔNG upload**: `local.properties`, `.gradle/`, `build/`, `.idea/`
- **Đảm bảo**: `gradlew` có execute permission
- **Kiểm tra**: `.github/workflows/android.yml` được upload

## ✅ Sau khi push thành công

1. **Vào GitHub repository**: https://github.com/brightstartssba/iOS-Gallery
2. **Kiểm tra Actions tab**: GitHub Actions sẽ tự động trigger build
3. **Đợi build complete** (5-10 phút)
4. **Download APK** từ Actions > Artifacts

## 🎯 Expected Results

Sau khi push, bạn sẽ thấy:
- ✅ Repository với tất cả source code
- ✅ GitHub Actions workflow chạy tự động
- ✅ APK artifacts được tạo ra
- ✅ Build status badge (nếu cần)

## 🐛 Nếu gặp lỗi

### Authentication failed:
- Kiểm tra token còn hiệu lực
- Đảm bảo username đúng: `brightstartssba`

### Build failed:
- Kiểm tra logs trong Actions tab
- Đảm bảo tất cả files đã được upload đúng

### Permission denied:
- Đảm bảo account có quyền write vào repository
- Token cần có scope `repo` permissions

Sau khi push thành công, GitHub Actions sẽ tự động build APK cho bạn!