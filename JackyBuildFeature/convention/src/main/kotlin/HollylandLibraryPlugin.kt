import com.hollyland.application.convention.configureLibraryFeature
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author Jacky
 *
 * @date 2023/5/15
 **/
class HollylandLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureLibraryFeature()
        }
    }
}