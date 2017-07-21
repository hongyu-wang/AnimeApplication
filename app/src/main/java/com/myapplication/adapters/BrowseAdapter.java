package com.myapplication.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.myapplication.R;
import com.squareup.picasso.Picasso;
import com.webservices.model.seriesEndpoints.BasicSeriesModel;

import java.util.List;

/**
 * Created by Darwin on 7/19/2017.
 */

public class BrowseAdapter extends
		RecyclerView.Adapter<BrowseAdapter.ViewHolder> {


	public class ViewHolder extends RecyclerView.ViewHolder {

		public ImageView ivCover;
		public TextView tvTitle;
		public TextView tvRating;

		public ViewHolder(View itemView) {
			super(itemView);

			ivCover = (ImageView) itemView.findViewById(R.id.ivCover);
			tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
			tvRating = (TextView) itemView.findViewById(R.id.tvRating);
		}
	}

	private List<BasicSeriesModel> seriesList;
	private Context mContext;

	public BrowseAdapter(Context context, List<BasicSeriesModel> series) {
		seriesList = series;
		mContext = context;
	}

	public Context getContext() {
		return mContext;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		Context context = parent.getContext();
		LayoutInflater inflater = LayoutInflater.from(context);
		View browseView = inflater.inflate(R.layout.generic_list_fragment_item, parent, false);
		ViewHolder viewHolder = new ViewHolder(browseView);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		BasicSeriesModel series = seriesList.get(position);

		TextView tvTitle = holder.tvTitle;
		tvTitle.setText(series.getTitleEnglish());
		TextView tvRating = holder.tvRating;
		tvRating.setText(Double.toString(series.getAverageScore()));
		ImageView ivCover = holder.ivCover;

		// uses Picasso to download cover art
		Picasso.with(getContext())
				.load(series.getImageUrlMed())
				.error(R.drawable.ic_placeholder_cover)
				.into(ivCover);
	}

	@Override
	public int getItemCount() {
		return seriesList.size();
	}

}
