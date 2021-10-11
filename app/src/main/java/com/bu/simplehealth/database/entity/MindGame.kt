package com.bu.simplehealth.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * @author Seema Palora
 * This is entity class to define the table
 */
@Entity(tableName = "mindgame")
class MindGame(@field:ColumnInfo(name = "game_name")  var gameName: String,
               @field:ColumnInfo(name = "game_url") var gameUrl: String,
               @field:ColumnInfo(name = "id") @field:PrimaryKey var gameID: Int) : Serializable