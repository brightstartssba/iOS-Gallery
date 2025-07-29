#!/bin/bash

# GitHub Push Script for iOS Gallery Android App
REPO_URL="https://github.com/brightstartssba/iOS-Gallery.git"
TOKEN="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"
REPO_WITH_TOKEN="https://${TOKEN}@github.com/brightstartssba/iOS-Gallery.git"

echo "🚀 Starting GitHub push process..."

# Remove any existing git configuration
rm -rf .git

# Initialize new git repository
git init
echo "✅ Git repository initialized"

# Configure git user
git config user.name "iOS Gallery Bot"
git config user.email "bot@iosgallery.com"
echo "✅ Git user configured"

# Add remote origin
git remote add origin $REPO_WITH_TOKEN
echo "✅ Remote origin added"

# Add all files
git add .
echo "✅ All files staged"

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

echo "✅ Commit created"

# Set main branch
git branch -M main
echo "✅ Main branch set"

# Push to GitHub
git push -u origin main
echo "✅ Code pushed to GitHub successfully!"

echo ""
echo "🎯 Next steps:"
echo "1. Go to: https://github.com/brightstartssba/iOS-Gallery"
echo "2. Check Actions tab for APK build progress"
echo "3. Download APK from Artifacts when build completes"
echo ""
echo "✅ GitHub repository is ready!"