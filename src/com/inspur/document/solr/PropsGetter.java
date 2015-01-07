package com.inspur.document.solr;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * ����·�������
 * 
 * @author Administrator
 * 
 */
public class PropsGetter {
	// Map<key,path>
	private static Map<String, String> paths = null;

	/**
	 * ָ��key���·��
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
	 * ��ʼ��·��
	 * 
	 * @return
	 */
	private static void init() {
		// ��������ļ�
		ResourceBundle resourceBundle = ResourceBundle.getBundle("props");
		// ʵ�������·����Map
		paths = new HashMap<String, String>();
		// һһ����
		Iterator<String> it = resourceBundle.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			paths.put(key, resourceBundle.getString(key));
		}
	}

}
