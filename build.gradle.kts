// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kover)
}

dependencies {
    kover(project(":cache"))
    kover(project(":cart-component"))
    kover(project(":cart-ui"))
    kover(project(":foundations"))
    kover(project(":httpclient"))
    kover(project(":main-ui"))
    kover(project(":money-component"))
    kover(project(":money-ui"))
    kover(project(":onboarding-ui"))
    kover(project(":plp-ui"))
    kover(project(":product-component"))
    kover(project(":user-component"))
    kover(project(":wishlist-component"))
    kover(project(":wishlist-ui"))
}

kover {
    reports {
        filters {
            excludes {
                // exclusion rules - classes to exclude from report
                classes(
                    "**.di.**",
                    "**.navigation.**",
                    "**.view.**",
                    "**.data.model.**",
                )
            }
        }
    }
}

subprojects {
    plugins.withId("org.jetbrains.kotlin.jvm") {
        apply(plugin = "org.jetbrains.kotlinx.kover")
    }

    plugins.withId("org.jetbrains.kotlin.multiplatform") {
        apply(plugin = "org.jetbrains.kotlinx.kover")
    }

    plugins.withId("com.android.application") {
        apply(plugin = "org.jetbrains.kotlinx.kover")
    }

    plugins.withId("com.android.library") {
        apply(plugin = "org.jetbrains.kotlinx.kover")
    }
}
