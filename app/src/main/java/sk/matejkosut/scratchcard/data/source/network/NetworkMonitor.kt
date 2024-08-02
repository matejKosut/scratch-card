package sk.matejkosut.scratchcard.data.source.network

interface NetworkMonitor {
    fun isConnected():Boolean
}