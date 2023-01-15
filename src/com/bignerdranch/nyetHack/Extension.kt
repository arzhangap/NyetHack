package com.bignerdranch.nyetHack

fun String.addEnthusiasm(enthusiasmLevel: Int = 1) =
    this + "!".repeat(enthusiasmLevel)

fun <T> T.print(): T {
    println(this)
    return this
}
infix fun Coordinate.move(direction: Direction) =
    direction.updateCoordinate(this)

val String.numVowels
    get() = count { it.lowercase() in "aeiou" }

fun Room?.orEmptyRoom(name: String = "the middle of nowhere"): Room =
    this ?: Room(name)

fun String.frame(padding: Int = 5, formatChar: String = "*") : String {
    val middle = formatChar
        .padEnd(padding)
        .plus(this)
        .plus(formatChar.padStart(padding))
    val end = (0 until middle.length).joinToString("") {formatChar}
    return "$end\n$middle\n$end\n"
}