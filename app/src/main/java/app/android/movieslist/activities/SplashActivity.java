package app.android.movieslist.activities;

import android.content.Intent;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import app.android.movieslist.R;

/**
 * Created by harrisazam on 2/8/2019.
 */

public class SplashActivity extends AwesomeSplash {

    @Override
    public void initSplash(ConfigSplash configSplash) {

        //CUSTOMIZE CIRCULAR VIEW
        configSplash.setBackgroundColor(R.color.colorPrimary);
        configSplash.setAnimCircularRevealDuration(1500);
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM);

        //CUSTOMIZE LOGO
        configSplash.setLogoSplash(R.drawable.ic_launcher);
        configSplash.setAnimLogoSplashDuration(1500);
        configSplash.setAnimLogoSplashTechnique(Techniques.FadeIn);

        //CUSTOMIZE TITLE
        configSplash.setTitleSplash(getResources().getString(R.string.str_welcome));
        configSplash.setTitleTextColor(R.color.colorWhite);
        configSplash.setTitleTextSize(20f);
        configSplash.setAnimTitleDuration(2500);
        configSplash.setAnimTitleTechnique(Techniques.FadeIn);
    }

    @Override
    public void animationsFinished() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}