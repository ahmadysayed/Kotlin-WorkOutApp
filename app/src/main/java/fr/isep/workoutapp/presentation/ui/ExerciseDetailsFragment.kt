package fr.isep.workoutapp.presentation.ui

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.carolmusyoka.workoutapp.R
import com.carolmusyoka.workoutapp.databinding.FragmentExerciseDetailsBinding
import fr.isep.workoutapp.presentation.viewmodel.ExercisesViewModel


class ExerciseDetailsFragment : Fragment() {
    private lateinit var _binding: FragmentExerciseDetailsBinding
    private val binding get() = _binding
    private lateinit var exerciseViewModel: ExercisesViewModel
    private var countDownTimer: CountDownTimer? = null
    private var timeLeftInMillis: Long = 60000 // 1 minute
    private var isTimerRunning: Boolean = false
    private var mediaPlayer: MediaPlayer? = null

    private val sounds = listOf(
        R.raw.one, R.raw.two, R.raw.three,
        R.raw.four, R.raw.five, R.raw.six
    )



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         _binding = FragmentExerciseDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       activity.let {
           exerciseViewModel = ViewModelProvider(it!!).get(ExercisesViewModel::class.java)
       }

        binding.startButton.setOnClickListener {
            if (isTimerRunning) {
                pauseTimer()
                mediaPlayer?.pause() // Pause the sound
            } else {
                startTimer(timeLeftInMillis)
                playRandomSound() // Start the sound
            }
        }

        // Initially hide the CardView
        binding.descriptionTv.visibility = View.GONE
        binding.descriptionTv.requestLayout() // Force layout update

        // Initialize MediaPlayer
        mediaPlayer = MediaPlayer.create(context, R.raw.background_sound)

        // Set click listener for the descButton
        binding.descButton.setOnClickListener {
            Log.d("VisibilityToggle", "descButton clicked") // Debug log
            if (binding.descriptionTv.visibility == View.GONE) {
                binding.descriptionTv.visibility = View.VISIBLE
            } else {
                binding.descriptionTv.visibility = View.GONE
            }
            binding.descriptionTv.requestLayout() // Force layout update after change
        }

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_exerciseDetailsFragment_to_homeFragment)
            }
        }


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)

        exerciseViewModel.selectedExercise.observe(viewLifecycleOwner){
            Log.d("TAG", "onViewCreatedDetail:$it ")
            binding.apply {
                //exerciseName.text = it.exerciseName
                //difficultyLevelTv.text = it.difficultyLevel
                //requiredEquipmentTv.text = it.requiredEquipment
                //primaryMusclesTv.text = it.primaryMuscles
                //secondaryMusclesTv.text = it.secondaryMuscles
                descriptionTv.text = it.description
                Glide.with(exerciseImage.context)
                    .load(it.secondaryImage)
                    .placeholder(R.drawable.image_three)
                    .fitCenter()
                    .centerCrop()
                    .into(exerciseImage)
            }
        }


    }

    private fun startTimer(timeInMillis: Long) {
        countDownTimer = object : CountDownTimer(timeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                resetTimer()
            }
        }.start()

        isTimerRunning = true
        binding.startButton.text = "PAUSE"
    }

    private fun playRandomSound() {
        mediaPlayer?.release() // Release any existing MediaPlayer resource
        val randomSoundId = sounds.random() // Randomly select a sound
        mediaPlayer = MediaPlayer.create(context, randomSoundId)
        mediaPlayer?.start()
    }

    private fun pauseTimer() {
        countDownTimer?.cancel()
        isTimerRunning = false
        binding.startButton.text = "RESUME"
    }

    private fun updateCountDownText() {
        val minutes = (timeLeftInMillis / 1000) / 60
        val seconds = (timeLeftInMillis / 1000) % 60
        val timeFormatted = String.format("%02d:%02d", minutes, seconds)
        binding.time.text = timeFormatted
    }

    private fun resetTimer() {
        timeLeftInMillis = 60000 // Reset to 1 minute
        updateCountDownText()
        isTimerRunning = false
        binding.startButton.text = "START"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        countDownTimer?.cancel()  // Cancel the timer when the view is destroyed
        mediaPlayer?.release() // Release MediaPlayer resources
        mediaPlayer = null
    }

}