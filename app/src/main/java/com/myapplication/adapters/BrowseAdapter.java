package com.myapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.myapplication.R;

/**
 * Created by Darwin on 7/19/2017.
 */

public class BrowseAdapter extends
		RecyclerView.Adapter<BrowseAdapter.ViewHolder> {


	public class ViewHolder extends RecyclerView.ViewHolder {

		public ImageView ivCover;
		public TextView tvTitle;

		public ViewHolder(View itemView) {
			super(itemView);

			ivCover = (ImageView) itemView.findViewById(R.id.ivCover);
			tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
		}
	}

	//TODO finish these methods

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return null;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {

	}

	@Override
	public int getItemCount() {
		return 0;
	}

}
