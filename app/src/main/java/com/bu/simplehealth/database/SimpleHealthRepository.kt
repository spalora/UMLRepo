package com.bu.simplehealth.database

import androidx.annotation.WorkerThread
import com.bu.simplehealth.database.entity.*

/**
 * @author Seema Palora
 * This class is to instantiate the room database
 */
class SimpleHealthRepository(private val simpleHealthDao: SimpleHealthDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.

    /**
     * This method is to insert the user data into User table, invoked from registration screen.
     * @param user
     * @return
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun insertUserData(user: User) {
        return simpleHealthDao.insertUserData(user)
    }

    /**
     * This method is to validate the user credential, invoked when user clicks on "Login",
     * Check if given username and password is availble in User table or not.
     * @param username
     * @param pwd
     * @return
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getUser(username: String?, pwd: String?): User? {
        return simpleHealthDao.getUser(username, pwd)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getUser(userName: String): User? {
        return simpleHealthDao.getUser(userName)
    }

    /**
     * This method is to reset the password, invoked from forgot password screen
     * @param pwd
     * @return
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updatePassword(pwd: String?,userName: String?,salt:ByteArray?): Int {
        return simpleHealthDao.updatePassword(pwd,userName,salt)
    }

    /**
     * This method is to insert the user medication data into medications table
     * @param medications
     * @return
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun insertUserMedicationData(medications: Medication) {
        return simpleHealthDao.insertUserMedicationData(medications)
    }


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getUserMedication(userName: String): Medication? {
        return simpleHealthDao.getUserMedication(userName)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun insertMindGameData(mindGames: List<MindGame>) {
        return simpleHealthDao.insertMindGameData(mindGames)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getMindGames(): List<MindGame>? {
        return simpleHealthDao.getMindgames()
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun insertExercises(exercises: List<Exercise>) {
        return simpleHealthDao.insertExercises(exercises)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getExercises(): List<Exercise>? {
        return simpleHealthDao.getExercises()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getExerciseCategories(): List<String>? {
        return simpleHealthDao.getExerciseDistinctCategories()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getExercisesCategories(category: String): List<Exercise>? {
        return simpleHealthDao.getExercisesFromCategory(category)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getExerciseFromCategory(category: String): List<String>? {
        return simpleHealthDao.getExerciseFromCategory(category)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getExerciseFromName(exerciseName: String): List<String>? {
        return simpleHealthDao.getExerciseFromName(exerciseName)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun insertConditions(conditions: List<Condition>) {
        return simpleHealthDao.insertConditionData(conditions)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getConditions(): List<Condition>? {
        return simpleHealthDao.getConditionData()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertUserConditions(userConditions: List<UserConditionRef>) {
        return simpleHealthDao.insertUserConditionData(userConditions)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getUserConditions(userName:String?) :List<String>?{
        return simpleHealthDao.getUserConditionData(userName)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteUserConditions(userName:String?) {
        return simpleHealthDao.deleteUserCondition(userName)
    }
}