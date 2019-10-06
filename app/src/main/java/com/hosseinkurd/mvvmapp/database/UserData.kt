package com.hosseinkurd.loginsample.database

import com.google.gson.annotations.SerializedName

class UserData {

    @SerializedName("address")
    lateinit var address: String
    @SerializedName("first_name")
    lateinit var first_name: String
    @SerializedName("last_name")
    lateinit var last_name: String
    @SerializedName("coordinate_mobile")
    lateinit var mobile: String
    @SerializedName("coordinate_phone_number")
    lateinit var phone: String
    @SerializedName("gender")
    var gender: Boolean = false
    @SerializedName("region")
    var region: Int = 0
    @SerializedName("lat")
    var lat: Double = 0.0
    @SerializedName("lng")
    var lng: Double = 0.0

    constructor()

    constructor(
        address: String,
        first_name: String,
        last_name: String,
        mobile: String,
        phone: String,
        gender: Boolean,
        region: Int,
        lat: Double,
        lng: Double
    ) {
        this.address = address
        this.first_name = first_name
        this.last_name = last_name
        this.mobile = mobile
        this.phone = phone
        this.gender = gender
        this.region = region
        this.lat = lat
        this.lng = lng
    }
}
