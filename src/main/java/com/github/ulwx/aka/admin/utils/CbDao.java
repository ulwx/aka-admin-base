package com.github.ulwx.aka.admin.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CbDao {

	//public static String SysDbPoolname = CbAppConfig.getSysDbPoolname();
	// 使用md的方式对数据库增删改查
	private static String getMdMethodStr(Class daoClass, String method) {
		String prefix = daoClass.getName();
		return prefix + ".md:" + method;
	}

	public static String md(Class daoClass, String method) {
		return getMdMethodStr(daoClass, method);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {


		ExecutorService es = Executors.newFixedThreadPool(10);


	}

}
