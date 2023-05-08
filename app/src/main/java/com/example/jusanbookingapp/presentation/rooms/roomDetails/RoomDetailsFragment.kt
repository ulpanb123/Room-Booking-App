package com.example.jusanbookingapp.presentation.rooms.roomDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.example.jusanbookingapp.R

class RoomDetailsFragment : Fragment() {

    private lateinit var viewModel: RoomDetailsViewModel
    private lateinit var btnBookRoom : Button
    private lateinit var toolbar : Toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_room_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnBookRoom = view.findViewById(R.id.bookButton)
        btnBookRoom.setOnClickListener {
            BookRoomDialogFragment().show(childFragmentManager, null)
        }

        toolbar = view.findViewById(R.id.toolbar_details)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

}