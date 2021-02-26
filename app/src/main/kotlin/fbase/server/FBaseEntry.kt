package fbase.server

import java.io.*
import java.net.*
import kotlinx.coroutines.*

fun runFBaseServer(cport:Int, routine: (socket: Socket, reader: BufferedReader, writer: BufferedWriter) -> Unit){
    val serverSocket = ServerSocket(port)
    GlobalScope.launch{
        while(true){
            val socket = serverSocket.accept()
            GlobalScope.launch{
                val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
                val writer = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))
                routine(socket, reader, writer)
            }
        }
    }
}