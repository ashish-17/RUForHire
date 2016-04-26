/**
 * 
 */
package com.ruforhire.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ruforhire.utils.Stemmer;
import com.ruforhire.utils.Stopwords;

/**
 * @author ashish
 *
 */
public class JobDescriptionVector {

	private static final int MAX_IMPORTANT_WORDS = 10;
	
	private int[] vector;
	private Stopwords stopWords = new Stopwords();
	
	public JobDescriptionVector(int size) {
		vector = new int[size];
	}

	public JobDescriptionVector(String content, List<String> data) {
		vector = new int[data.size()];
		int index = 0;
		List<String> words = Arrays.asList(content.toLowerCase().split("\\s+"));
		List<String> wordsToBeMatched = new ArrayList<>();
		for (String word : words) {
			word = word.trim().toLowerCase();
			if (!stopWords.is(word) && StringUtils.isAlphanumeric(word)) {
				Stemmer stemmer = new Stemmer();
				stemmer.add(word.toCharArray(), word.length());
				stemmer.stem();
				
				wordsToBeMatched.add(stemmer.toString());
			}
		}
		

		for (String str : data) {
			if (wordsToBeMatched.contains(str)) {
				vector[index]++;
				System.out.println("Matched " + str + " - " + vector[index]);
			}

			index++;
		}
	}
	
	public JobDescriptionVector(JobDescription jd, List<String> data) {
		vector = new int[data.size()];
		int index = 0;
		List<String> title = Arrays.asList(Jsoup.parse(jd.getJobTitle()).text().split("\\s+"));
		List<String> snippet = Arrays.asList(Jsoup.parse(jd.getSnippet()).text().split(" "));
		
		List<String> wordsToBeMatched = new ArrayList<>();
		for (String word : title) {
			word = word.trim().toLowerCase();
			if (!stopWords.is(word) && StringUtils.isAlphanumeric(word)) {
				Stemmer stemmer = new Stemmer();
				stemmer.add(word.toCharArray(), word.length());
				stemmer.stem();
				
				wordsToBeMatched.add(stemmer.toString());
			}
		}
		for (String word : snippet) {
			word = word.trim().toLowerCase();
			if (!stopWords.is(word) && StringUtils.isAlphanumeric(word)) {
				Stemmer stemmer = new Stemmer();
				stemmer.add(word.toCharArray(), word.length());
				stemmer.stem();
				
				wordsToBeMatched.add(stemmer.toString());
			}
		}

		for (String str : data) {
			if (wordsToBeMatched.contains(str)) {
				vector[index]++;
			}

			index++;
		}
	}
	
	public int[] getVector() {
		return this.vector;
	}

	public Double cosineSimilarity(JobDescriptionVector v) {
		double dotProduct = 0.0;
        double magnitude1 = 0.0;
        double magnitude2 = 0.0;
        double cosineSimilarity = 0.0;
		for (int i = 0; i < vector.length; ++i){
            dotProduct += this.vector[i] * v.vector[i];  //a.b
            magnitude1 += Math.pow(this.vector[i], 2);  //(a^2)
            magnitude2 += Math.pow(v.vector[i], 2); //(b^2)
		}

        magnitude1 = Math.sqrt(magnitude1);//sqrt(a^2)
        magnitude2 = Math.sqrt(magnitude2);//sqrt(b^2)

        if (magnitude1 != 0.0 | magnitude2 != 0.0) {
            cosineSimilarity = dotProduct / (magnitude1 * magnitude2);
        } else {
            return 0.0;
        }
        
		return cosineSimilarity;
	}
	
	public void addVector(JobDescriptionVector v) {
		for (int i = 0; i < vector.length; ++i){
			this.vector[i] += v.vector[i]; 
		}
	}

	public void logicalAndVector(JobDescriptionVector v) {
		for (int i = 0; i < vector.length; ++i){
			if (this.vector[i] == 0 || v.vector[i] == 0) {
				this.vector[i] = 0;
			} else {
				this.vector[i] += v.vector[i];
			}
		}
	}
	
	public List<String> getImportantWords(List<String> dictionary) {
		List<String> list = new ArrayList<>();
		ImportantWordsData[] data = new ImportantWordsData[vector.length];
		int index = 0;
		for (String str : dictionary) {
			data[index] = new ImportantWordsData(str, vector[index]);
			index++;
		}
		
		Arrays.sort(data);
		for (index = 0; index < MAX_IMPORTANT_WORDS; ++index) {
			list.add(data[index].getStr());
			System.out.println(data[index]);
		}
		
		return list;
	}
	
	static class ImportantWordsData implements Comparable<ImportantWordsData> {
		private String str;
		private int count;
		public ImportantWordsData(String str, int count) {
			super();
			this.str = str;
			this.count = count;
		}
		/**
		 * @return the str
		 */
		public String getStr() {
			return str;
		}
		/**
		 * @param str the str to set
		 */
		public void setStr(String str) {
			this.str = str;
		}
		/**
		 * @return the count
		 */
		public int getCount() {
			return count;
		}
		/**
		 * @param count the count to set
		 */
		public void setCount(int count) {
			this.count = count;
		}
		@Override
		public int compareTo(ImportantWordsData o) {
			return o.count-count; // decreasing order
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "ImportantWordsData [str=" + str + ", count=" + count + "]";
		}
	}
}
