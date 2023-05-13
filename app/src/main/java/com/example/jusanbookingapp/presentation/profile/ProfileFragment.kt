package com.example.jusanbookingapp.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jusanbookingapp.R
import com.example.jusanbookingapp.constants.AppPreferences

class ProfileFragment : Fragment() {

    private lateinit var tvMyReservations : TextView

    private lateinit var toolbar: Toolbar

    private lateinit var tvUsername : TextView
    private lateinit var tvEmail : TextView
    private lateinit var tvLogOut : TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tvMyReservations = view.findViewById(R.id.btn_my_bookings)
        tvMyReservations.setOnClickListener {
            findNavController().navigate(
                ProfileFragmentDirections.actionNavigationProfileToUserReservationsFragment()
            )
        }
        toolbar = view.findViewById(R.id.toolbar_profile)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
            findNavController().popBackStack(R.id.navigation_rooms, false)
        }

        tvUsername = view.findViewById(R.id.tv_username)
        tvUsername.text = AppPreferences.username

        tvEmail = view.findViewById(R.id.tv_email)
        tvEmail.text = AppPreferences.userEmail
//
//        tvLogOut = view.findViewById(R.id.btn_logOut)
//        tvLogOut.setOnClickListener {
//            findNavController().navigate(R.id.action_navigation_profile_to_registrationFragment)
//        }
    }

}