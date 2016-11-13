package com.infzm.slidingmenu.demo.adapter.news;

import java.util.ArrayList;

import cn.czc.cookhouse.DetailNews;
import cn.czc.cookhouse.groable.MyGloable;

import com.google.gson.Gson;
import com.infzm.slidingmenu.demo.R;
import com.infzm.slidingmenu.demo.bean.news.InfoList;
import com.infzm.slidingmenu.demo.bean.news.InfoList.mInfolist;
import com.infzm.slidingmenu.demo.bean.news.InfonClassify.NewsClassify;
import com.infzm.slidingmenu.demo.dialog.ShowMyDialog;
import com.infzm.slidingmenu.demo.view.ChiViewpager;
import com.infzm.slidingmenu.demo.view.HorizontalViewPager;
import com.infzm.slidingmenu.demo.view.MylistViewAddFoot;
import com.infzm.slidingmenu.demo.view.mScrollview;
import com.infzm.slidingmenu.demo.view.mScrollview.ScrollViewListener;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class NewsAdapter extends PagerAdapter{
	String mPosition;
	// private ArrayList<mInfolist> tngouList;//新闻集合
	int page = 1;
	ArrayList<NewsClassify> tngou;// 新闻总分类.用来获得分类
	// private mNewsListAdapter mNewsListAdapter;//新闻列表
	Context mContext;

	public NewsAdapter(ArrayList<NewsClassify> tngou, Context mContext) {
		this.tngou = tngou;
		this.mContext = mContext;
		ShowMyDialog.ShowMyDialog(mContext);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return tngou.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		final NewsListAdapter mNewsListAdapter = null;
		/**
		 * 新闻集合
		 */
		final ArrayList<mInfolist> tngouList = new ArrayList<InfoList.mInfolist>();

		View view = View.inflate(mContext, R.layout.item_news, null);
		final MylistViewAddFoot lv = (MylistViewAddFoot) view.findViewById(R.id.lv_item_news);
		ChiViewpager vp=(ChiViewpager) view.findViewById(R.id.vp_item_news);
		TextView tv=(TextView) view.findViewById(R.id.tv_item_news_addmore);
		initNewsList(tngou.get(position).id, page, lv, mNewsListAdapter,
				tngouList,vp);
//		vp.setAdapter(new ViewpageAdapter(mContext,tngouList));
		
		tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addMoreNews(tngou.get(position).id, page,
						mNewsListAdapter, tngouList,lv);
			}
		});
		
		//滚动监听
		lv.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
//				System.out.println("  scrolly:"+scrollState);
				System.out.println("chadf");
				
				if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
					if (view.getLastVisiblePosition() == view.getCount() - 1) {
						Toast.makeText(mContext, "底部",
								0).show();
						addMoreNews(tngou.get(position).id, page,
								mNewsListAdapter, tngouList,lv);
					}
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub

			}
		});
		
		
		//点击监听
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
//				Toast.makeText(mContext, tngouList.get(position).id,0).show();
				
				final String url = MyGloable.detailNewsUrl+tngouList.get(position).id;
				RequestParams params = new RequestParams();
				params.addHeader("apikey", MyGloable.apiKey);
				HttpUtils http = new HttpUtils();
				http.send(HttpMethod.GET, url, params, new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
//						Gson gson = new Gson();
						Intent intent=new Intent(mContext, DetailNews.class);
						intent.putExtra("url", url);
						Toast.makeText(mContext, url, 0).show();
						mContext.startActivity(intent);
					}

					@Override
					public void onFailure(HttpException error, String msg) {

					}

				});
			}
		});
		
		
		
		container.addView(view);
		return view;
	}

	public void initNewsList(String id, int page, final MylistViewAddFoot lvs,
			final NewsListAdapter adapter, final ArrayList<mInfolist> tngouList,final ChiViewpager vp) {
		String url = MyGloable.newsListUrl + id + "&row=20" + "&page=" + page;
		// this.page+=1;
		RequestParams params = new RequestParams();
		params.addHeader("apikey", MyGloable.apiKey);
		HttpUtils http = new HttpUtils();
		http.send(HttpMethod.GET, url, params, new RequestCallBack<String>() {
			NewsListAdapter we = adapter;

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				Gson gson = new Gson();
				InfoList fromJson = gson.fromJson(responseInfo.result,
						InfoList.class);
				final ArrayList<mInfolist> tngou2 = fromJson.tngou;
				for (int i = 5; i < tngou2.size(); i++) {
					tngouList.add(tngou2.get(i));
				}
				if (we==null) {
					System.out.println("  you l ");
					we = new NewsListAdapter(mContext, tngouList);
				}else{
					
				}
				ShowMyDialog.closeDialog();
				lvs.setAdapter(we);
				
				vp.setAdapter(new ViewpageAdapter(mContext,tngou2));
					
			}

			@Override
			public void onFailure(HttpException error, String msg) {

			}

		});
		// return mNewsListAdapter;
	}

	/**
	 * 加载跟多数据
	 * 
	 * @param id
	 * @param page
	 *            页数
	 */
	public void addMoreNews(String id, int page,
			final NewsListAdapter adapterT,
			final ArrayList<mInfolist> tngouList,
			final MylistViewAddFoot mylistView) {
		ShowMyDialog.ShowMyDialog(mContext);
		page += 1;
		this.page = page;
		String url = MyGloable.newsListUrl + id + "&row=20" + "&page=" + page;
		RequestParams params = new RequestParams();
		params.addHeader("apikey", MyGloable.apiKey);
		HttpUtils http = new HttpUtils();
		http.send(HttpMethod.GET, url, params, new RequestCallBack<String>() {
			NewsListAdapter adapter = adapterT;

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				Gson gson = new Gson();
				InfoList fromJson = gson.fromJson(responseInfo.result,
						InfoList.class);
				ArrayList<mInfolist> list = fromJson.tngou;
				System.out.println(tngouList.size()+"  1");
				for (int i = 0; i < list.size(); i++) {
					tngouList.add(list.get(i));
				}
				// System.out.println(list);
				if (adapter == null) {
					System.out.println("mNewsListAdapter  == null");
					// mNewsListAdapter=new mNewsListAdapter
					adapter=new NewsListAdapter(mContext, tngouList);
					mylistView.setAdapter(adapter);
				} else {
					adapter.notifyDataSetChanged();
				}
				System.out.println(tngouList.size()+"");
				ShowMyDialog.closeDialog();
			}

			@Override
			public void onFailure(HttpException error, String msg) {

			}

		});
	}




}
