package com.bignerdranch.nyetHack

object Game {
    private var currentRoom: Room = TownSquare()

    init {
        narrate("Welcome, adventurer")
        val mortality = if(player.isImmortal) "an Immortal" else "a mortal"
        narrate("${player.name}, $mortality, has ${player.healthPoints} health points.")
    }

    fun play() {
        while (true) {
            // Play NyetHack
            narrate("${player.name} of ${player.hometown}, ${player.title}, is in ${currentRoom.description()}")
            currentRoom.enterRoom()

            print("> Enter yout command:  ")
            println("Last command: ${readLine()}")
        }
    }
}