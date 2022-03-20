package com.rshub.gradle

import com.rshub.gradle.configurations.KrakenPluginConfiguration
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.bundling.Jar
import org.gradle.tooling.provider.model.ToolingModelBuilderRegistry
import org.jetbrains.kotlin.gradle.internal.Kapt3GradleSubplugin
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
        target.pluginManager.apply(Kapt3GradleSubplugin::class.java)

        target.tasks.named("jar", Jar::class.java) {
            it.manifest { m ->
                m.attributes["Plugin-Class"] = config.pluginClass
                m.attributes["Plugin-Id"] = config.pluginId
                m.attributes["Plugin-Version"] = config.pluginVersion
                m.attributes["Plugin-Provider"] = config.pluginProvider
                m.attributes["Plugin-Description"] = config.pluginDescription
            }
        }
    }
}