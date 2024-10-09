plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kover)
    alias(libs.plugins.kotlin.serialization)
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
                implementation(project(":foundations"))
                implementation(project(":money-component"))
                implementation(project(":httpclient"))
                implementation(libs.ktor)
                implementation(libs.ktor.serialization)
                implementation(libs.ktor.content.negotiation)
            }
        }
        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.coroutines.test)
                implementation(libs.netmock)
            }
        }
    }
}
