@file:Suppress("DEPRECATION")

const val BASE_URL = "http://kotlin-book.bignerdranch.com/2e"
const val FLIGHT_ENDPOINT = "$BASE_URL/flight"

fun main() {
    println("Started")
    val flight = fetchFlight()
    println(flight)
    println("Finished")
}

fun fetchFlight(): String = java.net.URL(FLIGHT_ENDPOINT).readText()
