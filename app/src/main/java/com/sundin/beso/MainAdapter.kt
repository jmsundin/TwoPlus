package com.sundin.beso

import android.content.Context
import java.util.ArrayList
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.sundin.beso.databinding.ActivityMainBinding
import com.sundin.beso.databinding.RecyclerviewItemBinding
import com.sundin.beso.models.ThingModel

class MainAdapter(private val context: Context, acitivityModelArrayList: ArrayList<ThingModel?>?
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private val activityModelArrayList: ArrayList<ThingModel>

    inner class MainViewHolder(val itemBinding: RecyclerviewItemBinding)
        : RecyclerView.ViewHolder(itemBinding.root){
        fun bindItem(){
//            itemBinding.
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        // to inflate the layout for each item of recycler view.
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_home, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        // to set data to textview and imageview of each card layout
        val model = activityModelArrayList[position]
//        holder.

    }

    override fun getItemCount(): Int {
        // this method is used for showing number
        // of card items in recycler view.
        return activityModelArrayList.size
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val courseIV: ImageView
        private val courseNameTV: TextView
        private val courseRatingTV: TextView

        init {
            courseIV = itemView.findViewById(R.id.idIVCourseImage)
            courseNameTV = itemView.findViewById(R.id.idTVCourseName)
            courseRatingTV = itemView.findViewById(R.id.idTVCourseRating)
        }
    }

    // Constructor
    init {
        activityModelArrayList = activityModelArrayList
    }
}