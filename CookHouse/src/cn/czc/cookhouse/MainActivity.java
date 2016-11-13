package cn.czc.cookhouse;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import cn.czc.cookhouse.groable.MyGloable;

import com.google.gson.Gson;
import com.infzm.slidingmenu.demo.R;
import com.infzm.slidingmenu.demo.adapter.food.ClassifyAdapter;
import com.infzm.slidingmenu.demo.fragment.ContentFragment;
import com.infzm.slidingmenu.demo.fragment.LeftFragment;
import com.infzm.slidingmenu.demo.fragment.MyFavoritesFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * @date 2014/11/14
 * @description 主界面
 */
public class MainActivity extends SlidingFragmentActivity implements
		OnClickListener {

	private ImageView topButton,ivHome;
	private Fragment mContent;
	public TextView topTextView;
	private static final String FRAGMENT_LEFT_MENU = "fragment_left_menu";
	private static final String FRAGMENT_CONTENT_MENU = "fragment_content_menu";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 无标题
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initSlidingMenu(savedInstanceState);

		// 获得数据

		topButton = (ImageView) findViewById(R.id.topButton);
		ivHome = (ImageView) findViewById(R.id.iv_home);
		topButton.setOnClickListener(this);
		ivHome.setOnClickListener(this);
		topTextView = (TextView) findViewById(R.id.topTv);
		switchConent(new MyFavoritesFragment(), "健康新闻");
	}

	/**
	 * 初始化侧边栏
	 */
	private void initSlidingMenu(Bundle savedInstanceState) {
		// 如果保存的状态不为空则得到之前保存的Fragment，否则实例化MyFragment
		if (savedInstanceState != null) {
			mContent = getSupportFragmentManager().getFragment(
					savedInstanceState, "mContent");
		}

		// if (mContent == null) {
		// mContent = new ContentFragment();
		// }

		// 设置左侧滑动菜单
		setBehindContentView(R.layout.menu_frame_left);
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.menu_frame, new LeftFragment(),
						FRAGMENT_LEFT_MENU).commit();
		// 实例化滑动菜单对象
		SlidingMenu sm = getSlidingMenu();
		// 设置可以左右滑动的菜单
		sm.setMode(SlidingMenu.LEFT);
		// 设置滑动阴影的宽度
		sm.setShadowWidthRes(R.dimen.shadow_width);
		// 设置滑动菜单阴影的图像资源
		sm.setShadowDrawable(null);
		// 设置滑动菜单视图的宽度
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		// 设置渐入渐出效果的值
		sm.setFadeDegree(0.35f);
		// 设置触摸屏幕的模式,这里设置为全屏
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		// 设置下方视图的在滚动时的缩放比例
		sm.setBehindScrollScale(0.0f);

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "mContent", mContent);
	}

	/**
	 * 切换Fragment
	 * 
	 * @param fragment
	 */
	public void switchConent(Fragment fragment, String title) {
		mContent = fragment;
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, fragment).commit();
		getSlidingMenu().showContent();
		topTextView.setText(title);
	}

	public LeftFragment getLeftMenu() {
		LeftFragment findFragmentByTag = (LeftFragment) getSupportFragmentManager()
				.findFragmentByTag(FRAGMENT_LEFT_MENU);
		return findFragmentByTag;
	}
	public ContentFragment getContentFragment() {
		ContentFragment findFragmentByTag = (ContentFragment) getSupportFragmentManager()
				.findFragmentByTag(FRAGMENT_CONTENT_MENU);
		return findFragmentByTag;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.topButton:
			toggle();
			break;
		case R.id.iv_home:
			switchConent(new MyFavoritesFragment(), "健康新闻");
			break;
		}
	}

}
