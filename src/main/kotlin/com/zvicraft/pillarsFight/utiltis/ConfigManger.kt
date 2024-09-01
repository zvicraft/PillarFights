package com.zvicraft.pillarsFight.utiltis

import org.bukkit.Material
import org.bukkit.configuration.file.FileConfiguration
import com.zvicraft.pillarsFight.PillarsFight

class ConfigManager(private val plugin: PillarsFight) {
    var pillarBlock: Material = Material.BEACON
    var spawnRadius: Int = 100
    var pillarHeight: Int = 5  // Added type Int for consistency

    init {
        loadConfigValues()
    }

    private fun loadConfigValues() {
        val config: FileConfiguration = plugin.config
        val blockTypeName = config.getString("pillars.pillarBlock", "BEACON")
        pillarBlock = Material.getMaterial(blockTypeName ?: "BEACON") ?: Material.BEACON
        spawnRadius = config.getInt("pillars.spawnRadius", 100)
        pillarHeight = config.getInt("pillars.pillarHeight", 5) // Added default value for pillarHeight
    }
}
