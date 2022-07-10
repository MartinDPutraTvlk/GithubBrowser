plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.martinezdputra.githubbrowser"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

apply(from = "../config.gradle.kts")
val kotlin: String by extra
val jodaTime: String by extra
val picasso: String by extra
val rxJava: String by extra
val rxAndroid: String by extra
val dagger: String by extra
val retrofit: String by extra
val retrofitAdapterRxJava: String by extra
val retrofitConverterGson: String by extra
val okhttp3LoggingInterceptor: String by extra
val room: String by extra
val chucker: String by extra
val glide: String by extra
val androidSvg: String by extra
val swipeRefresh: String by extra

dependencies {
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin")

    /** Joda Time **/
    implementation ("joda-time:joda-time:$jodaTime")

    /** Picasso **/
    implementation ("com.squareup.picasso:picasso:$picasso")

    /** RxJava **/
    implementation ("io.reactivex.rxjava2:rxjava:$rxJava")
    implementation ("io.reactivex.rxjava2:rxandroid:$rxAndroid")

    /** Dagger **/
    implementation ("com.google.dagger:dagger-android:$dagger")
    implementation ("com.google.dagger:dagger-android-support:$dagger")
    implementation ("com.google.dagger:dagger:$dagger")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.4.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.4.2")
    annotationProcessor ("com.google.dagger:dagger-android-processor:$dagger")
    kapt ("com.google.dagger:dagger-android-processor:$dagger")
    kapt ("com.google.dagger:dagger-compiler:$dagger")

    /** Retrofit **/
    implementation ("com.squareup.retrofit2:retrofit:$retrofit")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:$retrofitAdapterRxJava")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofitConverterGson")
    implementation ("com.squareup.okhttp3:logging-interceptor:$okhttp3LoggingInterceptor")

    /** Room **/
    implementation ("androidx.room:room-runtime:$room")
    implementation ("androidx.room:room-ktx:$room")
    implementation ("androidx.room:room-rxjava2:$room")
    implementation ("androidx.room:room-guava:$room")
    kapt ("androidx.room:room-compiler:$room")

    /** Chucker **/
    debugImplementation("com.github.chuckerteam.chucker:library:$chucker")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:$chucker")

    /** GLIDE **/
    implementation("com.github.bumptech.glide:glide:$glide")
    kapt("com.github.bumptech.glide:compiler:$glide")

    /** ANDROID SVG **/
    implementation("com.caverock:androidsvg-aar:$androidSvg")

    implementation("androidx.swiperefreshlayout:swiperefreshlayout:$swipeRefresh")

    implementation ("androidx.core:core-ktx:1.7.0")
    implementation ("androidx.appcompat:appcompat:1.4.1")
    implementation ("com.google.android.material:material:1.5.0")
    testImplementation ("junit:junit:4.+")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
}