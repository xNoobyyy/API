package de.noob.seinna.api.utils

import java.text.DecimalFormat
import kotlin.math.log
import kotlin.math.pow

class IntegerUtils {

    companion object {

        fun isParseable(s: String): Boolean {
            try { Integer.parseInt(s) } catch (ex: NumberFormatException) { return false }
            return true
        }

        fun toNotation(number: Long): String {
            val charA = 'a'.code
            val units = mapOf(0 to "", 1 to "k", 2 to "m", 3 to "b", 4 to "t")
            if (number.toString().contains("-") || number.toString() == "0") { return "0" }
            val n = log(number.toDouble(), 1000.0).toInt()
            val m = number / 1000.0.pow(n)
            var unit = "";
            unit = if (n < units.count()) {
                units[n]!!
            } else {
                val unitInt = n - units.count()
                val secondUnit = unitInt % 26
                val firstUnit = unitInt / 26
                (firstUnit + charA).toChar().toString() + (secondUnit + charA).toChar().toString()
            }
            return DecimalFormat("0.00").format(m * 100 / 100) + unit
        }

    }

}