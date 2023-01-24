package com.guru2_18.guru2_waterdrop


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_calpage.*

import kotlinx.android.synthetic.main.activity_mainpage.*


class Calpage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calpage)

        flower1.setVisibility(View.INVISIBLE)
        flower2.setVisibility(View.INVISIBLE)
        flower3.setVisibility(View.INVISIBLE)
        flower4.setVisibility(View.INVISIBLE)
        flower5.setVisibility(View.INVISIBLE)

        btn_ex.setOnClickListener {
            val intent = Intent(this@Calpage, Exclamation::class.java)
            startActivity(intent)
        }

        bar2.setOnClickListener {
            val intent = Intent(this@Calpage, Menu::class.java)
            startActivity(intent)
        }


        calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            diaryTextView.visibility = View.VISIBLE // 해당 날짜가 뜨는 textView가 Visible
            diaryTextView.text = String.format( "%d 년 %d 월 %d 일", year, month + 1, dayOfMonth) // 날짜를 보여주는 텍스트에 해당 날짜를 넣는다.
            dayml.visibility=View.VISIBLE

            Mainpage.strnow = String.format("%d년 %d월 %d일",year,month+1,dayOfMonth)
            //dayml.text=String.format(Mainpage.strnow)
            //dayml.setText("총 섭취량 = "+Mainpage.waterintake.toString()+"ml")
            loadData()

            bg_select(Mainpage.waterintake)


        }




    }

    override fun onPause() {
        super.onPause()

        val sharedPreference = getSharedPreferences("water drop",Context.MODE_PRIVATE)

        val editor : SharedPreferences.Editor = sharedPreference.edit()
        editor.putInt(Mainpage.strnow, Mainpage.waterintake)
        editor.commit()


    }

    private fun loadData(){
        val pref=getSharedPreferences("water drop", Context.MODE_PRIVATE)
        Mainpage.waterintake = pref.getInt(Mainpage.strnow, 0)
        dayml.setText("\n하루 섭취량 = "+Mainpage.waterintake.toString()+"ml")
    }



    fun bg_select(frist: Int) {
        if (frist >= 0) {
            //메인 물 차오르는 이미지를 위해 이미지 숨기기
            flower_select(0)
            if (frist >= 400) {
                flower_select(1)
                if (frist >= 800) {
                    flower_select(2)
                    if (frist >= 1200) {
                        flower_select(3)
                        if (frist >= 1600) {
                            flower_select(4)
                            if (frist >= 2000) {
                                flower_select(5)

                            }
                        }
                    }
                }

            }

        }

    }




    fun flower_select(frist: Int) {
        flower1.setVisibility(View.INVISIBLE)
        flower2.setVisibility(View.INVISIBLE)
        flower3.setVisibility(View.INVISIBLE)
        flower4.setVisibility(View.INVISIBLE)
        flower5.setVisibility(View.INVISIBLE)

        when (frist) {
            1 -> flower1.setVisibility(View.VISIBLE)
            2 -> flower2.setVisibility(View.VISIBLE)
            3 -> flower3.setVisibility(View.VISIBLE)
            4 -> flower4.setVisibility(View.VISIBLE)
            5 -> flower5.setVisibility(View.VISIBLE)
            else -> flower1.setVisibility(View.INVISIBLE)


        }




    }





}