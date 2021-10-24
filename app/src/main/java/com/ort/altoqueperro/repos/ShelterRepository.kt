package com.ort.altoqueperro.repos

import com.ort.altoqueperro.entities.Address
import com.ort.altoqueperro.entities.Shelter

class ShelterRepository {
    var shelters: MutableList<Shelter> = mutableListOf()


    init {
        createSheltersDatabase();
    }

    private fun createSheltersDatabase() {
        /*shelters.add(Shelter("Prueba 1", "1122223333", Address("calle falsa", "123"), "https://cdn.akamai.steamstatic.com/steam/apps/1239320/capsule_616x353.jpg?t=1631279860", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"));
        shelters.add(Shelter("Prueba 2", "1133334444", Address("calle uno", "3434"), "https://cdn.akamai.steamstatic.com/steam/apps/1239320/capsule_616x353.jpg?t=1631279860", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"));
        shelters.add(Shelter("Prueba 3", "114442222", Address("calle dos", "543"), "https://cdn.akamai.steamstatic.com/steam/apps/1239320/capsule_616x353.jpg?t=1631279860", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"));
        shelters.add(Shelter("Prueba 4", "1111113452", Address("calle tres", "222"), "https://cdn.akamai.steamstatic.com/steam/apps/1239320/capsule_616x353.jpg?t=1631279860", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"));
        shelters.add(Shelter("Prueba 5", "1112345678", Address("Av. San Juan", "100"), "https://cdn.akamai.steamstatic.com/steam/apps/1239320/capsule_616x353.jpg?t=1631279860", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"));
        shelters.add(Shelter("Prueba 6", "1166667777", Address("calle falsa", "1584"), "https://cdn.akamai.steamstatic.com/steam/apps/1239320/capsule_616x353.jpg?t=1631279860", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"));
*/
    }

}