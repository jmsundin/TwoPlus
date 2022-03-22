package com.sundin.beso.utils

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import com.sundin.beso.R
import com.sundin.beso.models.PostModel
import com.sundin.beso.models.UserModel
import com.sundin.beso.ui.profile.ProfileFragment

object Constants {

    // TODO: Take out hardcoded USERS string
    const val USERS:String = "Users"
    const val BESO_PREFERENCES: String = "besoPrefs"

    const val FCM_TOKEN:String = "fcmToken"
    const val FCM_TOKEN_UPDATED:String = "fcmTokenUpdated"

    // TODO (Step 1: Add the base url  and key params for sending firebase notification.)
    // START
    const val FCM_BASE_URL:String = "https://fcm.googleapis.com/fcm/send"
    const val FCM_AUTHORIZATION:String = "authorization"
    const val FCM_KEY:String = "key"
    const val FCM_SERVER_KEY:String = "AAAA-_vvGNI:APA91bF9xfSzbacs2j9RKkmEg7aYqY4pmRr89vYoy8pOfr0Ds2yHyVlhkhDiryrPndNYbXHUYyCdzZakvrlxxDLjsyQv5Ybtom5dFr7VWaMzDOL6YcSF-09GAOoxHU7SAisyZ222PW3w"
    const val FCM_KEY_TITLE:String = "title"
    const val FCM_KEY_MESSAGE:String = "message"
    const val FCM_KEY_DATA:String = "data"
    const val FCM_KEY_TO:String = "to"
    // END

    //A unique code for asking the Read Storage Permission using this we will be check and identify in the method onRequestPermissionsResult
    const val READ_STORAGE_PERMISSION_CODE = 1
    // A unique code of image selection from Phone Storage.
    const val PICK_IMAGE_REQUEST_CODE = 2

    val userObj = UserModel(
        uid = "332323eee23p23",
        userProfileImage = R.drawable.man1_stock_photo.toString(),
        userPersonName = "John Smith",
        userEmail = "johnsmith@gmail.com"
        )

    val postsList = arrayListOf(
        PostModel(
            postImage = R.drawable.friends_hanging_out_stock_image,
            postTitle = "Hanging out",
            postDescription = "Hanging out with friends and making new ones.",
            postTime = "7:00 PM",
            postDate = "Today",
            postLocation = "1234 Culver Ave\nCulver City, CA",
            postDistanceFromUser = 1.2F,
            friendsGoing = arrayListOf("Kendall", "George"),
            othersGoing = arrayListOf("Bill")
        ),
        PostModel(
            postImage = R.drawable.coffee_stock_image,
            postTitle = "Getting coffee",
            postDescription = "Anyone want to get coffee?",
            postTime = "11:00 AM",
            postDate = "Today",
            postLocation = "1234 Culver Ave\nCulver City, CA",
            postDistanceFromUser = 0.5F,
            friendsGoing = arrayListOf("Mark", "Sara"),
            othersGoing = arrayListOf("Kenny")
        ),
        PostModel(
            postImage = R.drawable.boardgame_stock_image,
            postTitle = "Playing boardgames",
            postDescription = "Who wants to play some boardgames?",
            postTime = "6:00 PM",
            postDate = "Today",
            postLocation = "1234 Culver Ave\nCulver City, CA",
            postDistanceFromUser = 2.6F,
            friendsGoing = arrayListOf("Mark", "Sara"),
            othersGoing = arrayListOf("Kenny")
        ),
        PostModel(
            postImage = R.drawable.basketball_stock_image,
            postTitle = "Playing basketball",
            postDescription = "Who wants to get together for a pickup game of basketball?",
            postTime = "2:00 PM",
            postDate = "Today",
            postLocation = "1234 Culver Ave\nCulver City, CA",
            postDistanceFromUser = 1.3F,
            friendsGoing = arrayListOf("Mark", "Sara"),
            othersGoing = arrayListOf("Kenny")
        )
    )

    /**
     * A function for user profile image selection from phone storage.
     */
    fun showImageChooserActivity(activity: Activity) {
        // An intent for launching the image selection of phone storage.
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        // Launches the image selection of phone storage using the constant code.
        activity.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }

    fun showImageChooserFragment(fragment: Fragment){
        // An intent for launching the image selection of phone storage.
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        // Launches the image selection of phone storage using the constant code.
        fragment.startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }
}