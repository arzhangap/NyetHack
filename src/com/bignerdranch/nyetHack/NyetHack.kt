package com.bignerdranch.nyetHack

val player = Player("Arzhang","Jacksonvillie")
fun main() {
    //change narrator mood
    val mortality = if(player.isImmortal) "an Immortal" else "a mortal"
    narrate("${player.name} of ${player.hometown}, ${player.title}, heads to the town square." +
            " ${player.name}, $mortality, has ${player.healthPoints} health points.")

//    com.bignerdranch.nyetHack.narrate("A hero enters the town of Kronstadt. What is their name?") { message ->
//        "\u001b[33;1m$message\u001b[0m"
//    }
//    val heroName = readLine()
//    require(heroName != null && heroName.isNotEmpty()) {
//        "The hero must have a name"
//    }

//    com.bignerdranch.nyetHack.changeNarratorMood()
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
