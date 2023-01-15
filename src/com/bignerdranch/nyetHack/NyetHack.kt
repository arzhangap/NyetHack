package com.bignerdranch.nyetHack

lateinit var player: Player
fun main() {
    narrate("Welcome to NyetHack!")
    val playerName = promptHeroName()
    player = Player(playerName)
    changeNarratorMood()
    Game.play()
}

private fun promptHeroName() : String {
    narrate("A hero enters the town of Kronstadt. What is your name?") {
        //print message in yellow
        "\u001b[33;1m$it\u001b[0m"
    }

    val input = readLine()
    require(input != null && input.isNotEmpty()) {
    "The hero must have a name"
    }

    return input
}
