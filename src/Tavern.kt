import java.io.File
import kotlin.random.Random
import kotlin.random.nextInt

private const val TAVERN_MASTER = "Taernyl"
private const val TAVERN_NAME = "$TAVERN_MASTER's folly"
var big: Int = 0

private val firstNames = setOf("Alex", "Mordoc", "Sophie", "Tariq")
private val lastNames = setOf("Ironfoot", "Fernsworth", "Baggins", "Downstrider")

private val menuData = File("data/tavern-menu-data.txt").readText().split("\n").map {it.split(",")}
private val menuItemName = mutableListOf<String>()

private val menuItems = menuData.map { menuEntry ->
    val (_,name,price) = menuEntry
    if(big < "$name holder $price".length){
        big = "$name holder $price".length
    }
    menuItemName += name
        "$name holder $price"
}

private val menuItemPrice = menuData.associate { menuEnty ->
    val (_,name,price) = menuEnty
    name to price.toDouble()
}

private val menuItemTypes= menuData.associate { menuEnty ->
    val (type, name, _) = menuEnty
    name to type
}

fun visitTavern() {
    narrate("$heroName enters the $TAVERN_NAME")
    narrate("There are several items for sale:")

   menuItems.forEachIndexed item@{ index, item ->
       if(index == menuItems.lastIndex) {
           println(item.replace(" holder ", ".".repeat((big - item.length) + 4)))
           return@item
       }
       println(item.replace(" holder ", ".".repeat((big-item.length)+5)))
   }

    val patrons: MutableSet<String> = mutableSetOf()
    val patronGold = mutableMapOf(
        TAVERN_MASTER to 86.00,
        heroName to 4.50
    )
    while(patrons.size < 5) {
        val patronName = "${firstNames.random()} ${lastNames.random()}"
        patrons += patronName
        patronGold += patronName to 6.0
    }


    narrate("$heroName sees several patrons in the tavern.")

    narrate(patrons.joinToString())

    val itemOfDay = patrons.flatMap { getFavoriteMenuItems(it)}.random()
    println("item of the day: $itemOfDay")

    displayPatronBalances(patronGold)
    repeat(3){
        placeOrder(patrons.random(), menuItemName.random(), patronGold)
    }
    displayPatronBalances(patronGold)

    val departingPatrons: List<String> = patrons.filter { patron -> patronGold.getOrDefault(patron, 0.0) < 4.0 }
    patrons -= departingPatrons
    patronGold -= departingPatrons
    departingPatrons.forEach { patron -> narrate("$heroName sees $patron departing the tavern") }

    narrate("There are still some patrons in the tavern")
    narrate(patrons.joinToString())

}

private fun getFavoriteMenuItems(patron: String) : List<String> {
    return when (patron) {
        "ALex Ironfoot" -> menuItemName.filter { menuItem ->
            menuItemTypes[menuItem]?.contains("desert") == true
        }
        else -> menuItemName.shuffled().take(Random.nextInt(1..2))
    }
}


private fun displayPatronBalances(patronGold: Map<String, Double>) {
    narrate("$heroName intuitively knows how much money each patron has")
    patronGold.forEach {(patron, balance) ->
        narrate("$patron has ${"%.2f".format(balance)} gold")

    }
}

private fun placeOrder(patronName: String, menuItemName: String, patronGold: MutableMap<String, Double>){
    val itemPrice = menuItemPrice.getValue(menuItemName)

    narrate("$patronName speaks with $TAVERN_MASTER to place an order.")
    if(itemPrice <= patronGold.getOrDefault(patronName, 0.0)) {
        val action = when(menuItemTypes[menuItemName]) {
            "shandy", "elixir" -> "pours"
            "meal" -> "serves"
            else -> "hands"
        }
        narrate("$TAVERN_MASTER $action $patronName a $menuItemName")
        narrate("$patronName pays $TAVERN_MASTER $itemPrice gold.")
        patronGold[patronName] = patronGold.getValue(patronName) - itemPrice
        patronGold[TAVERN_MASTER] = patronGold.getValue(TAVERN_MASTER) + itemPrice
    } else {
        narrate("$TAVERN_MASTER says, \"You need more coin for a $menuItemName \"")
    }
}