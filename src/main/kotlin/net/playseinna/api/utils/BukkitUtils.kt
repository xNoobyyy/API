package net.playseinna.api.utils

import org.bukkit.Bukkit
import org.bukkit.entity.Player

open class BukkitUtils {

    companion object {
        fun getOnlinePlayers(): ArrayList<Player> {
            val result = ArrayList<Player>()
            for (p in Bukkit.getOnlinePlayers()) {
                result.add(p!!)
            }
            return result
        }
    }

}