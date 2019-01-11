package com.weige.tree.test;

import java.util.ArrayList;
import java.util.List;

public class FontTree {
	
	private boolean expand;
	
	private String id;
	
	private String title;
	
	private List<FontTree> children = new ArrayList<FontTree>();

	public boolean isExpand() {
		return expand;
	}

	public void setExpand(boolean expand) {
		this.expand = expand;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<FontTree> getChildren() {
		return children;
	}

	public void setChildren(List<FontTree> children) {
		this.children = children;
	}

	
	

}
