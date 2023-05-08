package com.example.jusanbookingapp.presentation.rooms

import android.os.Bundle
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


class RoomsFragment : Fragment() {

//    private val vm: PostsViewModel by viewModel()

    lateinit var rvRooms: RecyclerView

    lateinit var adapter: RoomsAdapter

    val tempData : List<Room> = listOf(Room("Mac room", "Floor 3", "40", ""),
        Room("Main Room", "Floor 3", "60", ""))

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
//        initObservers()
    }

    private fun initViews(view: View) {
        rvRooms = view.findViewById(R.id.rv_rooms)
    }

    private fun initAdapter() {
        adapter = RoomsAdapter(layoutInflater)
        adapter.listener = ClickListener { room ->
            onRoomClick(room)
        }
    }

    private fun onRoomClick(room: Room) {
        findNavController().navigate(
            RoomsFragmentDirections.actionNavigationRoomsToRoomDetailsFragment(1)
        )
    }

    private fun initRecycler() {
        val currentContext = context ?: return

        rvRooms.adapter = adapter
        rvRooms.layoutManager = LinearLayoutManager(currentContext)

        val spaceItemDecoration =
            SpaceItemDecoration(verticalSpaceInDp = 8, horizontalSpaceInDp = 16)
        rvRooms.addItemDecoration(spaceItemDecoration)

        adapter.setData(tempData)
    }
}