package com.bignerdranch.nyetHack

import kotlin.random.Random
import kotlin.random.nextInt

var narrationModifier: (String) -> String = {it}

fun narrate(
    message: String,
    modifier: (String) -> String = { narrationModifier(it) }
) {
    println(modifier(message))
}
fun changeNarratorMood(){
    val mood: String
    val modifier: (String) -> String

    when(Random.nextInt(1..8)){
        1 -> {
            mood = "loud"
            modifier = {message ->
                val numExclamationPoints = 3
                message.toUpperCase() + "!".repeat(numExclamationPoints)
            }
            }
        2 -> {
            mood = "tired"
            modifier = {message ->
                message.toLowerCase().replace(" ", "... ")
            }
        }
        3 -> {
            mood = "unsure"
            modifier = {message ->
                "$message?"
            }
        }
        4 -> {
            var narrationGiven = 0
            mood = "Like sending an itemized bill"
            modifier = {message ->
                narrationGiven++
                "$message. \n(I have narrated $narrationGiven things.)"
            }
        }
        5 -> {
            mood = "lazy"
            modifier = {message ->
                message.substring(1..7)
            }
        }
        6 -> {
            mood = "1337"
            modifier = {message ->
                message.replace('e','3').replace('t','7')
            }
        }
        7 -> {
            mood = "poetic"
            modifier = {message ->
                message.replace(' ' , '\n')
            }
        }
        else -> {
            mood = "professional"
            modifier = {message ->
                "$message."
            }
        }
    }
    narrationModifier = modifier
    narrate("The narrator begins to feel $mood")
}