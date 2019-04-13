package com.example.gymguide;


import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.gymguide.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class QRCodeScaningTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.CAMERA");

    @Test
    public void qRCodeScaning2() {

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btnGuest), withText("Continue as a Guest"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                                0)),
                                5),
                        isDisplayed()));
        appCompatButton.perform(click());


        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.qrcode_button),
                        childAtPosition(
                                withParent(withId(R.id.viewpager)),
                                3),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.scan_btn), withText("SCAN"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton2.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textView = onView(
                allOf(withId(R.id.exercise_title), withText("Dumbbell Bicep Curls"),
//                        childAtPosition(
//                                allOf(withId(R.id.coordinatorLayout),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                1),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.imageView),
//                        childAtPosition(
//                                allOf(withId(R.id.coordinatorLayout),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                2),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.instructions_textview), withText("Instructions"),
//                        childAtPosition(
//                                allOf(withId(R.id.coordinatorLayout),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                4),
                        isDisplayed()));
        textView2.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.description_textview), withText("1.Stand up straight with a dumbbell in each hand at arm's length. Keep your elbows close to your torso and rotate the palms of your hands until they are facing forward. This will be your starting position. \n2.Now, keeping the upper arms stationary, exhale and curl the weights while contracting your biceps. Continue to raise the weights until your biceps are fully contracted and the dumbbells are at shoulder level. Hold the contracted position for a brief pause as you squeeze your biceps. \n3.Then, inhale and slowly begin to lower the dumbbells back to the starting position. \n4.Repeat for the recommended amount of repetitions."),
//                        childAtPosition(
//                                allOf(withId(R.id.coordinatorLayout),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                5),
                        isDisplayed()));
        textView3.check(matches(isDisplayed()));

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.btnAddWorkout),
//                        childAtPosition(
//                                allOf(withId(R.id.coordinatorLayout),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                6),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        ViewInteraction viewGroup = onView(
                allOf(withId(R.id.include),
//                        childAtPosition(
//                                allOf(withId(R.id.coordinatorLayout),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                0),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));

        ViewInteraction viewGroup2 = onView(
                allOf(withId(R.id.include),
//                        childAtPosition(
//                                allOf(withId(R.id.coordinatorLayout),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                0),
                        isDisplayed()));
        viewGroup2.check(matches(isDisplayed()));


        //exit and return

        pressBack();
        pressBack();
        ViewInteraction tabView = onView(
                allOf(withContentDescription("PROFILE"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                2),
                        isDisplayed()));
        tabView.perform(click());

        ViewInteraction loginButtontoExit = onView(
                allOf(withId(R.id.btnLogout), withText("LOGIN"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                4)));
        loginButtontoExit.perform( click());

        }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
