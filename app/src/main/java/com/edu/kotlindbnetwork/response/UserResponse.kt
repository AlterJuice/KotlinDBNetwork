package com.edu.kotlindbnetwork.response

import com.google.gson.annotations.SerializedName
import java.lang.StringBuilder
import com.edu.kotlindbnetwork.db.user.User as DatabaseUser

data class UserResponse(
    @SerializedName("results") val results: List<User>
) {
    override fun toString(): String {
        return results.toString()
    }
    data class User(

        @SerializedName("id") val id: Id,
        @SerializedName("email") val email: String,
        @SerializedName("phone") val phone: String,
        @SerializedName("name") val name: Name,
        @SerializedName("picture") val picture: Picture
    ) {

        data class Name(@SerializedName("title") val title: String,
                        @SerializedName("first") val first: String,
                        @SerializedName("last") val last: String)

        data class Id(@SerializedName("name") val name: String,
                      @SerializedName("value") val value: String
                      ){ override fun toString(): String { return "$name $value" } }

        data class Picture(@SerializedName("large") val large: String,
                           @SerializedName("medium") val medium: String,
                           @SerializedName("thumbnail") val thumbnail: String)

        override fun toString(): String { return "User<id=${id}, name=$name>" }

    }
}

fun UserResponse.User.toModel(): DatabaseUser = DatabaseUser(
    firstName = "${name.title} ${name.first}",
    lastName = name.last,
    phoneNumber = phone,
    email = email,
    photoUrl = picture.medium,
    uid = id.toString()

)
