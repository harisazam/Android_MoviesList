package app.android.movieslist.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import app.android.movieslist.Constants;
import app.android.movieslist.R;
import app.android.movieslist.adapters.MoviesListAdapter;
import app.android.movieslist.adapters.PeopleListAdapter;
import app.android.movieslist.models.PeopleModel;
import app.android.movieslist.models.SpeciesModel;
import app.android.movieslist.utils.MessageUtil;
import app.android.movieslist.utils.Utils;
import app.android.movieslist.utils.custom_views.CustomTextView;
import app.android.movieslist.utils.toolbar_utils.ToolbarOp;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by harrisazam on 2/10/2019.
 */

public class SpeciesFragment extends ToolbarFragment implements View.OnClickListener {

    private Context context;
    private String TAG = this.getClass().getSimpleName();

    @BindView(R.id.tv_Name)
    CustomTextView tv_Name;
    @BindView(R.id.tv_classification)
    CustomTextView tv_classification;
    @BindView(R.id.tv_eye_colors)
    CustomTextView tv_eye_colors;
    @BindView(R.id.tv_hair_colors)
    CustomTextView tv_hair_colors;
    @BindView(R.id.rv_SpeciesCardParent)
    RelativeLayout rv_SpeciesCardParent;

    public static String SPECIESURL = "URL";
    private SkeletonScreen skeletonScreen;
    private String speciesURL;

    public static SpeciesFragment newInstance(String url) {
        Bundle args = new Bundle();
        SpeciesFragment fragment = new SpeciesFragment();
        args.putString(SPECIESURL, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_species, container, false);
        context = inflater.getContext();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        try {
            if (getArguments() != null) {
                speciesURL = getArguments().getString(SPECIESURL);
                getPeopleListAPICall(speciesURL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * METHOD TO SHOW SKELETON VIEW WHILE API CALLING
     */
    private void showSkeletonView() {
        skeletonScreen = Skeleton.bind(rv_SpeciesCardParent)
                .shimmer(true)
                .angle(20)
                .duration(1200)
                .load(R.layout.layout_skeleton_item_location)
                .show();
    }

    /**
     * GET MOVIES LIST DATA API NETWORK CALL
     */
    private void getPeopleListAPICall(String speciesURL) {
        if (!Utils.hasInternetConnection(context)) {
            showNoResult(true);
            MessageUtil.showSnackbar(context, getString(R.string.str_connection_error), true);
            return;
        }
        showSkeletonView();
        AndroidNetworking.get(speciesURL)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            SpeciesModel speciesModel = new Gson().fromJson(response.toString(), SpeciesModel.class);

                            populateView(speciesModel);
                            Log.e(TAG, "" + response.toString());
                        } catch (Exception e) {
                            Log.e(TAG, "" + e.getMessage());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        showNoResult(true);
                        Log.e(TAG, "" + anError.toString());
                    }
                });
    }

    /**
     * METHOD TO SHOW/HIDE NO RESULT VIEW
     */
    private void showNoResult(boolean isShow) {
        if (!isShow && skeletonScreen != null) {
            skeletonScreen.hide();
        }
    }

    /**
     * METHOD TO POPULATE DATA IN RECYCLER VIEW
     */
    private void populateView(final SpeciesModel speciesModels) {

        if (speciesModels != null) {
            showNoResult(false);
            tv_classification.setText(speciesModels.getClassification());
            tv_eye_colors.setText(speciesModels.getEye_colors());
            tv_hair_colors.setText(speciesModels.getHair_colors());
            tv_Name.setText(speciesModels.getName());

        } else {
            showNoResult(true);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_TryAgain:
                getPeopleListAPICall(speciesURL);
                break;
        }
    }

    @Override
    public void refreshToolbar() {
        ToolbarOp.refreshToolbar(getView(), context, getString(R.string.str_species),
                null, ToolbarOp.Theme.Dark, 0, null);
    }
}
