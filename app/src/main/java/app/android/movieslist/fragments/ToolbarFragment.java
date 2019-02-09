package app.android.movieslist.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import app.android.movieslist.utils.KeyboardOp;

/**
 * Created by harrisazam on 2/9/2019.
 */

public abstract class ToolbarFragment extends Fragment {

    private static boolean isActive;

    public abstract void refreshToolbar();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshToolbar();
    }

    @Override
    public void onPause() {
        super.onPause();
        KeyboardOp.hideKeyboard(getActivity());
    }
}
