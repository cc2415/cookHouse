package com.infzm.slidingmenu.demo.adapter.news;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragtPageAdapter extends FragmentPagerAdapter {
	public List<Fragment> fList;
	public FragtPageAdapter(FragmentManager fm,List<Fragment> fList) {
		super(fm);
		this.fList=fList;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return fList.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fList==null?0:fList.size();
	}

}
