package com.example.jusanbookingapp.presentation.rooms.roomDetails

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jusanbookingapp.R
import com.example.jusanbookingapp.domain.models.Event
import com.example.jusanbookingapp.presentation.rooms.RoomsAdapter
import com.example.jusanbookingapp.presentation.utils.SpaceItemDecoration
import com.shrikanthravi.collapsiblecalendarview.data.Day
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar
import java.util.Calendar
import java.util.Date
import java.util.Locale


class RoomDetailsFragment : Fragment() {

    val mockEvents = listOf(
        Event("12:00-13:00", "Presentation"),
        Event("13:15-14:00", "Java Lesson"),
        Event("16:00-18:00", "Android Meetup")
    )
    lateinit var adapter: EventsAdapter

    private lateinit var viewModel: RoomDetailsViewModel
    private lateinit var btnBookRoom : Button
    private lateinit var toolbar : Toolbar
    private lateinit var datePicker : CollapsibleCalendar

    private lateinit var tvEmptyDay : TextView
    private lateinit var rvEvents : RecyclerView

    private var selectedDate : Long = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_room_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view)
        initAdapter()
        initRecycler()


    }

    fun initViews(view : View) {

        btnBookRoom = view.findViewById(R.id.bookButton)
        btnBookRoom.setOnClickListener {
            val sharedPref = context?.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
            val editor = sharedPref?.edit()
            editor?.putLong("date", selectedDate)
            editor?.apply()

            BookRoomDialogFragment().show(childFragmentManager, null)
        }

        toolbar = view.findViewById(R.id.toolbar_details)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        rvEvents = view.findViewById(R.id.rv_events_by_date)
        tvEmptyDay = view.findViewById(R.id.tv_empty_day)

        initDatePicker(view)

    }

    fun initDatePicker(view : View) {
        datePicker = view.findViewById(R.id.timeslot_picker)

        datePicker.setCalendarListener(object : CollapsibleCalendar.CalendarListener {
            override fun onClickListener() {
                //
            }

            override fun onDataUpdate() {
                //
            }

            override fun onDayChanged() {
               //
            }

            override fun onDaySelect() {
                //call getReservationOfRoomByDate(date)
                //if it's empty, just save selected data
                //if it's not, display all events

                val day: Day = datePicker.selectedDay!!
                val calendar : Calendar = Calendar.getInstance()
                calendar.set(day.year, day.month, day.day)
                selectedDate = calendar.timeInMillis

                tvEmptyDay.visibility = View.GONE
                rvEvents.visibility = View.VISIBLE
            }

            override fun onItemClick(v: View) {
                //
            }

            override fun onMonthChange() {
                //
            }

            override fun onWeekChange(position: Int) {
                //
            }

        })
    }

    private fun initAdapter() {
        adapter = EventsAdapter(layoutInflater)
    }

    private fun initRecycler() {
        val currentContext = context ?: return

        rvEvents.adapter = adapter
        rvEvents.layoutManager = LinearLayoutManager(currentContext)

        val spaceItemDecoration =
            SpaceItemDecoration(verticalSpaceInDp = 4, horizontalSpaceInDp = 0)
        rvEvents.addItemDecoration(spaceItemDecoration)

        adapter.setData(mockEvents)
    }



}