package com.bu.simplehealth.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * @author Seema Palora
 * This is entity class to define the table
 */
@Entity(tableName = "user")
class User(@field:ColumnInfo(name = "user_name")
           @field:PrimaryKey var userName: String,
           @field:ColumnInfo(name = "name") var name: String,
           @field:ColumnInfo(name = "password") var password: String,
           @field:ColumnInfo(name = "email") var email: String,
           @field:ColumnInfo(name = "dob") var dateOfBirth: String,
           @field:ColumnInfo(name = "salt") var salt: ByteArray) : Serializable