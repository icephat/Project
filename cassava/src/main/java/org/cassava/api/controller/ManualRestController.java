package org.cassava.api.controller;

import java.io.File;
import java.io.*;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/manual")
public class ManualRestController {

	@RequestMapping(path = "/download", method = RequestMethod.GET)
	public ResponseEntity<Resource> download() throws IOException {

		//File file = new File(getClass().getResource("USERMANUAL_MOBILE_APPLICATION.pdf").getFile());
		Resource r = new ClassPathResource("USERMANUAL_MOBILE_APPLICATION.pdf");
		File file = r.getFile();
	    FileInputStream f = new FileInputStream(r.getFile());
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=user_manual.pdf");
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		InputStreamResource resource = new InputStreamResource(f);

		return ResponseEntity.ok().headers(headers).contentLength(file.length())
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	}

}
