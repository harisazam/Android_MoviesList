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
import app.android.movieslist.models.PeopleModel;
import app.android.movieslist.utils.custom_views.CustomTextView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by harrisazam on 2/10/2019.
 */

public class PeopleListAdapter extends RecyclerView.Adapter {
    private List<PeopleModel> dataSet;
    Context mContext;
    int total_types;
    public final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener mOnLoadMoreListener;
    private int visibleThreshold = 5;
    private boolean isLoading;

    public PeopleListAdapter() {
    }

    public PeopleListAdapter(List<PeopleModel> data, Context context, RecyclerView mRecyclerView) {
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

    class PeopleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_UserName)
        CustomTextView tv_UserName;
        @BindView(R.id.tv_Gender)
        CustomTextView tv_Gender;
        @BindView(R.id.tv_Age)
        CustomTextView tv_Age;
        @BindView(R.id.tv_EyeColor)
        CustomTextView tv_EyeColor;
        @BindView(R.id.tv_HairColor)
        CustomTextView tv_HairColor;

        public PeopleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
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

    public void addItems(List<PeopleModel> streamBOs) {
        dataSet.addAll(streamBOs);
        notifyDataSetChanged();
    }

    public List<PeopleModel> getItems() {
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

    public PeopleModel getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case VIEW_TYPE_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_people, parent, false);
                return new PeopleViewHolder(view);
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
            PeopleModel itemBO = dataSet.get(listPosition);
            if (holder instanceof PeopleViewHolder) {
                PeopleViewHolder itemTypeViewHolder = (PeopleViewHolder) holder;

                itemTypeViewHolder.tv_UserName.setText(itemBO.getName());
                itemTypeViewHolder.tv_Gender.setText(itemBO.getGender());
                itemTypeViewHolder.tv_Age.setText(itemBO.getAge());
                itemTypeViewHolder.tv_EyeColor.setText(itemBO.getEye_color());
                itemTypeViewHolder.tv_HairColor.setText(itemBO.getHair_color());

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
