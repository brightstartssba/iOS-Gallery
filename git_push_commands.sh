#!/bin/bash

# Git Push Commands for iOS Gallery Android App
echo "📱 iOS Gallery - Git Push Script"
echo "Repository: https://github.com/brightstartssba/iOS-Gallery.git"
echo ""

# Repository details
REPO_URL="https://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@github.com/brightstartssba/iOS-Gallery.git"

# Initialize git
echo "🔧 Setting up git repository..."
git init
git config user.name "iOS Gallery Developer"
git config user.email "developer@iosgallery.com"
git remote add origin $REPO_URL

# Files TO INCLUDE (explicitly add these)
echo ""
echo "✅ FILES TO PUSH:"

# Root configuration files
git add build.gradle.kts
git add settings.gradle.kts
git add gradle.properties
git add gradlew
git add gradlew.bat
echo "   ✓ Gradle build files"

# App source code
git add app/build.gradle.kts
git add app/proguard-rules.pro
git add app/src/
echo "   ✓ Android app source code"

# Gradle wrapper
git add gradle/wrapper/
echo "   ✓ Gradle wrapper"

# GitHub Actions
git add .github/
echo "   ✓ GitHub Actions workflow"

# Documentation
git add README.md
git add DEPLOYMENT_GUIDE.md
git add PROJECT_SUMMARY.md
git add GITHUB_PUSH_MANUAL.md
git add replit.md
echo "   ✓ Documentation files"

# Git configuration
git add .gitignore
echo "   ✓ Git configuration"

echo ""
echo "❌ FILES NOT TO PUSH:"
echo "   ✗ local.properties (local SDK paths)"
echo "   ✗ .gradle/ (build cache)"
echo "   ✗ build/ (compiled outputs)"
echo "   ✗ .idea/ (IDE settings)"
echo "   ✗ *.apk, *.aab (built applications)"
echo "   ✗ .cache/ (Replit cache)"
echo "   ✗ .local/ (Replit local data)"
echo "   ✗ .upm/ (Replit package manager)"
echo "   ✗ attached_assets/ (demo images)"
echo "   ✗ push_to_github.sh (temporary scripts)"

# Ensure these are NOT added (remove if accidentally staged)
git reset HEAD local.properties 2>/dev/null || true
git reset HEAD .gradle/ 2>/dev/null || true
git reset HEAD build/ 2>/dev/null || true
git reset HEAD .idea/ 2>/dev/null || true
git reset HEAD .cache/ 2>/dev/null || true
git reset HEAD .local/ 2>/dev/null || true
git reset HEAD .upm/ 2>/dev/null || true
git reset HEAD attached_assets/ 2>/dev/null || true
git reset HEAD push_to_github.sh 2>/dev/null || true
git reset HEAD git_push_commands.sh 2>/dev/null || true

echo ""
echo "📋 COMMIT AND PUSH:"

# Create commit
git commit -m "🎉 Initial commit: Android iOS-style Gallery App

✨ Features implemented:
• iOS Photos app UI with Jetpack Compose
• Photo grid layout (3 columns, rounded corners)
• Recent Days section with date grouping
• People & Pets section with circular avatars
• Photo detail viewer with zoom and swipe
• MediaStore API integration for device photos
• Permission handling for Android 7.0+
• MVVM architecture with ViewModel + StateFlow

🛠️ Technical stack:
• Kotlin + Jetpack Compose
• Material Design 3 with iOS-style colors
• Coil for image loading
• Navigation Compose
• StateFlow for reactive UI

🚀 CI/CD configured:
• GitHub Actions for automatic APK builds
• Debug and release variants
• Artifacts upload with 30-day retention
• Lint checks and caching

📱 Compatibility:
• Min SDK: 24 (Android 7.0) - 95%+ device coverage
• Target SDK: 34 (Android 14)
• Supports READ_MEDIA_IMAGES and legacy permissions

Ready to build APK via GitHub Actions! 🚀"

# Set main branch and push
git branch -M main
git push -u origin main

echo ""
echo "✅ PUSH COMPLETED!"
echo ""
echo "🎯 Next steps:"
echo "1. Go to: https://github.com/brightstartssba/iOS-Gallery"
echo "2. Check 'Actions' tab for build progress"
echo "3. Wait 5-10 minutes for APK build to complete"
echo "4. Download APK from 'Artifacts' section"
echo ""
echo "📱 APK files that will be generated:"
echo "   • ios-gallery-debug-apk (for testing)"
echo "   • ios-gallery-release-apk (for distribution)"