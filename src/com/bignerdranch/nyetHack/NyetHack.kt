package com.bignerdranch.nyetHack

lateinit var player: Player
fun main() {
    narrate("Welcome to NyetHack!")
    val playerName = promptHeroName()
    player = Player(playerName)

    val lootBoxOne: LootBox<Fedora> = LootBox(Fedora("a generic-looking fedora", 15))
//    val lootBoxTwo: LootBox<Gemstones> = LootBox(Gemstones(150))

    repeat(2) {
        narrate(
            lootBoxOne.takeLoot()?.let {
                "The hero retrieves ${it.name} from the box"
            } ?: "The box is empty"
        )
    }
//    com.bignerdranch.nyetHack.narrate("A hero enters the town of Kronstadt. What is their name?") { message ->
//        "\u001b[33;1m$message\u001b[0m"
//    }
//    val heroName = readLine()
//    require(heroName != null && heroName.isNotEmpty()) {
//        "The hero must have a name"
//    }

//    com.bignerdranch.nyetHack.changeNarratorMood()

    Game.play()

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
