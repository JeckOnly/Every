plugins {
    alias(libs.plugins.hollyland.library.compose.plugin)
    alias(libs.plugins.hollyland.hilt.plugin)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    namespace = "com.sjy.aidlclient"

    buildFeatures {
        aidl = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)

}