package com.example.myapplication.activity

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import androidx.test.espresso.Espresso

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.myapplication.R
import com.example.myapplication.MainActivity

import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule


@RunWith(JUnit4::class)
class ActivityEspressoTestLanguageTest{

    @Rule
    var activityScenarioRule = ActivityScenarioRule(
        ActivityEspressoTestLanguage::class.java
    )

    @Test
     fun selectLanguageAndCheck() {
        onView(withId(R.id.german))    // ViewMatchers - withId(R.id.german) is to
                                       // specify that we are looking for Button
                                       // with id = R.id.german

            .perform(click())          // ViewActions - Performs click action on view.

        onView(withId(R.id.preferred_language))  // ViewMatchers - withId(R.id.preferred_language)
                                                 // is to specify that we are looking for a TextView
                                                 // with id = R.id.preferred_language

            .check(matches(withText("German"))) // ViewAssertions - validates if preferred_language
                                                    // matches with the text "German" since we
                                                    // pressed german language button.
    }
}