package com.example.jusanbookingapp.presentation.auth.registration

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jusanbookingapp.R
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment() {

    val viewModel: RegistrationViewModel by viewModel()

    private lateinit var btnRegister : AppCompatButton
    private lateinit var etUsername : TextInputEditText
    private lateinit var etEmail : TextInputEditText
    private lateinit var etPassword : TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view)
    }

    fun initViews(view : View) {
        etUsername = view.findViewById(R.id.usernameInputEditText)
        etEmail = view.findViewById(R.id.emailEditTextView)
        etPassword = view.findViewById(R.id.passwordEditTextView)

        etUsername.doOnTextChanged { text, start, before, count ->
            checkRequiredFields()
        }
        etEmail.doOnTextChanged { text, start, before, count ->
            if(text != null) {
                if(Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
                    etEmail.setTextColor(Color.BLACK)
                } else {
                    etEmail.setTextColor(Color.RED)
                }
                checkRequiredFields()
            }
        }
        etPassword.doOnTextChanged { text, start, before, count ->
            checkRequiredFields()
        }

        btnRegister = view.findViewById(R.id.registerButton)
        btnRegister.setOnClickListener {
            Log.e("TAG", "click")
            viewModel.onSubmit()
            findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment())
        }
    }

    private fun checkRequiredFields() {
        val isInputValid : Boolean = etEmail.currentTextColor == Color.BLACK
        if(etUsername.text.toString().isNotEmpty() && etEmail.text.toString().isNotEmpty()
            && etPassword.text.toString().isNotEmpty() && isInputValid) {
            //enable the button
            btnRegister.isEnabled = true
            btnRegister.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorButtonPrimary))
            btnRegister.setTextColor(resources.getColor(R.color.white))

            //change values in viewmodel
            viewModel.username = etUsername.text.toString()
            viewModel.email = etEmail.text.toString()
            viewModel.password = etPassword.text.toString()
        } else {
            btnRegister.isEnabled = false
            btnRegister.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorGray6))
            btnRegister.setTextColor(resources.getColor(R.color.colorGray7))
        }
    }

}