package com.example.jusanbookingapp.presentation

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.jusanbookingapp.R
import com.example.jusanbookingapp.constants.AppPreferences

class SplashScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler().postDelayed({
            if(userLoggedIn()){
                findNavController().navigate(R.id.action_splashScreenFragment2_to_navigation_rooms)
            }else{
                findNavController().navigate(R.id.action_splashScreenFragment_to_registrationFragment)
            }
        }, 3000)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    private fun userLoggedIn() : Boolean{
        val token = AppPreferences.accessToken
        return !token.isNullOrEmpty()
    }

}