import java.net.URL

const val BASE_URL = "http://kotlin-book.bignerdranch.com/2e"
const val FLIGHT_ENDPOINT = "$BASE_URL/flight"

fun main() {
    val flight = fetchFlight()
    println(flight)
}

fun fetchFlight(): String = URL(FLIGHT_ENDPOINT).readText()
