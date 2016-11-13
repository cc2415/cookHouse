package com.infzm.slidingmenu.demo.expandableBean;

public class Item {
	public String detail_classifyName;
	public String cookclass;
	public String description;
	public String id;
	public String keywords;
	public String name;
	public String title;
	/**
	 * 
	 * @param cookclass
	 * @param description
	 * @param id
	 * @param keywords
	 * @param name
	 * @param title
	 */
	public Item(String cookclass,String description,String id,String keywords,String name,String title){
		this.name=name;
		this.cookclass=cookclass;
		this.description=description;
		this.id=id;
		this.keywords=keywords;
		this.title=title;
		
	}
}
 