package com.bignerdranch.nyetHack

import kotlin.system.exitProcess

object Game {
    private val worldMap = listOf(
        listOf(TownSquare(), Tavern(), Room("Back Room")),
        listOf(MonsterRoom("A long Corridor"), Room("A generic Room")),
        listOf(MonsterRoom("The Dungeon"),MonsterRoom("The Mountain"),MonsterRoom("Secret Room"))
    )

    private var currentRoom: Room = worldMap[0][0]
    private var currentPosition = Coordinate(0,0)
    private var isPlaying = false

    init {
        narrate("Welcome, adventurer")
        val mortality = if(player.isImmortal) "an Immortal" else "a mortal"
        narrate("${player.name}, $mortality, has ${player.healthPoints} health points.")
    }

    fun play() {
        isPlaying = true
        while (isPlaying) {
            // Play NyetHack
            narrate("${player.name} of ${player.hometown}, ${player.title}, is in ${currentRoom.description()}")
            currentRoom.enterRoom()

            print("> Enter yout command:  ")
            GameInput(readLine()).processCommand()
        }
    }

    fun move(direction: Direction) {
        val newPosition = direction.updateCoordinate(currentPosition)
        val newRoom = worldMap.getOrNull(newPosition.y)?.getOrNull(newPosition.x)

        if (newRoom != null) {
            narrate("The hero moves ${direction.name}")
            currentPosition = newPosition
            currentRoom = newRoom
        } else {
            narrate("You cannot move ${direction.name}")
        }
    }

    private class GameInput(arg: String?) {
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1) {""}

        fun fight() {
            val monsterRoom = currentRoom as? MonsterRoom
            val currentMonster = monsterRoom?.monster
            if(currentMonster == null) {
                narrate("there is nothing to fight here.")
                return
            }

            while (player.healthPoints > 0 && currentMonster.healthPoints > 0) {
                player.attack(currentMonster)
                if(currentMonster.healthPoints > 0) {
                    currentMonster.attack(player)
                }
                Thread.sleep(1000)
            }

            if(player.healthPoints <= 0) {
                narrate("You have been defeated! Thanks for playing")
                exitProcess(0)
            } else {
                narrate("${currentMonster.name} has been defeated!")
                monsterRoom.monster = null
            }
        }
        fun processCommand() = when(command.lowercase()) {
            "fight" -> {
                fight()
            }
            "move" -> {
                val direction = Direction.values()
                    .firstOrNull { it.name.equals(argument, ignoreCase = true) }
                if(direction != null) {
                    move(direction)
                } else {
                    narrate("I don't know what direction that is")
                }
            }
            "castfireball" -> {
                val number = argument.toIntOrNull()
                if(number != null) { player.castFireBall(number) } else {
                    player.castFireBall()
                }
            }
            "prophesize" -> {
                player.prophesize()
            }
            "map" -> {
               worldMap.forEach {
                   it.forEach { if(!it.equals(currentRoom)) print("0 ") else print("X ") }
                   println()
               }
            }
            "ring" -> {
                if(currentRoom.equals(worldMap[0][0])) {
                    (currentRoom as TownSquare).ringBell()
                } else {
                    narrate("There is no bell to ring here.")
                }
            }
            "quit" -> {
                narrate("farewell ${player.name}.")
                isPlaying = false
            }
            else -> narrate("I'm not sure what you're trying to do")
        }
    }
}