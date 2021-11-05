package com.ort.altoqueperro.utils

object Notifications {
    var hidePetLost:Boolean = false
    var hidePetFound:Boolean = false

    init{
        println("Singleton notifications invoked.")
    }

    fun getNotificationPetLost(): Boolean{
        return this.hidePetLost
    }

    fun getNotificationPetFound(): Boolean{
        return this.hidePetFound
    }

    fun setNotificationPetLost(switch:Boolean){
        this.hidePetLost = switch
    }

    fun setNotificationPetFound(switch: Boolean){
        this.hidePetFound = switch
    }
}