import java.net.URL

fun main() {
    val flight = fetchFlight()
    println(flight)
}

fun fetchFlight(url: String): String = URL(url).readText()