package com.pd.userlisttest.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidatorTest {

    @Test
    fun inputValid() {
        val name = "Pratik"
        val number = "123"
        val result = Validator.validateInput(name, number)

        assertThat(result).isEqualTo(true)
    }

    @Test
    fun inputInValid() {
        val name = ""
        val number = ""
        val result = Validator.validateInput(name, number)

        assertThat(result).isEqualTo(false)
    }
}