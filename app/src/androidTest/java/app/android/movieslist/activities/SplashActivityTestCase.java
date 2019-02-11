package app.android.movieslist.activities;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.widget.Toolbar;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import app.android.movieslist.R;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

public class SplashActivityTestCase {


    @Rule
    public ActivityTestRule<SplashActivity> splashActivityTestRule = new ActivityTestRule<SplashActivity>(SplashActivity.class);

    private SplashActivity splashActivity = null;

    Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
        splashActivity = splashActivityTestRule.getActivity();

    }

    /**
     * TEST CASE TO CHECK MAIN ACTIVITY CALLED AFTER SPLASH ACTIVITY
     */
    @Test
    public void checkCallFromSplash() {
       Activity mainActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(mainActivity);
    }


    @After
    public void tearDown() throws Exception {
        splashActivity = null;
    }

}