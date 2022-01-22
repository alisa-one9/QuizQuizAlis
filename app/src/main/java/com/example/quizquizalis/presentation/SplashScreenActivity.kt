package com.example.quizquizalis.presentation
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.quizquizalis.R

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {


    lateinit var ivBrain: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        ivBrain = findViewById(R.id.iv_brain)

        ivBrain.animate().alpha(1f).withEndAction{
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }



}
