object CoreDependencies {
    const val ktxCore = "androidx.core:core-ktx:${KotlinCoreVersions.ktxCoreVersion}"
    const val kotlinStdLib =
        "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${KotlinCoreVersions.kotlinVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${KotlinCoreVersions.appCompatVersion}"
    const val gradleAndroid = "com.android.tools.build:gradle:${BuildVersions.androidGradle}"
    const val gradleKotlin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${KotlinCoreVersions.kotlinVersion}"
}

object TestDependencies {
    const val androidxJUnit = "androidx.test.ext:junit:${TestVersions.androidJunit}"
    const val junit = "junit:junit:${TestVersions.junit}"
    const val assertJ = "org.assertj:assertj-core:${TestVersions.assertJ}"
    const val androidTestCore = "androidx.test:core:${TestVersions.androidTestArchCore}"
    const val androidCoreKtx = "androidx.test:core-ktx:${TestVersions.androidTestArchCore}"
    const val androidTestRunner = "androidx.test:runner:${TestVersions.androidTextRunner}"
    const val androidTestRules = "androidx.test:rules:${TestVersions.androidTextRules}"
    const val espresso = "androidx.test.espresso:espresso-core:${TestVersions.espresso}"
    const val coreTesting = "androidx.arch.core:core-testing:${TestVersions.coreTesting}"

    const val mockitoAndroid = "org.mockito:mockito-android:${TestVersions.mockito}"
    const val mockitoCore = "org.mockito:mockito-core:${TestVersions.mockito}"
    const val mockitoInline = "org.mockito:mockito-inline:${TestVersions.mockito}"
    const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${TestVersions.mockitoKotlin}"
}

object Dependencies {
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${DesignVersions.constraintLayoutVersion}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val materialDesign =
        "com.google.android.material:material:${DesignVersions.materialDesign}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${DesignVersions.recyclerView}"
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"

    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomRxJava2 = "androidx.room:room-rxjava2:${Versions.room}"

    const val composeUi = "androidx.compose.ui:ui:${DesignVersions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${DesignVersions.compose}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${DesignVersions.compose}"
    const val composeActivity =
        "androidx.activity:activity-compose:${DesignVersions.activityCompose}"

    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
}
