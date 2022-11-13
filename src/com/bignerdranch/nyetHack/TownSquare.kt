package com.bignerdranch.nyetHack

class TownSquare : Room(name = "The Town Square") {
    override val status = "Bustling"

    override fun enterRoom() {
        narrate("The Villagers rally and cheer as the hero enters.")
    }
}