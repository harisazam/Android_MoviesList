package app.android.movieslist.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import app.android.movieslist.R;
import app.android.movieslist.models.MovieModel;
import app.android.movieslist.utils.custom_views.CustomTextView;
import app.android.movieslist.utils.fragment_utils.FragmentOp;
import app.android.movieslist.utils.toolbar_utils.ToolbarOp;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by harrisazam on 2/9/2019.
 */

public class DetailFragment extends ToolbarFragment implements View.OnClickListener {

    private Context context;
    private String TAG = this.getClass().getSimpleName();
    public static String MOVIESMODEL = "MoviesModel";
    private MovieModel movieModel;

    @BindView(R.id.tv_MovieName)
    CustomTextView tv_MovieName;
    @BindView(R.id.tv_Description)
    CustomTextView tv_Description;
    @BindView(R.id.tv_producer)
    CustomTextView tv_producer;
    @BindView(R.id.tv_Director)
    CustomTextView tv_Director;
    @BindView(R.id.tv_release)
    CustomTextView tv_release;
    @BindView(R.id.tv_rate)
    CustomTextView tv_rate;
    @BindView(R.id.iv_locations)
    ImageView iv_locations;
    @BindView(R.id.iv_species)
    ImageView iv_species;
    @BindView(R.id.iv_vehicles)
    ImageView iv_vehicles;
    @BindView(R.id.iv_people)
    ImageView iv_people;

    public static DetailFragment newInstance(MovieModel movieModel) {
        Bundle args = new Bundle();
        DetailFragment fragment = new DetailFragment();
        args.putParcelable(MOVIESMODEL, movieModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        context = inflater.getContext();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        try {
            initView();
            if (getArguments() != null) {
                movieModel = getArguments().getParcelable(MOVIESMODEL);
                populateView(movieModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * METHOD TO INIT VIEWS
     */
    private void initView() {
        iv_locations.setOnClickListener(this);
        iv_people.setOnClickListener(this);
        iv_species.setOnClickListener(this);
        iv_vehicles.setOnClickListener(this);
    }

    /**
     * METHOD TO POPULATE DATA ON VIEW
     *
     * @param movieModel
     */
    private void populateView(MovieModel movieModel) {
        tv_MovieName.setText(movieModel.getTitle());
        tv_Description.setText(movieModel.getDescription());
        tv_Director.setText(movieModel.getDirector());
        tv_producer.setText(movieModel.getProducer());
        tv_release.setText(movieModel.getRelease_date());
        tv_rate.setText(movieModel.getRt_score());
    }

    @Override
    public void refreshToolbar() {
        ToolbarOp.refreshToolbar(getView(), context, getString(R.string.str_detail_screen),
                null, ToolbarOp.Theme.Dark, 0, null);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_locations:
                FragmentOp.gotoNextFragment(context, LocationFragment.newInstance());
                break;
            case R.id.iv_people:
                FragmentOp.gotoNextFragment(context, PeopleFragment.newInstance());
                break;
            case R.id.iv_species:
                FragmentOp.gotoNextFragment(context, SpeciesFragment.newInstance());
                break;
            case R.id.iv_vehicles:
                FragmentOp.gotoNextFragment(context, VehiclesFragment.newInstance());
                break;
        }
    }
}
