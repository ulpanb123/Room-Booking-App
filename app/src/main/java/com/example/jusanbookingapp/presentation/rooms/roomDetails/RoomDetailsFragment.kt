package com.example.jusanbookingapp.presentation.rooms.roomDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jusanbookingapp.R

class RoomDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = RoomDetailsFragment()
    }

    private lateinit var viewModel: RoomDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_room_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RoomDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}