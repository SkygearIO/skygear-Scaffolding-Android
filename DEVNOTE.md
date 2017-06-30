## Use other version of Skygear SDK with git

Use https://jitpack.io.

### Steps:

1. Add the JitPack repository to your build file Add it in your build.gradle at the end of repositories:

```
allprojects {
    repositories {
        // ...
        maven { url "https://jitpack.io" }
    }
}
```

2. Add the dependency in the form

```
dependencies {
    // ...

    // remove the original line for installing Skygear SDK
    // compile 'io.skygear:skygear:+'

    compile 'com.github.SkygearIO:skygear-SDK-Android:master-SNAPSHOT'
}
```

This example is using master branch of skygear-SDK-Android. You may go to https://jitpack.io/#SkygearIO/skygear-SDK-Android and see how to specify other version.
