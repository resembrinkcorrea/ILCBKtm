import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    kotlin("plugin.serialization") version "1.9.22"
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.coil.compose)

            //koin
//            implementation(project.dependencies.platform("io.insert-koin:koin-bom:3.5.1"))
//            implementation("io.insert-koin:koin-core")
//            implementation("io.insert-koin:koin-android")

            //ktor
            implementation(libs.ktor.client.okhttp)
            implementation("io.ktor:ktor-client-android:2.3.0")
            implementation("io.ktor:ktor-client-cio:2.3.0")
            implementation("io.ktor:ktor-client-content-negotiation:2.0.3")
            implementation("io.ktor:ktor-client-logging:2.3.0")
            implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.3")

            implementation(libs.kotlinx.coroutines.android)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.coil.compose)
            api(compose.materialIconsExtended)

            //Navigatio Pre Compose
            api("moe.tlaster:precompose:1.5.10")

            //ViewModel
            api("moe.tlaster:precompose-viewmodel:1.5.10")


            //Koin Common
//            implementation(project.dependencies.platform("io.insert-koin:koin-bom:3.5.1"))
//            implementation("io.insert-koin:koin-core")
//            implementation("io.insert-koin:koin-compose")
//            api("moe.tlaster:precompose-koin:1.5.10")

            //ktor
            implementation(libs.ktor.client.core)
           implementation("io.ktor:ktor-client-cio:2.3.0")
            implementation("io.ktor:ktor-client-logging:2.3.0")
            implementation("io.ktor:ktor-client-content-negotiation:2.0.3")
            implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.3")

            implementation(libs.kotlinx.coroutines.core)

            implementation(libs.ktor.serialization)
            implementation(libs.ktor.content.negotiation)


        }

        commonMain.dependencies {
            implementation(compose.components.resources)

        }

        iosMain.dependencies {
            //iOS dependencies

            //ktor
            implementation(libs.ktor.client.darwin)

            //
            implementation(libs.coil.compose)
        }

    }
}

android {
    namespace = "pe.lecordonbleu.institutoestudiante"
    compileSdk = libs.versions.android.compileSdk.get().toInt()



    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "pe.lecordonbleu.institutoestudiante"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.12"
    }
}
dependencies {
    implementation(libs.androidx.lifecycle.viewmodel.compose)
}

