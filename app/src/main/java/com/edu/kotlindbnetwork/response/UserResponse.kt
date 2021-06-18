package com.edu.kotlindbnetwork.response

import com.edu.kotlindbnetwork.Consts
import com.google.gson.annotations.SerializedName
import java.lang.StringBuilder
import kotlin.math.log
import com.edu.kotlindbnetwork.db.user.User as DatabaseUser

data class UserResponse(
    @SerializedName(Consts.apiKeyResults) val results: List<User>
) {
    override fun toString(): String {
        return results.toString()
    }
    data class User(

        @SerializedName(Consts.apiKeyUserLogin) val login: Login,
        @SerializedName(Consts.apiKeyUserEmail) val email: String,
        @SerializedName(Consts.apiKeyUserPhone) val phone: String,
        @SerializedName(Consts.apiKeyUserName) val name: Name,
        @SerializedName(Consts.apiKeyUserPicture) val picture: Picture
    ) {

        data class Name(@SerializedName(Consts.apiKeyUserNameTitle) val title: String,
                        @SerializedName(Consts.apiKeyUserNameFirst) val first: String,
                        @SerializedName(Consts.apiKeyUserNameLast) val last: String)

        data class Picture(@SerializedName(Consts.apiKeyUserPictureLarge) val large: String,
                           @SerializedName(Consts.apiKeyUserPictureMedium) val medium: String,
                           @SerializedName(Consts.apiKeyUserPictureThumbnail) val thumbnail: String)

        data class Login(@SerializedName(Consts.apiKeyUserLoginUUID) val uuid: String)

        override fun toString(): String { return "User<id=${login.uuid}, name=$name>" }

    }
}

fun UserResponse.User.toModel(): DatabaseUser = DatabaseUser(
    firstName = "${name.title} ${name.first}",
    lastName = name.last,
    phoneNumber = phone,
    email = email,
    photoUrl = picture.large,
    uid = login.uuid

)
