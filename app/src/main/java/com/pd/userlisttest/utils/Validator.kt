package com.pd.userlisttest.utils

//Check valid input for Name and Number
object Validator {
    fun validateInput(name: String, number: String): Boolean {
        return !(name.isNullOrEmpty() || number.isNullOrEmpty())
    }
}