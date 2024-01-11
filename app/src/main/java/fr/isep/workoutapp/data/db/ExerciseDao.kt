package fr.isep.workoutapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.isep.workoutapp.data.entity.Exercise
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertAllExercises(exercise: List<Exercise>)

    @Query("SELECT * FROM exercise")
    fun getExercises(): LiveData<List<Exercise>>
    @Query("SELECT * FROM exercise")
    fun getAllExercises(): Flow<List<Exercise>>

    @Query("SELECT * FROM exercise")
    fun getExercisesList(): List<Exercise>

    @Query("Select * from exercise WHERE bodyPartMuscles=:bodyPart")
    fun getBodyPart(bodyPart: String?): LiveData<List<Exercise>>

    @Query("Select * from exercise WHERE bodyPartMuscles=:bodyPart")
    fun getByBodyPart(bodyPart: String?): Flow<List<Exercise>>

}