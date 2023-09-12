import com.hollyland.application.convention.implementation
import com.hollyland.application.convention.kapt
import com.hollyland.application.convention.kaptAndroidTest
import com.hollyland.application.convention.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * @author sjy
 *
 * Created on 2023/9/12
 **/
class HollylandRetrofitPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = versionCatalog()
            dependencies {
                implementation(libs, "okhttp")
                implementation(libs, "okhttp.logging.interceptor")
                implementation(libs, "retrofit")
                implementation(libs, "retrofit.gson")
            }
        }
    }
}