plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kover)
}

kotlin {
    jvmToolchain(17)
    jvm()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    sourceSets {
        commonMain {
            dependencies {
                api(libs.ktor)
                api(libs.ktor.cio)
                api(libs.ktor.serialization)
                api(libs.ktor.content.negotiation)
            }
        }
    }
}
