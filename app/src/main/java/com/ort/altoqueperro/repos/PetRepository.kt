package com.ort.altoqueperro.repos

import com.ort.altoqueperro.entities.Pet

class PetRepository {
    var pets : MutableList<Pet> = mutableListOf()


    init{
        createSheltersDatabase();
    }

    private fun createSheltersDatabase(){
        pets.add(Pet("pepito", "lost","https://post.medicalnewstoday.com/wp-content/uploads/sites/3/2020/02/322868_1100-800x825.jpg"));
        pets.add(Pet("josecito", "lost","https://post.medicalnewstoday.com/wp-content/uploads/sites/3/2020/02/322868_1100-800x825.jpg"));
        pets.add(Pet("javier", "lost","https://post.medicalnewstoday.com/wp-content/uploads/sites/3/2020/02/322868_1100-800x825.jpg"));
        pets.add(Pet("termotanque", "found","https://post.medicalnewstoday.com/wp-content/uploads/sites/3/2020/02/322868_1100-800x825.jpg"));
        pets.add(Pet("lechuga", "found","https://post.medicalnewstoday.com/wp-content/uploads/sites/3/2020/02/322868_1100-800x825.jpg"));
        pets.add(Pet("rocco", "found","https://post.medicalnewstoday.com/wp-content/uploads/sites/3/2020/02/322868_1100-800x825.jpg"));
        pets.add(Pet("valko", "found","https://post.medicalnewstoday.com/wp-content/uploads/sites/3/2020/02/322868_1100-800x825.jpg"));

    }

    fun getLostPets() : MutableList<Pet> {
        val lostPets = mutableListOf<Pet>()
        lostPets.addAll(pets.filter { it.state == "lost" })
        lostPets.forEach { println(it.toString()) }
        return lostPets
    }

    fun getFoundPets() : MutableList<Pet> {
        val foundPets = mutableListOf<Pet>()
        foundPets.addAll(pets.filter { it.state == "found" })
        return foundPets
    }
}