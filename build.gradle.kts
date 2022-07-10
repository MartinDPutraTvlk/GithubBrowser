buildscript {
    repositories {
        google()
        mavenCentral()
    }

    apply(from = "config.gradle.kts")
    val gradle: String by extra
    val kotlinGradlePlugin: String by rootProject.extra

    dependencies {
        classpath("com.android.tools.build:gradle:$gradle")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register(name = "type", type = Delete::class) {
    delete(rootProject.buildDir)
}