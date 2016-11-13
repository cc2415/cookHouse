package cn.czc.cookhouse.groable;

import java.util.ArrayList;

import com.infzm.slidingmenu.demo.bean.food.Recipes.detail_Recipes;


public class MyGloable {
	public static String topText=null;
	public static boolean firstShow = true;
	public static ArrayList<detail_Recipes> mrecipesList;
	
	public static String imge="http://tnfs.tngou.net/image";
	/**
	 * 获得总分类url，后面加？id=1可以获得大分类为1下的小分类
	 */
	public static String classifyUrl="http://www.tngou.net/api/cook/classify";
	/**
	 * 获得食谱列表
	 */
	public static String recipesUrl="http://www.tngou.net/api/cook/list?id=";
	/**
	 * 可以获得一道菜的多种做法列表
	 */
	public static String moreRecipesUrl="http://www.tngou.net/api/cook/name?name=";
//	public static String moreRecipesUrl="http://www.tngou.net/api/cook/name?name=%E5%AE%AB%E4%BF%9D%E9%B8%A1%E4%B8%81";
	
	/**
	 * 获得一道菜的详细做法  message
	 */
	public static String detailRecipesUrl="http://www.tngou.net/api/cook/show?id=";
	
	public static String apiKey="30ea4f75e5c1de05adcafe9bb7da36d4";
	/**
	 * 获得新闻的所有集合
	 */
	public static String newsListUrl="http://www.tngou.net/api/info/list?id=";
	
	public static String detailNewsUrl="http://www.tngou.net/info/show/";
	
}
