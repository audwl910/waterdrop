package com.guru2_18.guru2_waterdrop

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_mainpage.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class Mainpage : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    //  val context_main : Context;

    // public static Context context_main; // context 변수 선언
    // public int var; // 다른 Activity에서 접근할 변수

    companion object {
        var waterintake = 0
        var strnow = "date"
        var waterrecommendedintake = 2000

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainpage)

        //context_main = this; // onCreate에서 this 할당


        bar.setOnClickListener {
            val intent = Intent(this@Mainpage, Menu::class.java)
            startActivity(intent)
        }


        cal.setOnClickListener {
            val intent = Intent(this, Calpage::class.java)
            intent.putExtra("strnow", "Int")
            startActivity(intent)
        }


        //느낌표 버튼 누르면 activity_exclamation 창 뜨게하기
        btn_exclamation.setOnClickListener {
            val intent = Intent(this@Mainpage, Exclamation::class.java)
            startActivity(intent)
        }


        //날짜 받아오기
        val now = LocalDate.now()
        strnow = now.format(DateTimeFormatter.ofPattern("20YY년 M월 d일"))

        //메인화면 상단의 날짜 출력
        val textViewDate: TextView = findViewById(R.id.text_date);
        textViewDate.setText(strnow)

        //권장섭취량 임의로 2리터로 정하고 메인화면 상단의 목표 섭취량(target_intake)에 출력함

        target_intake.setText(waterrecommendedintake.toString() + "ml")


        //SharePreference 에 값을 불러오는 방법
        val sharedPreference2 = getSharedPreferences("water drop", Context.MODE_PRIVATE)
        waterintake = sharedPreference2.getInt(strnow, 0)
        water_intake.setText(waterintake.toString() + "ml")
        water_recommended_intake.setText((waterrecommendedintake - waterintake).toString() + "ml")


        var percent = percentage(waterintake, waterrecommendedintake)


        //배경함수
        bg_select(percent)


        //메인 화면 하단의  +/-를 누르면 100ml씩 증가하거나 감소함
        //감소
        btn_minus.setOnClickListener {
            waterintake = waterintake - 100
            water_intake.setText(waterintake.toString() + "ml")
            water_recommended_intake.setText((waterrecommendedintake - waterintake).toString() + "ml")
            percent = percentage(waterintake, waterrecommendedintake)
            bg_select(percent)

        }
        //증가
        btn_plus.setOnClickListener {
            waterintake = waterintake + 100
            water_intake.setText(waterintake.toString() + "ml")
            water_recommended_intake.setText((waterrecommendedintake - waterintake).toString() + "ml")
            percent = percentage(waterintake, waterrecommendedintake)
            bg_select(percent)
        }


    }

    override fun onPause() {
        super.onPause()

        val sharedPreference = getSharedPreferences("water drop", Context.MODE_PRIVATE)

        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putInt(strnow, waterintake)
        editor.commit()


    }

    fun percentage(first: Int, second: Int): Int {
        return (first * 100) / second
        var result = (first * 100) / second
        Log.d("result123", result.toString())
    }

    //메인 페이지 물이 차오르는 배경 만드는 함수
    //권장 섭취량 2리터일때 기준 -> 바뀌면 수정해야함
    fun bg_select(first: Int) {
        if (first >= 0) {
            //메인 물 차오르는 이미지를 위해 이미지 숨기기
            full_bg.setVisibility(View.INVISIBLE)
            eighty_bg.setVisibility(View.INVISIBLE)
            sixty_bg.setVisibility(View.INVISIBLE)
            fourty_bg.setVisibility(View.INVISIBLE)
            twenty_bg.setVisibility(View.INVISIBLE)
            flower_select(0)
            if (first >= 20) {
                twenty_bg.setVisibility(View.VISIBLE)
                full_bg.setVisibility(View.INVISIBLE)
                eighty_bg.setVisibility(View.INVISIBLE)
                sixty_bg.setVisibility(View.INVISIBLE)
                fourty_bg.setVisibility(View.INVISIBLE)
                flower_select(1)
                if (first >= 40) {
                    fourty_bg.setVisibility(View.VISIBLE)
                    full_bg.setVisibility(View.INVISIBLE)
                    eighty_bg.setVisibility(View.INVISIBLE)
                    sixty_bg.setVisibility(View.INVISIBLE)
                    flower_select(2)
                    if (first >= 60) {
                        sixty_bg.setVisibility(View.VISIBLE)
                        full_bg.setVisibility(View.INVISIBLE)
                        eighty_bg.setVisibility(View.INVISIBLE)
                        flower_select(3)
                        if (first >= 80) {
                            eighty_bg.setVisibility(View.VISIBLE)
                            full_bg.setVisibility(View.INVISIBLE)
                            flower_select(4)
                            if (first >= 100) {
                                full_bg.setVisibility(View.VISIBLE)
                                flower_select(5)

                            }
                        }
                    }
                }

            }

        }

    }

    fun flower_select(frist: Int) {
        flower_1.setVisibility(View.INVISIBLE)
        flower_2.setVisibility(View.INVISIBLE)
        flower_3.setVisibility(View.INVISIBLE)
        flower_4.setVisibility(View.INVISIBLE)
        flower_5.setVisibility(View.INVISIBLE)

        when (frist) {
            1 -> flower_1.setVisibility(View.VISIBLE)
            2 -> flower_2.setVisibility(View.VISIBLE)
            3 -> flower_3.setVisibility(View.VISIBLE)
            4 -> flower_4.setVisibility(View.VISIBLE)
            5 -> flower_5.setVisibility(View.VISIBLE)
            else -> flower_1.setVisibility(View.INVISIBLE)


        }


    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
//            R.id.c -> Toast.makeText(applicationContext, "알림", Toast.LENGTH_SHORT).show()
//            R.id.d -> Toast.makeText(applicationContext, "알림", Toast.LENGTH_SHORT).show()
        }
        layout_drawer.closeDrawers()
        return false
    }

    override fun onBackPressed() {
        if (layout_drawer.isDrawerOpen(GravityCompat.START)) {
            layout_drawer.closeDrawers()
        } else {
            super.onBackPressed()
        }
    }
}