package net.playseinna.api.inventory

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

open class InventoryBuilder(val size: Int, val title: String) {

    val content = ArrayList<ItemStack?>()
    var filler: ItemStack? = null

    init {
        for (i in 0 until size) {
            content.add(null)
        }
    }

    fun addItem(vararg items: ItemStack): InventoryBuilder {
        for (item in items) {
            if (content.size < size) {
                for (c in content) {
                    if (c == null) {
                        content.add(item)
                    }
                    break
                }
            }
        }
        return this
    }

    fun setItem(slot: Int, item: ItemStack?): InventoryBuilder {
        content[slot-1] = item
        return this
    }

    fun setFiller(filler: ItemStack?): InventoryBuilder {
        this.filler = filler
        return this
    }

    fun build(): Inventory {
        val inventory = Bukkit.createInventory(null, size, ChatColor.translateAlternateColorCodes('&', title))
        if (filler != null) {
            for (i in 0 until content.size) {
                if (content[i] == null) {
                    content[i] = filler
                }
            }
        }
        inventory.contents = content.toTypedArray()
        return inventory
    }

}