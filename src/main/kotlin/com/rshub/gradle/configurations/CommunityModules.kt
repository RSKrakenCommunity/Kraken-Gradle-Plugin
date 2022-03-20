package com.rshub.gradle.configurations

enum class CommunityModules(val moduleName: String, vararg val deps: CommunityModules) {

    UTILITIES("utilities"),
    KRAKEN("kraken-api"),
    FILESYSTEM("filesystem", UTILITIES),
    DEFINITIONS("definitions", FILESYSTEM),
    IMGUI("imgui", KRAKEN),
    API("community-api", KRAKEN, FILESYSTEM, DEFINITIONS, UTILITIES),


}