package fbase.server

import java.io.*
import java.net.*
import kotlinx.coroutines.*

class FBaseStream(val istream: InputStream, val ostream: OutputStream, val reader: BufferedReader, val writer: BufferedWriter)

fun <T> runFBaseServer(port:Int, routine: (socket:Socket, fst: FBaseStream, stateobj: T?) -> Unit, stateobj: T? = null){
    val serverSocket = ServerSocket(port)
    GlobalScope.launch{
        while(true){
            val socket = serverSocket.accept()
            GlobalScope.launch{
                val istream = socket.getInputStream()
                val ostream = socket.getOutputStream()
                val reader = BufferedReader(InputStreamReader(istream))
                val writer = BufferedWriter(OutputStreamWriter(ostream))
                routine(socket, FBaseStream(istream, ostream, reader, writer), stateobj)
            }
        }
    }
}