package com.inspur.document.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.inspur.document.domin.Document;
import com.inspur.document.solr.SolrServerVisitor;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 查询效率较低的action（暂未使用）
 * @author Administrator
 *
 */
public class SearchAction3 extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private List<Document> docs = null;
	private String content = null;

	private int totalRecs;
	private int fromPage;

	@Override
	public String execute() throws Exception {
		if ("".equals(content.trim())) {
			docs = new ArrayList<Document>();
			totalRecs = 0;
		}
		List<Map<String, String[]>> list = SolrServerVisitor.query(content,"");
		List<Document> docsList = new ArrayList<Document>();
		for (Map<String, String[]> map : list) {
			Document document = new Document();
			String title = map.get("id")[0].substring(map.get("id")[0]
					.lastIndexOf('/') + 1);
			document.setTitle(title);
			document.setUrl(map.get("id")[0]);
			document.setContent(map.get("content")[0]);
			document.setSummary(map.get("summary")[0]);
			document.setKeywords(Arrays.asList(map.get("keywords")));
			document.setUploadedTime("2014-01-02");
			document.setAuthor("Tom");
			document.setExtension(title.substring(title.lastIndexOf('.') + 1));
			docsList.add(document);
		}

		docs = new ArrayList<Document>();
		totalRecs = docsList.size();
		for (int i = (fromPage - 1) * 8; i < (fromPage - 1) * 8 + 8
				&& i < docsList.size(); ++i) {
			docs.add(docsList.get(i));
		}
		return super.execute();
	}

	public List<Document> getDocs() {
		return docs;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getTotalRecs() {
		return totalRecs;
	}

	public void setTotalRecs(int totalRecs) {
		this.totalRecs = totalRecs;
	}

	public int getFromPage() {
		return fromPage;
	}

	public void setFromPage(int fromPage) {
		this.fromPage = fromPage;
	}

}
