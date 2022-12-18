package com.bignerdranch.nyetHack

open class Room(val name: String) {

    protected open val status = "Calm"

    open fun description() = "$name (Currently: $status)"

    open fun enterRoom() {
        narrate("There is nothing to do here")
    }
}

private fun defineMonster(): Monster {
    return listOf(
        Goblin(),
        Goblin(),
        Goblin(),
        Werewolf(),
        Werewolf(),
        Dragon()
    ).random()
}
open class MonsterRoom(
    name: String,
    var monster: Monster? = defineMonster()
) : Room(name) {
    override fun description(): String =
        super.description() + " (Creature: ${monster?.description ?: "None"}"
    override fun enterRoom() {
        if(monster == null) {
            super.enterRoom()
        } else {
            narrate("Danger is lurking in this room")
        }
    }
}

