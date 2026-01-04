plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.apollo)
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
   // id("com.apollographql.apollo") version "4.0.1"
}

android {
    namespace = "com.launchapp.launches.network"
    compileSdk = 36

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
    apollo {
        service("service") {
            packageName.set("com.launchapp.launches.network")
            introspection {
                endpointUrl.set("https://apollo-fullstack-tutorial.herokuapp.com/graphql")
                schemaFile.set(file("src/main/graphql/service/schema.graphqls"))
            }
        }
    }
}
kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.apollo.runtime)
    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    // OkHttp Logging
    implementation("com.squareup.okhttp3:logging-interceptor:5.3.2")
    implementation("javax.inject:javax.inject:1")

}