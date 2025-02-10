import java.net.URI

rootProject.name = "KvColorPicker-Android"

listOf(
    ":kv-color-picker"
).onEach {
    include(it)
}

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            when(requested.id.toString()) {
                in listOf(
                    "com.google.gms.google-services"
                ) -> useModule("com.google.gms:google-services:${requested.version}")
                "org.jetbrains.dokka" -> useModule("org.jetbrains.dokka:dokka-gradle-plugin:${requested.version}")
                else -> return@eachPlugin
            }
        }
    }

    repositories {
        mavenCentral()
        google()
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("./gradle/version-catalog/libs.versions.toml"))
        }
    }

    repositories {
        google()
        mavenCentral()
        maven { url = URI("https://jitpack.io") }
    }
}
