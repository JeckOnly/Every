plugins {
    alias(libs.plugins.hollyland.application.compose.plugin)
    alias(libs.plugins.hollyland.hilt.plugin)
}

android {
    namespace = "com.sjy.every"
    defaultConfig {
        applicationId = "com.sjy.every"
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":feature:widget"))
    implementation(project(":feature:catfact"))
    implementation(project(":core:util"))
    implementation(project(":core:design"))
}