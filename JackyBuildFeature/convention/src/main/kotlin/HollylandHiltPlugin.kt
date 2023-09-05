import com.hollyland.application.convention.implementation
import com.hollyland.application.convention.kapt
import com.hollyland.application.convention.kaptAndroidTest
import com.hollyland.application.convention.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * @author Jacky
 *
 * Created on 2023/4/4
 **/
class HollylandHiltPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("dagger.hilt.android.plugin")
                apply("kotlin-kapt")
            }
            val libs = versionCatalog()
            dependencies {
                implementation(libs, "hilt.android")
                kapt(libs, "hilt.compiler")
                kaptAndroidTest(libs, "hilt.compiler")
            }
        }
    }
}