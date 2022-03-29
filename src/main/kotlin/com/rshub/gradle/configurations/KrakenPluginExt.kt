package com.rshub.gradle.configurations

import org.gradle.api.Project
import org.gradle.api.provider.Property

abstract class KrakenPluginExt(val project: Project) {

    abstract val pluginClass: Property<String>
    abstract val krakenPluginLocation: Property<String>
    abstract val configuration: Property<String>
    abstract val krakenStubName: Property<String>

    internal val modules = mutableSetOf<String>()
    internal var isSDNPlugin = false

    init {
        pluginClass.convention("none")
        krakenPluginLocation.convention(System.getProperty("user.home"))
        configuration.convention("implementation")
        krakenStubName.convention("KrakenStub-1.0-SNAPSHOT")
    }

    fun setSDNPlugin(value: Boolean) {
        isSDNPlugin = value
        if (value) {
            with(project.dependencies) {
                add("implementation", project.files("${krakenPluginLocation.get()}/${krakenStubName.get()}.jar"))
            }
        }
    }

    fun modules(vararg modules: String) {
        this.modules.addAll(modules)
        with(project.dependencies) {
            for (value in modules) {
                when (value) {
                    CommunityModules.KRAKEN.moduleName -> {
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