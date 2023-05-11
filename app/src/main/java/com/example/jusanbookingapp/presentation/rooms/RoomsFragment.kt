package com.example.jusanbookingapp.presentation.rooms

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jusanbookingapp.R
import com.example.jusanbookingapp.domain.models.Room
import com.example.jusanbookingapp.presentation.utils.ClickListener
import com.example.jusanbookingapp.presentation.utils.SpaceItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel


class RoomsFragment : Fragment() {

    private val vm: RoomsViewModel by viewModel()

    lateinit var rvRooms: RecyclerView

    lateinit var adapter: RoomsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_rooms, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view)
        initAdapter()
        initRecycler()
        Log.e(this.javaClass.name, vm.rooms.toString())
        initObservers()
    }

    private fun initViews(view: View) {
        rvRooms = view.findViewById(R.id.rv_rooms)
    }

    private fun initAdapter() {
        adapter = RoomsAdapter(requireContext(), layoutInflater)
        adapter.listener = ClickListener { room ->
            onRoomClick(room)
        }
    }

    private fun onRoomClick(room: Room) {
        findNavController().navigate(
            RoomsFragmentDirections.actionNavigationRoomsToRoomDetailsFragment(room.roomNumber)
        )
    }

    private fun initRecycler() {
        val currentContext = context ?: return

        rvRooms.adapter = adapter
        rvRooms.layoutManager = LinearLayoutManager(currentContext)

        val spaceItemDecoration =
            SpaceItemDecoration(verticalSpaceInDp = 8, horizontalSpaceInDp = 16)
        rvRooms.addItemDecoration(spaceItemDecoration)
    }

    private fun initObservers() {
        vm.rooms.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }

}