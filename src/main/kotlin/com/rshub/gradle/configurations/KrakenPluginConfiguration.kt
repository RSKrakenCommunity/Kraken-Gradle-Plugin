package com.rshub.gradle.configurations

import org.gradle.api.provider.Property
import org.gradle.api.provider.SetProperty

abstract class KrakenPluginConfiguration {

    abstract val krakenLocation: Property<String>
    abstract val krakenPluginLocation: Property<String>
    abstract val mainClass: Property<String>
    abstract val modules: SetProperty<String>

    init {
        krakenLocation.convention(System.getProperty("user.home"))
        krakenPluginLocation.convention(System.getProperty("user.home"))
        mainClass.convention("not set")
        modules.convention(mutableSetOf())
    }

    fun modules(vararg modules: String) {
        this.modules.addAll(*modules)
    }

}