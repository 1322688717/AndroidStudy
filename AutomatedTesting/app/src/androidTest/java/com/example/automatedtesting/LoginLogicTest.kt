package com.example.automatedtesting

import com.example.automatedtesting.MainActivity.LoginLogic
import org.junit.Assert
import org.junit.Test


class LoginLogicTest {
    @Test
    fun should_return_false_when_password_is_null() {
        val loginLogic = LoginLogic()
        val password: String? = null
        val result = loginLogic.isPasswordValid(password)
        Assert.assertFalse(result)
    }

    @Test
    fun should_return_false_when_password_length_is_less_than_5() {
        val loginLogic = LoginLogic()
        val password = "1234"
        val result = loginLogic.isPasswordValid(password)
        Assert.assertFalse(result)
    }

    @Test
    fun should_return_false_when_password_length_is_equal_5() {
        val loginLogic = LoginLogic()
        val password = "12345"
        val result = loginLogic.isPasswordValid(password)
        Assert.assertFalse(result)
    }

    @Test
    fun should_return_true_when_password_length_greater_than_5() {
        val loginLogic = LoginLogic()
        val password = "123456"
        val result = loginLogic.isPasswordValid(password)
        Assert.assertTrue(result)
    }
}