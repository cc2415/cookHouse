package com.infzm.slidingmenu.demo.bean.food;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipes implements Serializable {
	public ArrayList<detail_Recipes> tngou;
	public class detail_Recipes{
		public String count; //访问次数
		public String description; //描述
		public String fcount; //收藏数
		public String food; 
		public String id; 
		public String img; //单个图片
		public String keywords; //关键字
		public String name;
		@Override
		public String toString() {
			return "detail_Recipes [count=" + count + ", description="
					+ description + ", food=" + food + ", id=" + id + ", img="
					+ img + ", name=" + name + "]";
		}
		
		
	}
}
