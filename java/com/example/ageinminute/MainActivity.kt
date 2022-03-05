package com.example.ageinminute

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datePicker = findViewById<Button>(R.id.btnDatePicker)

        datePicker.setOnClickListener(){ view ->
            clickDatePicker(view)

        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDatePicker(view: View){
      val myCalendar = Calendar.getInstance()
        val  year = myCalendar.get(Calendar.YEAR)
        val  month = myCalendar.get(Calendar.MONTH)
        val  day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog( this, DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDayOfMonth ->

            Toast.makeText(this, "The chosen year $selectedYear, the month is $selectedMonth, the day of the month $selectedDayOfMonth", Toast.LENGTH_LONG).show()

            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            val idSelectedDate = findViewById<TextView>(R.id.tv_Selected_Date)
            val idSelectedDateInMin =findViewById<TextView>(R.id.tvSelectedDateInMinutes)

            idSelectedDate.setText(selectedDate)

            val sdf = SimpleDateFormat("DD/MM/yyyy", Locale.ENGLISH)
            val theDate =sdf.parse(selectedDate)
            val selectedDateInMinute = theDate!!.time / 60000
            val currentDate = sdf.parse(sdf.format((System.currentTimeMillis())))
            val currentDateInMinute = currentDate!!.time / 60000
            val differenceInMinute = currentDateInMinute - selectedDateInMinute

            idSelectedDateInMin.setText(differenceInMinute.toString())
        }
            ,year
            ,month
            ,day)
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }
}





