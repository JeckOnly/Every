package com.sjy.udp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

/**
 * Created by JeckOnly on 2023/9/22
 * Describe:
 */
object UDPUtil {

    const val BROADCAST_IP = "255.255.255.255"
    const val BROADCAST_PORT = 8887


    suspend fun sendBroadcastData(sendData: String) = withContext(Dispatchers.IO) {
        try {
            val inetAddress = InetAddress.getByName(BROADCAST_IP)
            val datagramSocketSend = DatagramSocket()
            val data = sendData.toByteArray()
            val datagramPacket = DatagramPacket(data, data.size, inetAddress, BROADCAST_PORT)
            datagramSocketSend.send(datagramPacket)
            datagramSocketSend.broadcast = true
            datagramSocketSend.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun receiveBroadcastData(): Flow<String> = flow {
        withContext(Dispatchers.IO) {
            try {
                val datagramSocket = DatagramSocket(BROADCAST_PORT)
                while (true) {
                    val buf = ByteArray(1024)
                    val datagramPacket = DatagramPacket(buf, buf.size)
                    datagramSocket.receive(datagramPacket)// blocks until a datagram is received

                    val serverHost = datagramPacket.address.hostAddress
                    if (datagramPacket.data.isNotEmpty()) {
                        val message = String(datagramPacket.data, 0, datagramPacket.length) +
                                " from ${datagramPacket.address.hostAddress}:${datagramPacket.port}"
                        emit(message)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit("收到错误：${e.message}")
            }
        }
    }

}