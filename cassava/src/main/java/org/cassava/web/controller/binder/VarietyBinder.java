package org.cassava.web.controller.binder;

import java.beans.PropertyEditorSupport;

import org.cassava.model.Disease;
import org.cassava.model.Variety;
import org.cassava.services.DiseaseService;
import org.cassava.services.VarietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VarietyBinder  extends PropertyEditorSupport  {
	
	private @Autowired VarietyService varietyService;
	
	@Override
	public void setAsText(String text) {
		if (text.length() > 0) {
			Variety d = this.varietyService.findById(Integer.parseInt(text));

			this.setValue(d);
		}
	}

}
