package de.noob.seinna.api.utils

import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.nio.channels.Channels
import java.nio.channels.ReadableByteChannel

open class FileDownloader(private var link: String, private var path: String) {

    @Throws(IOException::class)
    private fun startDownload(link: String) {
        val url = URL(link)
        val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
        conn.addRequestProperty("User-Agent", "Mozilla")
        conn.instanceFollowRedirects = true
        HttpURLConnection.setFollowRedirects(true)
        var redirect = false
        val status: Int = conn.responseCode
        if (status != HttpURLConnection.HTTP_OK) {
            if (status == HttpURLConnection.HTTP_MOVED_TEMP || status == HttpURLConnection.HTTP_MOVED_PERM || status == HttpURLConnection.HTTP_SEE_OTHER) redirect =
                true
        }
        if (redirect) this.startDownload(conn.getHeaderField("Location")) else {
            File(path).parentFile.mkdirs()
            val readableByteChannel: ReadableByteChannel = Channels.newChannel(conn.inputStream)
            FileOutputStream(path).use { fileOutputStream ->
                fileOutputStream.channel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE)
            }
        }
        conn.disconnect()
    }

    @Throws(IOException::class)
    fun startDownload() {
        this.startDownload(link)
    }

}