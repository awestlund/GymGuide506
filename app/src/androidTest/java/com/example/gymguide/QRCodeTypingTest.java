package com.example.gymguide;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.gymguide.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.replaceText;
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
public class QRCodeTypingTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void qRCodeTypingTest() {
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


        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.typedQR),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.typedQR),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.TextInputLayout")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText8.perform(replaceText("pd8LWJ3FLlUv0N5XbPWV"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.type_btn), withText("SUBMIT TEXT"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton2.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textView = onView(
                allOf(withId(R.id.exercise_title), withText("Side Lateral Raise"),
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
                allOf(withId(R.id.description_textview), withText("1.Pick a couple of dumbbells and stand with a straight torso and the dumbbells by your side at arms length with the palms of the hand facing you. This will be your starting position.\n2.While maintaining the torso in a stationary position (no swinging), lift the dumbbells to your side with a slight bend on the elbow and the hands slightly tilted forward as if pouring water in a glass. Continue to go up until you arms are parallel to the floor. Exhale as you execute this movement and pause for a second at the top.\n3.Lower the dumbbells back down slowly to the starting position as you inhale.4.Repeat for the recommended amount of repetitions."),
//                        childAtPosition(
//                                allOf(withId(R.id.coordinatorLayout),
//                                        childAtPosition(
//                                                withId(android.R.id.content),
//                                                0)),
//                                5),
                        isDisplayed()));
        textView3.check(matches(isDisplayed()));

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
