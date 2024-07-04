package com.learn.coroutines

import kotlinx.atomicfu.atomic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

const val passengersPerFlight = 75
const val numberOfFlights = 1000

val checkedInPassengers = atomic(0)

fun main() {
    runBlocking {
        repeat(numberOfFlights) {
            launch(Dispatchers.Unconfined) {
//                checkedInPassengers.addAndGet(passengersPerFlight)
                checkedInPassengers.plusAssign(passengersPerFlight)
            }
        }
        println(checkedInPassengers.value)
    }
}
