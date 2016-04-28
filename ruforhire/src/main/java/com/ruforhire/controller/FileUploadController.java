/**
 * 
 */
package com.ruforhire.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ruforhire.model.JobTitleIndex;
import com.ruforhire.service.InMemoryUtils;

/**
 * @author ashish
 *
 */
@Controller
public class FileUploadController {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	private InMemoryUtils inMemoryUtils = null;
	
    @RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
    public String showFileUploadPage() {
    	return "upload";
    }
    
	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody List<JobTitleIndex> uploadFileHandler(@RequestParam("file") MultipartFile file) {

		Random rn = new Random();
		String name = "tmp" + rn.nextInt();
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				List<JobTitleIndex> matchingJobProfiles = inMemoryUtils.getMatchingJobProfilesUsingCosineSimilarity(serverFile);
				System.out.println("Matched = " + matchingJobProfiles);
				
				serverFile.delete();
				
				logger.info("Server File Location=" + serverFile.getAbsolutePath());

				return matchingJobProfiles;
			} catch (Exception e) {
				logger.info("You failed to upload " + name + " => " + e.getMessage());
			}
		} else {
			logger.info("You failed to upload " + name + " because the file was empty.");
		}
		
		return new ArrayList<JobTitleIndex>();
	}
}