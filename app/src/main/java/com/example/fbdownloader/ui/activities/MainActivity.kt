package com.example.fbdownloader.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ads.GoogleManager
import com.example.fbdownloader.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var googleManager: GoogleManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        googleManager.init()
    }
}