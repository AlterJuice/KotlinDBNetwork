package com.edu.kotlindbnetwork.data.db.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.edu.kotlindbnetwork.Consts

@Entity(tableName = Consts.TABLE_NAME_USERS)
class User(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    @ColumnInfo(name = "phone_number") val phoneNumber: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "photo_url") val photoUrl: String?
) {
    override fun toString(): String {
        return "<DBUser: $firstName $lastName ($phoneNumber, $email)>"
    }

    class Factory {
        companion object {
            fun createEmptyUser(): User {
                return buildFromEmptyUser().build()
            }

            fun createFromUId(uid: String): User {
                return buildFromUId(uid).build()
            }

            fun buildFromUId(uid: String): Builder {
                return Builder()
                    .uuid(uid)
                    .firstname("Firstname$uid")
                    .lastname("Lastname$uid")
                    .phoneNumber("PhoneNumber$uid")
                    .email("Email$uid")
            }

            fun buildFromEmptyUser(): Builder {
                return Builder()
            }
        }
    }

    class Builder {

        private var uuid: String = ""
        private var firstName: String = ""
        private var lastName: String = ""
        private var phoneNumber: String = ""
        private var email: String = ""
        private var photoUrl: String = ""

        fun uuid(uuid: String): Builder {
            this.uuid = uuid
            return this
        }

        fun firstname(firstName: String): Builder {
            this.firstName = firstName
            return this
        }

        fun lastname(lastName: String): Builder {
            this.lastName = lastName
            return this
        }

        fun name(firstName: String, lastName: String): Builder {
            return firstname(firstName).lastname(lastName)
        }

        fun email(email: String): Builder {
            this.email = email
            return this
        }

        fun phoneNumber(phoneNumber: String): Builder {
            this.phoneNumber = phoneNumber
            return this
        }

        fun photoUrl(photoUrl: String): Builder {
            this.photoUrl = photoUrl
            return this
        }

        fun build(): User {
            return User(uuid, firstName, lastName, phoneNumber, email, photoUrl)
        }
    }
}