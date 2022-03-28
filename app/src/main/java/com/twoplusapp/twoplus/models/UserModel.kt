package com.twoplusapp.twoplus.models

import android.os.Parcel
import android.os.Parcelable

data class UserModel(
    val uid: String? = "",
    val userEmail: String? = "",
    val userProfileImage: String? = "",
    val userFirstName: String? = "",
    val userLastName: String? = "",
    ): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
    ) {
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uid)
        parcel.writeString(userEmail)
        parcel.writeString(userProfileImage)
        parcel.writeString(userFirstName)
        parcel.writeString(userLastName)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }
}
