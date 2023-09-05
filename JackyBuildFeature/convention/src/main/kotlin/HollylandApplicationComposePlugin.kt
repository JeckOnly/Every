import com.hollyland.application.convention.configureApplicationComposeFeature
import com.hollyland.application.convention.configureApplicationFeature
import org.gradle.api.Plugin
import org.gradle.api.Project


class HollylandApplicationComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureApplicationComposeFeature()
        }
    }
}