package de.noob.seinna.api.utils

import com.google.common.base.Strings
import org.bukkit.ChatColor

class StringUtils {

    companion object {

        fun intToRoman(i: Int): String {
            if (i < 1 || i > 3999) return "Error"

            var input = i

            var s = ""
            while (input >= 1000) {
                s += "M"
                input -= 1000
            }
            while (input >= 900) {
                s += "CM"
                input -= 900
            }
            while (input >= 500) {
                s += "D"
                input -= 500
            }
            while (input >= 400) {
                s += "CD"
                input -= 400
            }
            while (input >= 100) {
                s += "C"
                input -= 100
            }
            while (input >= 90) {
                s += "XC"
                input -= 90
            }
            while (input >= 50) {
                s += "L"
                input -= 50
            }
            while (input >= 40) {
                s += "XL"
                input -= 40
            }
            while (input >= 10) {
                s += "X"
                input -= 10
            }
            while (input >= 9) {
                s += "IX"
                input -= 9
            }
            while (input >= 5) {
                s += "V"
                input -= 5
            }
            while (input >= 4) {
                s += "IV"
                input -= 4
            }
            while (input >= 1) {
                s += "I"
                input -= 1
            }
            return s

        }

        fun getProgressBar(current: Int, max: Int, totalBars: Int, symbol: Char, completedColor: ChatColor, notCompletedColor: ChatColor): String? {
            val percent = current.toFloat() / max
            val progressBars = (totalBars * percent).toInt()
            return (Strings.repeat("" + completedColor + symbol, progressBars)
                    + Strings.repeat("" + notCompletedColor + symbol, totalBars - progressBars))
        }

    }

}