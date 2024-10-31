plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.screenshot)
}

apply(from = "../coverage/androidCoverageReport.gradle")

android {
    namespace = "com.denisbrandi.androidrealca.wishlist.ui"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.compiler.extension.get()
    }
    experimentalProperties["android.experimental.enableScreenshotTest"] = true
}

dependencies {
    implementation(project(":foundations"))
    implementation(project(":money-component"))
    implementation(project(":wishlist-component"))
    implementation(project(":cart-component"))
    implementation(project(":money-ui"))
    implementation(project(":viewmodel"))
    implementation(project(":designsystem"))
    implementation(libs.coroutines.core)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.androidx.runtime.android)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.core.ktx)

    implementation(libs.coil.compose)
    implementation(libs.coil.okhttp)

    testImplementation(libs.junit)
    testImplementation(libs.coroutines.test)
    testImplementation(project(":flow-test-observer"))
    testImplementation(project(":coroutines-test-dispatcher"))

    screenshotTestImplementation(libs.androidx.ui.tooling)
}
