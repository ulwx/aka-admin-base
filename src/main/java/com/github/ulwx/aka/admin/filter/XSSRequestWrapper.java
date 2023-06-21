package com.github.ulwx.aka.admin.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

public class XSSRequestWrapper extends HttpServletRequestWrapper {

private static Map<String,String> patterns = new HashMap<String,String>();
 static{

	 patterns.put("%", "％");
	 patterns.put("&", "＆");
	 patterns.put("\\", "＼");
	 patterns.put("<", "＜");
	 patterns.put(";", "；");
	 patterns.put("\"", "＂");
	 patterns.put("'", "＇");
    };
    
    public XSSRequestWrapper(HttpServletRequest servletRequest) {
    	 
        super(servletRequest);
    }
 
    
    @Override
	public Map<String, String[]> getParameterMap() {
		Map<String,String[]> map= super.getParameterMap();
		for(String key:map.keySet()){
		    String[] vals=map.get(key);
			for(int i=0; i<vals.length;i++){
				vals[i]=stripXSS(vals[i]);
			}
		}
		
		return map;
	}


	@Override
    public String[] getParameterValues(String parameter) {
    	
        String[] values = super.getParameterValues(parameter);
 
        if (values == null) {
            return null;
        }
 
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = stripXSS(values[i]);
        }
 
        return encodedValues;
    }
 
    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
 
        return stripXSS(value);
    }
 
    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return stripXSS(value);
    }
 
    private static String stripXSS(String value) {
    	
    	if(value==null) return null;
    	
    	//value=ESAPI.encoder().canonicalize(value,false,false);
    	
        // Avoid null characters
        //value = value.replaceAll("\0", "");

        // Remove all sections that match a pattern
        for (String key : patterns.keySet()){
            value = value.replace(key, patterns.get(key));
        }
        
        //value=Encode.forHtml(value);
        
        return value;
    }
    
    public static void main(String[] args) throws Exception{
    	
    	//ESAPI.override(new MyDefaultSecurityConfiguration());
    	
		//System.out.println(Encode.forHtml("中国<>:='\""));
    	String str=stripXSS("\\ % % %");
    	
    	System.out.println("ev%5\\66&3");
		//System.out.println("\u2334&amp;&amp&#x123&&amp;javascri%20pt:");
		//System.out.println(  ESAPI.encoder().en);
	

		//System.out.println(ESAPI.validator().getValidSafeHTML("","999<h>45555</h>", 30, true));
		//System.out.println(Encode.forJavaScript("{a:0123,b:'alert(1);'}"));
		//System.out.println(ObjectUtils.toJsonString("});alert(1);({s:"));
		
		
		System.out.println(str);
	}
    
    
}
