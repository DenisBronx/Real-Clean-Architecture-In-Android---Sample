plugins {
    kotlin("multiplatform") version libs.versions.kotlin
    alias(libs.plugins.kover)
}

kotlin {
    jvm {}
    sourceSets {
        commonMain {
            dependencies {
            }
        }
        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}