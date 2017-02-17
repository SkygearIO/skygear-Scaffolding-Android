# Scaffolding for Skygear Android App

You can start create an android app with [Skygear](https://skygear.io) by the following steps:

1. Install [Android Studio](https://developer.android.com/studio/)
2. Install Android Build Tools (version >= 24.0.0)
3. Register an account on [Skygear Portal](https://portal.skygear.io)
4. Download the [Skygear Starter Project for Android](https://github.com/SkygearIO/skygear-Scaffolding-Android/archive/master.zip).
5. Unzip it and open in Terminal.
6. Declare the location of Android SDK:

  For default Android Studio installation on Mac, the path should be as follow

  `export ANDROID_HOME=$HOME/Library/Android/sdk`

  For Linux distribution, the path default path should be as follow

  `export ANDROID_HOME=/opt/android/sdk`

  For other installation method, like homebrew. Please refer back to the
  installation document.

7. Run a script to update your application settings. For the first time, it
   may take some mintues to download the gradle wrapper:

  `./gradlew -q updateAppSettings`

8. Build your project:

  `./gradlew -q build`

9. Open the project with Android Studio.


You can learn more about the Skygear Android SDK by going to https://docs.skygear.io/android/guide.
