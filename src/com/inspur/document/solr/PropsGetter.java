package com.inspur.document.solr;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * 绝对路径获得器
 * 
 * @author Administrator
 * 
 */
public class PropsGetter {
	// Map<key,path>
	private static Map<String, String> paths = null;

	/**
	 * 指定key获得路径
	 * 
	 * @param key
	 * @return
	 */
	public static String getProp(String key) {
		if (null == paths) {
			init();
		}
		return paths.get(key);
	}

	/**
	 * 初始化路径
	 * 
	 * @return
	 */
	private static void init() {
		// 获得配置文件
		ResourceBundle resourceBundle = ResourceBundle.getBundle("props");
		// 实例化存放路径的Map
		paths = new HashMap<String, String>();
		// 一一放入
		Iterator<String> it = resourceBundle.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			paths.put(key, resourceBundle.getString(key));
		}
	}

}
