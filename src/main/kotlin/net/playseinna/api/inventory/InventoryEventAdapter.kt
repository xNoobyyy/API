package net.playseinna.api.inventory

import net.playseinna.api.SeinnaAPI
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.event.inventory.InventoryOpenEvent

abstract class InventoryEventAdapter(val inventory: InventoryBuilder, val removeOnClose: Boolean = true) {

    val title = inventory.title

    init {
        SeinnaAPI.getInventoryEventManager().adapters.add(this)
    }

    abstract fun onOpen(e: InventoryOpenEvent)
    abstract fun onClick(e: InventoryClickEvent)
    abstract fun onDrag(e: InventoryDragEvent)
    abstract fun onClose(e: InventoryCloseEvent)

}