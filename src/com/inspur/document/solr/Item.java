package com.inspur.document.solr;

import org.apache.solr.client.solrj.beans.Field;

public class Item {
	@Field
	String id;
	@Field("cat")
	String categories;
	@Field
	String content;
	@Field
	String Summary;
	@Field
	String keywords;
	@Field
	String title;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSummary() {
		return Summary;
	}

	public void setSummary(String summary) {
		Summary = summary;
	}

	public String getKeyWords() {
		return keywords;
	}

	public void setKeyWords(String keyWords) {
		keywords = keyWords;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
