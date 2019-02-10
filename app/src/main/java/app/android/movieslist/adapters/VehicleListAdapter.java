package app.android.movieslist.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import app.android.movieslist.R;
import app.android.movieslist.interfaces.OnLoadMoreListener;
import app.android.movieslist.models.VehicleModel;
import app.android.movieslist.utils.custom_views.CustomTextView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by harrisazam on 2/10/2019.
 */

public class VehicleListAdapter extends RecyclerView.Adapter {
    private List<VehicleModel> dataSet;
    Context mContext;
    int total_types;
    public final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener mOnLoadMoreListener;
    private int visibleThreshold = 5;
    private boolean isLoading;

    public VehicleListAdapter() {
    }

    public VehicleListAdapter(List<VehicleModel> data, Context context, RecyclerView mRecyclerView) {
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

    class VehicleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_VehicleName)
        CustomTextView tv_VehicleName;
        @BindView(R.id.tv_Description)
        CustomTextView tv_Description;
        @BindView(R.id.tv_length)
        CustomTextView tv_length;
        @BindView(R.id.tv_type)
        CustomTextView tv_type;

        public VehicleViewHolder(View itemView) {
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

    public void addItems(List<VehicleModel> streamBOs) {
        dataSet.addAll(streamBOs);
        notifyDataSetChanged();
    }

    public List<VehicleModel> getItems() {
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

    public VehicleModel getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case VIEW_TYPE_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_vehicle, parent, false);
                return new VehicleViewHolder(view);
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
            VehicleModel itemBO = dataSet.get(listPosition);
            if (holder instanceof VehicleViewHolder) {
                VehicleViewHolder itemTypeViewHolder = (VehicleViewHolder) holder;

                itemTypeViewHolder.tv_VehicleName.setText(itemBO.getName());
                itemTypeViewHolder.tv_Description.setText(itemBO.getDescription());
                itemTypeViewHolder.tv_length.setText(itemBO.getLength());
                itemTypeViewHolder.tv_type.setText(itemBO.getVehicle_class());

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
