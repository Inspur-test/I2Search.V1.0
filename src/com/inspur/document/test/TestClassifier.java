package com.inspur.document.test;

import java.util.Map;
import java.util.Set;

import org.apache.tika.exception.TikaException;

import com.inspur.mahout.MahoutDemo.WordsBag;
import com.inspur.mahout.MahoutDemo.Classifier;
public class TestClassifier {
	public static void main(String[] args) throws Exception, TikaException{
		String path="G:\\ฟฮผþ\\สตั้";
		Map<String,Map<String,String>> contents=WordsBag.getContent(path);
		Set<Map.Entry<String, Map<String,String>>> e=contents.entrySet();
		for(Map.Entry<String, Map<String,String>> c:e){
			Set<Map.Entry<String, String>> content=c.getValue().entrySet();
			for(Map.Entry<String, String> text:content){
				System.out.println(text.getKey());
				Classifier.GetClassifierResult(text.getValue());
			}
		}
	}
}
