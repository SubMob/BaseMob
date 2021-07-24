/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */

import org.gradle.api.Project
import java.io.File
import java.util.concurrent.TimeUnit

object ProjectSettings {
    private const val projectMayorVersion = 2
    private const val projectMinorVersion = 0

    const val compileSdkVersion = 29
    const val minSdkVersion = 21
    const val targetSdkVersion = 29

    fun getVersionCode(project: Project) = gitCommitCount(project).let {
        if (it.isEmpty()) 1 else it.toInt()
    }

    fun getVersionName(
        project: Project
    ) = "$projectMayorVersion.$projectMinorVersion.${gitCommitCount(project)}"

    private fun gitCommitCount(
        project: Project
    ) = "git rev-list --first-parent --count origin/master"
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
