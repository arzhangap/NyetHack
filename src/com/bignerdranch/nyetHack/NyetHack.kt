package com.bignerdranch.nyetHack

lateinit var player: Player
fun main() {
    narrate("Welcome to NyetHack!")
    val playerName = promptHeroName()
    player = Player(playerName)
    //change narrator mood
    player.prophesize()

    val currentRoom: Room = Tavern()
    val mortality = if(player.isImmortal) "an Immortal" else "a mortal"

    narrate("${player.name} of ${player.hometown}, ${player.title}, is in ${currentRoom.description()}")
    narrate("${player.name}, $mortality, has ${player.healthPoints} health points.")
    currentRoom.enterRoom()

//    com.bignerdranch.nyetHack.narrate("A hero enters the town of Kronstadt. What is their name?") { message ->
//        "\u001b[33;1m$message\u001b[0m"
//    }
//    val heroName = readLine()
//    require(heroName != null && heroName.isNotEmpty()) {
//        "The hero must have a name"
//    }

//    com.bignerdranch.nyetHack.changeNarratorMood()

    player.castFireBall()
    player.prophesize()

}

private fun promptHeroName() : String {
    narrate("A hero enters the town of Kronstadt. What is your name?") {
        //print message in yellow
        "\u001b[33;1m$it\u001b[0m"
    }

    /* val input = readline()
    require(input != null && input.isNotEmpty()) {
    "The hero must have a name"
    }

    return input
    */

    println("Madrigal")
    return "Madrigal"
}
