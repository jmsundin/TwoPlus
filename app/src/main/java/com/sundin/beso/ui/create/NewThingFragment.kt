package com.sundin.beso.ui.create

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sundin.beso.databinding.FragmentCreateBinding
import java.text.SimpleDateFormat
import java.util.*

class NewThingFragment : Fragment() {

    private lateinit var dashboardViewModel: CreateViewModel
    private var _binding: FragmentCreateBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun clickDatePicker(view: View){

        val myCalendar: Calendar = Calendar.getInstance()
        val currentYear = myCalendar.get(Calendar.YEAR)
        val currentMonth = myCalendar.get(Calendar.MONTH)
        val currentDay = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(requireActivity(), {
                view, selectedYear, selectedMonth, selectedDay ->
            // month returns as an integer, with Jan = 0

            val selectedDate: String = "${selectedMonth + 1}/$selectedDay/$selectedYear"
//            var tvSelectedDate: TextView = binding.tvSelectedDate
//            tvSelectedDate.text = selectedDate

            val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
//            val dateObject: Date = sdf.parse(selectedDate)
//            val selectedDateInMinutes = dateObject.time / 60000

//            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
//            val currentDateInMinutes = currentDate.time / 60000
//
//            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
//
//            val tvMinutesToDate: TextView = binding.tvMinutesToDate
//            tvMinutesToDate.text = differenceInMinutes.toString()


        }, // the next line provides the current date as the initial calendar date picked
            currentYear, currentMonth, currentDay)

        dpd.datePicker.maxDate = Date().time - 86400000 // subtract number of milliseconds in a day
        dpd.show()
    }
}