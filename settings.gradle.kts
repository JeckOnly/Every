import java.net.URI

pluginManagement {
    includeBuild("JackyBuildFeature")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = URI("https://jitpack.io") }
        maven { url = URI("https://maven.aliyun.com/repository/public") }
    }
}

rootProject.name = "Every"
include(":app")
include(":feature:widget")
include(":core:design")
include(":feature:accessibility")
include(":core:util")
include(":core:httpsdk")
include(":feature:catfact")
include(":feature:udp")
include(":feature:viewentry")
