package com.sundin.beso.ui.profile

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Log.DEBUG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sundin.beso.R
import com.sundin.beso.adapters.PostAdapter
import com.sundin.beso.database.FirestoreClass
import com.sundin.beso.databinding.FragmentProfileBinding
import com.sundin.beso.models.PostModel
import com.sundin.beso.models.UserModel
import com.sundin.beso.utils.Constants
import java.io.FileNotFoundException
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var rvUserPosts: RecyclerView
    private lateinit var mPosts: ArrayList<PostModel>
    private lateinit var ivProfile: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentProfileBinding.inflate(inflater)
        Log.d("bindingVariable", _binding.toString())

        // .loadUserData calls the ProfileFragment.updatedUserProfile method.
        FirestoreClass().fetchUserData(activity=null, fragment=ProfileFragment())

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        // Recyclerview on User Profile - Posts //
        rvUserPosts = _binding!!.rvUserPosts

        mPosts = Constants.postsList
        rvUserPosts.adapter = PostAdapter(mPosts)
        // Set layout manager to position the items
        rvUserPosts.layoutManager = LinearLayoutManager(activity)

        return binding.root
    }

    // TODO: Reference the appropriate IDs.
    fun loadUserProfile(loggedInUser: UserModel?){
        val ivProfileIconProfileFragment = _binding?.ivProfileIconProfileFragment
        val tvProfileName = _binding?.tvProfileName

        loggedInUser?.userProfileImage?.let { ivProfileIconProfileFragment?.setImageResource(it.toInt()) }
        tvProfileName?.text = loggedInUser?.userPersonName
        Log.d("tvProfileName", tvProfileName?.text.toString())

//        val ivUserImage: ImageView = ImageView(context)
        if (loggedInUser != null) {
            loggedInUser.userProfileImage?.let { ivProfileIconProfileFragment?.setImageResource(it.toInt()) }
            tvProfileName?.text = loggedInUser.userPersonName
            Log.d("tvProfileName", tvProfileName?.text.toString())
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

    var uploadImageFromGallery = registerForActivityResult(
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ivProfile = view.findViewById(R.id.ivProfileIconProfileFragment)
            ivProfile.setOnClickListener {
                Log.d("profileImageClick", "outside if statement")

                val photoPickerIntent = Intent(Intent.ACTION_PICK)
                photoPickerIntent.type = "image/*"
                uploadImageFromGallery.launch(photoPickerIntent)
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}