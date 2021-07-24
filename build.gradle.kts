/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        with(ClassPaths) {
            classpath(ANDROID_GRADLE_PLUGIN)
            classpath(KOTLIN_GRADLE_PLUGIN)
        }
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}