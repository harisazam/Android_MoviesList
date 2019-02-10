package app.android.movieslist.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.android.movieslist.R;
import app.android.movieslist.utils.toolbar_utils.ToolbarOp;
import butterknife.ButterKnife;

/**
 * Created by harrisazam on 2/10/2019.
 */

public class VehiclesFragment extends ToolbarFragment {

    private Context context;
    private String TAG = this.getClass().getSimpleName();

    public static VehiclesFragment newInstance() {
        Bundle args = new Bundle();
        VehiclesFragment fragment = new VehiclesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_vehicles, container, false);
        context = inflater.getContext();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void refreshToolbar() {
        ToolbarOp.refreshToolbar(getView(), context, getString(R.string.ste_vehicles),
                null, ToolbarOp.Theme.Dark, 0, null);

    }
}
