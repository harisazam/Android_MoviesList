package app.android.movieslist.activities;

import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import app.android.movieslist.R;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

public class MainActivityTestCase {


    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mainActivity = null;


    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityTestRule.getActivity();
    }

    /**
     * TEST CASE TO CHECK MAIN ACTIVITY CALLED AFTER SPLASH ACTIVITY
     */
    @Test
    public void checkViewRendered() {
        assertNotNull(mainActivity.findViewById(R.id.mainContainer));
        assertNotNull(mainActivity.findViewById(R.id.drawerLayout));
        assertNotNull(mainActivity.findViewById(R.id.toolbar));
    }


    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }

}