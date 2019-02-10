package app.android.movieslist.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.List;

import app.android.movieslist.R;
import app.android.movieslist.interfaces.OnLoadMoreListener;
import app.android.movieslist.models.MovieModel;
import app.android.movieslist.utils.custom_views.CustomTextView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by harrisazam on 2/9/2019.
 */

public class MoviesListAdapter extends RecyclerView.Adapter {
    private List<MovieModel> dataSet;
    Context mContext;
    int total_types;
    public final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener mOnLoadMoreListener;
    private int visibleThreshold = 5;
    private boolean isLoading;

    public MoviesListAdapter() {
    }

    public MoviesListAdapter(List<MovieModel> data, Context context, RecyclerView mRecyclerView) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                try {
                    int lastVisibleItem, totalItemCount;

                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                    if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        if (mOnLoadMoreListener != null) {
                            mOnLoadMoreListener.onLoadMore();
                        }
                        isLoading = true;
                    }
                } catch (Exception e) {
                    Log.e("MoviesListAdapter", "" + e.getMessage());
                }
            }
        });

    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
        @BindView(R.id.rv_MovieCardParent)
        RelativeLayout rv_MovieCardParent;


        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            iv_locations.setOnClickListener(this);
            iv_people.setOnClickListener(this);
            iv_species.setOnClickListener(this);
            iv_vehicles.setOnClickListener(this);
            rv_MovieCardParent.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rv_MovieCardParent:
                    if (clickListener != null)
                        clickListener.onItemClick(v, getAdapterPosition());
                    break;
                case R.id.iv_locations:
                    if (clickListener != null)
                        clickListener.onItemClick(v, getAdapterPosition());
                    break;
                case R.id.iv_people:
                    if (clickListener != null)
                        clickListener.onItemClick(v, getAdapterPosition());
                    break;
                case R.id.iv_species:
                    if (clickListener != null)
                        clickListener.onItemClick(v, getAdapterPosition());
                    break;
                case R.id.iv_vehicles:
                    if (clickListener != null)
                        clickListener.onItemClick(v, getAdapterPosition());
                    break;
            }
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
        }
    }


    public void setLoaded() {
        isLoading = false;
    }


    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    public void addItems(List<MovieModel> streamBOs) {
        dataSet.addAll(streamBOs);
        notifyDataSetChanged();
    }

    public List<MovieModel> getItems() {
        return dataSet;
    }

    public void removeLoadingItem() {
        dataSet.remove(dataSet.size() - 1);
        notifyItemRemoved(dataSet.size());
    }

    public void addLoadingItem() {
        dataSet.add(null);
        notifyItemInserted(dataSet.size() - 1);
    }

    public void removeItems(int position) {
        dataSet.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, dataSet.size());
    }


    public void clearItems() {
        dataSet.clear();
    }

    public MovieModel getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case VIEW_TYPE_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_movie, parent, false);
                return new MovieViewHolder(view);
            case VIEW_TYPE_LOADING:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_loading_item, parent, false);
                return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return dataSet.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {
        try {
            MovieModel itemBO = dataSet.get(listPosition);
            if (holder instanceof MovieViewHolder) {
                MovieViewHolder itemTypeViewHolder = (MovieViewHolder) holder;

                itemTypeViewHolder.tv_MovieName.setText(itemBO.getTitle());
                itemTypeViewHolder.tv_Description.setText(itemBO.getDescription());
                itemTypeViewHolder.tv_Director.setText(itemBO.getDirector());
                itemTypeViewHolder.tv_producer.setText(itemBO.getProducer());
                itemTypeViewHolder.tv_release.setText(itemBO.getRelease_date());
                itemTypeViewHolder.tv_rate.setText(itemBO.getRt_score());

            } else if (holder instanceof LoadingViewHolder) {
                LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
                loadingViewHolder.progressBar.setIndeterminate(true);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        int itemCount = 0;
        if (dataSet != null && dataSet.size() > 0) {
            itemCount = dataSet.size();
        }
        return itemCount;
    }

    /*Listeners*/
    ClickListeners clickListener;

    public void setClickListener(ClickListeners clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListeners {
        void onItemClick(View view, int position);

    }

}
