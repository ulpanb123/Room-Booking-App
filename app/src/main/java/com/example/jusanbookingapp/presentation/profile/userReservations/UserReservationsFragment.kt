package com.example.jusanbookingapp.presentation.profile.userReservations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jusanbookingapp.R
import com.example.jusanbookingapp.databinding.ShimmerLayoutRoomBinding
import com.example.jusanbookingapp.domain.models.UserBookingInfo
import com.example.jusanbookingapp.presentation.utils.ClickListener
import com.example.jusanbookingapp.presentation.utils.SpaceItemDecoration
import com.facebook.shimmer.ShimmerFrameLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserReservationsFragment : Fragment() {
    private val vm: UserReservationsViewModel by viewModel()

    private lateinit var rvReservations: RecyclerView

    private lateinit var adapter: ReservationsAdapter

    private lateinit var toolbar: Toolbar

    private lateinit var shimmer : ShimmerFrameLayout

    lateinit var data : List<UserBookingInfo>

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
        initObservers()
    }

    private fun initViews(view: View) {
        rvReservations = view.findViewById(R.id.rv_reservations)
        toolbar = view.findViewById(R.id.toolbar_myreserv)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        shimmer = view.findViewById(R.id.shimmerFrameLayout)
        shimmer.startShimmer()
    }

    private fun initAdapter() {
        adapter = ReservationsAdapter(layoutInflater)
        adapter.listener = ClickListener { reservation ->
            onReservationDelete(reservation)
        }
    }

    private fun onReservationDelete(reservation: UserBookingInfo) {
        vm.deleteReservation(reservation.room.roomNumber, reservation.timeslotID)
        adapter.deleteElement(reservation)
    }

    private fun initRecycler() {
        val currentContext = context ?: return

        rvReservations.adapter = adapter
        rvReservations.layoutManager = LinearLayoutManager(currentContext)

        val spaceItemDecoration =
            SpaceItemDecoration(verticalSpaceInDp = 8, horizontalSpaceInDp = 16)
        rvReservations.addItemDecoration(spaceItemDecoration)

    }

    private fun initObservers() {
        vm.userReservations.observe(viewLifecycleOwner) {
            data = vm.convertInfo(it)
        }
        vm.isDataReady.observe(viewLifecycleOwner) {
            if(it) {
                shimmer.stopShimmer()
                shimmer.visibility = View.GONE
                adapter.setData(data)
            }
        }
    }

}