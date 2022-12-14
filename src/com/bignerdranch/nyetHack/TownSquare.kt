package com.bignerdranch.nyetHack

class TownSquare : Room(name = "The Town Square") {
    override val status = "Bustling"
    private val bellSound = "GWONG"

    final override fun enterRoom() {
        narrate("The Villagers rally and cheer as the hero enters.")
        ringBell()
    }

    public fun ringBell() {
        narrate("The bell tower announces the hero's presence: $bellSound")
    }
}