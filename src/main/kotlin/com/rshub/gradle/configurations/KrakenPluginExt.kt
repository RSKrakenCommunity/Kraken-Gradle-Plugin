package com.rshub.gradle.configurations

import org.gradle.api.Project
import org.gradle.api.provider.Property

abstract class KrakenPluginExt(val project: Project) {

    abstract val pluginClass: Property<String>
    abstract val krakenPluginLocation: Property<String>
    abstract val configuration: Property<String>

    init {
        pluginClass.convention("none")
        krakenPluginLocation.convention(System.getProperty("user.home"))
        configuration.convention("implementation")
    }

    fun modules(vararg modules: String) {
        with(project.dependencies) {
            for (value in modules) {
                when (value) {
                    CommunityModules.KRAKEN.moduleName -> {
                        println("Adding Kraken Module!")
                        add(
                            configuration.get(),
                            "com.github.RSKraken:KrakenAPI:master-SNAPSHOT"
                        )
                    }
                    CommunityModules.API.moduleName -> {
                        add(
                            configuration.get(),
                            "com.github.RSKrakenCommunity.CommunityAPI:KrakenCommunityAPI:master-SNAPSHOT"
                        )
                    }
                    CommunityModules.IMGUI.moduleName -> {
                        add(
                            configuration.get(),
                            "com.github.RSKrakenCommunity.CommunityAPI:KrakenCommunityImGui:master-SNAPSHOT"
                        )
                    }
                    CommunityModules.DEFINITIONS.moduleName -> {
                        add(
                            configuration.get(),
                            "com.github.RSKrakenCommunity.CommunityAPI:KrakenCommunityDefinitions:master-SNAPSHOT"
                        )
                    }
                    CommunityModules.FILESYSTEM.moduleName -> {
                        add(
                            configuration.get(),
                            "com.github.RSKrakenCommunity.CommunityAPI:KrakenCommunityFilesystem:master-SNAPSHOT"
                        )
                    }
                    CommunityModules.UTILITIES.moduleName -> {
                        add(
                            configuration.get(),
                            "com.github.RSKrakenCommunity.CommunityAPI:KrakenCommunityUtilities:master-SNAPSHOT"
                        )
                    }
                }
            }
        }
    }
}