package com.example.jusanbookingapp.presentation.rooms.roomDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jusanbookingapp.R
import com.example.jusanbookingapp.domain.models.Event

class EventsAdapter(private val layoutInflater: LayoutInflater) :
    RecyclerView.Adapter<EventViewHolder>()
{

    private val events : MutableList<Event> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = layoutInflater.inflate(R.layout.item_event, parent, false)

        return EventViewHolder(view)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events.get(position)
        holder.bind(event)
    }

    fun setData(newData: List<Event>) {
        notifyItemRangeRemoved(0, events.size)
        events.clear()
        events.addAll(newData)
        notifyItemRangeInserted(0, events.size)
    }
}

class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var tvTimeRange: TextView = itemView.findViewById(R.id.tv_event_timeslot)
    private var tvDescription: TextView = itemView.findViewById(R.id.tv_event_descr)

    fun bind(event: Event) {
        tvTimeRange.text = event.timeRange
        tvDescription.text = event.description
    }
}