package app.android.movieslist.utils.fragment_utils;

import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import app.android.movieslist.R;

/**
 * Created by harrisazam on 2/9/2019.
 */

public class FragmentOp {

    /**
     * METHOD TO MOVE TO NEXT FRAGMENT SLIDE WITH ANIMATION
     */
    public static void gotoNextFragment(Context context, Fragment fragment) {
        AppCompatActivity activity = ((AppCompatActivity) context);
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_from_right,
                R.anim.slide_out_to_left, R.anim.slide_in_from_left,
                R.anim.slide_out_to_right);
        ft.replace(R.id.mainContainer, fragment);
        ft.addToBackStack("");
        ft.commitAllowingStateLoss();
    }

    /**
     * METHOD TO MOVE TO BACK (n) FRAGMENTS
     */
    public static void gotoBackFragment(Context context, int count) {
        try {
            AppCompatActivity activity = ((AppCompatActivity) context);
            FragmentManager fm = activity.getSupportFragmentManager();
            if (count > 0) {
                for (int i = 0; i < count; i++)
                    fm.popBackStack();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
