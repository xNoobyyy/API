package net.playseinna.api.utils

import de.dytanic.cloudnet.driver.CloudNetDriver
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot
import de.dytanic.cloudnet.ext.bridge.player.ICloudPlayer
import de.dytanic.cloudnet.ext.bridge.player.IPlayerManager
import de.dytanic.cloudnet.ext.bridge.player.executor.PlayerExecutor
import de.dytanic.cloudnet.ext.bridge.player.executor.ServerSelectorType
import org.bukkit.entity.Player

open class CloudNetUtils {

    companion object {

        val driver: CloudNetDriver = CloudNetDriver.getInstance()
        val playerManager: IPlayerManager = driver.servicesRegistry.getFirstService(IPlayerManager::class.java)

        fun sendToTask(player: Player, task: String) {
            if (getPlayer(player) == null) return
            if (driver.serviceTaskProvider.isServiceTaskPresent(task)) {
                playerManager.getPlayerExecutor(playerManager.getOnlinePlayer(player.uniqueId)!!).connectToTask(task,
                    ServerSelectorType.RANDOM
                )
            }
        }

        fun sendToServer(player: Player, service: String) {
            if (getPlayerExecutor(player) == null) return
            if (getCloudService(service).let { s -> s == null || !s.isConnected }) return
            getPlayerExecutor(player)!!.connect(service)
        }

        fun getPlayer(player: Player): ICloudPlayer? {
            return playerManager.getOnlinePlayer(player.uniqueId)
        }

        fun getPlayerExecutor(player: Player): PlayerExecutor? {
            if (getPlayer(player) == null) return null
            return playerManager.getPlayerExecutor(getPlayer(player)!!)
        }

        fun getCloudService(service: String): ServiceInfoSnapshot? {
            return driver.cloudServiceProvider.getCloudServiceByName(service)
        }

        fun sendMessage(player: Player, message: String) {
            if (getPlayerExecutor(player) == null) return
            getPlayerExecutor(player)!!.sendChatMessage(message)
        }

    }

}