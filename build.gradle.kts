// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    `java-gradle-plugin`
    alias(libs.plugins.android.application) apply (false)
    alias(libs.plugins.android.library) apply (false)
    alias(libs.plugins.android.parcelize) apply (false)
    alias(libs.plugins.android.kotlin) apply (false)
    alias(libs.plugins.hilt.plugin) apply false
    alias(libs.plugins.android.ksp)
}

dependencies {
    compileOnly(libs.hilt.gradle.plugin)
}
