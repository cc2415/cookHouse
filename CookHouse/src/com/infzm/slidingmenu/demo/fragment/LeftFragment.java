package com.infzm.slidingmenu.demo.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import cn.czc.cookhouse.MainActivity;
import cn.czc.cookhouse.groable.MyGloable;

import com.google.gson.Gson;
import com.infzm.slidingmenu.demo.R;
import com.infzm.slidingmenu.demo.adapter.food.ClassifyAdapter;
import com.infzm.slidingmenu.demo.adapter.food.ExpandableListViewAdapter;
import com.infzm.slidingmenu.demo.bean.food.Classify;
import com.infzm.slidingmenu.demo.bean.food.DetailClassify;
import com.infzm.slidingmenu.demo.bean.food.Classify.detail_tngou;
import com.infzm.slidingmenu.demo.expandableBean.Group;
import com.infzm.slidingmenu.demo.expandableBean.Item;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * @date 2014/11/14
 * @author wuwenjie
 * @description 侧边栏菜单
 */
public class LeftFragment extends Fragment {
	private ListView classifyListView;
	public ExpandableListViewAdapter epAdapter;
	public ArrayList<Group> gData;
	public ArrayList<ArrayList<Item>> iData;
	/**
	 * 总分类
	 */
	private ArrayList<detail_tngou> classify_tngousList;
	/**
	 * 总分类下的小分类
	 */
	private ArrayList<com.infzm.slidingmenu.demo.bean.food.DetailClassify.detail_tngou> detail_classify;
	private HttpUtils http;
	private ExpandableListView expandableListView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.layout_menu, null);
		findViews(view);
		initData();
		classifyListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				detail_tngou detail_tngou = classify_tngousList.get(position);
				initDetailClassify(detail_tngou);
				MyGloable.topText=detail_tngou.name;
			}
		});
		return view;
	}

	/**
	 * 初始总分类化数据
	 */
	private void initData() {
//		String url = "http://www.tngou.net/api/cook/classify";
		http = new HttpUtils();
		RequestParams params = new RequestParams();
		params.addHeader("apikey", "30ea4f75e5c1de05adcafe9bb7da36d4");

		http.send(HttpMethod.GET, MyGloable.classifyUrl, params, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				Gson gson = new Gson();
				Classify fromJson = gson.fromJson(responseInfo.result,
						Classify.class);
				classify_tngousList = fromJson.tngou;
//				System.out.println(classify_tngousList);
				classifyListView.setAdapter(new ClassifyAdapter(
						classify_tngousList, getActivity()));
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(getActivity(), "网络连接异常", 1).show();
			}
		});

	}

	/**
	 * 初始化总分类下的小分类
	 */
	private void initDetailClassify(final detail_tngou detail_tngou) {
		// 获得某个分类下的小分类url
		String url = MyGloable.classifyUrl + "?id="
				+ detail_tngou.id;
		http = new HttpUtils();
		RequestParams params = new RequestParams();
		params.addHeader("apikey", "30ea4f75e5c1de05adcafe9bb7da36d4");
		http.send(HttpMethod.GET, url, params, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				Gson gson = new Gson();
				DetailClassify fromJson = gson.fromJson(responseInfo.result,
						DetailClassify.class);
				detail_classify = fromJson.tngou;
//				System.out.println(fromJson.tngou);
				
				
				gData = new ArrayList<Group>();
				iData = new ArrayList<ArrayList<Item>>();
				ArrayList<Item> IData = new ArrayList<Item>();

				gData.add(new Group("详细情况"));

				for (int i = 0; i < detail_classify.size(); i++) {
					IData.add(new Item(detail_classify.get(i).cookclass,
							detail_classify.get(i).description, detail_classify
									.get(i).id,
							detail_classify.get(i).keywords, detail_classify
									.get(i).name, detail_classify.get(i).title));
				}
				iData.add(IData);
//				epAdapter = new mExpandableListViewAdapter(gData, iData,
//						getActivity());
				switchFragment(new ContentFragment(), detail_tngou.name);
			}

			@Override
			public void onFailure(HttpException error, String msg) {

			}
		});

	}

	/**
	 * layout_menu中的控件
	 * 
	 * @param view
	 */
	public void findViews(View view) {
		classifyListView = (ListView) view.findViewById(R.id.lv_classify);
		expandableListView = (ExpandableListView) view
				.findViewById(R.id.ep_classify);

	}

	// @Override
	// public void onDestroyView() {
	// super.onDestroyView();
	// }
	//
	// @Override
	// public void onDestroy() {
	// super.onDestroy();
	// }

	// @Override
	// public void onClick(View v) {
	// Fragment newContent = null;
	// String title = null;
	// switch (v.getId()) {
	// case R.id.tvToday: // 今日
	// newContent = new TodayFragment();
	// title = getString(R.string.today);
	// break;
	// case R.id.tvLastlist:// 往期列表
	// newContent = new LastListFragment();
	// title = getString(R.string.lastList);
	// break;
	// case R.id.tvDiscussMeeting: // 讨论集会
	// newContent = new DiscussFragment();
	// title = getString(R.string.discussMeetting);
	// break;
	// case R.id.tvMyFavorites: // 我的收藏
	// newContent = new MyFavoritesFragment();
	// title = getString(R.string.myFavorities);
	// break;
	// case R.id.tvMyComments: // 我的评论
	// newContent = new MyCommentsFragment();
	// title = getString(R.string.myComments);
	// break;
	// case R.id.tvMySettings: // 设置
	// newContent = new MySettingsFragment();
	// title = getString(R.string.settings);
	// break;
	// default:
	// break;
	// }
	// if (newContent != null) {
	// switchFragment(newContent, title);
	// }
	// }

	/**
	 * 切换fragment
	 * 
	 * @param fragment
	 */
	private void switchFragment(Fragment fragment, String title) {
		if (getActivity() == null) {
			return;
		}
		if (getActivity() instanceof MainActivity) {
			MainActivity fca = (MainActivity) getActivity();
			fca.switchConent(fragment, title);
		}
	}

}
