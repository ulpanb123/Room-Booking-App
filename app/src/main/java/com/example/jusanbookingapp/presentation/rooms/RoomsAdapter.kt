package com.example.jusanbookingapp.presentation.rooms

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jusanbookingapp.R
import com.example.jusanbookingapp.domain.models.Room
import com.example.jusanbookingapp.presentation.utils.ClickListener
import com.example.jusanbookingapp.presentation.utils.FacilitiesChip
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.imageview.ShapeableImageView

class RoomsAdapter(private val context: Context, private val layoutInflater: LayoutInflater) :
    RecyclerView.Adapter<RoomsAdapter.RoomViewHolder>() {

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
    inner class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvRoomNumber: TextView = itemView.findViewById(R.id.tv_room_name)
        private var tvFloor: TextView = itemView.findViewById(R.id.tv_room_floor)
        private var tvCapacity: Chip = itemView.findViewById(R.id.chip_capacity)

        private var chipGroupFacilities : ChipGroup = itemView.findViewById(R.id.chipGroup)
        private var imgRoom : ShapeableImageView = itemView.findViewById(R.id.img_room)


        fun bind(room : Room) {
            tvRoomNumber.text = "Room ${room.roomNumber}"
            tvFloor.text = "Floor ${room.floor}"
            tvCapacity.text = room.capacity

            if(room.images.isNotEmpty()) {
                Glide.with(imgRoom)
                    .load(room.images[0].url)
                    .into(imgRoom)
            } else {
                imgRoom.setImageResource(R.drawable.ic_broken_image)
            }

            (room.facilities).forEach {
                (layoutInflater.inflate(R.layout.chip_facilities, chipGroupFacilities, false) as? Chip)?.let { chip ->
                    chip.id = View.generateViewId()
                    chip.text = it.name
                    chipGroupFacilities.addView(chip)
                }
            }
        }
    }
}

