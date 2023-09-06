plugins {
    alias(libs.plugins.hollyland.library.compose.plugin)
    alias(libs.plugins.hollyland.hilt.plugin)
}

android {
    namespace = "com.sjy.design"
}

dependencies {
    implementation(libs.glance.appwidget)
    implementation(libs.glance.material3)
}