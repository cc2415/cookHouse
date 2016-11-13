package com.infzm.slidingmenu.demo.fragment;

import java.util.ArrayList;

import cn.czc.cookhouse.DetailRecipesActivity;
import cn.czc.cookhouse.MainActivity;
import cn.czc.cookhouse.groable.MyGloable;

import com.google.gson.Gson;
import com.infzm.slidingmenu.demo.R;
import com.infzm.slidingmenu.demo.adapter.food.ExpandableListViewAdapter;
import com.infzm.slidingmenu.demo.adapter.food.RecipesAdapter;
import com.infzm.slidingmenu.demo.bean.food.DetailRecipes;
import com.infzm.slidingmenu.demo.bean.food.Recipes;
import com.infzm.slidingmenu.demo.bean.food.Recipes.detail_Recipes;
import com.infzm.slidingmenu.demo.dialog.CustomProgressBar;
import com.infzm.slidingmenu.demo.dialog.ShowMyDialog;
import com.infzm.slidingmenu.demo.expandableBean.Group;
import com.infzm.slidingmenu.demo.expandableBean.Item;
import com.infzm.slidingmenu.demo.view.MyGridView;
import com.infzm.slidingmenu.demo.view.MylistViewAddFoot;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.R.integer;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.provider.VoicemailContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author wuwenjie
 * @description 今日
 */
