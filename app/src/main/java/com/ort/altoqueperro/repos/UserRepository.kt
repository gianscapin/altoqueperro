package com.ort.altoqueperro.repos

import com.ort.altoqueperro.entities.User

class UserRepository {
    var users: MutableList<User> = mutableListOf()


    init {
        createUsersDatabase();
    }

    private fun createUsersDatabase() {
        users.add(User("user1", "asd", "asd"))
        users.add(User("user2", "asd", "asd"))
        users.add(User("user3", "asd", "asd"))
        users.add(User("user4", "asd", "asd"))
        users.add(User("user5", "asd", "asd"))
        users.add(User("user6", "asd", "asd"))
        users.add(User("user7", "asd", "asd"))
    }

    fun getRandomUser(): User {
        return users[(0..UserRepository().users.size -1).random()]
    }
}