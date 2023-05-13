package com.example.jusanbookingapp.presentation.rooms.roomDetails

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.jusanbookingapp.R
import com.example.jusanbookingapp.constants.AppPreferences
import com.example.jusanbookingapp.domain.models.Event
import com.example.jusanbookingapp.domain.models.Reservation
import com.example.jusanbookingapp.domain.models.UserBookingInfo
import com.example.jusanbookingapp.presentation.utils.ClickListener
import com.example.jusanbookingapp.presentation.utils.extensions.convertMillisToDate
import java.util.*

class EventsAdapter(private val layoutInflater: LayoutInflater) :
    RecyclerView.Adapter<EventViewHolder>()
{

    private val events : MutableList<Reservation.TimeSlot> = mutableListOf()

    var listener: ClickListener<Reservation.TimeSlot>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = layoutInflater.inflate(R.layout.item_event, parent, false)

        return EventViewHolder(view)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.bind(event)

        holder.btnDeleteByAdmin.setOnClickListener {
            listener?.onClick(event)
        }
    }

    fun setData(newData: List<Reservation.TimeSlot>) {
        notifyItemRangeRemoved(0, events.size)
        events.clear()
        events.addAll(newData)
        notifyItemRangeInserted(0, events.size)
    }

    fun deleteElement(timeslot : Reservation.TimeSlot) {
        val indexToRemove = events.indexOf(timeslot)
        notifyItemRangeRemoved(indexToRemove, 1)
        events.removeAt(indexToRemove)
    }
}

class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var tvTimeRange: TextView = itemView.findViewById(R.id.tv_event_timeslot)
    private var tvDescription: TextView = itemView.findViewById(R.id.tv_event_descr)
    var btnDeleteByAdmin : AppCompatImageView = itemView.findViewById(R.id.btnAdminDelete)

    fun bind(event: Reservation.TimeSlot) {
        val start = Date(event.start).convertMillisToDate("HH:mm")
        val end = Date(event.end).convertMillisToDate("HH:mm")

        tvTimeRange.text = "$start-$end"
        tvDescription.text = event.purpose

        Log.e("TAG", AppPreferences.userId.toString())

        if(AppPreferences.userId == 0)
            btnDeleteByAdmin.visibility = View.VISIBLE
    }
}