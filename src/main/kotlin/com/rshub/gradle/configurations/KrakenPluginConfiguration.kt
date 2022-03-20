package com.rshub.gradle.configurations

import org.gradle.api.provider.Property
import org.gradle.api.provider.SetProperty

abstract class KrakenPluginConfiguration {

    abstract val pluginId: Property<String>
    abstract val pluginClass: Property<String>
    abstract val pluginProvider: Property<String>
    abstract val pluginDescription: Property<String>
    abstract val pluginVersion: Property<String>

    abstract val modules: SetProperty<String>

    init {
        pluginId.convention("kraken-plugin")
        pluginClass.convention("null")
        pluginProvider.convention("Kraken Community")
        pluginDescription.convention("A Kraken Plugin")
        pluginVersion.convention("1.0")
        modules.convention(mutableSetOf())
    }

    fun modules(vararg modules: String) {
        fun flattenDeps(modules: CommunityModules, list: MutableSet<CommunityModules>): List<CommunityModules> {
            list.add(modules)
            for (dep in modules.deps) {
                flattenDeps(dep, list)
            }
            return list.toList()
        }

        val list = mutableSetOf<CommunityModules>()
        for (value in CommunityModules.values()) {
            if (value.moduleName in modules) {
                flattenDeps(value, list)
            }
        }
        this.modules.set(list.map { it.moduleName })
    }

}