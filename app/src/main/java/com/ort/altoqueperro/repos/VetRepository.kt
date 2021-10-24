package com.ort.altoqueperro.repos

import com.ort.altoqueperro.entities.Vet

class VetRepository {
    fun getAllVets(): MutableList<Vet> {

        return mutableListOf(/*
            Vet(
                "v1",
                "Hers Pet",
                -34.6089609,
                -58.4370584,
                "Caballito",
                "11 4477-3486",
                "Av. Díaz Vélez",
                "4342",
                null,
                "L a L 10 a 16",
                null,
                "Buenos Aires",
                "Argentina"
            ),
            Vet(
                "v2",
                "VETERINARIA CENTRO MÉDICO DELFI'S",
                -34.626831,
                -58.4362001,
                "Caballito",
                "11 4922-5520",
                "Av. José María Moreno 515",
                "515",
                null,
                "L a V 10 a 20",
                null,
                "Buenos Aires",
                "Argentina"
            )

        */)
    }
}