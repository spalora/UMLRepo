package com.bu.simplehealth.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * @author Seema Palora
 * This is entity class to define the table
 */
@Entity(tableName = "exercise")
class Exercise(@field:ColumnInfo(name = "exercise_name") @field:PrimaryKey var exerciseName: String,
               @field:ColumnInfo(name = "exercise_category") var exerciseCategory: String,
               @field:ColumnInfo(name = "exercise_url") var exerciseUrl: String) : Serializable