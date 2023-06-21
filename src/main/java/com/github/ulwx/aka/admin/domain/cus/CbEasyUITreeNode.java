package com.github.ulwx.aka.admin.domain.cus;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CbEasyUITreeNode  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5863437889610133615L;
	private String id;
	private String text;
	private String state;// 'open' or 'closed', default is 'open'
	private Boolean checked;
	private String iconCls;
	private Map<String, String> attributes;
	private List<CbEasyUITreeNode> children;

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public List<CbEasyUITreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<CbEasyUITreeNode> children) {
		this.children = children;
	}

}
