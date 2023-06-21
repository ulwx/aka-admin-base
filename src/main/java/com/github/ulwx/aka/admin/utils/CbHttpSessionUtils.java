package com.github.ulwx.aka.admin.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CbHttpSessionUtils {

	public static HttpSession getCopySession(HttpServletRequest request){
		HttpSession oldSession=request.getSession();
		Map<String,Object> temp=new ConcurrentHashMap<String,Object>();
		Enumeration e=oldSession.getAttributeNames();
		while(e!=null && e.hasMoreElements()){
			String name=(String)e.nextElement();
			Object value=oldSession.getAttribute(name);
			temp.put(name, value);
		}
		oldSession.invalidate();
		HttpSession newSession=request.getSession();
		for(Map.Entry<String, Object> stringObjectEntry:temp.entrySet()){
			newSession.setAttribute(stringObjectEntry.getKey(), stringObjectEntry.getValue());
		}
		return newSession;
		
		
	}
}
