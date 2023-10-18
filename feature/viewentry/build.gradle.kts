plugins {
    alias(libs.plugins.hollyland.library.compose.plugin)
    alias(libs.plugins.hollyland.hilt.plugin)
}

android {
    namespace = "com.sjy.viewentry"

}

dependencies {

    // GSYVideoPlayer
    implementation("com.github.CarGuo.GSYVideoPlayer:gsyVideoPlayer-java:v8.4.0-release-jitpack")

    //是否需要ExoPlayer模式
    implementation("com.github.CarGuo.GSYVideoPlayer:GSYVideoPlayer-exo2:v8.4.0-release-jitpack")

    //更多ijk的编码支持
    implementation("com.github.CarGuo.GSYVideoPlayer:gsyVideoPlayer-ex_so:v8.4.0-release-jitpack")
}