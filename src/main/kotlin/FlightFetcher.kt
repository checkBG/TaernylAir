@file:Suppress("DEPRECATION")

import java.net.URL

fun main() {
    
}

fun fetchFlight(url: String): String = URL(url).readText()