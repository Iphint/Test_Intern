package com.iphint.testmagang.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.iphint.testmagang.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.check.setOnClickListener {
            val inputText = binding.palindrome.text.toString()
            if (inputText.isNotBlank()) {
                val isPalindrome = binding.palindrome.checkPalindrome(inputText)
                showMaterialAlertDialog(isPalindrome)
            } else {
                Toast.makeText(this, "Please enter a palindrome", Toast.LENGTH_SHORT).show()
            }
        }

        binding.next.setOnClickListener {
            val inputName = binding.name.editableText?.toString()
            if (inputName != null) {
                if (inputName.isNotEmpty()) {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("EXTRA_NAME", inputName)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showMaterialAlertDialog(isPalindrome: Boolean) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Check result :")
        val message = if (isPalindrome) {
            "is palindrome"
        } else {
            "not palindrome"
        }
        builder.setMessage(message)
        builder.setPositiveButton("OK") { _, _ -> }
        builder.show()
    }
}