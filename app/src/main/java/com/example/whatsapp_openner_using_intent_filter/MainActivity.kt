package com.example.whatsapp_openner_using_intent_filter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Handle intent if action is ACTION_PROCESS_TEXT
        var number: String = "0"
        if (intent.action == Intent.ACTION_PROCESS_TEXT) {
            number = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()
        }

        if (number.isDigitsOnly()) {
            startWhatsapp(number)
        } else {
            Toast.makeText(this, "Please check the number", Toast.LENGTH_LONG).show()
        }

        // Button click listener
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val number: String = "919005255435"
            startWhatsapp(number)
        }
    }

    private fun startWhatsapp(number: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setPackage("com.whatsapp")
        val data: String = if (number[0] == '+') {
            number.substring(1)
        } else if (number.length == 10) {
            "91$number"
        } else {
            number
        }
        intent.data = Uri.parse("https://wa.me/$data")
        if (packageManager.resolveActivity(intent, 0) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "Please install Whatsapp", Toast.LENGTH_LONG).show()
        }
        finish()
    }
}
