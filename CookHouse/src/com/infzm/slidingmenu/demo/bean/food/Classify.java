package com.infzm.slidingmenu.demo.bean.food;

import java.util.ArrayList;
/**
 * 总分类
 * @author cc
 *
 */
public class Classify {
	public ArrayList<detail_tngou> tngou;
	public class detail_tngou{
		/**
		 * 分类级别
		 */
		public String cookclass;
		public 	String description;
		/**
		 * 分类的id
		 */
		public String id;
		public String keywords;
		/**
		 * 分类名称
		 */
		public String name;
		public String title;
		@Override
		public String toString() {
			return "detail_tngou [cookclass=" + cookclass + ", id=" + id
					+ ", name=" + name + "]";
		}
	}
}
