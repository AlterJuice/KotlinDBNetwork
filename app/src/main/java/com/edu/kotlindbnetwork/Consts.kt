package com.edu.kotlindbnetwork

object Consts {
    // Consts file move to ...?
    // Replace ([A-Z]) to \_\L$1

    const val MODULE_TAG_API_SERVICE = "apiService"
    // const val MODULE_TAG_USER_REPO = "apiService"

    const val FRAGMENT_USER_PROFILE_TAG = "_user_profile"
    const val FRAGMENT_USER_PROFILE_ARG_USER_ID = "user_id"
    const val COUNT_USERS_PER_REQUEST = 20
    const val DATABASE_FILENAME = "main_db"
    const val BASE_API_URL = "https://randomuser.me/"
    const val INCLUDED_PARAMS = "id,name,email,phone,picture,login"
    const val API_URL_TAG = "api"
    const val API_KEY_RESULTS = "results"
    const val API_KEY_USER_ID = "id"
    const val API_KEY_USER_LOGIN = "login"
    const val API_KEY_USER_LOGIN_UUID = "uuid"

    // const val API_KEY_USER_ID_NAME = "name"
    // const val API_KEY_USER_ID_VALUE = "value"
    const val API_KEY_USER_EMAIL = "email"
    const val API_KEY_USER_PHONE = "phone"
    const val API_KEY_USER_NAME = "name"
    const val API_KEY_USER_NAME_TITLE = "title"
    const val API_KEY_USER_NAME_FIRST = "first"
    const val API_KEY_USER_NAME_LAST = "last"
    const val API_KEY_USER_PICTURE = "picture"
    const val API_KEY_USER_PICTURE_LARGE = "large"
    const val API_KEY_USER_PICTURE_MEDIUM = "medium"
    const val API_KEY_USER_PICTURE_THUMBNAIL = "thumbnail"
    const val API_KEY_COUNT_USERS = "results"
    const val API_KEY_INCLUDE = "inc"
    const val TABLE_NAME_USERS = "users"
}