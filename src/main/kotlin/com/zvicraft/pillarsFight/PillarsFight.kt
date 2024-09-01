package com.zvicraft.pillarsFight

import com.zvicraft.pillarsFight.listeners.PlayerInteractListener
import com.zvicraft.pillarsFight.listeners.PlayersJoinEvent
import com.zvicraft.pillarsFight.utiltis.ConfigManager
import com.zvicraft.pillarsFight.utiltis.WorldMangers
import org.bukkit.plugin.java.JavaPlugin

class PillarsFight : JavaPlugin() {
    lateinit var configManager: ConfigManager

    override fun onEnable() {
        // registers
        saveDefaultConfig()
        configManager = ConfigManager(this)

        registerListeners()
        registerUtiltis()


    }



    private fun registerListeners() {
        server.pluginManager.registerEvents(PlayersJoinEvent(this), this)
        server.pluginManager.registerEvents(PlayerInteractListener(this), this)
    }
    private fun registerUtiltis() {
        WorldMangers(this).worldManager()
        WorldMangers(this).loadWorld()
    }
}
