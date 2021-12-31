package net.playseinna.api.inventory

import org.bukkit.inventory.Inventory

open class InventoryEventManager {

    val adapters = ArrayList<InventoryEventAdapter>()

    fun getAdapter(inventory: Inventory): InventoryEventAdapter? {
        return adapters.stream().filter { a -> a.title == inventory.viewers[0].openInventory.title }.findFirst().orElse(null)
    }

    fun getAdapter(title: String): InventoryEventAdapter? {
        return adapters.stream().filter { a -> a.title == title }.findFirst().orElse(null)
    }

    fun removeAdapter(inventory: Inventory) {
        if (getAdapter(inventory) == null) return
        if (getAdapter(inventory)!!.removeOnClose)
            adapters.remove(getAdapter(inventory))
    }

    fun removeAdapter(title: String) {
        if (getAdapter(title) == null) return
        if (getAdapter(title)!!.removeOnClose)
            adapters.remove(getAdapter(title))
    }

}