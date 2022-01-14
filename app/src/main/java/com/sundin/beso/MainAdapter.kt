package com.sundin.beso

import android.content.Context
import java.util.ArrayList
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.sundin.beso.models.ThingModel


class MainAdapter(private val context: Context, private val thingsList: ArrayList<ThingModel>
) : RecyclerView.Adapter<MainAdapter.ViewHolder>(){
    //private val activityModelArrayList: ArrayList<ThingModel>

    // Constructor
    init {
        //activityModelArrayList = thingModelArrayList
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(thingToDoCardView: View)
        : RecyclerView.ViewHolder(thingToDoCardView){
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val ivThing: ImageView = thingToDoCardView.findViewById(R.id.ivThing)
        val tvThingTitle: TextView = thingToDoCardView.findViewById(R.id.tvThingTitle)
        val tvThingDescription: TextView = thingToDoCardView.findViewById(R.id.tvThingDescription)
        val tvThingTime: TextView = thingToDoCardView.findViewById(R.id.tvThingTime)
        val tvThingDistanceFromUser: TextView = thingToDoCardView.findViewById(R.id.tvThingDistanceFromUser)
        val tvPeopleGoingToThing: TextView = thingToDoCardView.findViewById(R.id.tvPeopleGoingToThing)
    }


    // ... constructor and member variables
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: MainAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val thing: ThingModel = thingsList[position]
        // Set item views based on your views and data model
        val ivThing: ImageView = viewHolder.ivThing
        val tvThingTitle: TextView = viewHolder.tvThingTitle
        val tvThingDescription: TextView = viewHolder.tvThingDescription
        val tvThingTime: TextView = viewHolder.tvThingTime
        val tvThingDistanceFromUser: TextView = viewHolder.tvThingDistanceFromUser
        val tvPeopleGoingToThing: TextView = viewHolder.tvPeopleGoingToThing

        ivThing.setImageResource(thing.thingImage)
        tvThingTitle.text = thing.thingName
        tvThingDescription.text = thing.thingDescription
        tvThingTime.text = thing.thingTime
        tvThingDistanceFromUser.text = thing.thingDistanceFromUser.toString()
        tvPeopleGoingToThing.text = thing.friendsGoingToThing.toString()
    }


    override fun getItemCount(): Int {
        // this method is used for showing number
        // of card items in recycler view.
       // return activityModelArrayList.size
        // TODO: take out hardcoded integer
        return thingsList.size
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivThing: ImageView
        private val tvThingTitle: TextView
        private val tvThingDescription: TextView
        private val tvThingTime: TextView
        private val tvThingDistanceFromUser: TextView
        private val tvPeopleGoingToThing: TextView

        init {
            ivThing = itemView.findViewById(R.id.ivThing)
            tvThingTitle = itemView.findViewById(R.id.tvThingTitle)
            tvThingDescription = itemView.findViewById(R.id.tvThingDescription)
            tvThingTime = itemView.findViewById(R.id.tvThingTime)
            tvThingDistanceFromUser = itemView.findViewById(R.id.tvThingDistanceFromUser)
            tvPeopleGoingToThing = itemView.findViewById(R.id.tvPeopleGoingToThing)
        }
    }
}