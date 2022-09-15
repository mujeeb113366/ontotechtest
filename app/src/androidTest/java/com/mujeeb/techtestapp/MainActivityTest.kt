package com.mujeeb.techtestapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mujeeb.techtestapp.presentation.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    /*
    * ActivityScenarioRule launches a given activity before the test starts and closes after the test.
    */
    @get:Rule
    val mainActivityRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun should_pass_when_ExpectedMileageValue() {
        onView(withId(R.id.fb_refresh_user_data)).perform(click())
        onView(withId(R.id.tv_mileage_level)).check(matches(withText("747")))
    }


    @Test
    fun should_pass_when_ExpectedEnergyLevel() {
        onView(withId(R.id.fb_refresh_user_data)).perform(click())
        onView(withId(R.id.tv_energy_level)).check(matches(withText("72%")))
    }
}