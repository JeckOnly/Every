plugins {
    alias(libs.plugins.hollyland.application.compose.plugin)
    alias(libs.plugins.hollyland.hilt.plugin)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    namespace = "com.sjy.aidlserver"

    buildFeatures {
        aidl = true
    }
}

dependencies {

}