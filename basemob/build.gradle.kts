/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
plugins {
    with(Plugins) {
        id(ANDROID_LIB)
        kotlin(ANDROID)
        `maven-publish`
        signing
    }
}

android {
    with(ProjectSettings) {
        compileSdk = COMPILE_SDK_VERSION

        defaultConfig {
            minSdk = MIN_SDK_VERSION
            targetSdk = TARGET_SDK_VERSION
        }

        buildFeatures {
            viewBinding = true
            dataBinding = true
        }
    }
}

dependencies {
    with(Dependencies.Android) {
        implementation(NAVIGATION)
        implementation(ANDROID_MATERIAL)
    }
}

tasks {
    register("androidJavadocJar", Jar::class) {
        archiveClassifier.set("javadoc")
        from("$buildDir/javadoc")
    }

    register("androidSourcesJar", Jar::class) {
        archiveClassifier.set("sources")
        from(android.sourceSets.getByName("main").java.srcDirs().toString())
    }
}

publishing {
    publications {
        register<MavenPublication>("mavenAndroid") {
            with(Library) {
                group = GROUP
                version = ProjectSettings.getVersionName(project)

                afterEvaluate {
                    artifact(tasks.getByName("bundleReleaseAar"))

                    extensions.findByType<PublishingExtension>()?.apply {
                        repositories {
                            maven {
                                url = uri(if (isReleaseBuild) RELEASE_URL else SNAPSHOT_URL)
                                credentials {
                                    username = System.getenv("MAVEN_USERNAME")?.toString()
                                    password = System.getenv("MAVEN_PASSWORD")?.toString()
                                }
                            }
                        }
                    }

                    extensions.findByType<SigningExtension>()?.apply {
                        val publishing =
                            extensions.findByType<PublishingExtension>() ?: return@apply
                        val key = System.getenv("GPG_KEY")?.toString()?.replace("\\n", "\n")
                        val password = System.getenv("GPG_PASSWORD")?.toString()

                        @Suppress("UnstableApiUsage")
                        useInMemoryPgpKeys(key, password)
                        sign(publishing.publications)
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
