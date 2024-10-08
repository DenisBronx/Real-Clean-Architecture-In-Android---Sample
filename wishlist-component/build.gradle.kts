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
                implementation(libs.coroutines.core)
                implementation(project(":money-component"))
                implementation(project(":user-component"))
            }
        }
        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.coroutines.test)
                implementation(project(":flow-test-observer"))
            }
        }
    }
}
