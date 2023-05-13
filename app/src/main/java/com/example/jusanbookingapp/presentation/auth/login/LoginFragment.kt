package com.example.jusanbookingapp.presentation.auth.login

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.example.jusanbookingapp.R
import com.example.jusanbookingapp.presentation.MainActivity
import com.google.android.material.textfield.TextInputEditText
import com.kusu.loadingbutton.LoadingButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {


    private val viewModel: LoginViewModel by viewModel()
    private lateinit var loadingButton: LoadingButton
    private lateinit var etUsername : TextInputEditText
    private lateinit var etPassword : TextInputEditText
    private lateinit var tvNavToRegistration : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view)
        initObservers()
    }

    fun initViews(view: View) {
        etUsername = view.findViewById(R.id.usernameInputEditText)
        etPassword = view.findViewById(R.id.passwordEditTextView)
        tvNavToRegistration = view.findViewById(R.id.createUserButton)

        tvNavToRegistration.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
        }

        etUsername.doOnTextChanged { text, start, before, count ->
            checkRequiredFields()
        }

        etPassword.doOnTextChanged { text, start, before, count ->
            checkRequiredFields()
        }

        loadingButton = view.findViewById(R.id.loginButton)
        loadingButton.setOnClickListener {
            viewModel.onLogin()
        }
    }

    private fun checkRequiredFields() {
        if(etUsername.text.toString().isNotEmpty()
            && etPassword.text.toString().isNotEmpty()) {
            //enable the button
            loadingButton.isEnabled = true
            loadingButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorButtonPrimary))
            loadingButton.setTextColor(resources.getColor(R.color.white))

            //change values in viewmodel
            viewModel.username = etUsername.text.toString()
            viewModel.password = etPassword.text.toString()
        } else {
            loadingButton.isEnabled = false
            loadingButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorGray6))
            loadingButton.setTextColor(resources.getColor(R.color.colorGray7))
        }
    }

    private fun initObservers() {
        viewModel.isLoggedIn.observe(viewLifecycleOwner) {
            if(it){
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }


}