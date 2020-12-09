/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
plugins {
    with(Plugins) {
        id(androidLibrary)
        kotlin(android)
    }
}

android {
    with(ProjectSettings) {
        compileSdkVersion(projectCompileSdkVersion)

        defaultConfig {
            minSdkVersion(projectMinSdkVersion)
            targetSdkVersion(projectTargetSdkVersion)

            versionCode = getVersionCode(project)
            versionName = getVersionName(project)
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        buildFeatures {
            viewBinding = true
            dataBinding = true
        }
    }
}

dependencies {
    with(Dependencies.Android) {
        implementation(navigation)
        implementation(androidMaterial)
    }
}
