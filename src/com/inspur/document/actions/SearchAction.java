package com.inspur.document.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.inspur.document.domin.Document;
import com.inspur.document.solr.SolrServerVisitor;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ����������Action������ʹ�ã�
 * 
 * @author Administrator
 * 
 */
public class SearchAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * �����ò�ѯ������ĵ�ǰ��ҳ�ڵ�8������
	 */
	private List<Document> docs = null;
	/**
	 * ����ǰ̨�������Ĳ�ѯ����ֵ������޸ı�������ǰ̨��input�����nameֵҲ������Ÿģ�
	 */
	private String content = null;
	/**
	 * ��¼����������ǰ̨��������ҳ�밴ť��totalRecs/8����
	 */
	private int totalRecs;
	/**
	 * ����һҳ��ʼ
	 */
	private int fromPage;
	/**
	 * ��׺����
	 */
	private String extension;

	/**
	 * ������
	 */
	@Override
	public String execute() throws Exception {
		// ����ǿմ���ʲô�����ò�
		if ("".equals(content.trim())) {
			docs = new ArrayList<Document>();
			totalRecs = 0;
		}
		// �����ǿմ�����ʼ����solr���в�ѯ
		List<Map<String, String[]>> list = SolrServerVisitor.query(content,
				extension);
		// �����¼����
		totalRecs = list.size();
		// Ȼ��ѷ����û�ѡ��ĵ�ǰҳ�ļ�¼�ŵ��µļ�����
		List<Map<String, String[]>> list2 = new ArrayList<Map<String, String[]>>();
		for (int i = (fromPage - 1) * 8; i < (fromPage - 1) * 8 + 8
				&& i < list.size(); ++i) {
			list2.add(list.get(i));
		}
		// Ȼ����¼����е���������ʽת������
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
	 * ������get��set����
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
