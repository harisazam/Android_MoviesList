package app.android.movieslist.activities;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

public class SplashActivityTest {

    @Rule
    public ActivityTestRule<SplashActivity> activityActivityTestRule = new ActivityTestRule<SplashActivity>(SplashActivity.class);

    SplashActivity splashActivity;
    Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
        splashActivity = activityActivityTestRule.getActivity();
    }

    /**
     * TEST CASE TO CHECK INTENT CALL FROM SPLASH TO MAIN ACTIVITY
     */
    @Test
    public void checkBaseURLs() throws Exception {
        Activity mainActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(mainActivity);
    }

    @After
    public void tearDown() throws Exception {
    }
}