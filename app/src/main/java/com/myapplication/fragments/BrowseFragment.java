package com.myapplication.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapplication.R;
import com.myapplication.adapters.BrowseAdapter;
import com.webservices.model.seriesEndpoints.BasicSeriesModel;

import java.util.List;

/**
 * Created by Darwin on 7/19/2017.
 */

public class BrowseFragment extends Fragment {

	private RecyclerView rvBrowse;
	private BrowseAdapter browseAdapter;
	private List<BasicSeriesModel> seriesList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		seriesList = getSeriesList();
		// get series list here (depending on nav drawer selection?)
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.generic_list_fragment_layout, parent, false);
		rvBrowse = (RecyclerView) layout.findViewById(R.id.rvList);
		browseAdapter = new BrowseAdapter(getActivity(), seriesList);
		rvBrowse.setAdapter(browseAdapter);
		rvBrowse.setLayoutManager(new LinearLayoutManager(getActivity()));
		return layout;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

	}

	public List<BasicSeriesModel> getSeriesList() {
		return null;
	}
}
