buildscript {
    dependencies {
        classpath(files("plugins/com/android/tools/build/gradle/8.5.0/gradle-8.5.0.jar"))
    }
}

plugins {
    id("com.android.application") version "7.3.0" apply false
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.20" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "1.3.2" apply false
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
