package com.bu.simplehealth.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bu.simplehealth.database.entity.*

/**
 * @author Seema Palora
 * This class is to declare type of  SQL queries(insert,update,delete,get) to be performed
 */
@Dao
interface SimpleHealthDao {
    //User Table queries
    @Insert
    fun insertUserData(userData: User?)

    @Query("SELECT * FROM user WHERE  user_name LIKE :username AND password LIKE :pwd")
    fun getUser(username: String?, pwd: String?): User?

    @Query("SELECT * FROM user WHERE user_name = :userName")
    fun getUser(userName: String): User?

    @Query("DELETE FROM user")
    suspend fun deleteAll()

    @Query("UPDATE user set password= :pwd, salt=:salt WHERE user_name = :userName")
    suspend fun updatePassword(pwd: String?,userName: String?,salt:ByteArray?): Int

    //Medication table queries
    @Insert
    fun insertUserMedicationData(medications: Medication?)

    @Query("SELECT * FROM medications WHERE name = :userName")
    fun getUserMedication(userName: String): Medication?

    //Mindgame table queries
    @Insert
    fun insertMindGameData(mindGames: List<MindGame>?)

    @Query("SELECT * FROM mindgame")
    fun getMindgames(): List<MindGame>?

    //Exercise table queries
    @Insert
    fun insertExercises(exercises: List<Exercise>?)

    @Query("SELECT * FROM exercise")
    fun getExercises(): List<Exercise>?

    @Query("SELECT DISTINCT(exercise_category) FROM exercise")
    fun getExerciseDistinctCategories(): List<String>?

    @Query("SELECT * FROM exercise WHERE exercise_category = :category")
    fun getExercisesFromCategory(category: String): List<Exercise>?

    @Query("SELECT exercise_name FROM exercise WHERE exercise_category = :category")
    fun getExerciseFromCategory(category: String): List<String>?

    @Query("SELECT exercise_url FROM exercise WHERE exercise_name = :exerciseName")
    fun getExerciseFromName(exerciseName: String): List<String>?

    //Condition table queries
    @Insert
    fun insertConditionData(conditions: List<Condition>?)

    @Query("SELECT * FROM conditions")
    fun getConditionData(): List<Condition>?

    //Usercondition table query
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUserConditionData(join: List<UserConditionRef>)

    @Query("SELECT condition_name FROM user_conditions WHERE  user_name  =:userName")
    fun getUserConditionData(userName:String?): List<String>?

    @Query("DELETE FROM user_conditions WHERE  user_name  =:userName")
    fun deleteUserCondition(userName:String?)

}
