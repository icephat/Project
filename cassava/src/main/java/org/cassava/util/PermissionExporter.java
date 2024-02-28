package org.cassava.util;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;

public class PermissionExporter {

	
	public void export(HttpServletResponse response,String file) throws IOException {
		
		FileInputStream fis = new FileInputStream(file);
		
		response.setContentType("application/x-dowload");
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file));
		FileCopyUtils.copy(fis, response.getOutputStream());
	}
}
