package org.cassava.model.dto;


import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class DateDTO {

	private Date dateInfoStart;
		
	private Date dateInfoEnd;

	public Date getDateInfoStart() {
		return dateInfoStart;
	}

	public void setDateInfoStart(Date dateInfoStart) {
		this.dateInfoStart = dateInfoStart;
	}

	public Date getDateInfoEnd() {
		return dateInfoEnd;
	}

	public void setDateInfoEnd(Date dateInfoEnd) {
		this.dateInfoEnd = dateInfoEnd;
	}

	
}
