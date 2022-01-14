package com.sundin.beso.utils

import com.sundin.beso.R
import com.sundin.beso.models.ThingModel

object Constants {

    // TODO: Take out hardcoded USERS string
    const val USERS: String = "Jon Sundin"

    val thingsList = arrayListOf<ThingModel>(
        ThingModel(
            thingImage = R.drawable.friends_hanging_out_stock_image,
            thingName = "Hanging out",
            thingDescription = "Hanging out with friends and making new ones.",
            thingTime = "7:00 PM",
            thingDate = "Today",
            thingLocation = "1234 Culver Ave\nCulver City, CA",
            thingDistanceFromUser = 1.2F,
            friendsGoingToThing = arrayListOf("Kendall", "George"),
            othersGoingToThing = arrayListOf("Bill")
        ),
        ThingModel(
            thingImage = R.drawable.coffee_stock_image,
            thingName = "Getting coffee",
            thingDescription = "Anyone want to get coffee?",
            thingTime = "11:00 AM",
            thingDate = "Today",
            thingLocation = "1234 Culver Ave\nCulver City, CA",
            thingDistanceFromUser = 0.5F,
            friendsGoingToThing = arrayListOf("Mark", "Sara"),
            othersGoingToThing = arrayListOf("Kenny")
        ),
        ThingModel(
            thingImage = R.drawable.boardgame_stock_image,
            thingName = "Playing boardgames",
            thingDescription = "Who wants to play some boardgames?",
            thingTime = "6:00 PM",
            thingDate = "Today",
            thingLocation = "1234 Culver Ave\nCulver City, CA",
            thingDistanceFromUser = 2.6F,
            friendsGoingToThing = arrayListOf("Mark", "Sara"),
            othersGoingToThing = arrayListOf("Kenny")
        ),
        ThingModel(
            thingImage = R.drawable.basketball_stock_image,
            thingName = "Playing basketball",
            thingDescription = "Who wants to get together for a pickup game of basketball?",
            thingTime = "2:00 PM",
            thingDate = "Today",
            thingLocation = "1234 Culver Ave\nCulver City, CA",
            thingDistanceFromUser = 1.3F,
            friendsGoingToThing = arrayListOf("Mark", "Sara"),
            othersGoingToThing = arrayListOf("Kenny")
    )
    )
}