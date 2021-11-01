package com.ort.altoqueperro.repos

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.ort.altoqueperro.entities.User

class UserRepository {
    var users: MutableList<User> = mutableListOf() //ToDo no va
    val db = Firebase.firestore


    init {
        createUsersDatabase(); //ToDo no va
    }

    private fun createUsersDatabase() { //ToDo no va
        users.add(User("user1", "asd", "asd"))
        users.add(User("user2", "asd", "asd"))
        users.add(User("user3", "asd", "asd"))
        users.add(User("user4", "asd", "asd"))
        users.add(User("user5", "asd", "asd"))
        users.add(User("user6", "asd", "asd"))
        users.add(User("user7", "asd", "asd"))
    }

    fun getRandomUser(): User { //ToDo no va
        return users[(0..UserRepository().users.size - 1).random()]
    }

    fun getUser(liveData: MutableLiveData<User>, userUuid: String) {
        db.collection("users").document(userUuid).get()
            .addOnSuccessListener {
                liveData.postValue(it.toObject())
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }
    }

    fun getUsers(liveData: MutableLiveData<MutableList<User>>) {
        val users: MutableList<User> = mutableListOf()
        db.collection("users").get()
            .addOnSuccessListener {
                for (shelter in it) {
                    users.add(shelter.toObject())
                }
                liveData.postValue(users)
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }
    }
}