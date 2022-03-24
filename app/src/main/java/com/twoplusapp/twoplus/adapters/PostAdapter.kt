package com.twoplusapp.twoplus.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.sundin.twoplus.R
import com.twoplusapp.twoplus.activities.PostActivity
import com.twoplusapp.twoplus.models.PostModel


class PostAdapter (private val mPosts: ArrayList<PostModel>): RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val ivPost: ImageView = itemView.findViewById(R.id.ivPost)
        val tvPostTitle: TextView = itemView.findViewById(R.id.tvPostTitle)
        val tvPostDescription: TextView = itemView.findViewById(R.id.tvPostDescription)
        val tvPostTime: TextView = itemView.findViewById(R.id.tvPostTime)
        val tvPostDistanceFromUser: TextView = itemView.findViewById(R.id.tvPostDistanceFromUser)
        val tvPeopleGoing: TextView = itemView.findViewById(R.id.tvPeopleGoing)

        val cardView: ConstraintLayout = itemView.findViewById(R.id.postView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val postView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        // Return a new holder instance
        return ViewHolder(postView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get the data model based on position
        val post: PostModel = mPosts[position]
        // Set item views based on your views and data model
        val ivPost = viewHolder.ivPost
        ivPost.setImageResource(post.postImage)

        val tvPostTitle = viewHolder.tvPostTitle
        tvPostTitle.text = post.postTitle

        val tvPostDescription = viewHolder.tvPostDescription
        tvPostDescription.text = post.postDescription

        val tvPostTime = viewHolder.tvPostTime
        tvPostTime.text = post.postTime

        val tvPostDistanceFromUser = viewHolder.tvPostDistanceFromUser
        tvPostDistanceFromUser.text = post.postLocation

//        val tvPeopleGoing = viewHolder.tvPeopleGoing
//        tvPeopleGoing.text = post.friendsGoing[]

        viewHolder.cardView.setOnClickListener {
            val intent: Intent = Intent(viewHolder.itemView.context, PostActivity::class.java)
            intent.putExtra("postImage", post.postImage)
            intent.putExtra("postTitle", post.postTitle)
            intent.putExtra("postDescription", post.postDescription)
            intent.putExtra("postTime", post.postTime)
            intent.putExtra("postDate", post.postDate)
            intent.putExtra("postLocation", post.postLocation)
            intent.putExtra("postDistanceFromUser", post.postDistanceFromUser)
            intent.putExtra("friendsGoing", post.friendsGoing)
            intent.putExtra("othersGoing", post.othersGoing)

            viewHolder.cardView.context.startActivity(intent);
        }

    }

    override fun getItemCount(): Int {
        return mPosts.size
    }
}