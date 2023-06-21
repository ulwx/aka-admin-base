package com.github.ulwx.aka.admin.domain.cus;

import java.io.Serializable;
import java.util.Map;

public class CbEasyUIComboboxTree implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1052441336215379307L;
	private String id;
	private String text;
	private CbEasyUIComboboxTree[] children ;
	private Boolean checked ;
	private String iconCls="" ;
	private String state ;// "closed"、"open"

	private Map<String, String> attributes;



	public CbEasyUIComboboxTree() {
		
	}
	public CbEasyUIComboboxTree(String id,String text) {
		this.id=id;
		this.text=text;
	}
	public CbEasyUIComboboxTree(String id,String text,Boolean checked,String iconCls,String state) {
		this.id=id;
		this.text=text;
		this.checked=checked;
		this.iconCls=iconCls;
		this.state=state;
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
	public CbEasyUIComboboxTree[] getChildren() {
		return children;
	}
	public void setChildren(CbEasyUIComboboxTree[] children) {
		this.children = children;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Map<String, String> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	
	
	
}
