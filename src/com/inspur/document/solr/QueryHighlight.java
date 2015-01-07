package com.inspur.document.solr;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class QueryHighlight {
	public static void main(String[] args) throws IOException,
			SolrServerException {
		QueryHighlight qh = new QueryHighlight();
		qh.queryHighlight("国");
	}

	public void queryHighlight(String query) throws IOException,
			SolrServerException {
		SolrConnecterGetter connecter = new SolrConnecterGetter();
		String urlString = connecter.getServerAddress();
		HttpSolrServer solr = new HttpSolrServer(urlString);
		solr.setParser(new XMLResponseParser());
		solr.setConnectionTimeout(5000);
		solr.setSoTimeout(1000);
		solr.setMaxTotalConnections(100);
		SolrQuery qu = new SolrQuery();
		qu.setQuery(query);
		qu.setHighlight(true);
		qu.addHighlightField("content");
		qu.setHighlightSimplePre("<font color=\"red\">");// 渲染标签
		qu.setHighlightSimplePost("</font>");// 渲染标签
		QueryResponse qr = solr.query(qu);
		// List<Item> results=qr.getBeans(Item.class);
		// for(Item i:results){
		// System.out.println(i.content);
		// }
		SolrDocumentList dlist = qr.getResults();
		// 第一个Map的键是文档的ID，第二个Map的键是高亮显示的字段名
		Map<String, Map<String, List<String>>> map = qr.getHighlighting();
		for (int i = 0; i < dlist.size(); i++) {
			SolrDocument d = dlist.get(i);// 获取每一个document
			System.out.println(map.get(d.get("id")).get("content"));// 打印高亮的内容
		}
	}
}
