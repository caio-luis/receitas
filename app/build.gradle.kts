plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.android.kapt)
}

android {
    namespace = "com.caioluis.receitas"
    compileSdk = 34


    defaultConfig {
        minSdk = 24
        applicationId = "com.caioluis.receitas"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            excludes.add("/META-INF/LICENSE")
            excludes.add("/META-INF/LICENSE.md")
            excludes.add("/META-INF/NOTICE")
            excludes.add("META-INF/LICENSE-notice.md")
            excludes.add("META-INF/README.md")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(projects.data)
    implementation(projects.domain)
    implementation(projects.notification.bridge)
    implementation(projects.notification.di)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.material)

    implementation(libs.rxandroid)
    implementation(libs.rxkotlin)
    implementation(libs.rxjava)
    implementation(libs.gson)
    implementation(libs.mockk)
    implementation(libs.dagger)
    implementation(libs.dagger.android)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    kapt(libs.room.compiler)
    kapt(libs.dagger.compiler)
    kapt(libs.dagger.android.processor)

    testImplementation(libs.test.junit)
    testImplementation(libs.test.core)

    androidTestImplementation(libs.test.junit)
    androidTestImplementation(libs.test.runner)
    androidTestImplementation(libs.androidx.rules)
    androidTestImplementation(libs.test.core)
    androidTestImplementation(libs.test.core.ktx)
    androidTestImplementation(libs.test.core.testing)
}
