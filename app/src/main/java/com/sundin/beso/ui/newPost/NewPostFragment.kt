package com.sundin.beso.ui.newPost

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sundin.beso.R

import com.sundin.beso.databinding.FragmentNewPostBinding
import java.text.SimpleDateFormat
import java.util.*

class NewPostFragment : Fragment(R.layout.fragment_new_post) {

    private lateinit var bindingNewPostFragment: FragmentNewPostBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        bindingNewPostFragment = FragmentNewPostBinding.inflate(layoutInflater)

        val datePicker = bindingNewPostFragment.editTextDate
        datePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }

        return bindingNewPostFragment.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }


    private fun clickDatePicker(view: View){

        val myCalendar: Calendar = Calendar.getInstance()
        val currentYear = myCalendar.get(Calendar.YEAR)
        val currentMonth = myCalendar.get(Calendar.MONTH)
        val currentDay = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(requireActivity(), {
                view, selectedYear, selectedMonth, selectedDay ->
            // month returns as an integer, with Jan = 0

            val selectedDate: String = "${selectedMonth + 1}/$selectedDay/$selectedYear"
            var tvSelectedDate: TextView = bindingNewPostFragment.editTextDate
            tvSelectedDate.text = selectedDate

            val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
            val dateObject: Date = sdf.parse(selectedDate)

        }, // the next line provides the current date as the initial calendar date picked
            currentYear, currentMonth, currentDay)

        dpd.datePicker.maxDate = Date().time - 86400000 // subtract number of milliseconds in a day
        dpd.show()
    }
}