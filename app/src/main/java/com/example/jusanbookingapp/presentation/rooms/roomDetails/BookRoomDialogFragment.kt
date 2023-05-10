package com.example.jusanbookingapp.presentation.rooms.roomDetails

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.DialogFragment
import com.example.jusanbookingapp.R
import com.example.jusanbookingapp.domain.models.TimeSlot
import com.example.jusanbookingapp.presentation.utils.extensions.convertMillisToDate
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class BookRoomDialogFragment : DialogFragment() {

    private lateinit var etDate : TextInputEditText
    private lateinit var etStartTime : TextInputEditText
    private lateinit var etEndTime : TextInputEditText
    private lateinit var btnSubmit : AppCompatButton
    private lateinit var btnClose : AppCompatButton

    private lateinit var dialogMainContent : LinearLayout
    private lateinit var dialogSuccess : LinearLayout

    private lateinit var timeSlot : TimeSlot
    private var datePicked : Long = 0L
    private var startTime : Long = 0L
    private var endTime : Long = 0L

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val customView : View = layoutInflater.inflate(R.layout.dialog_book_room, null)
        val dialog : AlertDialog = AlertDialog.Builder(requireContext()).apply {
            setView(customView)
        }.create()

        with(customView) {
            etDate = findViewById(R.id.dateInputEditText)
            etStartTime = findViewById(R.id.startTimeInputEditText)
            etEndTime = findViewById(R.id.endTimeInputEditText)
            btnSubmit= findViewById(R.id.submitButton)
            btnClose = findViewById(R.id.closeButton)

            dialogMainContent= findViewById(R.id.dialog_main_content)
            dialogSuccess = findViewById(R.id.dialog_success)
        }
        btnSubmit.setOnClickListener {
            saveTimeSlot()
            //submitBooking()

            dialogMainContent.visibility = View.GONE
            dialogSuccess.visibility = View.VISIBLE
        }

        btnClose.setOnClickListener {
            dialog.dismiss()
        }

        initDatePicker()
        initTimePicker()

        dialog.show()

        return dialog
    }

    private fun initDatePicker() {
        val sharedPrefs = context?.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        datePicked = sharedPrefs?.getLong("date", 0L) ?: 0L
        val date = Date(datePicked).convertMillisToDate("yyyy-MM-dd")
        etDate.setText(date)
    }

    private fun initTimePicker() {
        etStartTime.setOnClickListener {
            val picker =
                MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(12)
                    .setMinute(15)
                    .setTitleText("Pick start time:")
                    .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
                    .build()

            picker.addOnPositiveButtonClickListener {
                val calendar = Calendar.getInstance().apply {
                    set(Calendar.YEAR, 1970)
                    set(Calendar.MONTH, Calendar.JANUARY)
                    set(Calendar.DAY_OF_MONTH, 1)
                    set(Calendar.HOUR_OF_DAY, picker.hour)
                    set(Calendar.MINUTE, picker.minute)
                }

                val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
                val time = formatter.format(calendar.time)

                startTime = calendar.timeInMillis

                etStartTime.setText(time)
            }

            picker.show(parentFragmentManager, "tag");
        }

        etEndTime.setOnClickListener {
            val picker =
                MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(12)
                    .setMinute(15)
                    .setTitleText("Pick start time:")
                    .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
                    .build()

            picker.addOnPositiveButtonClickListener {
                val calendar = Calendar.getInstance().apply {
                    set(Calendar.YEAR, 1970)
                    set(Calendar.MONTH, Calendar.JANUARY)
                    set(Calendar.DAY_OF_MONTH, 1)
                    set(Calendar.HOUR_OF_DAY, picker.hour)
                    set(Calendar.MINUTE, picker.minute)
                }
                val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
                val time = formatter.format(calendar.time)

                endTime = calendar.timeInMillis
                etEndTime.setText(time)
            }

            picker.show(parentFragmentManager, "tag");
        }
    }

    private fun saveTimeSlot() {
        timeSlot = TimeSlot(datePicked+startTime, datePicked+endTime)
    }


}