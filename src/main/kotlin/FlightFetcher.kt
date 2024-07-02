import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private const val BASE_URL = "http://kotlin-book.bignerdranch.com/2e"
private const val FLIGHT_ENDPOINT = "$BASE_URL/flight"
private const val LOYALTY_ENDPOINT = "$BASE_URL/loyalty"

fun main() {
    runBlocking {
        println("Started")
        launch {
            val flight = fetchFlight("Violett")
            println(flight)
        }
        println("Finished")
    }
}

suspend fun fetchFlight(passengerName: String): FlightStatus {
    val client = HttpClient(CIO)

    println("Started fetching flight info")
    val flightResponse = client.get(FLIGHT_ENDPOINT).bodyAsText().also {
        println("Finished fetching flight info")
    }

    println("Started fetching loyalty info")
    val loyaltyResponse = client.get(LOYALTY_ENDPOINT).bodyAsText().also {
        println("Finished fetching loyalty info")
    }

    println("Combining flight data")
    return FlightStatus.parse(
        flightResponse = flightResponse,
        loyaltyResponse = loyaltyResponse,
        passengerName = passengerName
    )
}
