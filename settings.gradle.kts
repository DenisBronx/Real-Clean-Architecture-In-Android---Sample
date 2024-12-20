pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Real Clean Architecture in Android"
include(":app")
include(":foundations")
include(":user-component")
include(":cache")
include(":httpclient")
include(":cache-test")
include(":onboarding-ui")
include(":flow-test-observer")
include(":viewmodel")
include(":coroutines-test-dispatcher")
include(":product-component")
include(":money-component")
include(":wishlist-component")
include(":cart-component")
include(":designsystem")
include(":plp-ui")
include(":wishlist-ui")
include(":cart-ui")
include(":main-ui")
include(":money-ui")
