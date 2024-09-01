package com.zvicraft.pillarsFight.listeners

import com.zvicraft.pillarsFight.PillarsFight
import com.zvicraft.pillarsFight.utiltis.ConfigManager
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChangedWorldEvent

class PlayersJoinEvent(private val plugin: PillarsFight): Listener {
    private val configManager: ConfigManager = plugin.configManager
    private val playerPillars: MutableMap<String, List<Location>> = mutableMapOf()

    @EventHandler
    fun onPlayerChangedWorld(event: PlayerChangedWorldEvent) {
        val player = event.player
        val newWorld = player.world
        val worldName = plugin.config.getString("world.name", "PillarsFightWorld")

        // Check if the player changed to the specific world
        if (newWorld.name == worldName) {
            val randomLocation = getRandomLocationInRadius(newWorld, configManager.spawnRadius)
            createPillarForPlayer(player, randomLocation)
            giveRandomItem(player)
        } else {
            removePillarForPlayer(player)
        }
    }

    private fun getRandomLocationInRadius(world: World, radius: Int): Location {
        val x = (-radius..radius).random().toDouble()
        val z = (-radius..radius).random().toDouble()
        val y = world.getHighestBlockYAt(x.toInt(), z.toInt()).toDouble()
        return Location(world, x, y, z)
    }

    private fun createPillarForPlayer(player: Player, location: Location) {
        val world = location.world
        val pillarLocations = mutableListOf<Location>()

        for (y in 0 until configManager.pillarHeight) {
            val blockLocation = location.clone().add(0.0, y.toDouble(), 0.0)
            world?.getBlockAt(blockLocation)?.type = configManager.pillarBlock
            pillarLocations.add(blockLocation)
        }
        playerPillars[player.name] = pillarLocations

        player.teleport(location.clone().add(0.0, configManager.pillarHeight.toDouble(), 0.0))
    }

    private fun removePillarForPlayer(player: Player) {
        val pillarLocations = playerPillars[player.name] ?: return

        for (location in pillarLocations) {
            location.world?.getBlockAt(location)?.type = Material.AIR
        }

        playerPillars.remove(player.name)
    }

    private fun getAllPossibleItems(): List<Material> {
        return Material.values().toList().filter { it.isItem }
    }

    private fun giveRandomItem(player: Player) {
        val possibleItems = getAllPossibleItems()

        val randomItem = possibleItems.random()
        val itemStack = org.bukkit.inventory.ItemStack(randomItem)

        player.inventory.addItem(itemStack)
    }



}
