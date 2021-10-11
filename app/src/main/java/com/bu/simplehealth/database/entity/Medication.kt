package com.bu.simplehealth.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * @author Seema Palora
 * This is entity class to define the table
 */
@Entity(tableName = "medications",
        foreignKeys = arrayOf(ForeignKey(entity = User::class,
        parentColumns = arrayOf("user_name"),
        childColumns = arrayOf("name"),
        onDelete = ForeignKey.NO_ACTION)))
class Medication(@field:ColumnInfo(name = "medication_name")var medication_name: String,
                 @field:ColumnInfo(name = "strength") var strength: String,
                 @field:ColumnInfo(name = "frequency") var frequency: String,
                 @field:ColumnInfo(name = "name") var name: String) : Serializable{
                 @field:ColumnInfo(name = "id")
                 @PrimaryKey(autoGenerate = true)
                 var id: Int=0
                 }