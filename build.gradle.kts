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
            classpath(androidBuildTools)
            classpath(kotlinGradlePlugin)
        }
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}