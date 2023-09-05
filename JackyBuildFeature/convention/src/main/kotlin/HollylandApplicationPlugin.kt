import com.hollyland.application.convention.configureApplicationFeature
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author Jacky
 *
 * @date 2023/5/15
 **/
class HollylandApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureApplicationFeature()
        }
    }
}