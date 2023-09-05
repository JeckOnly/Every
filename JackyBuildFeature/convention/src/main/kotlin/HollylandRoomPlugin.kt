import com.google.devtools.ksp.gradle.KspExtension
import com.hollyland.application.convention.implementation
import com.hollyland.application.convention.ksp
import com.hollyland.application.convention.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.process.CommandLineArgumentProvider
import java.io.File

/**
 * @author jacky
 *
 * @date 2023-05-17
 */
class HollylandRoomPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")

            // 规避 可能出现的schemas文件夹不存在的问题
            val schemasFile = File("${projectDir.absolutePath}${File.separator}schemas${File.separator}")
            if (!schemasFile.exists()) {
                schemasFile.mkdirs()
            }

            extensions.configure<KspExtension> {
                arg(RoomSchemaArgProvider(File(projectDir, "schemas")))
            }
            val libs = versionCatalog()
            dependencies {
                implementation(libs, "room")
                implementation(libs, "room.ktx")
                implementation(libs, "room-rxjava3")
                ksp(libs, "room.compiler")
            }
        }
    }

    /**
     * Copy by nowinandroid
     */
    class RoomSchemaArgProvider(
            @get:InputDirectory @get:PathSensitive(PathSensitivity.RELATIVE) val schemaDir: File,
    ) : CommandLineArgumentProvider {
        override fun asArguments() =
            listOf("room.schemaLocation=${schemaDir.path}", "room.incremental=true")
    }
}