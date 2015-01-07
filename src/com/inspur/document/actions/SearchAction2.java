package com.inspur.document.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.inspur.document.domin.Document;
import com.opensymphony.xwork2.ActionSupport;

/**
 * û��solr����Դ��ʱ��������action������
 * 
 * @author Administrator
 * 
 */
public class SearchAction2 extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private List<Document> docs = null;
	private String content = null;

	private int totalRecs;
	private int fromPage;
	private String extension;

	@Override
	public String execute() throws Exception {
		List<Document> docsList = new ArrayList<Document>();
		String[] ext = new String[] { "doc", "ppt", "xls", "pdf", "txt" };
		String str = "�ո�����Ӣ�����Ľ�ϣ���ʼ��1603�꣬��ʱԭ�����ո�����ղķʿ������λ��ΪӢ������������Ӣ������ղķʿһ�������ҽ�ԭ�����������µ��ո���������Ӣ�����ϲ���Ϊ����������1707��5��1�գ����Ϸ���ͨ�����ո�����ʽ��Ӣ�����ϲ�Ϊһ�����ң���Ϊ���е����������ϲ�ǰԭ�����ո���������3��26��ʱ��ɢ����Ӣ�������ϲ�Ϊ��һ�ġ����е߹��ᡱ���ո��������Ĺ�����ȫ���ƽ���λ���׶������еĵ�һ������ִ�У��õص�Ȩ����͸�������ﲿ��ϯ�����ո����˵�����ʵ�У�ֻ����һЩ�����������ػ����ֿ�����Ȼ���������������ո������ڶ��ܽ��������İ��ŷ�ʽ��������˵���ո�������ڵĵ�һ���ո������嵳����������һֱ��ı���ո�����Ӣ������������Ϊ�õ��Ļ����������š�1998�꣬ʱ���������ᡤ�������쵼��Ӣ��������������1997��ʱͨ���Ĺ���ͶƱ���飬�������ո���������Scotland Act 1998����ȷ���ָ���ʧ�˽ӽ���������ո�����ᡣ�µ��ո�����Ὣ��ӵ�д󲿷ֵط����������Ȩ���ټ��Ͼֲ�˰�ʵ����ռ䣬�����ַѡ�����ո����׸���Ҳ�ǹ�ȥ�ո����������׶������������ڵ�ʥ³�£�Holyrood����ȫ�µ��ո�������¥���ɳ�����������̩¡���ǵ��ѹʽ���ʦ�����ˡ�����Ү˹��Enric Miralles������ƣ�����2004��9��ʱ��ʽ���ã���ǰ���������ڹ�ɫ����İ������ɳ�������һ��������Ҳ��˻�õ������ǰ��᲻һ�����ո����������󣬹������ո���һֱ�����������������ά��ִ����λ��ֱ��2007�꣬������һϯ֮�������������Ӣ���������ո������嵳�������ո������嵳δ��Ӯ�ö�����ֻ�ܳ���������������2011�����ѡ�٣��õ�ȡ�ô�ʤ���״γɹ�ȡ�����Ŀ���Ȩ���ո������嵳��ŵ���ж�����Ͷ��Ӣ�����࿨÷�����Ѿ��׿��ո���������Ͷ��2014���＾���С�2014��2��5�գ��ո���������ͨ��ͬ���������Ϸ�����Ϊȫ���17��ͨ��������Ĺ��һ������";
		int max = str.length();
		Random ran = new Random();
		int count = 0;
		while (count < 8) {
			System.out.println(extension);
			String exten = ext[ran.nextInt(5)];
			if ("all".equals(extension) || exten.equals(extension)) {
				Document doc = new Document();
				String title = "";
				String summary = "";
				while (true) {
					try {
						String temp = new String(str);
						temp = temp.replaceAll("[^\\u4e00-\\u9fa5]", "")
								.replaceAll(" ", "");
						int ranPos = ran.nextInt(max);
						title = temp.substring(ranPos, ranPos + 12);
						summary = str.substring(ranPos, ranPos + 80) + "...";
						break;
					} catch (Exception e) {
					}
				}
				doc.setTitle(title.replace(content, "<font color='red'>"
						+ content + "</font>"));
				doc.setUrl("#");
				doc.setContent("");
				doc.setExtension(exten);
				doc.setSummary(summary.replace(content,
						"<span style='color:red;font-weight:bold;'>" + content
								+ "</span>"));
				doc.setUploadedTime("2014-01-02");
				doc.setAuthor("Tom");
				List<String> list = new ArrayList<String>();
				list.add(title.substring(0, 2));
				list.add(title.substring(2, 4));
				list.add(title.substring(4, 8));
				doc.setKeywords(list);
				doc.setKeywordsInStr(list.toString());
				docsList.add(doc);
				count++;
			}

		}
		docs = new ArrayList<Document>();
		for (int i = (fromPage - 1) * 8; i < (fromPage - 1) * 8 + 8
				&& i < docsList.size(); ++i) {
			docs.add(docsList.get(i));
		}
		totalRecs = docsList.size();
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
