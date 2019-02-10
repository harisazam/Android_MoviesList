package app.android.movieslist.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import app.android.movieslist.App;
import app.android.movieslist.Constants;
import app.android.movieslist.R;
import app.android.movieslist.fragments.HomeFragment;
import app.android.movieslist.utils.Utils;
import okhttp3.Response;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityTest {

    private MainActivity mainActivity;
    private HomeFragment homeFragment;
    private LinearLayoutManager mockLayoutManager;

    @Rule
    public final MockWebServer server = new MockWebServer();

    @Before
    public void setUp() throws Exception {
        mainActivity = Mockito.mock(MainActivity.class);
        homeFragment = Mockito.mock(HomeFragment.class);
    }

    @Test
    public void checkJSONArrayGetRequest() throws Exception {
        final CountDownLatch latch = new CountDownLatch(1);
        AndroidNetworking.get(Constants.API_END_POINT_FILMS)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        assertTrue(true);
                    }

                    @Override
                    public void onError(ANError anError) {
                        assertTrue(false);
                    }
                });
        assertTrue(latch.await(2, SECONDS));
    }

    /**
     * TEST CASE TO CHECK BASE URL ARE NOT EMPTY
     */
    @Test
    public void checkViews() throws Exception {
        RecyclerView recyclerView = (RecyclerView) homeFragment.getView().findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        assertNotNull(layoutManager);
    }

    /**
     * TEST CASE TO CHECK BASE URL ARE NOT EMPTY
     */
    @Test
    public void checkBaseURLs() throws Exception {
        assertTrue(!TextUtils.isEmpty(Constants.API_END_POINT_FILMS));
        assertTrue(!TextUtils.isEmpty(Constants.API_END_POINT_LOCATION));
        assertTrue(!TextUtils.isEmpty(Constants.API_END_POINT_PEOPLE));
        assertTrue(!TextUtils.isEmpty(Constants.API_END_POINT_VEHICLE));
    }

    /**
     * TEST CASE TO CHECK INTERNET CONNECTIVITY
     */
    @Test
    public void checkIfInternetConnected() throws Exception {
        final Context context = Mockito.mock(Context.class);
        final ConnectivityManager connectivityManager = Mockito.mock(ConnectivityManager.class);
        final NetworkInfo networkInfo = Mockito.mock(NetworkInfo.class);

        Mockito.when(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager);
        Mockito.when(connectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);
        Mockito.when(networkInfo.isConnected()).thenReturn(true);
    }

    @After
    public void tearDown() throws Exception {
    }
}