package com.ort.altoqueperro.repos

import com.ort.altoqueperro.entities.Pet

class PetRepository {
    var pets : MutableList<Pet> = mutableListOf()


    init{
        createPetsDatabase();
    }

    private fun createPetsDatabase(){
        pets.add(Pet(name = null, type = "Perro", size= "Grande", sex= "Macho", nose= "Chato", furColor= "Rojo", eyeColor= "Verde", furColor= "Rojo", lostDate= "22/02", comments= null))
        pets.add(Pet(name = "Juancin", type = "Perro", size= "Mediano", sex= "Macho", coat= "Azul", eyeColor= "Rojo"))
        pets.add(Pet(name = null, type = "Perro", size= "Mediano", sex= "Hembra", coat= "Rojo", eyeColor= "Rojo"))
        pets.add(Pet(name = "Termotanque", type = "Gato", size= "Grande", sex= "Macho", coat= "Rojo", eyeColor= "Verde"))
        pets.add(Pet(name = "Rocky", type = "Perro", size= "Grande", sex= "Macho", coat= "Rojo", eyeColor= "Azul"))
        pets.add(Pet(name = null, type = "Gato", size= "Chico", sex= "Macho", coat= "Purpura", eyeColor= "Verde"))
        pets.add(Pet(name = null, type = "Gato", size= "Grande", sex= "Hembre", coat= "Rojo", eyeColor= "Verde"))
        pets.add(Pet(name = null, type = "Gato", size= "Chico", sex= "Hembre", coat= "Amarillo", eyeColor= "Violeta"))
        pets.add(Pet(name = null, type = "Gato", size= "Chico", sex= "Hembre", coat= "Rojo", eyeColor= "Amarillo"))

    }
}