import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
  `java-library`
  id("xyz.jpenilla.run-paper") version "1.0.6"
  id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
  id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "com.kalimero2.team"
version = "1.0.0"
description = "Plugin Template"

repositories {
  mavenCentral()
  maven("https://papermc.io/repo/repository/maven-public/")
  maven("https://repo.opencollab.dev/maven-snapshots/")
}

dependencies {
  compileOnly("io.papermc.paper","paper-api","1.19.2-R0.1-SNAPSHOT")
  implementation("cloud.commandframework", "cloud-paper", "1.7.1")
  compileOnly("org.geysermc.floodgate", "api", "2.2.0-SNAPSHOT")
  implementation("com.fasterxml.jackson.core", "jackson-databind", "2.2.3")
}

java {
  toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

bukkit {
  main = "com.kalimero2.team.template.BedrockSkinGrabberPlugin"
  apiVersion = "1.19"
  load = BukkitPluginDescription.PluginLoadOrder.STARTUP
  authors = listOf("Schwerthecht")
  softDepend = listOf("floodgate")
}

tasks {
  shadowJar {
    fun reloc(pkg: String) = relocate(pkg, "com.kalimero2.team.template.dependency.$pkg")

    reloc("cloud.commandframework")
    reloc("io.leangen.geantyref")
  }

  runServer{
    minecraftVersion.set("1.19.2")
  }

}
