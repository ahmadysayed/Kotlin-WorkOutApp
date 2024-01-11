package fr.isep.workoutapp.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.carolmusyoka.workoutapp.R

class TermOfService : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_term_of_service)

        // Find the TextViews by their ID
        val textViewHeading = findViewById<TextView>(R.id.textViewHeading)
        val textViewTerms = findViewById<TextView>(R.id.textViewTerms)

        // Set the Terms of Service text
        val termsText = "Please read these terms and conditions carefully...\n\n" +
                "1. You must be at least 18 years old to use the app.\n" +
                "2. You are responsible for maintaining the confidentiality of your account credentials..."

        textViewHeading.text = "Terms of Service" // Set the heading
        textViewTerms.text = termsText // Set the terms text
    }
}
