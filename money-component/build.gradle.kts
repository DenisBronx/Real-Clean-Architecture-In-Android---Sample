plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
}

apply(from = "../coverage/kmpCoverageReport.gradle")

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
                implementation(libs.kotlin.serialization)
            }
        }
        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}
