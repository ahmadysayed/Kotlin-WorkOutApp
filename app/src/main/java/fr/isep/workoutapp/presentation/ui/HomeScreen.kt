package fr.isep.workoutapp.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.carolmusyoka.workoutapp.R

class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        findViewById<LinearLayout>(R.id.layoutHome).setOnClickListener {
            startActivity(Intent(this, HomeScreen::class.java))
        }

        findViewById<LinearLayout>(R.id.layoutExercise).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.layoutAboutUs).setOnClickListener {
            startActivity(Intent(this, AboutUs::class.java))
        }

        findViewById<LinearLayout>(R.id.layoutContactUs).setOnClickListener {
            startActivity(Intent(this, ContactUs::class.java))
        }
    }
}

