import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.50"
    `maven-publish`
    id("com.jfrog.bintray") version  "1.8.4"
}

project.group = "com.github.vittee"
project.version = "1.0"

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7")

    testImplementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.6"
}

val javaDocJar by tasks.creating(Jar::class) {
    archiveClassifier.set("javadoc")
}

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

artifacts {
    add("archives", sourcesJar)
    add("archives", javaDocJar)
}

publishing {
    publications {
        create<MavenPublication>("default") {
            from(components["java"])

            groupId = "com.github.vittee"
            artifactId = "kformula"
        }
    }
}

val bintrayUsername = (properties["bintrayUsername"] as String?) ?: System.getenv("BINTRAY_USER")
val bintrayApiKey = (properties["bintrayApiKey"] as String?) ?: System.getenv("BINTRAY_APIKEY")

if (bintrayUsername != null && bintrayApiKey != null) {
    bintray {
        user = bintrayUsername
        key = bintrayApiKey

        pkg.apply {
            repo = "kformula"
            name = "kformula"

            websiteUrl = "https://github.com/vittee/kformula"
            vcsUrl = "https://github.com/vittee/kformula.git"
            issueTrackerUrl = "https://github.com/vittee/kformula/issues"

            setLicenses("MIT")
            setPublications("default")
        }
    }
}