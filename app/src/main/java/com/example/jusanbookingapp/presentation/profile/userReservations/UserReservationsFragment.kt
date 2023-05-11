package com.example.jusanbookingapp.presentation.profile.userReservations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jusanbookingapp.R
import com.example.jusanbookingapp.domain.models.Reservation
import com.example.jusanbookingapp.domain.models.Room
import com.example.jusanbookingapp.presentation.rooms.RoomsAdapter
import com.example.jusanbookingapp.presentation.rooms.RoomsFragmentDirections
import com.example.jusanbookingapp.presentation.utils.ClickListener
import com.example.jusanbookingapp.presentation.utils.SpaceItemDecoration

class UserReservationsFragment : Fragment() {

    private lateinit var rvReservations: RecyclerView

    private lateinit var adapter: ReservationsAdapter

    private lateinit var toolbar: Toolbar

//    val tempData : List<Reservation> = listOf(
//        Reservation("15/07/2023", "15:00-15:30", Room("Mac room", "Floor 3", "40", "")),
//        Reservation("14/08/2023", "18:00-00:00", Room("Main Room", "Floor 3", "60", "")))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_reservations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view)
        initAdapter()
        initRecycler()
//        initObservers()
    }

    private fun initViews(view: View) {
        rvReservations = view.findViewById(R.id.rv_reservations)
        toolbar = view.findViewById(R.id.toolbar_myreserv)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initAdapter() {
        adapter = ReservationsAdapter(layoutInflater)
        adapter.listener = ClickListener { reservation ->
            onReservationDelete(reservation)
        }
    }

    private fun onReservationDelete(reservation: Reservation) {
        adapter.deleteElement(reservation)
    }

    private fun initRecycler() {
        val currentContext = context ?: return

        rvReservations.adapter = adapter
        rvReservations.layoutManager = LinearLayoutManager(currentContext)

        val spaceItemDecoration =
            SpaceItemDecoration(verticalSpaceInDp = 8, horizontalSpaceInDp = 16)
        rvReservations.addItemDecoration(spaceItemDecoration)

//        adapter.setData(tempData)
    }

}