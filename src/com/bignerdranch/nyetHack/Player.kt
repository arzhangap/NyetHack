package com.bignerdranch.nyetHack

class Player(
    initialName: String,
    val hometown: String = "alwaysSummer",
    override var healthPoints: Int,
    val isImmortal: Boolean
) : Fightable {
    override var name = initialName
        get() = field.replaceFirstChar { it.uppercase() }
        private set(name) {
            field = name.trim()
        }
    override val diceCount: Int = 3
    override var diceSides: Int = 4

    override fun takeDamage(damage: Int) {
        if(!isImmortal) {
            healthPoints -= damage
        }
    }

    val title
        get() = when {
            name.all {it.isDigit()} -> "The Identifiable"
            name.none {it.isLetter()} -> "The Witness Protection Member"
            name.numVowels > 4 -> "The Master of Vowels"
            name.all {it.isUpperCase() } -> "The Bold"
            name.count {it.isLetter()} > 10 -> "The Verbose"
            else -> "The Renowned Hero"
        }

    val prophecy by lazy {
        narrate("$name embarks on an arduous quest to locate a fortune teller")
        Thread.sleep(3000)
        narrate("The fortune teller bestows a prophecy upon $name")

        "An intrepid hero from $hometown shall some day " + listOf(
            "from an unlikely bond between two warring factions",
            "take possession of an otherworldly blade",
            "bring the gift from creation back to the world",
            "best the world-eater"
        ).random()
    }

    init {
      require(healthPoints > 0) { "healthPoints must be greater than zero" }
      require(name.isNotBlank()) { "Player must have a name" }
    }

    constructor(name: String) : this(
        initialName = name,
        healthPoints = 100,
        isImmortal = false
    ) {
        if(name.equals("Arzhang", ignoreCase = true)) {
            healthPoints = 500
        }
    }

    fun castFireBall(numFireBalls: Int = 2) {
        narrate("A glass of Fireball springs into existence (x$numFireBalls)")
    }

    fun changeName(newName: String) {
        narrate("$name legally changes their name to $newName")
        name = newName
    }

    fun prophesize(){
        narrate("$name thinks about their future")
        narrate("A fortune teller told Madrigal \"$prophecy\"")
    }

    val inventory = mutableListOf<Loot>()

    var gold = 0
}