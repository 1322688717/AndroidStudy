package com.example.automatedtesting

import android.R
import android.provider.Contacts
import android.provider.Telephony.Mms.Intents
import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed

import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.not
import org.junit.Test


class LoginActivityTest {
//    @Test
//    fun should_start_main_activity_when_execute_login_given_valid_username_and_password() {
//        ActivityScenario.launch(MainActivity::class.java)
//        onView(withId(R.id.edtUsename)).perform(typeText("123@163.com"))
//        onView(withId(R.id.edtPassword)).perform(typeText("123456"))
//        Contacts.Intents.
//        onView(withId(R.id.login)).perform(click())
//        intended(
//            allOf(
//                toPackage("com.example.automatedtesting"),
//                hasComponent(hasClassName(MainActivity::class.java.name))
//            )
//        )
//    }
//
//    @Test
//    fun should_show_failed_toast_when_execute_login_given_invalid_username_and_password() {
//        val launch = ActivityScenario.launch(
//            MainActivity::class.java
//        )
//        onView(withId(R.id.edtUsename)).perform(typeText("123"))
//        onView(withId(R.id.edtPassword)).perform(typeText("456"))
//        onView(withId(R.id.btnLogin)).perform(click())
//        val decorView: View? = null
//        launch.onActivity { activity: MainActivity ->
//            activity.window.decorView
//        }
//        onView(withText("login failed")).inRoot(withDecorView(not(decorView))).check(
//            matches(
//                isDisplayed()
//            )
//        )
//    }
}