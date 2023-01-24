package com.guru2_18.guru2_waterdrop

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    val RC_SIGN_IN = 9000
    val TAG: String ="SignUP"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        // Firebase 인증 초기화
        auth = Firebase.auth

        button2.setOnClickListener{
            signUp()
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_TAB) {
            passwordText
            return true
        }
        return false
        // 이런식으로 처리를 하게 되면 시스템 백 버튼을 누르게 되어도 바탕화면으로 나가지지않고
        // 다른 기능을 실행시킬 수 있도록 시스템 버튼을 제어 할수 있다.
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
    }
    fun signUp() {
        val email = emailText.text.toString()
        val password = passwordText.text.toString()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    val mHandler = Handler()
                    Toast.makeText(
                        baseContext, "Authentication success.",
                        Toast.LENGTH_SHORT
                    ).show()
                    mHandler.postDelayed(Runnable {
                        val intent = Intent(this, Mainpage::class.java)
                        startActivity(intent)// 시간 지난 후 실행할 코딩
                    }, 500) // 0.5초후
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}