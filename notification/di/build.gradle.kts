plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.android.ksp)
}

android {
    namespace = "com.caioluis.receitas.notification.di"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
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
}


dependencies {
    implementation(projects.notification.bridge)
    implementation(projects.notification.impl)

    implementation(libs.dagger)
    implementation(libs.hilt.android)

    ksp(libs.dagger.compiler)
    ksp(libs.hilt.android.compiler)
}