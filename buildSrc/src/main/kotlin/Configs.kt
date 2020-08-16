/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
@file:Suppress("SpellCheckingInspection")

import org.gradle.api.Project
import java.io.File
import java.util.concurrent.TimeUnit

object Versions {
    const val kotlinVersion = "1.3.72"
    const val androidPluginVersion = "4.0.1"
    const val daggerVersion = "2.28.3"
    const val androidMaterialVersion = "1.2.0"
    const val navigationVersion = "2.3.0"
    const val moshiConverterVersion = "2.9.0"
    const val okhttpInterceptorVersion = "4.8.1"
}

object Dependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
    const val androidMaterial = "com.google.android.material:material:${Versions.androidMaterialVersion}"
    const val dagger = "com.google.dagger:dagger-android-support:${Versions.daggerVersion}"
    const val navigation = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.moshiConverterVersion}"
    const val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpInterceptorVersion}"
}

object Classpaths {
    const val androidBuildTools = "com.android.tools.build:gradle:${Versions.androidPluginVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
}

object ProjectSettings {
    private const val projectMayorVersion = 2
    private const val projectMinorVersion = 0

    const val projectCompileSdkVersion = 29
    const val projectMinSdkVersion = 21
    const val projectTargetSdkVersion = 29

    fun getVersionCode(project: Project) = gitCommitCount(project).let {
        if (it.isEmpty()) 1 else it.toInt()
    }

    fun getVersionName(project: Project) =
        "$projectMayorVersion.$projectMinorVersion.${gitCommitCount(project)}"

    private fun gitCommitCount(project: Project) =
        "git rev-list --first-parent --count origin/master"
            .executeCommand(project.rootDir)?.trim()
            ?: ""

    @Suppress("SpreadOperator", "MagicNumber")
    private fun String.executeCommand(workingDir: File): String? = try {
        val parts = this.split("\\s".toRegex())
        ProcessBuilder(*parts.toTypedArray())
            .directory(workingDir)
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start().run {
                waitFor(10, TimeUnit.SECONDS)
                inputStream.bufferedReader().readText()
            }
    } catch (e: java.io.IOException) {
        e.printStackTrace()
        null
    }
}
