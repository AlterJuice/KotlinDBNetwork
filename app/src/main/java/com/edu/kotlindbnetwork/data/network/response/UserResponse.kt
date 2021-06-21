package com.edu.kotlindbnetwork.data.network.response

import com.edu.kotlindbnetwork.Consts
import com.google.gson.annotations.SerializedName
import com.edu.kotlindbnetwork.data.db.user.User as DatabaseUser

data class UserResponse(
    @SerializedName(Consts.API_KEY_RESULTS) val results: List<User>
) {
    override fun toString(): String {
        return results.toString()
    }
    data class User(

        @SerializedName(Consts.API_KEY_USER_LOGIN) val login: Login,
        @SerializedName(Consts.API_KEY_USER_EMAIL) val email: String,
        @SerializedName(Consts.API_KEY_USER_PHONE) val phone: String,
        @SerializedName(Consts.API_KEY_USER_NAME) val name: Name,
        @SerializedName(Consts.API_KEY_USER_PICTURE) val picture: Picture
    ) {

        data class Name(@SerializedName(Consts.API_KEY_USER_NAME_TITLE) val title: String,
                        @SerializedName(Consts.API_KEY_USER_NAME_FIRST) val first: String,
                        @SerializedName(Consts.API_KEY_USER_NAME_LAST) val last: String)

        data class Picture(@SerializedName(Consts.API_KEY_USER_PICTURE_LARGE) val large: String,
                           @SerializedName(Consts.API_KEY_USER_PICTURE_MEDIUM) val medium: String,
                           @SerializedName(Consts.API_KEY_USER_PICTURE_THUMBNAIL) val thumbnail: String)

        data class Login(@SerializedName(Consts.API_KEY_USER_LOGIN_UUID) val uuid: String)

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
