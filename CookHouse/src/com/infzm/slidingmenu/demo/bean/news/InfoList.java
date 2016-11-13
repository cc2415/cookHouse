package com.infzm.slidingmenu.demo.bean.news;

import java.util.ArrayList;

public class InfoList {
	public ArrayList<mInfolist> tngou;
	public class mInfolist{
		public String count;
		public String description;
		public String id;
		public String img;
		public String infoclass;
		public String keywords;
		public String time;
		public String title;
		@Override
		public String toString() {
			return "mInfolist [description=" + description + ", id=" + id
					+ ", img=" + img + ", infoclass=" + infoclass + ", title="
					+ title + "]";
		}
		
	}
}
