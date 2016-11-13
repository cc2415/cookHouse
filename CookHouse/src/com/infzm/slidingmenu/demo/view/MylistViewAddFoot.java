package com.infzm.slidingmenu.demo.view;

import com.infzm.slidingmenu.demo.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MylistViewAddFoot extends ListView {

	public MylistViewAddFoot(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
//		init();
	}

	public MylistViewAddFoot(Context context, AttributeSet attrs) {
		super(context, attrs);
//		init();
	}

	public MylistViewAddFoot(Context context) {
		super(context);
//		init();
	}

	private void init() {
		final View view=View.inflate(getContext(), R.layout.item_listview_foot, null);
		addFooterView(view);
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getContext(), "数据加载完毕", 0).show();
				view.setVisibility(View.GONE);
			}
		});
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
		MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
		}
	
	
//	@Override
//	public boolean dispatchTouchEvent(MotionEvent ev) {
//		// TODO Auto-generated method stub
//			getParent().requestDisallowInterceptTouchEvent(true);
//		return super.dispatchTouchEvent(ev);
//	}
}
