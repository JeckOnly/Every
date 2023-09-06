plugins {
    alias(libs.plugins.hollyland.library.compose.plugin)
    alias(libs.plugins.hollyland.hilt.plugin)
}

android {
    namespace = "com.sjy.widget"
}

dependencies {
    implementation(project(":core:design"))
    implementation(libs.glance.appwidget)
    implementation(libs.glance.material3)
}