package app.android.movieslist;

import android.app.Application;
import android.content.Context;

/**
 * Created by harrisazam on 2/9/2019.
 */

public class App extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
