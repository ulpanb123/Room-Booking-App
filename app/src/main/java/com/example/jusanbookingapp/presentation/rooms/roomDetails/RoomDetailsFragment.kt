package com.example.jusanbookingapp.presentation.rooms.roomDetails

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.jusanbookingapp.R
import com.example.jusanbookingapp.domain.models.Event
import com.example.jusanbookingapp.domain.models.Reservation
import com.example.jusanbookingapp.domain.models.UserBookingInfo
import com.example.jusanbookingapp.presentation.utils.ClickListener
import com.example.jusanbookingapp.presentation.utils.SpaceItemDecoration
import com.example.jusanbookingapp.presentation.utils.extensions.convertMillisToDate
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.shrikanthravi.collapsiblecalendarview.data.Day
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar
import me.relex.circleindicator.CircleIndicator
import okhttp3.internal.toImmutableList
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.text.SimpleDateFormat
import java.util.*


class RoomDetailsFragment : Fragment(), DialogListener {

    lateinit var adapter: EventsAdapter

    val args: RoomDetailsFragmentArgs by navArgs()
    val viewModel: RoomDetailsViewModel by viewModel {
        parametersOf(args.roomNumber)
    }

    //details
    private lateinit var tvTitle : TextView
    lateinit var viewPagerAdapter: ImageSliderAdapter
    lateinit var indicator: CircleIndicator
    lateinit var viewpager : ViewPager
    private lateinit var tvFloor : TextView
    private lateinit var chipCapacity : Chip
    private lateinit var tvDescr : TextView
    private lateinit var chipGroup : ChipGroup

    private lateinit var btnBookRoom : Button
    private lateinit var toolbar : Toolbar
    private lateinit var datePicker : CollapsibleCalendar

    private lateinit var tvEmptyDay : TextView
    private lateinit var rvEvents : RecyclerView

    private var selectedDate : Long = 0L
    private var nextDay : Long = 0L

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
        initObservers()
    }

    fun initViews(view : View) {
        tvTitle = view.findViewById(R.id.toolbar_text)
        indicator = view.findViewById(R.id.indicator)
        viewpager = view.findViewById(R.id.images_pager)
        tvFloor = view.findViewById(R.id.tv_room_floor)
        chipCapacity = view.findViewById(R.id.chip_capacity)
        tvDescr = view.findViewById(R.id.tv_room_descr)
        chipGroup = view.findViewById(R.id.chipGroup)

        btnBookRoom = view.findViewById(R.id.bookButton)
        btnBookRoom.setOnClickListener {
            val sharedPref = context?.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
            val editor = sharedPref?.edit()
            editor?.putString("date", Date(selectedDate).convertMillisToDate("yyyy-MM-dd"))
            editor?.apply()


            BookRoomDialogFragment(this as DialogListener).show(childFragmentManager, null)
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
                val calendar : Calendar = Calendar.getInstance().apply {
                    set(Calendar.HOUR, 0)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                }
                calendar.set(day.year, day.month, day.day)
                selectedDate = calendar.timeInMillis

                calendar.set(day.year, day.month, day.day+1)
                nextDay = calendar.timeInMillis

                Log.e("Range", "${selectedDate.toInt()} --- ${nextDay.toInt()}")
                viewModel.getReservationByDate(selectedDate, nextDay)

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
        adapter.listener = ClickListener { timeslot ->
            onTimeslotDelete(timeslot)
        }
    }

    private fun onTimeslotDelete(timeslot : Reservation.TimeSlot) {
        viewModel.deleteReservation(args.roomNumber, timeslot.timeslotID)
        adapter.deleteElement(timeslot)
    }

    private fun initRecycler() {
        val currentContext = context ?: return

        rvEvents.adapter = adapter
        rvEvents.layoutManager = LinearLayoutManager(currentContext)

        val spaceItemDecoration =
            SpaceItemDecoration(verticalSpaceInDp = 4, horizontalSpaceInDp = 0)
        rvEvents.addItemDecoration(spaceItemDecoration)
    }

    private fun initObservers() {
        viewModel.currRoom.observe(viewLifecycleOwner) {room ->
            tvTitle.text = "Room ${room.roomNumber}"
            tvFloor.text = "Floor ${room.floor}"
            chipCapacity.text = room.capacity
            tvDescr.text = room.description

            (room.facilities).forEach {
                (layoutInflater.inflate(R.layout.chip_facilities, chipGroup, false) as? Chip)?.let { chip ->
                    chip.id = View.generateViewId()
                    chip.text = it.name
                    chipGroup.addView(chip)
                }
            }

            if(room.images.isNotEmpty()) {
                val list = mutableListOf<String>()
                room.images.forEach {
                    list.add(it.url)
                }
                viewPagerAdapter = ImageSliderAdapter(requireContext(), list)
                viewpager.adapter = viewPagerAdapter
                indicator = requireView().findViewById(R.id.indicator) as CircleIndicator
                indicator.setViewPager(viewpager)
            }
            viewModel.isAdded.observe(viewLifecycleOwner) {
                if(it) {
                    Log.e("TAG", "reached")
                    viewModel.getReservationByDate(selectedDate, nextDay)
                }
            }

        }

        viewModel.eventsForDate.observe(viewLifecycleOwner) {
            tvEmptyDay.visibility = View.GONE
            adapter.setData(it)
            if(it.isEmpty())
                tvEmptyDay.visibility = View.VISIBLE
        }
    }

    override fun onDialogClosed() {
        val sharedPref = context?.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val startTime = sharedPref?.getString("startTime", "") ?: ""
        val endTime= sharedPref?.getString("endTime", "") ?: ""
        val dateString = sharedPref?.getString("date", "") ?: ""

        val startString = "$dateString $startTime"
        val endString = "$dateString $endTime"

        Log.e("TAG", startString)
        Log.e("TAG", endString)

        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val start = dateFormat.parse(startString)
        val end = dateFormat.parse(endString)

        val purpose = sharedPref?.getString("purpose", "purpose was not indicated") ?: ""
        viewModel.addReservation(start.time, end.time, purpose)
    }




}