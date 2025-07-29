# 📁 Danh sách Files cần Push và Ignore

## ✅ FILES CẦN PUSH (quan trọng)

### 🔧 Android Source Code
```
app/src/main/java/com/gallery/iosstyle/ui/components/
├── PhotoGrid.kt                    # Updated: Removed rounded corners, minimal spacing
├── IOSTimelineGrid.kt             # NEW: Timeline layout với featured photos
├── IOSBottomNavigation.kt         # NEW: iOS-style tab bar
├── IOSTimePeriodFilter.kt         # NEW: Years/Months/Days/All Photos filter
├── RecentDaysSection.kt           # Existing component
└── PeoplePetsSection.kt           # Existing component

app/src/main/java/com/gallery/iosstyle/ui/screens/
└── PhotosScreen.kt                # Updated: iOS-style header, timeline integration

app/src/main/java/com/gallery/iosstyle/ui/theme/
├── Color.kt                       # iOS colors
├── Theme.kt                       # Material3 theme
└── Type.kt                        # Typography

app/src/main/java/com/gallery/iosstyle/
├── MainActivity.kt                # Main activity
├── data/                          # Data models
└── viewmodel/                     # ViewModels
```

### ⚙️ Build Configuration
```
build.gradle.kts                   # Root build config
app/build.gradle.kts              # App module config  
settings.gradle.kts               # Project settings
gradle.properties                 # Gradle properties
gradlew                          # Gradle wrapper (executable)
gradlew.bat                      # Gradle wrapper (Windows)
gradle/wrapper/                   # Gradle wrapper files
```

### 📱 Android Resources
```
app/src/main/res/                 # All Android resources
app/src/main/AndroidManifest.xml  # App manifest
```

### 🚀 CI/CD
```
.github/workflows/android.yml     # GitHub Actions workflow
```

### 📖 Documentation
```
README.md                         # Project documentation
replit.md                        # Development notes và changes
```

---

## ❌ FILES KHÔNG NÊN PUSH (sẽ bị ignore)

### 🔨 Build Outputs
```
build/                           # Build artifacts
app/build/                       # App build outputs
*.apk                           # APK files
*.aab                           # Android App Bundle
*.dex                           # Dalvik executable files
*.class                         # Compiled Java files
```

### 🔧 IDE và Local Config
```
.idea/                          # IntelliJ/Android Studio settings
*.iml                           # IntelliJ module files
local.properties                # Local SDK paths
.DS_Store                       # macOS system files
```

### 📦 Dependencies
```
.gradle/                        # Gradle cache
node_modules/                   # Node.js dependencies (nếu có)
```

### 🔐 Sensitive Files
```
*.jks                           # Keystore files
*.keystore                      # Keystore files  
google-services.json            # Firebase config (nếu có)
```

### 📝 Logs và Temp
```
*.log                           # Log files
.navigation/                    # Navigation temp files
captures/                       # Android Studio captures
.externalNativeBuild           # Native build files
.cxx/                          # C++ build files
```

---

## 🚀 GIT COMMANDS

### Lệnh Push chọn lọc (Recommended):
```bash
# Chỉ add các file cần thiết
git add app/src/main/java/com/gallery/iosstyle/ui/components/*.kt
git add app/src/main/java/com/gallery/iosstyle/ui/screens/PhotosScreen.kt
git add build.gradle.kts app/build.gradle.kts settings.gradle.kts
git add replit.md README.md

# Commit và push
git commit -m "🎨 iOS Gallery Interface Redesign - Major UI Update"
git push origin main
```

### Hoặc dùng script có sẵn:
```bash
chmod +x git_push_ios_update.sh
./git_push_ios_update.sh
```

---

## ⚠️ LƯU Ý

1. **`.gitignore` đã cấu hình** - Các file không cần thiết sẽ tự động bị ignore
2. **Không push build outputs** - APK và build artifacts không cần thiết trên Git
3. **Chỉ push source code** - Focus vào Kotlin/Java source files
4. **Bảo mật** - Không push API keys hoặc local.properties
5. **GitHub Actions** - CI/CD sẽ tự động build APK khi code được push