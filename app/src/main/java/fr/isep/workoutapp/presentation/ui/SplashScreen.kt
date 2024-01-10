package fr.isep.workoutapp.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.airbnb.lottie.LottieAnimationView
import com.carolmusyoka.workoutapp.R

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
            finish()
        }, 4000)
        val lottieAnimationView = findViewById<LottieAnimationView>(R.id.lottie)
        lottieAnimationView.playAnimation()

    }
    }
