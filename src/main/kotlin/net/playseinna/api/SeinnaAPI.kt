package net.playseinna.api

import net.playseinna.api.inventory.InventoryEventManager
import net.playseinna.api.mongo.MongoManager
import org.bukkit.plugin.java.JavaPlugin

class SeinnaAPI : JavaPlugin() {

    override fun onLoad() { }
    override fun onEnable() { }
    override fun onDisable() { }

    companion object {
        private val mongoManagers = HashMap<String, MongoManager>()
        private val inventoryManager = InventoryEventManager()

        fun getMongoManager(database: String): MongoManager {
            if (!mongoManagers.containsKey(database))
                mongoManagers[database] = MongoManager(database)
            return mongoManagers[database]!!
        }

        fun getInventoryEventManager(): InventoryEventManager {
            return inventoryManager
        }
    }

}