plugins {
    alias(libs.plugins.hollyland.library.compose.plugin)
    alias(libs.plugins.hollyland.hilt.plugin)
}

android {
    namespace = "com.sjy.catfact"
}

dependencies {
    implementation(project(":core:httpsdk"))
}