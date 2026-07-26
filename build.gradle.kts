buildscript {
    dependencies {
        // Supply plugins locally (indirect) when repo/network download blocked.
        // Place jar files in plugins/<group-path>/<version>/<artifact>.jar with .pom file.
        classpath(files("plugins/com/android/tools/build/gradle/8.5.0/gradle-8.5.0.jar"))
    }
}

plugins {
    id("org.jetbrains.kotlin.android") version "2.0.21" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.21" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21" apply false
}

apply(plugin = "com.android.application")

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}

subprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.RequiresOptIn",
                "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
                "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
            )
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.plugin.KaptGenerateStubs>().configureEach {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}
