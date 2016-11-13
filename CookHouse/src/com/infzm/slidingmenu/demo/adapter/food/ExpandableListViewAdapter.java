package com.infzm.slidingmenu.demo.adapter.food;

import java.util.ArrayList;

import cn.czc.cookhouse.MainActivity;

import com.infzm.slidingmenu.demo.R;
import com.infzm.slidingmenu.demo.expandableBean.Group;
import com.infzm.slidingmenu.demo.expandableBean.Item;
import com.infzm.slidingmenu.demo.fragment.ContentFragment;
import com.infzm.slidingmenu.demo.view.MyGridView;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
	ArrayList<Group> gData;
	ArrayList<ArrayList<Item>> iData;
	Context mContext;
	ArrayList<Item> items;
	private adapter ad;
	public MyGridView gv;
	MainActivity main;
	ContentFragment contentFragment;
	Handler handler;
	public ExpandableListViewAdapter(ArrayList<Group> gData,ArrayList<ArrayList<Item>> iData,Context mContext,Handler handler){
		this.gData=gData;
		this.iData=iData;
		this.mContext=mContext;
		this.items = iData.get(0);
		this.handler=handler;
	}
	@Override
	public int getGroupCount() {
		return gData.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
//		return iData.get(groupPosition).size();
	}

	@Override
	public Group getGroup(int groupPosition) {
		return gData.get(groupPosition);
	}

	@Override
	public Item getChild(int groupPosition, int childPosition) {
		return iData.get(groupPosition).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		View view = View.inflate(mContext, R.layout.item_exanpandable_group, null);
		TextView tv=(TextView) view.findViewById(R.id.tv_Expan_classifyName);
		tv.setText(gData.get(groupPosition).classifyName);
		return view;
	}
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		View view = View.inflate(mContext, R.layout.item_exanpandable_item, null);
//		TextView tv=(TextView) view.findViewById(R.id.tv_detail_classifyName);
//		TextView tv2=(TextView) view.findViewById(R.id.tv_detail_classifyName2);
//		TextView tv3=(TextView) view.findViewById(R.id.tv_detail_classifyName3);
//		tv.setText(iData.get(groupPosition).get(childPosition).name);
		
		gv = (MyGridView) view.findViewById(R.id.gv_exanpandable);
		ad=new adapter(mContext, iData.get(groupPosition));
		gv.setAdapter(ad);
		ad.notifyDataSetChanged();
		gv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
//				Toast.makeText(mContext, position+" "+items.get(position).name, 0).show();
				Message msg=new Message();
				msg.what=1;
				msg.obj=position;
				handler.sendMessage(msg);
				Toast.makeText(mContext, items.get(position).id+"", 0).show();
//				handler.sendEmptyMessage(2);
			}
		});
		return view;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	
	class adapter extends BaseAdapter{
		Context mContext;
		ArrayList<Item> items;
		public adapter(Context mContext,ArrayList<Item> items){
			this.mContext=mContext;
			this.items=items;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return items.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(mContext, R.layout.item_grideview, null);
			TextView tv=(TextView) view.findViewById(R.id.tv_item_grideview);
			tv.setText(items.get(position).name);
			return view;
		}
	}
}
