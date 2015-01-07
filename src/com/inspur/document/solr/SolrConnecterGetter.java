package com.inspur.document.solr;

import java.io.IOException;

public class SolrConnecterGetter {
	private String serverAddress = "";

	public SolrConnecterGetter() throws IOException {
		// Properties prop=new Properties();
		// System.out.println(System.getProperty("user.dir"));
		//
		// InputStream in=getClass().getResourceAsStream("serverIP.properties");
		// prop.load(in);
		// Set<?> keyValue=prop.keySet();
		// for(Iterator<?> it=keyValue.iterator();it.hasNext();){
		// String key=(String)it.next();
		// if(key.equals("ServerAddress")){
		// serverAddress=prop.get(key).toString();
		// }
		// }
		serverAddress = PropsGetter.getProp("document.solr.server.address");
	}

	public String getServerAddress() {
		return serverAddress;
	}
}
