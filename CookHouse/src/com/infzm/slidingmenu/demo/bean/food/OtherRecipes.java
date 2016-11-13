package com.infzm.slidingmenu.demo.bean.food;

import java.util.ArrayList;

public class OtherRecipes {
	public ArrayList<moreRecipes> tngou;
	public class moreRecipes{
		public String count;
		public String description;
		public String food;
		public String id;
		public String img;
		public String keywords;
		public String message;
		public String images;
		public String name;
		@Override
		public String toString() {
			return "moreRecipes [count=" + count + ", description="
					+ description + ", food=" + food + ", id=" + id
					+ ", keywords=" + keywords + "]";
		}
		
	}
	
}
