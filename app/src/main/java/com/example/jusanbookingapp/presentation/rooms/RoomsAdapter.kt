package com.example.jusanbookingapp.presentation.rooms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jusanbookingapp.R
import com.example.jusanbookingapp.domain.models.Room
import com.example.jusanbookingapp.presentation.utils.ClickListener
import com.google.android.material.chip.Chip

class RoomsAdapter(private val layoutInflater: LayoutInflater) :
    RecyclerView.Adapter<RoomViewHolder>() {

    private val rooms: MutableList<Room> = mutableListOf()
    var listener: ClickListener<Room>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = layoutInflater.inflate(R.layout.item_room, parent, false)

        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val post = rooms[position]
        holder.bind(post)
        holder.itemView.setOnClickListener {
            listener?.onClick(post)
        }
    }

    override fun getItemCount(): Int {
        return rooms.size
    }

    fun setData(newData: List<Room>) {
        notifyItemRangeRemoved(0, rooms.size)
        rooms.clear()
        rooms.addAll(newData)
        notifyItemRangeInserted(0, rooms.size)
    }
}

class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var tvTitle: TextView = itemView.findViewById(R.id.tv_room_name)
    private var tvFloor: TextView = itemView.findViewById(R.id.tv_room_floor)
    private var tvCapacity: Chip = itemView.findViewById(R.id.chip_capacity)

    fun bind(room : Room) {
        tvTitle.text = room.roomNumber
        tvFloor.text = room.roomFloor
        tvCapacity.text = room.roomCapacity
    }
}