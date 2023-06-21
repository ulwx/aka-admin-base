package com.github.ulwx.aka.admin.domain.cus;

import java.io.Serializable;

public class CbEasyUICombobox implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String text;
	private boolean selected = false;
	private Object data;

	public Object getData() { 
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
