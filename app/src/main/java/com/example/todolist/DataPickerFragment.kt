package com.example.todolist

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.todolist.addList.KEY_FOR_DATE
import java.util.*

class DataPickerFragment:DialogFragment() {

    interface DataPickerCallBack{
        fun onDateSelected(date: Date)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val date=arguments?.getSerializable(KEY_FOR_DATE) as Date

        val calender=Calendar.getInstance()
        calender.time=date

        val year =calender.get(Calendar.YEAR)
        val month=calender.get(Calendar.MONTH)
        val day=calender.get(Calendar.DAY_OF_MONTH)

        val dateListener= DatePickerDialog.OnDateSetListener { _, year, month, day ->

            val resultDate= GregorianCalendar(year,month,day).time

            targetFragment?.let {
                (it as DataPickerCallBack).onDateSelected(resultDate)
            }

        }

        return DatePickerDialog(
            requireContext(),
            dateListener,
            year,
            month,
            day
        )
    }
}