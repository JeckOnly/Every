import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    `maven-publish`
    `version-catalog`
}

group = "com.sjy.JackyBuildFeature"


// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

configurations.all {
    resolutionStrategy.cacheChangingModulesFor(0, "seconds")
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}


gradlePlugin {
    plugins {
        // 注册Application模块的插件
        register("applicationFeature") {
            id = "hollyland.application.feature"
            implementationClass = "HollylandApplicationPlugin"
        }
        // 注册Library模块的插件
        register("libraryFeature") {
            id = "hollyland.library.feature"
            implementationClass = "HollylandLibraryPlugin"
        }
        // 注册Hilt模块的插件
        register("hiltFeature") {
            id = "hollyland.hilt.feature"
            implementationClass = "HollylandHiltPlugin"
        }
        // 注册Room模块的插件
        register("roomFeature") {
            id = "hollyland.room.feature"
            implementationClass = "HollylandRoomPlugin"
        }
        // 注册Application Compose模块的插件
        register("applicationComposeFeature") {
            id = "hollyland.application.compose.feature"
            implementationClass = "HollylandApplicationComposePlugin"
        }
        // 注册Library Compose模块的插件
        register("libraryComposeFeature") {
            id = "hollyland.library.compose.feature"
            implementationClass = "HollylandLibraryComposePlugin"
        }
    }
}


