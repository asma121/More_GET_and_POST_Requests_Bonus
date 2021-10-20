package com.example.moregetandpostrequestsbonus

import com.google.gson.annotations.SerializedName

class UsersDetails {
    @SerializedName("data")
    var data: List<Datum>? = null

    class Datum{
        @SerializedName("pk")
        var pk: String? = null

        @SerializedName("name")
        var name: String? = null

        @SerializedName("location")
        var location: String? = null

        @SerializedName("mobile")
        var mobile: String? = null

        @SerializedName("email")
        var email: String? = null

        constructor(name: String?, location:String?,mobile:String?,email:String?) {
            this.name = name
            this.location = location
            this.mobile = mobile
            this.email = email
        }
    }
}