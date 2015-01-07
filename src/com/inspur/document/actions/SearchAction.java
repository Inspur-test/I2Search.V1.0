package com.inspur.document.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.inspur.document.domin.Document;
import com.inspur.document.solr.SolrServerVisitor;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 处理搜索的Action（正在使用）
 * 
 * @author Administrator
 * 
 */
public class SearchAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * 将放置查询结束后的当前分页内的8条数据
	 */
	private List<Document> docs = null;
	/**
	 * 接受前台传过来的查询串的值（如果修改变量名，前台的input组件的name值也必须跟着改）
	 */
	private String content = null;
	/**
	 * 记录总数，决定前台产生几个页码按钮（totalRecs/8个）
	 */
	private int totalRecs;
	/**
	 * 从哪一页开始
	 */
	private int fromPage;
	/**
	 * 后缀搜索
	 */
	private String extension;

	/**
	 * 处理方法
	 */
	@Override
	public String execute() throws Exception {
		// 如果是空串，什么都不用查
		if ("".equals(content.trim())) {
			docs = new ArrayList<Document>();
			totalRecs = 0;
		}
		// 否则不是空串，开始访问solr进行查询
		List<Map<String, String[]>> list = SolrServerVisitor.query(content,
				extension);
		// 计算记录总数
		totalRecs = list.size();
		// 然后把符合用户选择的当前页的记录放到新的集合中
		List<Map<String, String[]>> list2 = new ArrayList<Map<String, String[]>>();
		for (int i = (fromPage - 1) * 8; i < (fromPage - 1) * 8 + 8
				&& i < list.size(); ++i) {
			list2.add(list.get(i));
		}
		// 然后对新集合中的数据做格式转换处理
		docs = new ArrayList<Document>();
		for (Map<String, String[]> map : list2) {
			Document document = new Document();
			String title = map.get("id")[0].substring(map.get("id")[0]
					.lastIndexOf('/') + 1);
			document.setTitle(title.replace(content, "<font color='red'>"
					+ content + "</font>"));
			document.setUrl(map.get("id")[0]);
			document.setContent(map.get("content")[0]);
			document.setSummary(map.get("summary")[0]);
			document.setKeywords(Arrays.asList(map.get("keywords")));
			document.setKeywordsInStr(Arrays.asList(map.get("keywords"))
					.toString());
			document.setCategoryInStr(map.get("Category")[0]);
			document.setUploadedTime("2014-01-02");
			document.setAuthor("Tom");
			document.setExtension(title.substring(title.lastIndexOf('.') + 1));
			docs.add(document);
		}
		return super.execute();
	}

	/**
	 * 以下是get和set方法
	 * 
	 * @return
	 */
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

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public void setDocs(List<Document> docs) {
		this.docs = docs;
	}

}
