package com.learn.coroutines

data class FlightStatus(
    val flightNumber: String,
    val passengerName: String,
    val passengerLoyaltyTier: String,
    val originAirport: String,
    val destinationAirport: String,
    val status: String,
    val departureTimeInMinutes: Int
) {
    companion object {
        fun parse(
            flightResponse: String,
            loyaltyResponse: String,
            passengerName: String
        ): FlightStatus {
            val (flightNumber, originAirport, destinationAirport, status, departureTimeInMinutes) = flightResponse.split(
                ","
            )

            val (loyaltyTierName, milesFlown, milesToNextTier) = loyaltyResponse.split(",")

            return FlightStatus(
                flightNumber = flightNumber,
                passengerName = passengerName,
                passengerLoyaltyTier = loyaltyTierName,
                originAirport = originAirport,
                destinationAirport = destinationAirport,
                status = status,
                departureTimeInMinutes = departureTimeInMinutes.toInt()
            )
        }
    }
}

enum class LoyaltyTier(
    val tierName: String,
    val boardingWindowStart: Int
) {
    Bronze("Bronze", 25),
    Silver("Silver", 25),
    Gold("Gold", 30),
    Platinum("Platinum", 35),
    Titanium("Titanium", 40),
    Diamond("Diamond", 45),
    DiamondPlus("Diamond+", 50),
    DiamondPlusPlus("Diamond++", 60)
}

enum class BoardingState {
    FlightCanceled,
    BoardingNotStarting,
    WaitingToBoard,
    Boarding,
    BoardingEnded
}


















