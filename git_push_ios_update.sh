#!/bin/bash

# Script để đẩy iOS Gallery updates lên GitHub
# Chỉ đẩy các file cần thiết, bỏ qua các file không cần

echo "🚀 Bắt đầu đẩy iOS Gallery updates lên GitHub..."

# Kiểm tra trạng thái git
echo "📋 Kiểm tra trạng thái hiện tại..."
git status

# Add các file Android cần thiết
echo "📁 Thêm các file Android code..."
git add app/src/main/java/com/gallery/iosstyle/ui/components/PhotoGrid.kt
git add app/src/main/java/com/gallery/iosstyle/ui/components/IOSTimelineGrid.kt
git add app/src/main/java/com/gallery/iosstyle/ui/components/IOSBottomNavigation.kt
git add app/src/main/java/com/gallery/iosstyle/ui/components/IOSTimePeriodFilter.kt
git add app/src/main/java/com/gallery/iosstyle/ui/screens/PhotosScreen.kt

# Add build configuration files
echo "⚙️ Thêm các file cấu hình build..."
git add build.gradle.kts
git add app/build.gradle.kts
git add settings.gradle.kts
git add gradle.properties

# Add documentation
echo "📖 Thêm documentation..."
git add replit.md
git add README.md

# Add preview file (optional)
git add index.html

# Commit với message rõ ràng
echo "💾 Commit changes..."
git commit -m "🎨 iOS Gallery Interface Redesign

✨ Major UI Update to match authentic iOS Photos app:

📱 New Components:
- IOSTimelineGrid: Timeline layout với featured photos + grid
- IOSBottomNavigation: Tab bar iOS-style với 4 tabs
- IOSTimePeriodFilter: Years/Months/Days/All Photos selector

🔧 Photo Grid Improvements:
- Removed rounded corners (8dp → 0dp)
- Minimal spacing (2dp → 1dp) 
- Replaced Card với Box (no elevation/shadows)

🎯 iOS-Style Features:
- Timeline organization by date (Today/Yesterday/dates)
- Large featured photo + smaller grid layout
- Authentic iOS colors (#007AFF blue)
- iOS typography (34sp header)
- Clean minimal design

📱 Full iOS Photos app experience on Android!"

# Push lên GitHub
echo "🚀 Đẩy lên GitHub..."
git push origin main

echo "✅ Hoàn thành! Code đã được đẩy lên GitHub thành công."
echo "🔗 Repository: https://github.com/brightstartssba/iOS-Gallery"