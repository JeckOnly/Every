plugins {
    alias(libs.plugins.hollyland.library.plugin)
    alias(libs.plugins.hollyland.hilt.plugin)
    alias(libs.plugins.hollyland.retrofit.plugin)
}

android {
    namespace = "com.sjy.httpsdk"
}

dependencies {
    api(libs.sandwich)
}