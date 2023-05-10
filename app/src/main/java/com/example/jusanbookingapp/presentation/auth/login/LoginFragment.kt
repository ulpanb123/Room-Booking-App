package com.example.jusanbookingapp.presentation.auth.login

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jusanbookingapp.R
import com.example.jusanbookingapp.presentation.MainActivity
import com.kusu.loadingbutton.LoadingButton

class LoginFragment : Fragment() {


    private lateinit var viewModel: LoginViewModel
    private lateinit var loadingButton: LoadingButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadingButton = view.findViewById(R.id.loginButton)
        loadingButton.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
    }


}