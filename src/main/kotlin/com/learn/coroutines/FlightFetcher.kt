package com.learn.coroutines

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.*

private const val BASE_URL = "http://kotlin-book.bignerdranch.com/2e"
private const val FLIGHT_ENDPOINT = "$BASE_URL/flight"
private const val LOYALTY_ENDPOINT = "$BASE_URL/loyalty"

suspend fun fetchFlight(passengerName: String): FlightStatus = coroutineScope {
    val client = HttpClient(CIO)

    val flightResponse = async {
        var data: String

        do {
            println("Started fetching flight info")
            data = client.get(FLIGHT_ENDPOINT).bodyAsText().also {
                println("Finished fetching flight info")
            }
        } while (data.contains("canceled", ignoreCase = true))
//        } while (data.split(",")[3].equals("canceled", ignoreCase = true))
        data
    }

    val loyaltyResponse = async {
        println("Started fetching loyalty info")
        client.get(LOYALTY_ENDPOINT).bodyAsText().also {
            println("Finished fetching loyalty info")
        }
    }

//    delay(500)

    println("Combining flight data")
    FlightStatus.parse(
        flightResponse = flightResponse.await(),
        loyaltyResponse = loyaltyResponse.await(),
        passengerName = passengerName
    )
}
