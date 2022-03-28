package com.twoplusapp.twoplus.ui.profile

import android.content.Intent
import android.graphics.BitmapFactory
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twoplusapp.twoplus.R
import com.twoplusapp.twoplus.adapters.PostAdapter
import com.twoplusapp.twoplus.database.FirestoreClass
import com.twoplusapp.twoplus.models.PostModel
import com.twoplusapp.twoplus.models.UserModel
import com.twoplusapp.twoplus.utils.Constants
import java.io.FileNotFoundException
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList

class ProfileFragment : Fragment() {

    // This property is only valid between onCreateView and
    // onDestroyView.
    private lateinit var rvUserPosts: RecyclerView
    private lateinit var mPosts: ArrayList<PostModel>
    private lateinit var ivProfile: ImageView
    private lateinit var tvProfileFragUserName: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val rvUserPosts: RecyclerView? = view?.findViewById(R.id.rvUserPosts)
        mPosts = Constants.postsList
        rvUserPosts?.adapter = PostAdapter(mPosts)
        // Set layout manager to position the items
        rvUserPosts?.layoutManager = LinearLayoutManager(activity)

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ivProfile = view.findViewById(R.id.ivProfileIconProfileFragment)
        tvProfileFragUserName = view.findViewById(R.id.tvProfileFragUserName)

        ivProfile.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            uploadImageFromGallery.launch(photoPickerIntent)
        }

        // .loadUserData calls the ProfileFragment.updatedUserProfile method.
        FirestoreClass().fetchUserData(activity=null, fragment=ProfileFragment())
    }

    fun loadUserProfile(loggedInUser: UserModel?){
        loggedInUser?.userProfileImage?.let {it ->
            if (this::ivProfile.isInitialized) {
                ivProfile.setImageResource(it.toInt())
            }
        }

        val userFirstName: String? = loggedInUser?.userFirstName
        val userLastName: String? = loggedInUser?.userLastName
        val name = "$userFirstName $userLastName"
        if (this::tvProfileFragUserName.isInitialized) {
            tvProfileFragUserName.text = name
        }

        // Load the user image into the Profile Fragment
//        if (ivProfileIconProfileFragment != null) {
//            Glide
//                .with(ProfileFragment())
//                .load(loggedInUser?.userProfileImage)
//                .centerCrop()
//                .placeholder(R.drawable.ic_baseline_account_circle_24)
//                .into(ivProfileIconProfileFragment)
//        }
    }

    
    private var uploadImageFromGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            // There are no request codes
            // doSomeOperations();
            val data = result.data
            val selectedImage: Uri? = Objects.requireNonNull(data)?.data
            var imageStream: InputStream? = null
            try {
                imageStream = selectedImage?.let { activity?.contentResolver?.openInputStream(it) }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
            BitmapFactory.decodeStream(imageStream)
            ivProfile.setImageURI(selectedImage) // To display selected image in image view
        }
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//
//    }
}