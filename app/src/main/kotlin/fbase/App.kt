/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package fbase

import java.io.*
import java.net.*
import fbase.server.*

fun <T> sample(@Suppress("UNUSED_PARAMETER") socket: Socket, fst: FBaseStream, @Suppress("UNUSED_PARAMETER") stateobj: T?){
    fst.reader.lines().forEach{
        i -> println(i)
    }
}

fun main() {
    runFBaseServer(8082, ::sample, null)

    while(true){}

}
