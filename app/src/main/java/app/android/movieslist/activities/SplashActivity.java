package app.android.movieslist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import app.android.movieslist.R;

/**
 * Created by harrisazam on 2/8/2019.
 */

public class SplashActivity extends AppCompatActivity {

    private int DELAYMILLISEC = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        moveToNext();
    }

    /**
     * METHOD TO MOVE TO NEXT ACTIVITY
     */
    private void moveToNext() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, DELAYMILLISEC);

    }
}
