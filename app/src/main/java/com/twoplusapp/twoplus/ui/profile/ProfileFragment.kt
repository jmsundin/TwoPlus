package com.twoplusapp.twoplus.ui.profile

import android.content.Intent
import android.graphics.BitmapFactory

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
import com.bumptech.glide.Glide
import com.twoplusapp.twoplus.R
import com.twoplusapp.twoplus.adapters.PostAdapter
import com.twoplusapp.twoplus.database.FirestoreClass
import com.twoplusapp.twoplus.databinding.FragmentProfileBinding
import com.twoplusapp.twoplus.models.PostModel
import com.twoplusapp.twoplus.models.UserModel
import com.twoplusapp.twoplus.utils.Constants
import java.io.FileNotFoundException
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var rvUserPosts: RecyclerView
    private lateinit var mPosts: ArrayList<PostModel>
    private lateinit var ivProfile: ImageView
    private lateinit var tvProfileFragUserName: TextView

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        Log.i("binding.root", binding.root.toString())
        return binding.root
    }


    // This event is triggered soon after onCreateView().
    // onViewCreated() is only called if the view returned from onCreateView() is non-null.
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentProfileBinding.bind(view)

        ivProfile = view.findViewById(R.id.ivProfileIconProfileFragment)
        tvProfileFragUserName = view.findViewById(R.id.tvProfileFragUserName)

        ivProfile.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            uploadImageFromGallery.launch(photoPickerIntent)
        }

        // RECYCLERVIEW //
        rvUserPosts = _binding!!.rvUserPosts
        mPosts = Constants.postsList
        // Create adapter, pass in user data, and attach the adapter to the recyclerview to
        // populate items
        rvUserPosts.adapter = PostAdapter(mPosts)
        // Set layout manager to position the items
        rvUserPosts.layoutManager = LinearLayoutManager(activity)

        // .loadUserData calls the ProfileFragment.updatedUserProfile method.
        FirestoreClass().fetchUserData(activity=null, fragment=ProfileFragment())

    }

    fun loadUserProfile(loggedInUser: UserModel?){
//        Log.i("binding.root loadUserProfile", binding.root.toString())
        // Load the user image into the Profile Fragment
//        Glide
//            .with(ProfileFragment())
//            .load(loggedInUser?.userProfileImage)
//            .centerCrop()
//            .placeholder(R.drawable.ic_baseline_account_circle_24)
//            .into(ivProfile)

        loggedInUser?.userProfileImage?.let {it ->
            binding.ivProfileIconProfileFragment.setImageResource(it.toInt())
        }

        val userFirstName: String? = loggedInUser?.userFirstName
        val userLastName: String? = loggedInUser?.userLastName
        val name = "$userFirstName $userLastName"
        binding.tvProfileFragUserName.text = name

    }

    private var uploadImageFromGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}