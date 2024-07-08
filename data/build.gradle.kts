plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.android.ksp)
}

android {
    namespace = "com.caioluis.receitas.data"
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

    implementation(projects.domain)

    implementation(libs.room.runtime)
    implementation(libs.gson)
    implementation(libs.rxjava)
    implementation(libs.dagger)
    implementation(libs.hilt.android)
    implementation(libs.retrofit)
    implementation(libs.room.rxjava2)
    implementation(libs.converter.gson)
    implementation(libs.adapter.rxjava2)

    androidTestImplementation(libs.test.junit)
    androidTestImplementation(libs.test.ext.junit)

    testImplementation(libs.mockk)
    testImplementation(libs.assertj.core)
    testImplementation(libs.test.core.testing)
    testImplementation(libs.test.core.ktx)
    testImplementation(libs.test.core)
    testImplementation(libs.test.runner)

    ksp(libs.room.compiler)
    ksp(libs.dagger.compiler)
    ksp(libs.hilt.android.compiler)
}