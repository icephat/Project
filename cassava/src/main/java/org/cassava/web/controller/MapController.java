package org.cassava.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.cassava.api.util.model.Response;
import org.cassava.model.Province;
import org.cassava.services.FieldService;
import org.cassava.services.ProvinceService;
import org.cassava.services.TargetOfSurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MapController {

	@Autowired
	private FieldService fieldService;

	@Autowired
	private TargetOfSurveyService targetofsurveyService;
	
	@Autowired
	private ProvinceService provinceService;

	@RequestMapping(value = "/map/", method = RequestMethod.GET)
	public String index(Model model) {
		List<Integer> targetIds = new ArrayList<Integer>();
		List<String> targetNames = new ArrayList<String>();
		List<Object> obj = targetofsurveyService.findNaturalEnemy();
		for (Object o : obj) {
			Object[] ob = (Object[]) o;
			targetIds.add((Integer) ob[0]);
			targetNames.add((ob[1]).toString());
		}
		List<Object> obj1 = targetofsurveyService.findDisease();
		for (Object o : obj1) {
			Object[] ob = (Object[]) o;
			targetIds.add((Integer) ob[0]);
			targetNames.add((ob[1]).toString());
		}
		List<Object> obj2 = targetofsurveyService.findPest();
		for (Object o : obj2) {
			Object[] ob = (Object[]) o;
			targetIds.add((Integer) ob[0]);
			targetNames.add((ob[1]).toString());
		}
		List<Province> provinces = provinceService.findAll();
		model.addAttribute("targetIds", targetIds);
		model.addAttribute("targetNames", targetNames);
		model.addAttribute("provinces", provinces);
		
		
		return "map/map";
	}

//	@RequestMapping(value = "/map/getLocation", method = RequestMethod.GET)
//	public ResponseEntity<Response<float[][]>> getLocation() {
//		List<String> targetIds = new ArrayList<String>();
//		List<Object> obj = targetofsurveyService.findNaturalEnemy();
//		for (Object o : obj) {
//			Object[] ob = (Object[]) o;
//			targetIds.add(ob[0].toString());
//		}
//		List<Object> obj1 = targetofsurveyService.findDisease();
//		for (Object o : obj1) {
//			Object[] ob = (Object[]) o;
//			targetIds.add(ob[0].toString());
//		}
//		List<Object> obj2 = targetofsurveyService.findPest();
//		for (Object o : obj2) {
//			Object[] ob = (Object[]) o;
//			targetIds.add(ob[0].toString());
//		}
//		MapDTO mapDTO = new MapDTO();
//		mapDTO.setTargetOfSurveiesId(targetIds);
//		Calendar cal = Calendar.getInstance();
//		Date today = cal.getTime();
//		cal.add(Calendar.YEAR, -1); // to get previous year add -1
//		Date beforeYear = cal.getTime();
//		mapDTO.setDateInfoStart(beforeYear);
//		mapDTO.setDateInfoEnd(today);
//		Response<float[][]> res = new Response<float[][]>();
//		try {
//			List<Float> latitude = new ArrayList<Float>();
//			List<Float> longitude = new ArrayList<Float>();
//			List<Integer> check = new ArrayList<Integer>();
//			if (mapDTO.getDateInfoEnd() != null && mapDTO.getDateInfoStart() != null
//					&& mapDTO.getTargetOfSurveiesId().size() > 0) {
//				Date startDate = mapDTO.getDateInfoStart();
//				Date endDate = mapDTO.getDateInfoEnd();
//				for (String id : mapDTO.getTargetOfSurveiesId()) {
//					List<Object> object = fieldService.findLocationByStartDateAndEndDateAndTargetOfSurveyId(startDate,
//							endDate, Integer.valueOf(id));
//					if (!object.isEmpty()) {
//						for (Object obj3 : object) {
//							Object[] objj = (Object[]) obj3;
//							latitude.add(Float.valueOf(objj[0].toString()));
//							longitude.add(Float.valueOf(objj[1].toString()));
//							check.add(Integer.valueOf(objj[2].toString()));
//						}
//
//					}
//				}
//			}
//			float[][] location = new float[1][3];
//			if (latitude.size() > 0) {
//				location = new float[latitude.size()][3];
//				for (int i = 0; i < latitude.size(); i++) {
//					location[i][0] = latitude.get(i);
//					location[i][1] = longitude.get(i);
//					location[i][2] = check.get(i);
//				}
//			}
//			res.setBody(location);
//			res.setHttpStatus(HttpStatus.OK);
//			return new ResponseEntity<Response<float[][]>>(res, res.getHttpStatus());
//
//		} catch (Exception ex) {
//
//			res.setBody(null);
//			res.setHttpStatus(HttpStatus.NOT_FOUND);
//			return new ResponseEntity<Response<float[][]>>(res, res.getHttpStatus());
//		}
//	}

	@RequestMapping(value = "/map/search", method = RequestMethod.POST)
	public ResponseEntity<Response<float[][]>> getLocation(
			@RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "MM/dd/yyyy") Date startDate,
			@RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "MM/dd/yyyy") Date endDate,
			@RequestParam(value = "id", required = false) String id) {
		Response<float[][]> res = new Response<float[][]>();
		System.out.println(startDate+" "+endDate+" "+id);
		try {
			List<Float> latitude = new ArrayList<Float>();
			List<Float> longitude = new ArrayList<Float>();
			List<Integer> check = new ArrayList<Integer>();
			if (endDate != null && startDate != null) {
				List<Object> object = fieldService.findLocationByStartDateAndEndDateAndTargetOfSurveyId(startDate,
						endDate, Integer.valueOf(id));
				if (!object.isEmpty()) {
					for (Object obj1 : object) {
						Object[] obj = (Object[]) obj1;
						latitude.add(Float.valueOf(obj[0].toString()));
						longitude.add(Float.valueOf(obj[1].toString()));
						check.add(Integer.valueOf(obj[2].toString()));
					}

				}
			}
			float[][] location = new float[1][3];
			if (latitude.size() > 0) {
				location = new float[latitude.size()][3];
				for (int i = 0; i < latitude.size(); i++) {
					location[i][0] = latitude.get(i);
					location[i][1] = longitude.get(i);
					location[i][2] = check.get(i);
				}
			}
			res.setBody(location);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<float[][]>>(res, res.getHttpStatus());

		} catch (Exception ex) {

			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<float[][]>>(res, res.getHttpStatus());
		}
	}
	
	@RequestMapping(value = "/map/count", method = RequestMethod.POST)
	public ResponseEntity<Response<Object[][]>> getSurvey(
			@RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "MM/dd/yyyy") Date startDate,
			@RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "MM/dd/yyyy") Date endDate,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "provinceId", required = false) String provinceId) {
		Response<Object[][]> res = new Response<Object[][]>();
		//System.out.println("Input = "+startDate+" "+endDate+" "+id+" "+provinceId);
		
		Object[][] survey = null;
		try {
			if (endDate != null && startDate != null) {
				List<Object> object = fieldService.countSurveyByStartDateAndEndDateAndTargetOfSurveyIdAndProvinceId(startDate,
						endDate, Integer.valueOf(id),Integer.valueOf(provinceId));
				List<Object> object2 = fieldService.countSurveyDetectedByStartDateAndEndDateAndTargetOfSurveyIdAndProvinceId(startDate,
						endDate, Integer.valueOf(id),Integer.valueOf(provinceId));
				survey = new Object[object.size()][3];
				for(int i  = 0 ; i < object.size(); i++) {
					Object[] obj1 = (Object[])object.get(i);
					Object[] obj2 = (Object[])object2.get(i);
					survey[i][0] = obj1[1].toString();
					survey[i][1] = Integer.valueOf(obj1[0].toString());
					survey[i][2] = Integer.valueOf(obj2[0].toString());
				}
				
			}
			res.setBody(survey);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<Object[][]>>(res, res.getHttpStatus());

		} catch (Exception ex) {

			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<Object[][]>>(res, res.getHttpStatus());
		}
	}
}
