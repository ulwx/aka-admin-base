package com.github.ulwx.aka.admin.utils;

import com.ulwx.tool.MD5;

public class CbConst {
	public static final String KEY = "@#FR%$$";
	
	public static String getPassword(String pass) {
		return MD5.MD5generator(pass + KEY);
	}

	public static String NoChangedPassword = "xyz123456@1234";


	public static void main(String[] args) {
		System.out.println(getPassword("123456"));
	}
	
	
}
