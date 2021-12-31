package de.noob.seinna.api

import de.noob.seinna.api.inventory.InventoryEventManager
import de.noob.seinna.api.mongo.MongoManager
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