package com.learn.coroutines

import com.learn.coroutines.BoardingState.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        println("Getting the latest flight info...")

        val flights = fetchFlights()
        val flightDescriptions = flights.joinToString {
            "${it.passengerName} (${it.flightNumber})"
        }

        println("Found flights for $flightDescriptions")

        val flightsAtGate = MutableStateFlow(value = flights.size)

        launch {
            flightsAtGate.collect { flightCount ->
                println("There are $flightCount flights being tracked")
            }
        }

        launch {
            flights.forEach {
                watchFlight(it)
//                flightsAtGate.value--
            }
        }
    }
}

suspend fun watchFlight(initialFlight: FlightStatus) {
    val passengerName = initialFlight.passengerName

    val currentFlight: Flow<FlightStatus> = flow {
        var flight = initialFlight

        while (flight.departureTimeInMinutes >= 0 && !flight.isFlightCanceled) {
            emit(flight)
            delay(100)
            flight = flight.copy(
                departureTimeInMinutes = flight.departureTimeInMinutes - 1
            )
        }
    }

    currentFlight.collect {
        val status = when (it.boardingStatus) {
            FlightCanceled -> "Your flight was canceled"
            BoardingNotStarted -> "Boarding will start soon"
            WaitingToBoard -> "Other passengers are boarding"
            Boarding -> "You can now board the plane"
            BoardingEnded -> "The boarding doors have closed"
        } + " (Flight departs in ${it.departureTimeInMinutes} minutes)"

        println("$passengerName: $status")
    }

    println("Finished tracking $passengerName's flight")
}

suspend fun fetchFlights(
    passengerNames: List<String> = listOf("Madrigal", "Polarcubis")
) = passengerNames.map { fetchFlight(it) }