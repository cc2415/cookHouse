package cn.czc.cookhouse;

import java.util.ArrayList;

import cn.czc.cookhouse.groable.MyGloable;

import com.google.gson.Gson;
import com.infzm.slidingmenu.demo.R;
import com.infzm.slidingmenu.demo.R.id;
import com.infzm.slidingmenu.demo.adapter.food.HorizaonlaAdapter;
import com.infzm.slidingmenu.demo.adapter.food.ViewPagerAdapter;
import com.infzm.slidingmenu.demo.bean.food.DetailRecipes;
import com.infzm.slidingmenu.demo.bean.food.OtherRecipes;
import com.infzm.slidingmenu.demo.bean.food.OtherRecipes.moreRecipes;
import com.infzm.slidingmenu.demo.bean.food.Recipes.detail_Recipes;
import com.infzm.slidingmenu.demo.view.HorizontalListView;
import com.infzm.slidingmenu.demo.view.mScrollview;
import com.infzm.slidingmenu.demo.view.mScrollview.ScrollViewListener;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 菜谱详情
 * 
 * @author cc
 * 
 */
public class DetailRecipesActivity extends Activity implements ScrollViewListener,OnClickListener {
	private ArrayList<detail_Recipes> mRecipesList;
	private ImageView iv;
	// private ViewPager vp;
	Context mContext;
	TextView tv_name;
	private TextView tv_decription;
	private TextView tv_food;
	private TextView tv_message;
	private GridView gv;
	private HorizontalListView horListView;
	private int width;
	private mScrollview sv;
	private TextView ll_top;//背景
	private ImageView iv_classify;
	private ImageView iv_notifi;
	private ArrayList<moreRecipes> tngou;
	private mScrollview scrollview;
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				ArrayList<String> ml = (ArrayList<String>) msg.obj;
				tv_name.setText(ml.get(6));
				BitmapUtils bitmapUtils = new BitmapUtils(mContext);
				bitmapUtils.display(iv, MyGloable.imge + ml.get(4));

				initNetData(ml);
				scrollview.scrollTo(0, 0);
				break;

			default:
				break;
			}
			
		};
		
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipes);
		//获得屏幕的宽
		WindowManager wm = (WindowManager) this
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		width = metric.widthPixels;
		
		System.out.println(width+"");
		initView();
		initData();
		horListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(mContext, position + "", 0).show();
				moreRecipes mr = tngou.get(position);
				// 0,count 1,description
				// 2,food 3,id
				// 4,img 5,keywords 6,name
				ArrayList<String> li=new ArrayList<String>();
				li.add(mr.count);
				li.add(mr.description);
				li.add(mr.food);
				li.add(mr.id);
				li.add(mr.img);
				li.add(mr.keywords);
				li.add(mr.name);
//				intent.putStringArrayListExtra("ok", li);
//				startActivity(intent);
				Message msg=new Message();
				msg.what=1;
				msg.obj=li;
				handler.sendMessage(msg);
			}
		});
		
//		sv.setscro
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		Intent intent = getIntent();
		// 0,count 1,description
		// 2,food 3,id
		// 4,img 5,keywords 6,name
		ArrayList<String> ml = intent.getStringArrayListExtra("detail_recipes");
		tv_name.setText(ml.get(6));

		BitmapUtils bitmapUtils = new BitmapUtils(mContext);
		bitmapUtils.display(iv, MyGloable.imge + ml.get(4));

		initNetData(ml);
	}

	/**
	 * 初始化网络数据
	 */
	private void initNetData(ArrayList<String> ml) {
		String url = MyGloable.detailRecipesUrl + ml.get(3);
		HttpUtils http = new HttpUtils();
		RequestParams params = new RequestParams();
		params.addHeader("apikey", MyGloable.apiKey);
		// 获得菜式的详情做法
		http.send(HttpMethod.GET, url, params, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				Gson gson = new Gson();
				DetailRecipes fromJson = gson.fromJson(responseInfo.result,
						DetailRecipes.class);
				tv_decription.setText(fromJson.description);
				tv_food.setText(fromJson.food);
				tv_message.setText(Html.fromHtml(fromJson.message));
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub

			}
		});

		// 获得该菜式的其他做法列表
		String listUrl = MyGloable.moreRecipesUrl + ml.get(6);
		HttpUtils oHttpUtils = new HttpUtils();

		oHttpUtils.send(HttpMethod.GET, listUrl, params,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						Gson gson = new Gson();
						OtherRecipes fromJson = gson.fromJson(
								responseInfo.result, OtherRecipes.class);
						// System.out.println(fromJson.tngou);
						horListView.setAdapter(new HorizaonlaAdapter(mContext,
								fromJson.tngou));
						tngou = fromJson.tngou;
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						Toast.makeText(mContext, "连接失败", 1).show();
					}
				});
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		mContext = this;
		iv = (ImageView) findViewById(R.id.vp_img);
		tv_name = (TextView) findViewById(R.id.tv_detail_name);
		tv_decription = (TextView) findViewById(R.id.tv_recipes_decription);
		tv_food = (TextView) findViewById(R.id.tv_recipes_food);
		tv_message = (TextView) findViewById(R.id.tv_recipes_doit);
		horListView = (HorizontalListView) findViewById(R.id.lv_horizaonla);
		sv = (mScrollview) findViewById(R.id.sv_detailRecipes);
		ll_top = (TextView) findViewById(R.id.ll_top);
		iv_classify = (ImageView) findViewById(R.id.iv_detail_recipes_classify);
		iv_notifi = (ImageView) findViewById(R.id.iv_detail_recipes_notifiction);
		scrollview = (mScrollview) findViewById(R.id.sv_detailRecipes);
		
		iv_notifi.setOnClickListener(this);
		sv.setScrollViewListener(this);
	}

	@SuppressLint("NewApi") @Override
	public void onScrollChanged(mScrollview scrollView, int x, int y, int oldx,
			int oldy) {
		if(y<302&&y>51){
			float alpha=(float) (Float.valueOf(y)/302.0);
			ll_top.setAlpha(alpha);
//			ll_top.setbac
		}
		if(y>270){
			iv_classify.setImageResource(R.drawable.down_classify);
			iv_notifi.setImageResource(R.drawable.home2);
		}else{
			iv_classify.setImageResource(R.drawable.classify);
			iv_notifi.setImageResource(R.drawable.home);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_detail_recipes_notifiction:
			Intent intent =new Intent(mContext, MainActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}
	}
	
}
