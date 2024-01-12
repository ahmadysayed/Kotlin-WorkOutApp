package fr.isep.workoutapp.domain

import androidx.lifecycle.LiveData
import fr.isep.workoutapp.data.db.ExerciseDao
import fr.isep.workoutapp.data.entity.Exercise
import kotlinx.coroutines.flow.Flow


class ExerciseRepository(private val exerciseDao: ExerciseDao) {

    fun getAllExercises(): LiveData<List<Exercise>> {
        return exerciseDao.getExercises()
    }
    fun getExercisesByDifficulty(bodyPart: String): LiveData<List<Exercise>>{
        return exerciseDao.getBodyPart(bodyPart)
    }

    fun getAllOrSearch(bodyPart: String): Flow<List<Exercise>> {
        return if (bodyPart == "all") {
            exerciseDao.getAllExercises()
        } else {
            exerciseDao.getByBodyPart(bodyPart)
        }
    }
}