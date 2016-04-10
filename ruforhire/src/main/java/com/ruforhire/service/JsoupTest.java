package com.ruforhire.service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTest {

	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("http://careers.bankofamerica.com/search-jobs.aspx?c=united-states&r=us").get();
		String[] countStr= doc.getElementById("footer-record-count").text().replaceAll("[^0-9]+", " ").trim().split(" ");
		int pages = (int)Math.ceil(Double.parseDouble(countStr[1]) / Double.parseDouble(countStr[0]));
		for (int page = 0; page < 1; ++page) {
			Element content = doc.getElementById("search-result");
			Elements results = content.getElementsByTag("tr");
			for (Element e : results) {
				System.out.println(e.text());
			}
			
			// javascript:__doPostBack('ctl00$PlhContentWrapper$dglistview$ctl14$ctl01','')
			/*doc = Jsoup.connect("http://careers.bankofamerica.com/search-jobs.aspx?c=united-states&r=us")
					.data("ctl00$PlhContentWrapper$dglistview$ctl14$ctl01", "")
					.userAgent("Mozilla")
					.post();*/
		}
	}
}
