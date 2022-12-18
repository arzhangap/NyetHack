package com.bignerdranch.nyetHack

import java.time.Month
import kotlin.random.Random


interface Fightable {
    val name: String
    var healthPoints: Int
    val diceCount: Int
    var diceSides: Int

    fun takeDamage(damage: Int)

    fun attack(opponent: Fightable) {
        val damageRoll = (0 until diceCount).sumOf {
            Random.nextInt(diceSides + 1)
        }
        narrate("$name deals $damageRoll to ${opponent.name}")
        opponent.takeDamage(damageRoll)
    }
}

abstract class Monster(
    override val name: String,
    val description: String,
    override var healthPoints: Int
) : Fightable {
    override fun takeDamage(damage: Int) {
        healthPoints -= damage
    }
}

class Goblin(
    name: String = "Goblin",
    description: String = "A nasty-looking goblin",
    healthPoints: Int = 30,
) : Monster(name, description, healthPoints) {
    override val diceCount = 1
    override var diceSides = 6
}

class Werewolf(
    name: String = "Werewolf",
    description: String = "A fluffy cute wolf with sharp teeths",
    healthPoints: Int = 50
) : Monster(name,description,healthPoints) {
    override val diceCount: Int = 2
    override var diceSides: Int = 8
}

class Dragon(
) : Monster("Dragon", "A big Lizard with wings and fire coming out of his mouth.",100) {
    override val diceCount: Int = 3
    override var diceSides: Int = 10
}