public class ContentFragment extends Fragment {
	private ExpandableListView expandableListView;
	private ExpandableListViewAdapter epadapter;
	private LeftFragment leftMenu;
	public ArrayList<Group> gData;
	public ArrayList<ArrayList<Item>> iData;// expandablelistview的数据
	private MylistViewAddFoot lv;
	private ArrayList<detail_Recipes> tngou;// lv的数据
	MyGridView mgGridView;
	int page = 0;
	boolean firse = true;
	int mPosition;
	int chilPo;//定义listview中的最后一个条目的位置
	// listview适配器
	private RecipesAdapter mRecipesAdapter;;
	/**
	 * 获取到的所有的数据的集合
	 */
	ArrayList<Recipes> sumReci = new ArrayList<Recipes>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frg_content, null);
		mgGridView = (MyGridView) inflater.inflate(
				R.layout.item_exanpandable_item, null).findViewById(
				R.id.gv_exanpandable);

		initView(view);
		getDataFromLeftMenu();

		Handler handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				switch (msg.what) {
				case 1:
					page=0;
					int position = Integer.parseInt(msg.obj + "");
					initRecipesData(iData.get(0).get(position).id);
					mPosition=Integer.parseInt(iData.get(0).get(position).id);
					expandableListView.collapseGroup(0);
					settopText(0, position);
					break;

				case 2:
					// expandableListView.collapseGroup(0);
					break;
				}
			};
		};

		epadapter = new ExpandableListViewAdapter(gData, iData, getActivity(),
				handler);
		expandableListView.setAdapter(epadapter);
		//第一次进入页面
		initRecipesData(iData.get(0).get(0).id);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(
						getActivity(),
						tngou.get(position).name + "  "
								+ tngou.get(position).id, 0).show();
				Intent intent = new Intent(getActivity(),
						DetailRecipesActivity.class);
				// Bundle extras=new Bundle();
				// extras.putSerializable("detail_recipes", tngou);
				// mGloable.mrecipesList=tngou;// 这个方法可以传递，但是会占用太多内存
				// intent.putExtras(extras);
				detail_Recipes mm = tngou.get(position);
				ArrayList<String> recipes = new ArrayList<String>();
				recipes.add(mm.count);
				recipes.add(mm.description);
				recipes.add(mm.food);
				recipes.add(mm.id);
				recipes.add(mm.img);
				recipes.add(mm.keywords);
				recipes.add(mm.name);
				intent.putStringArrayListExtra("detail_recipes", recipes);
				// startActivityForResult(intent, 1);
				startActivity(intent);

			}
		});

		lv.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(final AbsListView view, int scrollState) {
				if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
					if (view.getLastVisiblePosition() == view.getCount() - 1) {
						chilPo=view.getLastVisiblePosition();
						page+=1;
						String url = "http://www.tngou.net/api/cook/list?id="
								+ mPosition + "&row=20" + "&page=" + page;
						RequestParams params = new RequestParams();
						params.addHeader("apikey", MyGloable.apiKey);
						HttpUtils ht = new HttpUtils();
						ht.send(HttpMethod.GET, url, params,
								new RequestCallBack<String>() {

									@Override
									public void onSuccess(
											ResponseInfo<String> responseInfo) {
										Gson gson = new Gson();
										Recipes fj = gson.fromJson(
												responseInfo.result,
												Recipes.class);
										ArrayList<detail_Recipes> df=fj.tngou;
										if (df.size()==0) {
											System.out.println("空了");
//											System.out.println(view.getLastVisiblePosition());
////											LinearLayout vi = (LinearLayout) view.getChildAt(view.getLastVisiblePosition());
////											vi.setVisibility(View.GONE);
//											lv.removeViewAt(view.getLastVisiblePosition());
										}
										System.out.println(df);
//										detail_Recipes detail_Recipes = df.get(0);
										for (int i = 0; i < df.size(); i++) {
											tngou.add(df.get(i));
										}
										mRecipesAdapter.notifyDataSetChanged();
//										System.out.println("  dipage   "+page);
									}

									@Override
									public void onFailure(HttpException error,
											String msg) {

									}
								});
					}
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {

			}
		});

		return view;
	}

	/**
	 * 初始化控件
	 * 
	 * @param view
	 */
	private void initView(View view) {
		expandableListView = (ExpandableListView) view
				.findViewById(R.id.ep_classify);
		lv = (MylistViewAddFoot) view.findViewById(R.id.lv_);
//		TextView tv = new TextView(getActivity());
//		tv.setText("------到底了-----");
//		tv.setTextColor(Color.GRAY);
//		lv.addFooterView(tv);
		MainActivity man = (MainActivity) getActivity();
		leftMenu = man.getLeftMenu();

	}

	/**
	 * 设置顶部文字
	 * 
	 * @param groupPosition
	 * @param childPosition
	 */
	public void settopText(int groupPosition, int childPosition) {
		MainActivity man = (MainActivity) getActivity();
		man.topTextView.setText(MyGloable.topText + "->"
				+ iData.get(groupPosition).get(childPosition).name);
	}

	/**
	 * 从侧边栏获得数据
	 */
	private void getDataFromLeftMenu() {
		iData = leftMenu.iData;
		gData = leftMenu.gData;
	}

	/**
	 * 初始化食谱数据
	 */
	public void initRecipesData(String id) {
		page+=1;
		String url = "http://www.tngou.net/api/cook/list?id=" + id + "&row=20"
				+ "&page=1";
		HttpUtils http = new HttpUtils();
		RequestParams params = new RequestParams();
		params.addHeader("apikey", "30ea4f75e5c1de05adcafe9bb7da36d4");
		ShowMyDialog.ShowMyDialog(getActivity());
		// CustomProgressBar.show(getActivity());
		System.out.println("page  "+page);
		http.send(HttpMethod.GET, url, params, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				Gson gson = new Gson();
				Recipes fromJson = gson.fromJson(responseInfo.result,
						Recipes.class);
				// sumReci.add(fromJson);
				tngou = fromJson.tngou;
				// tngou = sumReci.get(sumReci.size()-1).tngou;
				mRecipesAdapter = new RecipesAdapter(tngou, getActivity());
				ShowMyDialog.closeDialog();
				// CustomProgressBar.Closeable(getActivity());
				lv.setAdapter(mRecipesAdapter);
				// expandableListView.collapseGroup(0);//关闭
				if (firse) {
					expandableListView.expandGroup(0);// 打开
					firse = false;
				}
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(getActivity(), "请求失败，请查看网络", 1).show();
			}
		});
	}

}
