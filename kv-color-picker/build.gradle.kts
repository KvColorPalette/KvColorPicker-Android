plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("maven-publish")
    alias(libs.plugins.dokka.documentation)
}

val kvColorPaletteGroupId: String by project
val kvColorPickerArtifactId: String by project
val kvColorPickerVersion: String by project

android {
    namespace = "com.kavi.droid.color.picker"
    compileSdk = libs.versions.compilerSdkVersion.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdkVersion.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        val javaVersion = JavaVersion.toVersion(libs.versions.jvmVersion.get().toInt())
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmVersion.get()
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.kv.color.palette)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

tasks.dokkaHtml {
    outputDirectory.set(layout.buildDirectory.asFile.get().resolve("docs/dokkaHtml"))
    dokkaSourceSets {
        named("main") {
            noAndroidSdkLink.set(false)
            reportUndocumented.set(true) // Optional, to include undocumented declarations
            skipDeprecated.set(false) // Optional, to skip deprecated members
            skipEmptyPackages.set(true) // Optional, to skip packages with no documentable members
            includeNonPublic.set(false) // Optional, to include non-public members
        }
    }
}

tasks.build {
    finalizedBy(tasks.dokkaHtml)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = kvColorPaletteGroupId
            artifactId = kvColorPickerArtifactId
            version = kvColorPickerVersion

            afterEvaluate {
                from(components["release"])
                // Attach Dokka documentation
                artifact(tasks.dokkaJavadoc.map { it.outputDirectory.get().resolve("index.html") }) {
                    classifier = "javadoc"
                    extension = "html"
                }
            }
        }
    }
}