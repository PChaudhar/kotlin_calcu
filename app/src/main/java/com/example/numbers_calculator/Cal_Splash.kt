package com.example.numbers_calculator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Cal_Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cal__splash)

        Handler().postDelayed({
            val intent = Intent(Cal_Splash this, Cal_Evaluation)
            Cal_Splash this.startActivity(intent)
            Cal_Splash this.finish();

        }, 5000)

    }
}
