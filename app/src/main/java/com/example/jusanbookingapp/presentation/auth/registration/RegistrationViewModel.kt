package com.example.jusanbookingapp.presentation.auth.registration

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.jusanbookingapp.domain.models.User
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel() {
    lateinit var email : String
    lateinit var username : String
    lateinit var password : String

    fun onSubmit() {
        val updateProfileRequest = User(username, email, password)
//        _navigateToMainActivity.value = true
        uiScope.launch {
            try {
                BaspanaApi.retrofitService.updateProfile(updateProfileRequest)
            } catch (t: Throwable) {
                Log.d("Registration.ViewModel", t.message.toString())
            }
        }
    }

}