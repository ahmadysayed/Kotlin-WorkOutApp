package fr.isep.workoutapp.presentation.ui

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import mehdi.sakout.aboutpage.AboutPage
import mehdi.sakout.aboutpage.Element
import java.util.Calendar


class AboutUs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val adsElement = Element()
        val aboutPage = AboutPage(this)
            .isRTL(false)
            .setDescription("our inspiration for creating this workout app stemmed from our deep passion for fitness and our belief in the transformative power of technology. As software engineering students, we saw an opportunity to make a positive impact on individuals striving for healthier lifestyles. We aim to inspire users worldwide to take small steps towards a more healthier lifestyle and to motivate fitness enthusiasts worldwide to pursue their health and wellness goals effectively     Team members - Victor Habila, Ahmady Mujtabha, Asmau Aminu.  Thank you to our professor Mr. jerome BATON for his guidance throughout this semester.")
            .addItem(Element().setTitle("Version 1.0"))
            .addGroup("CONNECT WITH US!")
            .addEmail("aminuhusnah@gmai.com ")
            .addWebsite("Your website/")
            .addYoutube("UCbekhhidkzkGryM7mi5Ys_w")
            .addPlayStore("com.example.Gym app")
            .addInstagram("Gymapp_now")
            .addItem(createCopyright())
            .create()
        setContentView(aboutPage)
    }

    private fun createCopyright(): Element {
        val copyright = Element()
        @SuppressLint("DefaultLocale") val copyrightString = String.format(
            "Copyright %d App team",
            Calendar.getInstance()[Calendar.YEAR]
        )
        copyright.title = copyrightString
        // copyright.setIcon(R.mipmap.ic_launcher);
        copyright.gravity = Gravity.CENTER
        copyright.onClickListener =
            View.OnClickListener {
                Toast.makeText(this@AboutUs, copyrightString, Toast.LENGTH_SHORT).show()
            }
        return copyright
    }
}