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

@Entity(tableName = "user_conditions",primaryKeys = ["condition_name", "user_name"])
class UserConditionRef(@field:ColumnInfo(name = "condition_name")  var conditionName: String,
                       @field:ColumnInfo(name = "user_name") var userName: String) : Serializable