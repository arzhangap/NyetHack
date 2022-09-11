package com.bignerdranch.nyetHack

import com.sun.org.apache.xpath.internal.operations.Bool

class Player(
    initialName: String,
    hometown: String,
    healthPoints: Int,
    isImmortal: Boolean
) {
    var name = initialName
        get() = field.replaceFirstChar { it.uppercase() }
        private set(name) {
            field = name.trim()
        }
    val hometown = hometown
    val healthPoints = healthPoints
    val isImmortal = isImmortal

    val title
        get() = when {
            name.all {it.isDigit()} -> "The Identifiable"
            name.none {it.isLetter()} -> "The Witness Protection Member"
            name.count {it.toLowerCase() in "aeiou"} > 4 -> "The Master of Vowels"
            name.all {it.isUpperCase() } -> "The Bold"
            name.count {it.isLetter()} > 10 -> "The Verbose"
            else -> "The Renowned Hero"
        }

    fun castFireBall(numFireBalls: Int = 2) {
        narrate("A glass of Fireball springs into existence (x$numFireBalls)")
    }

    fun changeName(newName: String) {
        narrate("$name legally changes their name to $newName")
        name = newName
    }
}