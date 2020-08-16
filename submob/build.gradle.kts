/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
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
    with(Dependencies) {
        implementation(kotlin)
        implementation(dagger)
        implementation(navigation)
        implementation(androidMaterial)
        implementation(moshiConverter)
        implementation(okhttpInterceptor)
    }
}