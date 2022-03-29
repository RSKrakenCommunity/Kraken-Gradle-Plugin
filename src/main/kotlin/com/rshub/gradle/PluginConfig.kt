package com.rshub.gradle

import org.gradle.api.provider.Property
import org.gradle.api.provider.SetProperty

abstract class PluginConfig {

    abstract val pluginClass: Property<String>
    abstract val modules: SetProperty<String>

}