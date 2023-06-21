package com.github.ulwx.aka.admin.domain.cus;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CbEasyUIGridModel<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8565743769423276515L;
	private Integer total;
	private List<T> rows;
	private List<Map<String, Object>> footer;

	public List<Map<String, Object>> getFooter() {
		return footer;
	}

	public void setFooter(List<Map<String, Object>> footer) {
		this.footer = footer;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}


}
