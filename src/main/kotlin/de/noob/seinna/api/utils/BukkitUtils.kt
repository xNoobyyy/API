package de.noob.seinna.api.utils

import org.bukkit.Bukkit
import org.bukkit.entity.Player

class BukkitUtils {

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