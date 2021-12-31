package net.playseinna.api.utils

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.UnsupportedEncodingException
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

open class UUIDUtils {

    companion object {

        @Throws(Exception::class)
        private fun getUUIDTime(name: String, time: Long, timeout: Int): UUID? {
            val url: URL = if (time == -1L) {
                URL("https://api.mojang.com/users/profiles/minecraft/$name")
            } else {
                URL("https://api.mojang.com/users/profiles/minecraft/$name?at=$time")
            }
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.connectTimeout = timeout
            connection.readTimeout = timeout
            if (connection.responseCode == 204) {
                return null
            }
            val scanner = Scanner(connection.inputStream)
            val input: String = scanner.nextLine()
            scanner.close()
            val UUIDObject = JsonParser.parseString(input).asJsonObject
            val uuidString = UUIDObject["id"].toString()
            val uuidSeperation = uuidString.replaceFirst(
                "([0-9a-fA-F]{8})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]+)".toRegex(),
                "$1-$2-$3-$4-$5"
            )
            return UUID.fromString(uuidSeperation)
        }

        @Throws(Exception::class)
        private fun getUUIDTime(name: String, time: Long): UUID? {
            return getUUIDTime(name, time, 30000)
        }

        @Throws(Exception::class)
        fun getNamesChanged(name: String): String {
            val Date = Date()
            val Time: Long = Date.time / 1000
            val UUIDOld = getUUIDTime(name, Time - 60 * 60 * 24 * 30)
            val uuid = UUIDOld.toString().replace("-", "")
            val scanner = Scanner(URL("https://api.mojang.com/user/profiles/$uuid/names").openStream())
            val input: String = scanner.nextLine()
            scanner.close()
            val nameArray = JsonParser.parseString(input).asJsonArray
            val playerSlot = nameArray[nameArray.size() - 1].toString()
            val nameObject = JsonParser.parseString(playerSlot).asJsonObject
            return nameObject["name"].toString()
        }

        @Throws(UnsupportedEncodingException::class)
        fun getCrackedUUID(name: String): UUID? {
            return UUID.nameUUIDFromBytes("OfflinePlayer:$name".toByteArray(charset("UTF_8")))
        }

        @Throws(Exception::class)
        fun getUUID(name: String): UUID? {
            return getUUIDTime(name, -1)
        }

        @Throws(Exception::class)
        fun getUUID(name: String, timeout: Int): UUID? {
            return getUUIDTime(name, -1, timeout)
        }

    }

}