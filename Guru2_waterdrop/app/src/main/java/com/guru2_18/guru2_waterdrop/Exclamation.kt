package com.guru2_18.guru2_waterdrop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_exclamation.*

class Exclamation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exclamation)

        close_btn.setOnClickListener {
            finish()
        }
    }
}