package com.iphint.testmagang.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.iphint.testmagang.databinding.ActivityMainBinding
import com.iphint.testmagang.ui.list.ListUserActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val receivedName = intent.getStringExtra("EXTRA_NAME")

        binding.nameUser.text = receivedName
        binding.username.text = "Selected $receivedName"

        binding.toolbarTitle.text = "Second Screen"
        binding.arrowBack.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
        binding.chooseUser.setOnClickListener {
            val i = Intent(this, ListUserActivity::class.java)
            startActivity(i)
        }
    }
}