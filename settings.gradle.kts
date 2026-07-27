enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }

    versionCatalogs {
        create("libs") {
            version("kotlin", "1.7.20")
            version("agp", "8.2.0")

            plugin("kotlin-serialization", "org.jetbrains.kotlin.plugin.serialization").versionRef("kotlin")

            library("kotlin-coroutines-core", "org.jetbrains.kotlinx", "kotlinx-coroutines-core").version("1.9.0")
            library("kotlin-coroutines-android", "org.jetbrains.kotlinx", "kotlinx-coroutines-android").version("1.9.0")

            version("compose-bom", "2022.10.00")
            library("compose-bom", "androidx.compose", "compose-bom").versionRef("compose-bom")

            library("compose-foundation", "androidx.compose.foundation", "foundation").versionRef("compose-bom")
            library("compose-ui", "androidx.compose.ui", "ui").versionRef("compose-bom")
            library("compose-ui-util", "androidx.compose.ui", "ui-util").versionRef("compose-bom")
            library("compose-material3", "androidx.compose.material3", "material3").versionRef("compose-bom")
            library("compose-material3-window-size", "androidx.compose.material3", "material3-window-size-class").version("1.3.0")
            library("compose-activity", "androidx.activity", "activity-compose").version("1.9.3")
            library("compose-viewmodel", "androidx.lifecycle", "lifecycle-viewmodel-compose").version("2.8.6")
            library("compose-navigation", "androidx.navigation", "navigation-compose").version("2.8.2")

            library("compose-shimmer", "com.valentinilk.shimmer", "compose-shimmer").version("1.3.2")

            version("compose-compiler", "1.3.2")
            plugin("compose-compiler", "org.jetbrains.kotlin.plugin.compose").versionRef("compose-compiler")

            version("room", "2.6.1")
            library("room-runtime", "androidx.room", "room-runtime").versionRef("room")
            library("room-ktx", "androidx.room", "room-ktx").versionRef("room")
            library("room-compiler", "androidx.room", "room-compiler").versionRef("room")

            version("media3", "1.4.1")
            library("media3-exoplayer", "androidx.media3", "media3-exoplayer").versionRef("media3")
            library("media3-session", "androidx.media3", "media3-session").versionRef("media3")
            library("media3-datasource", "androidx.media3", "media3-datasource").versionRef("media3")
            library("media3-common", "androidx.media3", "media3-common").versionRef("media3")
            library("media3-ui", "androidx.media3", "media3-ui").versionRef("media3")

            version("ktor", "3.0.2")
            library("ktor-client-core", "io.ktor", "ktor-client-core").versionRef("ktor")
            library("ktor-client-okhttp", "io.ktor", "ktor-client-okhttp").versionRef("ktor")
            library("ktor-client-cio", "io.ktor", "ktor-client-cio").versionRef("ktor")
            library("ktor-client-content-negotiation", "io.ktor", "ktor-client-content-negotiation").versionRef("ktor")
            library("ktor-client-encoding", "io.ktor", "ktor-client-encoding").versionRef("ktor")
            library("ktor-serialization-kotlinx-json", "io.ktor", "ktor-serialization-kotlinx-json").versionRef("ktor")

            library("kotlinx-serialization-json", "org.jetbrains.kotlinx", "kotlinx-serialization-json").version("1.7.3")

            library("coil", "io.coil-kt", "coil-compose").version("2.7.0")
            library("coil-video", "io.coil-kt", "coil-video").version("2.7.0")

            library("brotli", "org.brotli", "dec").version("0.1.2")

            version("palette", "1.0.0")
            library("palette-ktx", "androidx.palette", "palette-ktx").versionRef("palette")

            version("desugaring", "2.1.3")
            library("desugar", "com.android.tools", "desugar_jdk_libs").versionRef("desugaring")
        }

        create("testLibs") {
            library("junit", "junit", "junit").version("4.13.2")
            library("androidx-test", "androidx.test.ext", "junit-ktx").version("1.2.1")
            library("espresso", "androidx.test.espresso", "espresso-core").version("3.6.1")
        }
    }
}

rootProject.name = "ViMusic"
include(":app")
include(":compose-routing")
include(":compose-reordering")
include(":compose-persist")
// include(":innertube")  # TEMP: disabled for base build test
// include(":ktor-client-brotli")  # TEMP: disabled for base build test
// include(":kugou")  # TEMP: disabled for base build test
