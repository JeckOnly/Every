import com.hollyland.application.convention.configureLibraryComposeFeature
import com.hollyland.application.convention.configureLibraryFeature
import org.gradle.api.Plugin
import org.gradle.api.Project


class HollylandLibraryComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureLibraryComposeFeature()
        }
    }
}