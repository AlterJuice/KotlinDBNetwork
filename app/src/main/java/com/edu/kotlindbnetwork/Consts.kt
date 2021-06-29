package com.edu.kotlindbnetwork

object Consts {

    const val MODULE_TAG_REPO_NETWORK = "networkRepo"
    const val MODULE_TAG_REPO_DATABASE = "databaseRepo"

    const val FRAGMENT_USER_PROFILE_TAG = "_user_profile"
    const val FRAGMENT_USER_PROFILE_ARG_USER_ID = "user_id"

    const val DATABASE_FILENAME = "main_db"
    const val TABLE_NAME_USERS = "users"
    const val TABLE_USERS_FIELD_FIRST_NAME = "first_name"
    const val TABLE_USERS_FIELD_LAST_NAME = "last_name"
    const val TABLE_USERS_FIELD_PHONE_NUMBER = "phone_number"
    const val TABLE_USERS_FIELD_EMAIL = "email"
    const val TABLE_USERS_FIELD_PHOTO_URL = "photo_url"

    const val BASE_API_URL = "https://randomuser.me/"
    const val INCLUDED_PARAMS = "id,name,email,phone,picture,login"
    const val COUNT_USERS_PER_REQUEST = 20

    const val API_URL_TAG = "api"
    const val API_KEY_RESULTS = "results"
    const val API_KEY_USER_LOGIN = "login"
    const val API_KEY_USER_LOGIN_UUID = "uuid"

    const val API_KEY_USER_EMAIL = "email"
    const val API_KEY_USER_PHONE = "phone"
    const val API_KEY_USER_NAME = "name"
    const val API_KEY_USER_NAME_TITLE = "title"
    const val API_KEY_USER_NAME_FIRST = "first"
    const val API_KEY_USER_NAME_LAST = "last"
    const val API_KEY_USER_PICTURE = "picture"
    const val API_KEY_USER_PICTURE_LARGE = "large"
    const val API_KEY_COUNT_USERS = "results"
    const val API_KEY_SEED_OFFSET = "seed"
    const val API_KEY_INCLUDE = "inc"

}