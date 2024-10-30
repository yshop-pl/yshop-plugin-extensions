plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "pl.yshop.plugin.extension.shopgui"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repository.ycode.pl/releases")
    maven("https://repo.xenondevs.xyz/releases")
    maven("https://storehouse.okaeri.eu/repository/maven-public/")
}

dependencies {
    implementation("pl.yshop.plugin:api:4.0.3")
    implementation("eu.okaeri:okaeri-configs-yaml-bukkit:5.0.5")
    implementation("eu.okaeri:okaeri-configs-serdes-bukkit:5.0.5")
    compileOnly("com.squareup.okhttp3:okhttp:4.10.0")
    compileOnly("xyz.xenondevs.invui:invui:1.39")
    compileOnly("org.spigotmc:spigot-api:1.17-R0.1-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}