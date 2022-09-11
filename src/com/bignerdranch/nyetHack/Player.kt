package com.bignerdranch.nyetHack

import com.sun.org.apache.xpath.internal.operations.Bool

class Player(
    initialName: String,
    val hometown: String,
    var healthPoints: Int,
    val isImmortal: Boolean
) {
    var name = initialName
        get() = field.replaceFirstChar { it.uppercase() }
        private set(name) {
            field = name.trim()
        }

    val title
        get() = when {
            name.all {it.isDigit()} -> "The Identifiable"
            name.none {it.isLetter()} -> "The Witness Protection Member"
            name.count {it.toLowerCase() in "aeiou"} > 4 -> "The Master of Vowels"
            name.all {it.isUpperCase() } -> "The Bold"
            name.count {it.isLetter()} > 10 -> "The Verbose"
            else -> "The Renowned Hero"
        }

        constructor(name: String, hometown: String) : this(
            initialName = name,
            hometown = hometown,
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
}