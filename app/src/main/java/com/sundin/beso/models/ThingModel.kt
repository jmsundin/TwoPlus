package com.sundin.beso.models

data class ThingModel (
    val thingImage: Int,
    val thingName: String,
    val thingDescription: String,
    val thingTime: String,
    val thingDate: String,
    val thingLocation: String,
    val thingDistanceFromUser: Float,
    val peopleGoingToThing: List<String>)