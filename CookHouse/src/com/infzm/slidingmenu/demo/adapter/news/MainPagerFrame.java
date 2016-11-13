package com.infzm.slidingmenu.demo.adapter.news;

import com.infzm.slidingmenu.demo.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainPagerFrame extends Fragment {
	public View mRootView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = View.inflate(getActivity(), R.layout.item_news_pager, null);
		mRootView=view;
		return view;
	}
}
