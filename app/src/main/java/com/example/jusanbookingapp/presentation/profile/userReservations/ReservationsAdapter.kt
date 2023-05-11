package com.example.jusanbookingapp.presentation.profile.userReservations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.jusanbookingapp.R
import com.example.jusanbookingapp.domain.models.Reservation
import com.example.jusanbookingapp.presentation.utils.ClickListener
import com.google.android.material.chip.Chip

class ReservationsAdapter(private val layoutInflater: LayoutInflater) :
    RecyclerView.Adapter<ReservationViewHolder>() {

    private val reservations: MutableList<Reservation> = mutableListOf()
    var listener: ClickListener<Reservation>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val view = layoutInflater.inflate(R.layout.item_reservation, parent, false)

        return ReservationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return reservations.size
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        val reservation = reservations[position]
        holder.bind(reservation)
        holder.btnDelete.setOnClickListener {
            listener?.onClick(reservation)
        }
    }

    fun setData(newData: List<Reservation>) {
        notifyItemRangeRemoved(0, reservations.size)
        reservations.clear()
        reservations.addAll(newData)
        notifyItemRangeInserted(0, reservations.size)
    }

    fun deleteElement(reservation: Reservation) {
        val indexToRemove = reservations.indexOf(reservation)
        notifyItemRangeRemoved(indexToRemove, 1)
        reservations.removeAt(indexToRemove)
    }
}

class ReservationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var tvRoomId: TextView = itemView.findViewById(R.id.tv_room_name)
    private var tvFloor: TextView = itemView.findViewById(R.id.tv_room_floor)
    private var tvCapacity: Chip = itemView.findViewById(R.id.chip_capacity)

    private var tvDate: TextView = itemView.findViewById(R.id.tv_date)
    private var tvTimeslot: TextView = itemView.findViewById(R.id.tv_timeslot)

    var btnDelete: AppCompatImageButton = itemView.findViewById(R.id.btn_delete)

    fun bind(reservation: Reservation) {
//        tvRoomId.text = reservation.room.roomNumber
//        tvFloor.text = reservation.room.floor
//        tvCapacity.text = reservation.room.capacity
//
//        tvDate.text = reservation.date
//        tvTimeslot.text = reservation.timeslot
    }
}
