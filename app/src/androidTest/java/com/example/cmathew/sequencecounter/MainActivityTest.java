package com.example.cmathew.sequencecounter;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void clickCheckButton() {
        onView(withId(R.id.corpus_entry)).perform(replaceText("abc"), closeSoftKeyboard());
        onView(withId(R.id.sequence_entry)).perform(replaceText("abc"), closeSoftKeyboard());

        onView(withId(R.id.match_counter)).check(matches(withText("Matches: 1")));
    }

    @Test @Ignore
    public void noBodyError() {
        onView(withId(R.id.sequence_entry)).perform(replaceText("abc"), closeSoftKeyboard());

        ToastMatcher isToast = new ToastMatcher();
        onView(withText(R.string.missing_corpus_error)).inRoot(isToast).check(matches(isDisplayed()));
    }

    @Test @Ignore
    public void noSequenceError() {
        onView(withId(R.id.corpus_entry)).perform(replaceText("abc"), closeSoftKeyboard());

        ToastMatcher isToast = new ToastMatcher();
        onView(withText(R.string.missing_sequence_error)).inRoot(isToast).check(matches(isDisplayed()));
    }
}
