package com.sundin.beso.models

import android.os.Parcel
import android.os.Parcelable

data class UserModel(
    val uid: String?,
    val userEmail: String?,
    val userProfileImage: String?,
    val userPersonName: String?,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uid)
        if (userProfileImage != null) {
            parcel.writeString(userProfileImage)
        }
        parcel.writeString(userPersonName)
        parcel.writeString(userEmail)
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
