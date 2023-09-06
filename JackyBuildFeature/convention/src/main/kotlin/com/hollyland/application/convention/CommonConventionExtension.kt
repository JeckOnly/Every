@file:Suppress("UnstableApiUsage")

package com.hollyland.application.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * @author Jacky
 *
 * Created on 2023/4/4
 **/
private const val COMPILE_SDK = "compileSdk"
private const val MIN_SDK = "minSdk"
private const val TARGET_SDK = "targetSdk"

private const val JB_KT_ANDROID = "org.jetbrains.kotlin.android"

/**
 * Configure the Android common feature.
 */
internal fun <T : CommonExtension<*, *, *, *>> Project.configureCommonFeature(commonExtension: T, configBlock: T.(VersionCatalog) -> Unit = {}) {
    pluginManager.apply("kotlin-parcelize")
    pluginManager.apply(JB_KT_ANDROID)
    configureKotlinAndroidToolchain()
    val libs = versionCatalog()
    commonExtension.apply {
        compileSdk = libs.findVersionInt(COMPILE_SDK)
        defaultConfig {
            minSdk = libs.findVersionInt(MIN_SDK)
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_11.toString()
            }
        }

        dependencies {
            implementation(libs, "core.ktx")
            implementation(libs, "appcompat")
            implementation(libs, "material")
            implementation(libs, "constraintlayout")
            implementation(libs, "coroutines.android")
            implementation(libs, "coroutines.core")
            testImplementation(libs, "junit")
            androidTestImplementation(libs, "androidx.test.ext.junit")
            androidTestImplementation(libs, "espresso.core")
        }

        configBlock(libs)

        configurations.all {
            resolutionStrategy.cacheChangingModulesFor(0, "seconds")
        }
    }
}

/**
 * Configure the Android application feature.
 */
internal fun Project.configureApplicationFeature() {
    pluginManager.apply("com.android.application")
    extensions.configure<ApplicationExtension> {
        configureCommonFeature(this) {
            buildFeatures {
                viewBinding = true
            }
            defaultConfig.targetSdk = it.findVersionInt(TARGET_SDK)
        }
    }
}

/**
 * Configure the Android library feature.
 */
internal fun Project.configureLibraryFeature() {
    pluginManager.apply("com.android.library")
    extensions.configure<LibraryExtension> {
        configureCommonFeature(this) {
            defaultConfig.targetSdk = it.findVersionInt(TARGET_SDK)
        }
    }
}

/**
 * Configure the Android Application Compose feature.
 */
internal fun Project.configureApplicationComposeFeature() {
    pluginManager.apply("com.android.application")
    extensions.configure<ApplicationExtension> {
        configureCommonFeature(this) {
            buildFeatures {
                viewBinding = true
            }
            defaultConfig.targetSdk = it.findVersionInt(TARGET_SDK)
        }
        configureAndroidCompose(this)
    }
}


/**
 * Configure the Android library Compose feature.
 */
internal fun Project.configureLibraryComposeFeature() {
    pluginManager.apply("com.android.library")
    extensions.configure<LibraryExtension> {
        configureCommonFeature(this) {
            defaultConfig.targetSdk = it.findVersionInt(TARGET_SDK)
        }
        configureAndroidCompose(this)
    }
}


/**
 * Configure Kotlin's jvm toolchain for Android projects
 */
internal fun Project.configureKotlinAndroidToolchain() {
    extensions.configure<KotlinAndroidProjectExtension> {
        jvmToolchain(11)
    }
}

/**
 * Extension function to find the version of a library in a [VersionCatalog].
 */
fun VersionCatalog.findVersionInt(name: String): Int {
    return findVersion(name).get().toString().toInt()
}

/**
 * Kotlin DSL extension to configure the Kotlin compiler options.
 */
fun CommonExtension<*, *, *, *>.kotlinOptions(action: KotlinJvmOptions.() -> Unit) {
    (this as? ExtensionAware)?.extensions?.configure("kotlinOptions", action)
}

/**
 * Get the version catalog from the project.
 */
fun Project.versionCatalog(): VersionCatalog {
    return extensions.getByType<VersionCatalogsExtension>().named("libs")
}

// Group the dependencies by their type
internal fun DependencyHandlerScope.implementation(vc: VersionCatalog, alias: String) = "implementation".invoke(vc.findLibrary(alias).get())
internal fun DependencyHandlerScope.kapt(vc: VersionCatalog, alias: String) = "kapt".invoke(vc.findLibrary(alias).get())
internal fun DependencyHandlerScope.kaptAndroidTest(vc: VersionCatalog, alias: String) = "kaptAndroidTest".invoke(vc.findLibrary(alias).get())
internal fun DependencyHandlerScope.testImplementation(vc: VersionCatalog, alias: String) = "testImplementation".invoke(vc.findLibrary(alias).get())
internal fun DependencyHandlerScope.androidTestImplementation(vc: VersionCatalog, alias: String) = "androidTestImplementation".invoke(vc.findLibrary(alias).get())
internal fun DependencyHandlerScope.ksp(vc: VersionCatalog, alias: String) = "ksp".invoke(vc.findLibrary(alias).get())
internal fun DependencyHandlerScope.debugImplementation(vc: VersionCatalog, alias: String) = "debugImplementation".invoke(vc.findLibrary(alias).get())
