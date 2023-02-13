/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
import java.io.IOException
import java.util.Properties

plugins {
    @Suppress("DSL_SCOPE_VIOLATION")
    libs.plugins.apply {
        id(androidLib.get().pluginId)
        id(android.get().pluginId)
        `maven-publish`
        signing
    }
}

@Suppress("UnstableApiUsage")
android {
    namespace = "com.github.submob.basemob"
    compileSdk = ProjectSettings.COMPILE_SDK_VERSION

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    libs.android.apply {
        implementation(navigation)
        implementation(androidMaterial)
    }
}

tasks {
    register("androidJavadocJar", Jar::class) {
        archiveClassifier.set("javadoc")
        from("$buildDir/javadoc")
    }

    register("androidSourcesJar", Jar::class) {
        archiveClassifier.set("sources")
        @Suppress("UnstableApiUsage")
        from(android.sourceSets.getByName("main").kotlin.srcDirs().toString())
    }
}

publishing {
    publications {
        register<MavenPublication>("mavenAndroid") {
            Library.apply {
                group = GROUP
                version = ProjectSettings.getVersionName(project)

                afterEvaluate {
                    artifact(tasks.getByName("bundleReleaseAar"))

                    extensions.findByType<PublishingExtension>()?.apply {
                        repositories {
                            maven {
                                url = uri(if (isReleaseBuild) RELEASE_URL else SNAPSHOT_URL)
                                credentials {
                                    username = getSecret("MAVEN_USERNAME")
                                    password = getSecret("MAVEN_PASSWORD")
                                }
                            }
                        }
                    }

                    extensions.findByType<PublishingExtension>()?.let { publishing ->
                        val key = getSecret("GPG_KEY").replace("\\n", "\n")
                        val password = getSecret("GPG_PASSWORD")

                        extensions.findByType<SigningExtension>()?.apply {
                            useInMemoryPgpKeys(key, password)
                            sign(publishing.publications)
                        }
                    }

                    tasks.withType<Sign>().configureEach {
                        onlyIf { isReleaseBuild }
                    }
                }

                artifact(tasks.getByName("androidJavadocJar"))
                artifact(tasks.getByName("androidSourcesJar"))

                pom {

                    name.set(NAME)
                    description.set(DESCRIPTION)
                    url.set(URL)

                    licenses {
                        license {
                            name.set(LICENSE_NAME)
                            url.set(LICENSE_URL)
                            distribution.set(LICENSE_DISTRIBUTION)
                        }
                    }
                    developers {
                        developer {
                            id.set(DEVELOPER_ID)
                            name.set(DEVELOPER_NAME)
                            email.set(DEVELOPER_EMAIL)
                        }
                    }
                    scm { url.set(URL) }
                }
            }
        }
    }
}

val isReleaseBuild: Boolean
    get() = System.getenv("GPG_KEY") != null

fun getSecret(
    key: String,
    default: String = "secret" // these values can not be public
): String = System.getenv(key).let {
    if (it.isNullOrEmpty()) {
        getSecretProperties()?.get(key)?.toString() ?: default
    } else {
        it
    }
}

fun getSecretProperties() = try {
    Properties().apply { load(file("$projectDir/key.properties").inputStream()) }
} catch (e: IOException) {
    logger.debug(e.message, e)
    null
}

object Library {
    const val GROUP = "com.github.submob"
    const val URL = "https://github.com/SubMob/BaseMob"
    const val NAME = "BaseMob"
    const val DESCRIPTION = "Set of base classes for Android"
    const val DEVELOPER_NAME = "Mustafa Ozhan"
    const val DEVELOPER_ID = "mustafaozhan"
    const val DEVELOPER_EMAIL = "mr.mustafa.ozhan@gmail.com"
    const val LICENSE_NAME = "The Apache Software License, Version 2.0"
    const val LICENSE_URL = "http://www.apache.org/licenses/LICENSE-2.0.txt"
    const val LICENSE_DISTRIBUTION = "repo"
    const val RELEASE_URL = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2"
    const val SNAPSHOT_URL = "https://s01.oss.sonatype.org/content/repositories/snapshots"
}
