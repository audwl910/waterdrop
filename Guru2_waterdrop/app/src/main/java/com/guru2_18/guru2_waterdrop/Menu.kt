package com.guru2_18.guru2_waterdrop

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_menu.*

class Menu : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        var hour = 1
        var watersetting :Int = 2000

        button4.setOnClickListener {
            val intent = Intent(this, Mainpage::class.java)
            startActivity(intent)
        }

        textView9.setOnClickListener {
            if(hour >=1){
                hour = hour -1
                textView7.setText(hour.toString()+"시간")
            }
        }

        textView8.setOnClickListener {
            hour = hour +1
            textView7.setText(hour.toString() + "시간")

        }




        textView15.setText(Mainpage.waterrecommendedintake.toString() + "ml")

        loadData2()



//        watersetting_minus.setOnClickListener {
//            if (watersetting >= 100){
//                watersetting = watersetting - 100
//                watersetting_text.setText(watersetting.toString())
//            }
//           }
//
//        watersetting_plus.setOnClickListener {
//            watersetting = watersetting + 100
//            watersetting_text.setText(watersetting.toString())
//        }



        }


    private fun loadData2() {
        textView14.setOnClickListener {
            Mainpage.waterrecommendedintake = Mainpage.waterrecommendedintake - 100
            textView15.setText(Mainpage.waterrecommendedintake.toString() + "ml")
            //target_intake.setText(Mainpage.waterrecommendedintake.toString() + "ml")
        }
        textView16.setOnClickListener {
            Mainpage.waterrecommendedintake = Mainpage.waterrecommendedintake + 100
            textView15.setText(Mainpage.waterrecommendedintake.toString() + "ml")
            //target_intake.setText(Mainpage.waterrecommendedintake.toString() + "ml")
        }
    }
}