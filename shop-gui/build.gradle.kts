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
    implementation("pl.yshop.plugin:api:4.0.4")
    implementation("eu.okaeri:okaeri-configs-yaml-bukkit:5.0.5")
    implementation("eu.okaeri:okaeri-configs-serdes-bukkit:5.0.5")
    implementation("eu.okaeri:okaeri-placeholders-bukkit:5.1.1")

    implementation("net.kyori:adventure-text-serializer-plain:4.17.0")
    implementation("net.kyori:adventure-platform-bukkit:4.3.4")
    implementation("net.kyori:adventure-text-minimessage:4.17.0")
    compileOnly("com.squareup.okhttp3:okhttp:4.10.0")
    compileOnly("xyz.xenondevs.invui:invui:1.39")
    compileOnly("org.spigotmc:spigot-api:1.17-R0.1-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}