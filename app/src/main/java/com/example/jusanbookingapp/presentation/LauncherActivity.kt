package com.example.jusanbookingapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.jusanbookingapp.R
import com.example.jusanbookingapp.constants.AppPreferences
import com.example.jusanbookingapp.di.networkModule
import com.example.jusanbookingapp.di.repositoryModule
import com.example.jusanbookingapp.di.useCaseModule
import com.example.jusanbookingapp.di.viewModelModule
import com.example.jusanbookingapp.presentation.auth.AuthActivity
import org.koin.core.context.startKoin

class LauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppPreferences.setup(applicationContext)

        startKoin {
            modules(networkModule, viewModelModule, repositoryModule, useCaseModule)
        }

//        val intent = Intent(this, AuthActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        setContentView(R.layout.activity_auth)
//        startActivity(intent)
//

        if((AppPreferences.accessToken).isNullOrEmpty()) {
            val intent = Intent(this, AuthActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            setContentView(R.layout.activity_auth)
            startActivity(intent)
        } else {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            setContentView(R.layout.activity_main)
            startActivity(intent)
        }
    }
}