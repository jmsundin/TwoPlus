package com.sundin.beso

import android.content.Context
import java.util.ArrayList
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.sundin.beso.models.ThingToDoModel


class MainAdapter(private val context: Context, val thingToDoModelArrayList: ArrayList<ThingToDoModel?>?
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
        val ivThingToDo: ImageView = thingToDoCardView.findViewById(R.id.ivThingToDo)
        val tvThingToDoTitle: TextView = thingToDoCardView.findViewById(R.id.tvThingToDoTitle)
        val tvThingToDoDescription: TextView = thingToDoCardView.findViewById(R.id.tvThingToDoDescription)
        val tvThingToDoTime: TextView = thingToDoCardView.findViewById(R.id.tvThingToDoTime)
        val tvThingToDoDistanceFromUser: TextView = thingToDoCardView.findViewById(R.id.tvThingToDoDistanceFromUser)
        val tvPeopleGoingToThingToDo: TextView = thingToDoCardView.findViewById(R.id.tvPeopleGoingToThingToDo)
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
        val thingToDo: ThingToDoModel? = thingToDoModelArrayList?.get(position)
        // Set item views based on your views and data model
        val textView = viewHolder.ivThing
        textView.setText(contact.name)
        val button = viewHolder.messageButton
        button.text = if (contact.isOnline) "Message" else "Offline"
        button.isEnabled = contact.isOnline
    }


    override fun getItemCount(): Int {
        // this method is used for showing number
        // of card items in recycler view.
       // return activityModelArrayList.size
        // TODO: take out hardcoded integer
        return thingToDoModelArrayList!!.size
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
            ivThing = itemView.findViewById(R.id.ivThingToDo)
            tvThingTitle = itemView.findViewById(R.id.tvThingToDoTitle)
            tvThingDescription = itemView.findViewById(R.id.tvThingToDoDescription)
            tvThingTime = itemView.findViewById(R.id.tvThingToDoTime)
            tvThingDistanceFromUser = itemView.findViewById(R.id.tvThingToDoDistanceFromUser)
            tvPeopleGoingToThing = itemView.findViewById(R.id.tvPeopleGoingToThingToDo)
        }
    }
}