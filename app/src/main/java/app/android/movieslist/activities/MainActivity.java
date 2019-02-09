package app.android.movieslist.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import app.android.movieslist.R;
import app.android.movieslist.fragments.HomeFragment;
import app.android.movieslist.utils.AlertOP;
import app.android.movieslist.utils.toolbar_utils.ToolbarOp;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by harrisazam on 2/8/2019.
 */

public class MainActivity extends AppCompatActivity {

    private Context context;
    private String TAG = this.getClass().getSimpleName();

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private HomeFragment homeFragment = null;
    private FragmentManager fm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = MainActivity.this;

        initToolbar();
        setDefaultFragment();
    }

    /**
     * METHOD TO INIT AND SET DEFAULT TOOL BAR
     */
    private void initToolbar() {
        setSupportActionBar(toolbar);
        setHomeToolbar();
    }

    /**
     * METHOD TO SET DEFAULT FRAGMENT IN CONTAINER
     */
    private void setDefaultFragment() {
        fm = getSupportFragmentManager();

        if (homeFragment == null) {
            homeFragment = HomeFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.mainContainer, homeFragment).commit();
        }
    }

    /**
     * METHOD TO OPEN/CLOSE DRAWER
     */
    public void drawerAction(boolean isOpen) {
        try {
            if (isOpen) {
                drawerLayout.openDrawer(Gravity.START);
            } else {
                drawerLayout.closeDrawers();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * METHOD TO SET HOME TOOL BAR
     */
    private void setHomeToolbar() {
        ToolbarOp.refreshToolbar(toolbar, context, getString(R.string.str_movies_list),
                null, ToolbarOp.Theme.Dark, R.drawable.ic_menu, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawerAction(true);
                    }
                });
    }

    /**
     * METHOD TO GET CURRENT FRAGMENT
     */
    public Fragment getCurrentFragment() {
        Fragment fragment = (Fragment) fm.findFragmentById(R.id.mainContainer);
        return fragment;
    }

    @Override
    public void onBackPressed() {
        if (getCurrentFragment() instanceof HomeFragment) {
            AlertOP.showAlert(context, null, getString(R.string.str_exit), getString(R.string.str_yes), getString(R.string.str_no), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });

        } else {
            super.onBackPressed();
        }
    }
}
