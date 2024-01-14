package fr.isep.workoutapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.*
import fr.isep.workoutapp.data.db.ExerciseDatabase
import fr.isep.workoutapp.data.entity.Exercise
import fr.isep.workoutapp.domain.ExerciseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class ExercisesViewModel(application: Application) : AndroidViewModel(application)  {

    private val repository: ExerciseRepository
    val bodyPart = MutableStateFlow("all")

    init {
       val exerciseDao = ExerciseDatabase
           .getDatabase(application, viewModelScope, application.resources)
           .exerciseDao()
        repository = ExerciseRepository(exerciseDao)
    }

     val selectedExercise = MutableLiveData<Exercise>()

    fun setSelectedExercise(exercise: Exercise){
        selectedExercise.value = exercise
    }

    fun getAllExercises(): LiveData<List<Exercise>>{
        return repository.getAllExercises()
    }

    val exercises = bodyPart.flatMapLatest {
        repository.getAllOrSearch(it)
    }.asLiveData()


    fun getExercisesByBodyPart(category: String): LiveData<List<Exercise>>{
        return repository.getExercisesByBodyPart(category)

    }

}