package de.noob.seinna.api.inventory

import de.noob.seinna.api.SeinnaAPI
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.event.inventory.InventoryOpenEvent

class InventoryUtilListener : Listener {

    @EventHandler
    fun onInventoryOpen(e: InventoryOpenEvent) {
        SeinnaAPI.getInventoryEventManager().getAdapter(e.inventory)?.onOpen(e)
    }

    @EventHandler
    fun onInventoryClick(e: InventoryClickEvent) {
        if (e.currentItem == null) return
        if (e.clickedInventory == null) return
        if (SeinnaAPI.getInventoryEventManager().getAdapter(e.clickedInventory!!) != null) {
            e.isCancelled = true
            SeinnaAPI.getInventoryEventManager().getAdapter(e.clickedInventory!!)!!.onClick(e)
        }

    }

    @EventHandler
    fun onInventoryDrag(e: InventoryDragEvent) {
        SeinnaAPI.getInventoryEventManager().getAdapter(e.inventory)?.onDrag(e)
    }

    @EventHandler
    fun onInventoryClose(e: InventoryCloseEvent) {
        SeinnaAPI.getInventoryEventManager().getAdapter(e.inventory)?.onClose(e)
        SeinnaAPI.getInventoryEventManager().removeAdapter(e.inventory)
    }

}