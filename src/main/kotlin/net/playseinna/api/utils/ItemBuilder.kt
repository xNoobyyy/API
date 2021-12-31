package net.playseinna.api.utils

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

class ItemBuilder {

    private lateinit var material: Material
    private lateinit var displayName: String
    private var amount: Int = -1
    var lore = ArrayList<String>()
    var enchantments = HashMap<Enchantment, Int>()
    var itemFlags = ArrayList<ItemFlag>()

    fun setMaterial(material: Material): ItemBuilder {
        this.material = material
        return this
    }

    fun setDisplayName(displayName: String): ItemBuilder {
        this.displayName = displayName
        return this
    }

    fun setAmount(amount: Int): ItemBuilder {
        this.amount = amount
        return this
    }

    fun setLore(lore: List<String>): ItemBuilder {
        val lines = ArrayList<String>()
        for (l in lore) {
            lines.addAll(l.split("\n"))
        }
        this.lore = lines
        return this
    }

    fun addLore(vararg loreLine: String): ItemBuilder {
        val lines = ArrayList<String>()
        for (l in loreLine) {
            lines.addAll(l.split("\n"))
        }
        lore.addAll(lines)
        return this
    }

    fun addEnchantments(vararg enchantment: Pair<Enchantment, Int>): ItemBuilder {
        this.enchantments.putAll(enchantment)
        return this
    }

    fun addEnchantments(enchantments: Map<Enchantment, Int>): ItemBuilder {
        this.enchantments.putAll(enchantments)
        return this
    }

    fun addEnchantment(enchantment: Enchantment, level: Int): ItemBuilder {
        this.enchantments[enchantment] = level
        return this
    }

    fun addItemFlag(vararg itemFlag: ItemFlag): ItemBuilder {
        this.itemFlags.addAll(itemFlag)
        return this
    }

    fun addItemFlags(itemFlags: List<ItemFlag>): ItemBuilder {
        this.itemFlags.addAll(itemFlags)
        return this
    }

    fun build(): ItemStack? {
        if (!this::material.isInitialized) {
            return null
        }
        val itemStack = ItemStack(material)
        val itemMeta = itemStack.itemMeta!!

        if (amount < 1) {
            itemStack.amount = 1
        }

        if (this::displayName.isInitialized) {
            itemMeta.setDisplayName(displayName)
        }

        for (ench in enchantments) {
            itemMeta.addEnchant(ench.key, ench.value, true)
        }

        itemMeta.addItemFlags(*itemFlags.toTypedArray())

        if (lore.isNotEmpty()) {
            itemMeta.lore = lore
        }

        itemStack.itemMeta = itemMeta

        return itemStack
    }

}