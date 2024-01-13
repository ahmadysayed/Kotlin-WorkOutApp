package fr.isep.workoutapp.presentation.ui

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.os.Handler
import android.os.Looper
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.carolmusyoka.workoutapp.R
import com.carolmusyoka.workoutapp.databinding.ActivityHomeScreenBinding
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.abs

class HomeScreen : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityHomeScreenBinding
    private lateinit var  viewPager2: ViewPager2
    private lateinit var viewPager: ViewPager2
    private lateinit var handler : Handler
    private lateinit var imageList:ArrayList<Int>
    private lateinit var adapter: ImageAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)



        init()
        setUpTransformer()

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable , 2000)
            }
        })


        firebaseAuth = FirebaseAuth.getInstance()


        // Assuming you have a button in your layout to handle the logout action
        val logoutButton = findViewById<TextView>(R.id.logout)
        logoutButton.setOnClickListener {
            // Call the signOut method
            firebaseAuth.signOut()

            // Optionally, redirect the user to a login screen after logout
            val loginIntent = Intent(this, SignInActivity2::class.java)
            startActivity(loginIntent)

            // Finish the current activity so the user can't return to it after logging out
            finish()
        }


        findViewById<LinearLayout>(R.id.layoutExercise).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.layoutAboutUs).setOnClickListener {
            startActivity(Intent(this,  MainHome::class.java))
        }

        findViewById<LinearLayout>(R.id.layoutContactUs).setOnClickListener {
            startActivity(Intent(this, ContactUs::class.java))
        }



    }






    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()

        handler.postDelayed(runnable , 2000)
    }

    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    private fun setUpTransformer(){
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }

        viewPager2.setPageTransformer(transformer)
    }

    private fun init(){
        viewPager2 = findViewById(R.id.viewPager2)
        handler = Handler(Looper.myLooper()!!)
        imageList = ArrayList()

        imageList.add(R.drawable.arnold)
        imageList.add(R.drawable.cbum)
        imageList.add(R.drawable.mike)
        imageList.add(R.drawable.tibo)



        adapter = ImageAdapter(imageList, viewPager2)

        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }
}

