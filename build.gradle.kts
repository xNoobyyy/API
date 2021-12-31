import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.1.0"
    id("java")
    id("maven-publish")
    application
}

group = "com.github.SeinnaNetwork"
version = "master-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://oss.sonatype.org/content/repositories/central")
    maven("https://repo.cloudnetservice.eu/repository/releases/")
    maven("https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test"))
    compileOnly("org.spigotmc:spigot-api:1.18.1-R0.1-SNAPSHOT")
    implementation("de.dytanic.cloudnet:cloudnet-driver:3.4.1-RELEASE")
    implementation("de.dytanic.cloudnet:cloudnet-wrapper-jvm:3.4.1-RELEASE")
    implementation("de.dytanic.cloudnet:cloudnet-bridge:3.4.1-RELEASE")
    implementation("de.dytanic.cloudnet:cloudnet-cloudperms:3.4.1-RELEASE")
    implementation("de.dytanic.cloudnet:cloudnet-signs:3.4.1-RELEASE")
    implementation("de.dytanic.cloudnet:cloudnet-npcs:3.4.1-RELEASE")
    implementation("org.mongodb:mongo-java-driver:3.12.10")
    implementation("commons-io:commons-io:2.11.0")
    implementation("com.github.juliarn:npc-lib:development-SNAPSHOT")
    implementation("commons-io:commons-io:2.11.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("de.noob.seinna.api.SeinnaAPIKt")
}

tasks.named<ShadowJar>("shadowJar") {
    archiveBaseName.set("FarmingSimulator")
    archiveClassifier.set("")
    archiveVersion.set("")
}

tasks.test {
    useJUnitPlatform()
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}