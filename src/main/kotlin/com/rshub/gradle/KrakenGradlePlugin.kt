package com.rshub.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class KrakenGradlePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.extensions.create("kraken", PluginConfig::class.java)
        /*with(target.repositories) {
            maven {
                it.setUrl("https://jitpack.io")
            }
        }
        with(target.dependencies) {
            for (value in config.krakenModules.get()) {
                when (value) {
                    CommunityModules.KRAKEN.moduleName -> {
                        add(
                            "implementation",
                            "com.github.RSKraken:KrakenAPI:master-SNAPSHOT"
                        )
                    }
                    CommunityModules.API.moduleName -> {
                        add(
                            "implementation",
                            "com.github.RSKrakenCommunity.CommunityAPI:KrakenCommunityAPI:master-SNAPSHOT"
                        )
                    }
                    CommunityModules.IMGUI.moduleName -> {
                        add(
                            "implementation",
                            "com.github.RSKrakenCommunity.CommunityAPI:KrakenCommunityImGui:master-SNAPSHOT"
                        )
                    }
                    CommunityModules.DEFINITIONS.moduleName -> {
                        add(
                            "implementation",
                            "com.github.RSKrakenCommunity.CommunityAPI:KrakenCommunityDefinitions:master-SNAPSHOT"
                        )
                    }
                    CommunityModules.FILESYSTEM.moduleName -> {
                        add(
                            "implementation",
                            "com.github.RSKrakenCommunity.CommunityAPI:KrakenCommunityFilesystem:master-SNAPSHOT"
                        )
                    }
                    CommunityModules.UTILITIES.moduleName -> {
                        add(
                            "implementation",
                            "com.github.RSKrakenCommunity.CommunityAPI:KrakenCommunityUtilities:master-SNAPSHOT"
                        )
                    }
                }
            }
        }*/

        /*target.tasks.withType(Jar::class.java) {
            it.dependsOn(target.tasks.named("createPluginConfiguration"))
            it.duplicatesStrategy = DuplicatesStrategy.EXCLUDE
            target.configurations.named("runtimeClasspath").get()
                .map { t -> if (t.isDirectory) t else target.zipTree(t) }
            it.finalizedBy(target.tasks.named("copy"))
        }
        target.tasks.register("copy", Copy::class.java) {
            val file = File(config.krakenPluginLocation.get())
            if (file.exists()) {
                it.from(target.tasks.withType(Jar::class.java))
                it.into(file.absolutePath)
            }
        }
        target.tasks.register("createPluginConfiguration") {
            val file = target.projectDir.resolve("src/main/resources")
            if (!file.exists()) {
                file.mkdirs()
            }
            val pluginConfig = file.resolve("plugin.ini")
            if (!pluginConfig.exists()) {
                pluginConfig.createNewFile()
            }
            pluginConfig.writeText(config.pluginClass.get())
        }*/
    }
}