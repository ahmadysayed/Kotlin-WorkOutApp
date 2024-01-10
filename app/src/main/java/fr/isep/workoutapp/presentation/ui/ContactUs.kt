package fr.isep.workoutapp.presentation.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.carolmusyoka.workoutapp.databinding.ActivityContactUsBinding

class ContactUs : AppCompatActivity() {

    private lateinit var binding: ActivityContactUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactUsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sendBtn.setOnClickListener {
            val email = binding.emailAddress.text.toString().trim()
            val subject = binding.subject.text.toString().trim()
            val message = binding.message.text.toString().trim()

            // Validating email, subject, and message
            if (!isValidEmail(email)) {
                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (subject.isEmpty()) {
                Toast.makeText(this, "Please enter a subject", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (message.isEmpty()) {
                Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val addresses = arrayOf(email)

            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "message/rfc822"
                putExtra(Intent.EXTRA_EMAIL, addresses)
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, message)
            }

            val chooser = Intent.createChooser(intent, "Choose an Email client")
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(chooser)
            } else {
                Toast.makeText(this@ContactUs, "No email client installed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidEmail(target: CharSequence): Boolean {
        return !target.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}
