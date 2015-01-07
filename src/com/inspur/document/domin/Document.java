package com.inspur.document.domin;

import java.util.List;

public class Document {
	private String id;
	private String title;
	private String extension;
	private String content;
	private String summary;
	private String keywordsInStr;
	private String uploadedTime;
	private String author;
	private String url;
	private List<String> keywords;
	private String Category;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getUploadedTime() {
		return uploadedTime;
	}

	public void setUploadedTime(String uploadedTime) {
		this.uploadedTime = uploadedTime;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public String getKeywordsInStr() {
		return keywordsInStr;
	}

	public void setKeywordsInStr(String keywordsInStr) {
		this.keywordsInStr = keywordsInStr;
	}
	public void setCategoryInStr(String CategoryInStr){
		this.Category=CategoryInStr;
	}
	public String getCategory(){
		return Category;
	}
}
