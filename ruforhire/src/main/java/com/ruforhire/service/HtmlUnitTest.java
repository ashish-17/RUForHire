/**
 * 
 */
package com.ruforhire.service;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * @author ashish
 *
 */
public class HtmlUnitTest {

    final static WebClient webClient = new WebClient();
	/**
	 * @param args
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws FailingHttpStatusCodeException 
	 */
	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
	    //try (final WebClient webClient = new WebClient()) {
	        HtmlPage page = webClient.getPage("http://careers.bankofamerica.com/search-jobs.aspx?c=united-states&r=us");
	        String[] countStr= page.getHtmlElementById("footer-record-count").asText().replaceAll("[^0-9]+", " ").trim().split(" ");
	        int pages = (int)Math.ceil(Double.parseDouble(countStr[1]) / Double.parseDouble(countStr[0]));
			for (int index = 0; index < pages; ++index) {
				HtmlDivision content = page.getHtmlElementById("search-result");
				
			}
	  //  }
	}

}
