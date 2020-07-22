object GradlePluginVersion {
    const val ANDROID_GRADLE = "4.0.1"
    const val GRADLE_VERSION_PLUGIN = "0.22.0"
    const val KOTLIN = CoreVersion.KOTLIN
    const val SAFE_ARGS = CoreVersion.NAVIGATION
}


object GradlePlugins {
    const val KOTLIN_ANDROID_EXTENSIONS =
        "org.jetbrains.kotlin.android.extensions:${GradlePluginVersion.KOTLIN}"
    const val KOTLIN_ANDROID =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${GradlePluginVersion.KOTLIN}"
    const val ANDROID_GRADLE =
        "com.android.tools.build:gradle:${GradlePluginVersion.ANDROID_GRADLE}"
    const val SAFE_ARGS =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${GradlePluginVersion.SAFE_ARGS}"
}
