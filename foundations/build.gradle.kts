plugins {
    alias(libs.plugins.kotlin.multiplatform)
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
            dependencies {}
        }
        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}
