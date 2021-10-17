package com.ort.altoqueperro.repos

import com.ort.altoqueperro.entities.Pet

class PetRepository {
    var pets : MutableList<Pet> = mutableListOf()


    init{
        createPetsDatabase();
    }

    private fun createPetsDatabase(){
        pets.add(Pet(name = "petLost1", type = "Perro", size= "Grande", sex= "Macho", coat= "Rojo", eyeColor= "Verde"))
        pets.add(Pet(name = "petLost2", type = "Perro", size= "Mediano", sex= "Macho", coat= "Azul", eyeColor= "Rojo"))
        pets.add(Pet(name = "petLost3", type = "Perro", size= "Mediano", sex= "Hembra", coat= "Rojo", eyeColor= "Rojo"))
        pets.add(Pet(name = "petLost4", type = "Gato", size= "Grande", sex= "Macho", coat= "Rojo", eyeColor= "Verde"))
        pets.add(Pet(name = "petLost5", type = "Perro", size= "Grande", sex= "Macho", coat= "Rojo", eyeColor= "Azul"))
        pets.add(Pet(name = "petLost4", type = "Gato", size= "Chico", sex= "Macho", coat= "Purpura", eyeColor= "Verde"))
        pets.add(Pet(name = "petLost4", type = "Gato", size= "Grande", sex= "Hembre", coat= "Rojo", eyeColor= "Verde"))
        pets.add(Pet(name = "petLost4", type = "Gato", size= "Chico", sex= "Hembre", coat= "Amarillo", eyeColor= "Violeta"))
        pets.add(Pet(name = "petLost4", type = "Gato", size= "Chico", sex= "Hembre", coat= "Rojo", eyeColor= "Amarillo"))

    }
    fun getLostPets() : MutableList<Pet> {
        val lostPets = mutableListOf<Pet>()
/*        lostPets.addAll(pets.filter { it.state == "lost" })
        lostPets.forEach { println(it.toString()) }*/
        return lostPets
    }

    fun getFoundPets() : MutableList<Pet> {
        val foundPets = mutableListOf<Pet>()
/*        foundPets.addAll(pets.filter { it.state == "found" })*/
        return foundPets
    }
}