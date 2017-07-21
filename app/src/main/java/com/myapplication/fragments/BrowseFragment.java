package com.myapplication.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myapplication.R;
import com.myapplication.adapters.BrowseAdapter;
import com.webservices.model.ModelFactory;
import com.webservices.model.seriesEndpoints.BasicSeriesModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import static android.content.ContentValues.TAG;

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
		int id = this.getArguments().getInt("id");
		EventBus.getDefault().register(this);
		//requestSeriesList();
		// get series list here (depending on nav drawer selection)
		Log.d(TAG, Integer.toString(id));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.generic_list_fragment_layout, parent, false);
		//rvBrowse = (RecyclerView) layout.findViewById(R.id.rvList);
		//browseAdapter = new BrowseAdapter(getActivity(), seriesList);
		//rvBrowse.setAdapter(browseAdapter);
		//rvBrowse.setLayoutManager(new LinearLayoutManager(getActivity()));
		return layout;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

	}

	public void requestSeriesList() {
		ModelFactory.requestModelList(getContext(), BasicSeriesModel.class);
	}


	//TODO get this working
	@Subscribe
	public void onEvent(BasicSeriesModel[] seriesModel) {
		Log.d(TAG, "hi");
		// set series list here
	}
}
