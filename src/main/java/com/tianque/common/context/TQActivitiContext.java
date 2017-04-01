package com.tianque.common.context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TQActivitiContext {
	private volatile static ThreadLocal context = new ThreadLocal();

	public static Object get(String key){
		Map map = (Map) context.get();
		if (map != null) {
			return map.get(key);
		}
		return null;
	}
	
	public static void set(String key,Object data){
		Map map = (Map) context.get();
		if (map == null) {
			map = new HashMap();
		}
		map.put(key, data);
		context.set(map);
	}
	
	public static void clean(){
		context.remove();
	}
	

}
