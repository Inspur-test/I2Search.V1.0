package com.inspur.document.solr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;

/**
 * 
 *
 */
public class SolrServerVisitor {
	public static List<Map<String, String[]>> query(String querystring,
			String extension) throws SolrServerException, IOException {
		SolrConnecterGetter connecter = new SolrConnecterGetter();
		String urlString = connecter.getServerAddress();
		HttpSolrServer solr = new HttpSolrServer(urlString);
		solr.setParser(new XMLResponseParser());
		SolrQuery query = new SolrQuery();
		//2014.9.23修改（加上了以下紧跟着的两行）
		query.setStart(0);
		query.setRows(Integer.MAX_VALUE);
		query.setQuery(querystring);
		query.addSort("id", SolrQuery.ORDER.asc);
		// query.setHighlight(true);
		// query.addHighlightField("content");
		// query.setHighlightSimplePre("<font color=\"red\">");// 渲染标签
		// query.setHighlightSimplePost("</font>");// 渲染标签
		QueryResponse rsp = solr.query(query);
		List<Item> beans = rsp.getBeans(Item.class);
		// List<Map<字段值,字段名>>存放一个个查出来的文档
		List<Map<String, String[]>> result = new ArrayList<Map<String, String[]>>();
		for (Item i : beans) {
			String content = i.content;
			// System.out.println(content);
			// content = content.substring(content.indexOf(':', 4));
			// 提取摘要
//			SummaryComputer summaryComputer = new SummaryComputer(100, "",
//					content);
//			Summary summary = summaryComputer.toSummary();
//			String summaryStr = summary.getSummary().replaceAll(
//					querystring,
//					"<span style='color:red;font-weight:bold;'>" + querystring
//							+ "</span>");
			String summaryStr=i.Summary.replaceAll(querystring, "<span style='color:red;font-weight:bold;'>" + querystring	+ "</span>");
			// 提取关键词
//			List<Keyword> keywords = summary.getKeyWords();
//			String[] keywordsArr = new String[keywords.size()];
//			for (int j = 0; j < keywords.size(); ++j) {
//				String kw = keywords.get(j).toString();
//				keywordsArr[j] = kw.substring(0, kw.indexOf('/'));
//			}
			String[] keywordsArr=new String[1];
			String keywords=i.keywords.substring(1, i.keywords.indexOf(']'));
			keywords=keywords.replaceAll("　　,", "");
			keywordsArr[0]=keywords;
			//分类
			String Category= i.categories;
			// 提取各字段放到Map组织一个Doc的各种字段
			Map<String, String[]> doc = new HashMap<String, String[]>();

			if ("all".equals(extension)
					|| i.id.substring(i.id.lastIndexOf('.') + 1).equals(
							extension)
					|| i.id.substring(i.id.lastIndexOf('.') + 1).substring(0,3)
							.equals(extension)) {
				doc.put("id", new String[] { i.id });
				// doc.put("categories", i.categories);
				// doc.put("features",
				// i.features.toArray(new String[i.features.size()]));
				doc.put("content", new String[] { content });
				doc.put("summary", new String[] { summaryStr });
				doc.put("keywords", keywordsArr);
				doc.put("Category", new String[] { Category });
				// 当前Doc放到总list中
				result.add(doc);
			}

		}
		return result;
	}
}
