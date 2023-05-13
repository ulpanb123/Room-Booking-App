package com.example.jusanbookingapp.presentation.rooms.roomDetails

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import com.example.jusanbookingapp.R
import com.example.jusanbookingapp.presentation.utils.extensions.convertMillisToDate
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*

interface DialogListener {
    fun onDialogClosed()
}
class BookRoomDialogFragment(val listener : DialogListener) : DialogFragment() {

    private lateinit var etPurpose : TextInputEditText
    private lateinit var etStartTime : TextInputEditText
    private lateinit var etEndTime : TextInputEditText
    private lateinit var btnSubmit : AppCompatButton
    private lateinit var btnClose : AppCompatButton

    private lateinit var dialogMainContent : LinearLayout
    private lateinit var dialogSuccess : LinearLayout

    private var datePicked = ""
    private var startTime = ""
    private var endTime = ""
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val customView : View = layoutInflater.inflate(R.layout.dialog_book_room, null)
        val dialog : AlertDialog = AlertDialog.Builder(requireContext()).apply {
            setView(customView)
        }.create()

        with(customView) {
            etPurpose = findViewById(R.id.dateInputEditText)
            etStartTime = findViewById(R.id.startTimeInputEditText)
            etEndTime = findViewById(R.id.endTimeInputEditText)
            btnSubmit= findViewById(R.id.submitButton)
            btnClose = findViewById(R.id.closeButton)

            dialogMainContent= findViewById(R.id.dialog_main_content)
            dialogSuccess = findViewById(R.id.dialog_success)
        }
        btnSubmit.setOnClickListener {
            saveTimeSlot()
            dialogMainContent.visibility = View.GONE
            dialogSuccess.visibility = View.VISIBLE
        }

        btnClose.setOnClickListener {
            dialog.dismiss()
            listener.onDialogClosed()
        }

        initDatePicker()
        initTimePicker()

        dialog.show()

        return dialog
    }

    private fun initDatePicker() {
        etPurpose.doOnTextChanged { text, start, before, count ->
            val sharedPrefs = context?.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
            val editor = sharedPrefs?.edit()
            editor?.putString("purpose", text.toString())
            editor?.apply()
        }
    }


    private fun initTimePicker() {
        etStartTime.setOnClickListener {
            val picker =
                MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(9)
                    .setMinute(0)
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

                startTime = time

                etStartTime.setText(time)
            }

            picker.show(parentFragmentManager, "tag");
        }

        etEndTime.setOnClickListener {
            val picker =
                MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(9)
                    .setMinute(0)
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

                endTime = time
                etEndTime.setText(time)
            }

            picker.show(parentFragmentManager, "tag");
        }
    }

    private fun saveTimeSlot() {
        val sharedPref = context?.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val editor = sharedPref?.edit()
        editor?.putString("startTime", startTime)
        editor?.putString("endTime", endTime)
        editor?.apply()
    }

    data class TimeSlot(
        val start : Long,
        val end : Long
    )


}