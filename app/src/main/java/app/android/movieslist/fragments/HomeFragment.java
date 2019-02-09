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

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;

import app.android.movieslist.Constants;
import app.android.movieslist.R;
import app.android.movieslist.adapters.MoviesListAdapter;
import app.android.movieslist.models.MovieModel;
import app.android.movieslist.utils.custom_views.CustomTextView;
import app.android.movieslist.utils.fragment_utils.FragmentOp;
import app.android.movieslist.utils.toolbar_utils.ToolbarOp;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by harrisazam on 2/9/2019.
 */

public class HomeFragment extends ToolbarFragment {

    private Context context;
    private String TAG = this.getClass().getSimpleName();

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;

//    @BindView(R.id.noResultView)
//    View noResultView;

    private MoviesListAdapter moviesListAdapter;
    private SkeletonScreen skeletonScreen;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        context = inflater.getContext();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        initViews();
        showSkeletonView();
    }

    private void showSkeletonView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        MoviesListAdapter adapter = new MoviesListAdapter();
        skeletonScreen = Skeleton.bind(recyclerView)
                .adapter(adapter)
                .shimmer(true)
                .angle(20)
                .frozen(false)
                .duration(1200)
                .count(10)
                .load(R.layout.layout_skeleton_item_movie)
                .show(); //default count is 10
    }

    @Override
    public void refreshToolbar() {
        ToolbarOp.refreshToolbar(getView(), context, getString(R.string.str_movies_list),
                null, ToolbarOp.Theme.Dark, 0, null);
    }

    /**
     * METHOD TO INIT VIEWS
     */
    private void initViews() {

        showNoResult(true);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimary,
                R.color.colorPrimary);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (moviesListAdapter != null) {
                    moviesListAdapter = null;
                }
                getDummyDataAPICall();
            }
        });
    }

    /**
     * GET MOVIES LIST DATA API NETWORK CALL
     */
    private void getDummyDataAPICall() {

        AndroidNetworking.get(Constants.API_BASE_URL + Constants.API_END_POINT_FILMS)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        stopRefreshing();
                        try {

                            Type listType = new TypeToken<ArrayList<MovieModel>>() {
                            }.getType();
                            ArrayList<MovieModel> userModelArrayList = new Gson().fromJson(response.toString(), listType);

                            if (userModelArrayList != null && userModelArrayList.size() > 0) {
                                showNoResult(false);
                                populateRecyclerView(userModelArrayList);
                            }
                            Log.e(TAG, "" + response.toString());
                        } catch (Exception e) {
                            Log.e(TAG, "" + e.getMessage());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        stopRefreshing();
                        showNoResult(true);
                        Log.e(TAG, "" + anError.toString());
                    }
                });
    }

    /**
     * METHOD TO STOP REFRESHING
     */
    private void stopRefreshing() {
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    /**
     * METHOD TO SHOW/HIDE NO RESULT VIEW
     */
    private void showNoResult(boolean isShow) {
//        noResultView.setVisibility(isShow ? View.VISIBLE : View.GONE);
//        recyclerView.setVisibility(isShow ? View.GONE : View.VISIBLE);
    }

    /**
     * METHOD TO POPULATE DATA IN RECYCLER VIEW
     */
    private void populateRecyclerView(final ArrayList<MovieModel> movieModels) {

        if (movieModels != null && movieModels.size() > 0) {
            if (moviesListAdapter == null) {
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setLayoutManager(new LinearLayoutManager(context,
                        LinearLayoutManager.VERTICAL, false));
                moviesListAdapter = new MoviesListAdapter(movieModels, context, recyclerView);
                recyclerView.setAdapter(moviesListAdapter);
            } else {
                //TODO OTHER WISE ADD DATA
            }
        } else {
            showNoResult(true);
        }
    }
}
