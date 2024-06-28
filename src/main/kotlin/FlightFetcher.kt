@file:Suppress("DEPRECATION")

import java.net.URL
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers

const val BASE_URL = "http://kotlin-book.bignerdranch.com/2e"
const val FLIGHT_ENDPOINT = "$BASE_URL/flight"

fun main() {
    runBlocking {
        println("Started")
        launch {
            val flight = fetchFlight()
            println(flight)
        }
        println("Finished")
    }
}

suspend fun fetchFlight(): String = withContext(Dispatchers.IO) {
    URL(FLIGHT_ENDPOINT).readText()
}
