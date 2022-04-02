package com.twoplusapp.twoplus.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.twoplusapp.twoplus.adapters.PostAdapter
import com.twoplusapp.twoplus.utils.Constants
import com.twoplusapp.twoplus.databinding.FragmentHomeBinding
import com.twoplusapp.twoplus.models.PostModel

class HomeFragment : Fragment() {

    private lateinit var mPosts: ArrayList<PostModel>

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // RECYCLERVIEW //
        val rvPosts = _binding!!.rvPosts
        mPosts = Constants.postsList
        // Create adapter, pass in user data, and attach the adapter to the recyclerview to
        // populate items
        rvPosts.adapter = PostAdapter(mPosts)
        // Set layout manager to position the items
        rvPosts.layoutManager = LinearLayoutManager(activity)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}