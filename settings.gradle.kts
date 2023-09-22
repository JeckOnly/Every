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
