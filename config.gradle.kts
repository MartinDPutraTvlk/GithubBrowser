mapOf(
    "gradle" to "7.0.4",
    "kotlinGradlePlugin" to "1.5.30",
    "kotlin" to "1.5.30",
    "core_ktx" to "1.7.0",
    "app_compat_version" to "1.4.1",
    "jodaTime" to "2.10.2",
    "picasso" to "2.71828",
    "rxJava" to "2.2.9",
    "rxAndroid" to "2.1.1",
    "dagger" to "2.35.1",
    "retrofit" to "2.9.0",
    "retrofitAdapterRxJava" to "2.6.1",
    "retrofitConverterGson" to "2.7.2",
    "okhttp3LoggingInterceptor" to "4.9.0",
    "room" to "2.2.5",
    "chucker" to "3.5.0",
    "glide" to "4.12.0",
    "androidSvg" to "1.4",
    "swipeRefresh" to "1.1.0"
).forEach { (name, version) ->
    project.extra.set(name, version)
}