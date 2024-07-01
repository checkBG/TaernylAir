import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
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

suspend fun fetchFlight(): String {
    val client = HttpClient(CIO)
    return client.get(FLIGHT_ENDPOINT, ).bodyAsText()
}
