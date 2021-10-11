package com.bu.simplehealth.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * @author Seema Palora
 * This is entity class to define the table
 */
@Entity(tableName = "conditions")
class Condition(@field:ColumnInfo(name = "condition_name")  @field:PrimaryKey var conditionName: String,
                @field:ColumnInfo(name = "active_notification") var notificationStatus: String,
                @field:ColumnInfo(name = "conditionId")  var conditionID: Int) : Serializable