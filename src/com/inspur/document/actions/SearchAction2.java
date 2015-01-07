package com.inspur.document.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.inspur.document.domin.Document;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 没有solr数据源的时候挂上这个action做测试
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
		String str = "苏格兰与英格兰的结合，起始于1603年，当时原本的苏格兰王詹姆士六世继位成为英格兰王，世称英格兰的詹姆士一世，并且将原本属于他名下的苏格兰王国与英格兰合并成为共主邦联。1707年5月1日，联合法案通过，苏格兰正式与英格兰合并为一个国家，成为大不列颠王国，而合并前原本的苏格兰国会于3月26日时解散，与英格兰议会合并为单一的“大不列颠国会”。苏格兰地区的管理工作全都移交到位于伦敦西敏市的单一国会来执行，该地的权益则透过国会里部分席次由苏格兰人担当来实行，只保留一些立法方面的相关机构分开处理。然而，并不是所有苏格兰民众都能接受这样的安排方式，举例来说，苏格兰议会内的第一大党苏格兰民族党长久以来就一直将谋求苏格兰自英国独立而出作为该党的基本政策主张。1998年，时任首相托尼·布莱尔领导的英国工党政府根据1997年时通过的公民投票决议，公布了苏格兰法案（Scotland Act 1998），确定恢复消失了接近三百年的苏格兰议会。新的苏格兰议会将会拥有大部分地方事务的治理权，再加上局部税率调整空间，议会新址选择在苏格兰首府、也是过去苏格兰王国的首都爱丁堡市区内的圣鲁德（Holyrood），全新的苏格兰议会大楼是由出身西班牙加泰隆尼亚的已故建筑师安立克·米拉耶斯（Enric Miralles）所设计，已于2004年9月时正式启用，其前卫的造型在古色古香的爱丁堡旧城区中是一大异数，也因此获得的评价是褒贬不一落差极大。苏格兰议会成立后，工党在苏格兰一直与自民党组成联合政府维持执政地位，直至2007年，工党以一席之差，败予主张脱离英国独立的苏格兰民族党。由于苏格兰民族党未能赢得多数，只能筹组少数派政府。2011年议会选举，该党取得大胜，首次成功取得议会的控制权。苏格兰民族党承诺举行独立公投，英国首相卡梅伦亦已经首肯苏格兰独立公投于2014年秋季举行。2014年2月5日，苏格兰国会表决通过同性恋婚姻合法化，为全球第17个通过此项法案的国家或地区。";
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
