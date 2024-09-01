package com.zvicraft.pillarsFight.listeners

import com.zvicraft.pillarsFight.PillarsFight
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.entity.TNTPrimed
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class PlayerInteractListener(private val plugin: PillarsFight): Listener {

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        val action = event.action
        if (!action.isRightClick) return
        if (player.inventory.itemInMainHand.type == Material.TNT)
            launchTNT(player)
        if (!action.isLeftClick) return

    }

    private fun launchTNT(player: Player) {
        val tnt =
            player.world.spawn(player.location.add(player.eyeLocation.direction.multiply(2)), TNTPrimed::class.java)
        tnt.velocity = player.eyeLocation.direction.normalize().multiply(1.5)

    }
}