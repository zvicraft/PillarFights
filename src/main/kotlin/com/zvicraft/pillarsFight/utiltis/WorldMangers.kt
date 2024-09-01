package com.zvicraft.pillarsFight.utiltis

import com.onarandombox.MultiverseCore.MultiverseCore
import com.onarandombox.MultiverseCore.api.MVWorldManager
import com.zvicraft.pillarsFight.PillarsFight
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.WorldType


class WorldMangers(private val plugin: PillarsFight) {
    private val core = Bukkit.getServer().pluginManager.getPlugin("Multiverse-Core") as MultiverseCore?
    private val worldManager: MVWorldManager = core!!.mvWorldManager
    val worldName = plugin.config.getString("world.name", "PillarsFightWorld")
    val world = Bukkit.getWorld(worldName!!)
     fun worldManager(){


   
        worldManager.addWorld(
            plugin.config.getString("world.name", "PillarsFightWorld"),
            plugin.config.getString("world.environment".toUpperCase(), "NORMAL" )?.let { World.Environment.valueOf(it) },
            plugin.config.getString("seed", null),
            plugin.config.getString("world.type".toUpperCase(), "NORMAL")?.let { WorldType.valueOf(it) },
            plugin.config.getBoolean("structures", false),
            plugin.config.getString("custom-generator", null)
        )
    }
    fun loadWorld() {
        worldManager.loadWorld(plugin.config.getString("gamemode", "survival"))
    }
}