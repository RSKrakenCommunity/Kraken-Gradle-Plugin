package com.rshub.gradle

import com.rshub.gradle.configurations.CommunityModules
import com.rshub.gradle.configurations.KrakenPluginConfiguration
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.DuplicatesStrategy
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.bundling.Jar
import org.gradle.tooling.provider.model.ToolingModelBuilderRegistry
import java.io.File
import javax.inject.Inject

class KrakenGradlePlugin @Inject internal constructor(private val registry: ToolingModelBuilderRegistry) :
    Plugin<Project> {
    override fun apply(target: Project) {
        val config = target.extensions.create("kraken", KrakenPluginConfiguration::class.java)
        with(target.repositories) {
            maven {
                it.setUrl("https://jitpack.io")
            }
        }
        with(target.dependencies) {
            for (value in config.modules.get()) {
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
        }

        target.tasks.named("Jar", Jar::class.java) {
            it.duplicatesStrategy = DuplicatesStrategy.EXCLUDE
            target.configurations.named("runtimeClasspath").get().map { t -> if(t.isDirectory) t else target.zipTree(t) }
            it.finalizedBy(target.tasks.named("copy"))
        }

        target.tasks.named("copy", Copy::class.java) {
            val file = File(config.krakenPluginLocation.get())
            if(file.exists()) {
                it.from(target.tasks.named("jar"))
                it.into(file.absolutePath)
            }
        }


        target.tasks.create("createPluginConfiguration") {
            it.doFirst {
                val file = target.projectDir.resolve("src/main/resources")
                if (!file.exists()) {
                    file.mkdirs()
                }
                val pluginConfig = file.resolve("plugin.ini")
                if (!pluginConfig.exists()) {
                    pluginConfig.createNewFile()
                }
                pluginConfig.writeText(config.mainClass.get())
            }
        }
        target.tasks.getByName("build") {
            it.dependsOn(target.tasks.named("createPluginConfiguration"))
        }
    }
}