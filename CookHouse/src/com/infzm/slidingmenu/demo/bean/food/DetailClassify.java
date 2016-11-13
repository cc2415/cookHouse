package com.infzm.slidingmenu.demo.bean.food;

import java.util.ArrayList;
/**
 * 总分类下的小分类
 * @author cc
 *
 */
public class DetailClassify {
	public ArrayList<detail_tngou> tngou;
	public class detail_tngou{
		public String cookclass;
		public String description;
		public String id;
		public String keywords;
		public String name;
		public String title;
		@Override
		public String toString() {
			return "detail_tngou [cookclass=" + cookclass + ", id=" + id
					+ ", name=" + name + "]";
		}
		
	}
}
