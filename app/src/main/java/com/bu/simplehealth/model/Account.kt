package com.bu.simplehealth.model

/**
 * @author Seema Palora
 * Model class to map user data to entity
 */
data class Account (
    var userName: String = "",
    var password: String = "",
    var confirmPassword: String = "",
    var fullName: String = "",
    var email: String = "",
    var dob: String = ""
)