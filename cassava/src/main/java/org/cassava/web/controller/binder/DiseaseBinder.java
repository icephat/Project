package org.cassava.web.controller.binder;

import java.beans.PropertyEditorSupport;

import org.cassava.model.Disease;
import org.cassava.services.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DiseaseBinder extends PropertyEditorSupport {
	
	private @Autowired DiseaseService diseaseService;

	@Override
	public void setAsText(String text) {
		if (text.length() > 0) {

			Disease d = this.diseaseService.findById(Integer.parseInt(text));

			this.setValue(d);
		}
	}

}
