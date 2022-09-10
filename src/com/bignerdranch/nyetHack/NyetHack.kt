package com.bignerdranch.nyetHack

val player = Player()
fun main() {
    narrate("${player.name} is ${player.title}")
    player.changeName("Arzhang")
//    com.bignerdranch.nyetHack.narrate("A hero enters the town of Kronstadt. What is their name?") { message ->
//        "\u001b[33;1m$message\u001b[0m"
//    }
//    val heroName = readLine()
//    require(heroName != null && heroName.isNotEmpty()) {
//        "The hero must have a name"
//    }

//    com.bignerdranch.nyetHack.changeNarratorMood()
    narrate("${player.name}, ${player.title} heads to the town square")
    visitTavern()
    player.castFireBall()

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
