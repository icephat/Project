package org.cassava.web.controller;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.cassava.model.Disease;
import org.cassava.model.ImgSurveyTargetPoint;
import org.cassava.services.DiseaseService;
import org.cassava.services.ImgSurveyTargetPointService;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;;

@Controller
public class ExportController {

	@Value("${external.resoures.path}")
	private String externalPath;

	@Value("${imgdowloadsize.mb}")
	private String sizel;

	@Autowired
	private DiseaseService diseaseService;

	@Autowired
	private ImgSurveyTargetPointService imgSurveyTargetPointService;

	@RequestMapping(value = {"/export/index","/export/","/export"}, method = RequestMethod.GET)
	public String index(Model model) {
		List<String> i = new ArrayList<String>();
		List<String> s = new ArrayList<String>();
		List<Object> o = imgSurveyTargetPointService.findAmountOnUploadYear();
		for (Object object : o) {
			Object[] ob = (Object[]) object;
			if(!ob[0].toString().equals("0")) {
				i.add(ob[0].toString());
				s.add(ob[1].toString());
			}
		}
		model.addAttribute("amountimg", i);
		model.addAttribute("year", s);
		model.addAttribute("disease", diseaseService.findAll());
		return "/export/imageIndex";
	}

	@RequestMapping(value = "/export/detail", method = RequestMethod.POST)
	public String detail(@RequestParam("listdiseaseid") List<Integer> listdiseaseid,
			@RequestParam("yearselect") int year, Model model) {
		int l = 0;
		List<Disease> diseaseselect = new ArrayList<Disease>();
		List<Integer> checkmoy = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		for (int j : listdiseaseid) {
			int a = imgSurveyTargetPointService.findAmountImgSurveyTargetPointByDiseaseIdAndYear(j, year) ;
			for (int i = 1; i < 13; i++) {
				Object b = imgSurveyTargetPointService.findAmountImgSurveyTargetPointByDiseaseIdAndYearAndMonth(j,year, i);
				if (b != null) {
					checkmoy.set(i, 1);
				}
			}
			if (a != 0) {
				checkmoy.set(0, 1);
			}
		}
		ArrayList<ArrayList<Integer>> num = new ArrayList<ArrayList<Integer>>();
		List<String> a = new ArrayList<String>();
		for (int j : listdiseaseid) {
			List<Integer> check = new ArrayList<Integer>();
			diseaseselect.add(diseaseService.findById(j));
			a.add(String.valueOf(j));
			List<Object> p = imgSurveyTargetPointService.findAmountImgSurveyTargetPointEachMonthByDiseaseIdAndYear(j,
					year);
			num.add(new ArrayList<>(Arrays.asList(j, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)));
			for (Object object : p) {
				Integer cmon = 0;
				Integer mon = 0;
				Object[] ob = (Object[]) object;
				cmon = Integer.parseInt(ob[0].toString());
				mon = Integer.parseInt(ob[1].toString());
				for (int k = 1; k <= 12; k++) {
					if (mon == k) {
						num.get(l).set(k, cmon);
					}
				}
			}
			num.get(l).addAll(check);
			l++;
		}
		
		List<String> i = new ArrayList<String>();
		List<String> s = new ArrayList<String>();
		List<Object> o = imgSurveyTargetPointService.findAmountOnUploadYear();
		for (Object object : o) {
			Object[] ob = (Object[]) object;
			if(!ob[0].toString().equals("0")) {
				i.add(ob[0].toString());
				s.add(ob[1].toString());
			}
		}
		model.addAttribute("checkmoy",checkmoy);
		model.addAttribute("yearamount", i);
		model.addAttribute("num", num);
		model.addAttribute("year", s);
		model.addAttribute("diseaseselect", diseaseselect);
		model.addAttribute("listdiseaseid", listdiseaseid);
		model.addAttribute("diseaseselectst",
				(((listdiseaseid.toString()).replaceAll(" ", "")).replace("]", "")).replace("[", ""));
		model.addAttribute("yearselect", year);
		model.addAttribute("disease", diseaseService.findAll());
		return "/export/imageDetail";
	}

	@RequestMapping(value = "/export/imginmonth", method = RequestMethod.GET)
	public String surveyDetailMonth(@RequestParam("id") int diseaseselectid, @RequestParam("month") int month,
			@RequestParam("year") int year, @RequestParam("listid") List<Integer> listdiseaseid, Model model) {

		List<String> m = Arrays.asList("ม.ค.", "ก.พ.", "มี.ค.", "เม.ย.", "พ.ค.", "มิ.ย.", "ก.ค.", "ส.ค.", "ก.ย.",
				"ต.ค.", "พ.ย.", "ธ.ค.");
		int l = 0;
		float size = 0;
		List<Float> w = new ArrayList<Float>();
		ArrayList<ArrayList<Integer>> imgid = new ArrayList<ArrayList<Integer>>();
		imgid.add(new ArrayList<Integer>());
		List<Object> s = imgSurveyTargetPointService
				.findImgSurveyTargetPointApprovedByYearAndMonthAndDiseaseId(diseaseselectid, year, month);
		for (Object o : s) {
			Object[] ob = (Object[]) o;
			String f = null;
			f = externalPath + "/" + "SurveyTargetPoint" + "/" + ob[0].toString();
			File dd = new File(f);
			size += (float) dd.length() / (1024 * 1024);
			if (size <= Integer.parseInt(sizel)) {
				imgid.get(l).add(Integer.parseInt(ob[1].toString()));
			} else {
				if (l == 0 && imgid.get(l).size() == 0) {
					imgid.get(l).add(Integer.parseInt(ob[1].toString()));
					l++;
				} else {
					l++;
					w.add(size - (float) dd.length() / (1024 * 1024));
					imgid.add(new ArrayList<Integer>());
					size = (float) dd.length() / (1024 * 1024);
					imgid.get(l).add(Integer.parseInt(ob[1].toString()));
				}
			}
		}
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedValue = decimalFormat.format(size);
		w.add(Float.parseFloat(formattedValue));

		Object numimg = imgSurveyTargetPointService
				.findAmountImgSurveyTargetPointByDiseaseIdAndYearAndMonth(diseaseselectid, year, month);
		if (numimg == null) {
			model.addAttribute("numimg", 0);
		} else {
			model.addAttribute("numimg", numimg.toString());
		}
		model.addAttribute("imgid", imgid);
		model.addAttribute("size", w);
		model.addAttribute("year", year);
		model.addAttribute("month", m);
		model.addAttribute("monthint", month);
		model.addAttribute("diseaseselect", diseaseService.findById(diseaseselectid));
		model.addAttribute("listdiseaseid", listdiseaseid);
		return "/export/DetailDiseaseInMonth";
	}

	@RequestMapping(value = "/export/allimginmonth", method = RequestMethod.POST)
	public String surveyDetailMonthAll(@RequestParam("year") int year,
			@RequestParam("listdiseaseid") List<Integer> listdiseaseid, @RequestParam("month") int month,
			Model model) {
		List<String> m = Arrays.asList("ม.ค.", "ก.พ.", "มี.ค.", "เม.ย.", "พ.ค.", "มิ.ย.", "ก.ค.", "ส.ค.", "ก.ย.",
				"ต.ค.", "พ.ย.", "ธ.ค.");
		int k = 0;
		int l = 0;
		float size = 0;
		List<Disease> listdisease = new ArrayList<Disease>();
		List<Float> w = new ArrayList<Float>();
		List<Object> s = new ArrayList<Object>();
		for (int loop : listdiseaseid) {
			s.addAll(imgSurveyTargetPointService.findImgSurveyTargetPointApprovedByYearAndMonthAndDiseaseId(loop, year,
					month));
		}
		ArrayList<ArrayList<Integer>> imgid = new ArrayList<ArrayList<Integer>>();
		imgid.add(new ArrayList<Integer>());
		for (Object o : s) {
			Object[] ob = (Object[]) o;
			String f = null;
			f = externalPath + "/" + "SurveyTargetPoint" + "/" + ob[0].toString();
			File dd = new File(f);
			size += (float) dd.length() / (1024 * 1024);
			if (size <= Integer.parseInt(sizel)) {
				imgid.get(l).add(Integer.parseInt(ob[1].toString()));
			} else {
				if (l == 0 && imgid.get(l).size() == 0) {
					imgid.get(l).add(Integer.parseInt(ob[1].toString()));
					l++;
				} else {
					l++;
					w.add(size - (float) dd.length() / (1024 * 1024));
					imgid.add(new ArrayList<Integer>());
					size = (float) dd.length() / (1024 * 1024);
					imgid.get(l).add(Integer.parseInt(ob[1].toString()));
				}
			}
		}
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedValue = decimalFormat.format(size);
		w.add(Float.parseFloat(formattedValue));
		for (int i : listdiseaseid) {
			listdisease.add(diseaseService.findById(i)) ;
			Object numimg = imgSurveyTargetPointService.findAmountImgSurveyTargetPointByDiseaseIdAndYearAndMonth(i,
					year, month);
			if (numimg == null) {

			} else {
				k += Integer.parseInt(numimg.toString());
			}
		}
		model.addAttribute("imgid", imgid);
		model.addAttribute("size", w);
		model.addAttribute("sum", k);
		model.addAttribute("year", year);
		model.addAttribute("month", m);
		model.addAttribute("monthint", month);
		model.addAttribute("listdiseaseid", listdiseaseid);
		model.addAttribute("listdisease", listdisease);
		return "/export/DetailAllDiseaseInMonth";
	}

	@RequestMapping(value = "/export/imginyear", method = RequestMethod.POST)
	public String surveyDetailYear(@RequestParam("listdiseaseid") List<Integer> listdiseaseid,
			@RequestParam("year") int year, Model model) {
		int k = 0;
		int l = 0;
		float size = 0;
		List<Disease> listdisease = new ArrayList<Disease>();
		List<Float> w = new ArrayList<Float>();
		List<Object> s = new ArrayList<Object>();
		for (int id : listdiseaseid) {
			s.addAll(imgSurveyTargetPointService.findImgSurveyTargetPointApprovedByYearAndDiseaseId(id, year));
		}
		ArrayList<ArrayList<Integer>> imgid = new ArrayList<ArrayList<Integer>>();
		imgid.add(new ArrayList<Integer>());
		for (Object o : s) {
			Object[] ob = (Object[]) o;
			String f = null;
			f = externalPath + "/" + "SurveyTargetPoint" + "/" + ob[0].toString();
			File dd = new File(f);
			size += (float) dd.length() / (1024 * 1024);
			if (size <= Integer.parseInt(sizel)) {
				imgid.get(l).add(Integer.parseInt(ob[1].toString()));
			} else {
				if (l == 0 && imgid.get(l).size() == 0) {
					imgid.get(l).add(Integer.parseInt(ob[1].toString()));
					l++;
				} else {
					l++;
					w.add(size - (float) dd.length() / (1024 * 1024));
					imgid.add(new ArrayList<Integer>());
					size = (float) dd.length() / (1024 * 1024);
					imgid.get(l).add(Integer.parseInt(ob[1].toString()));
				}
			}
		}
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedValue = decimalFormat.format(size);
		w.add(Float.parseFloat(formattedValue));
		for (int i : listdiseaseid) {
			listdisease.add(diseaseService.findById(i)) ;
			Object numimg = imgSurveyTargetPointService.findAmountImgSurveyTargetPointByDiseaseIdAndYear(i, year);
			if (numimg != null) {
				k += Integer.parseInt(numimg.toString());
			}
		}
		model.addAttribute("imgid", imgid);
		model.addAttribute("size", w);
		model.addAttribute("numimg", k);
		model.addAttribute("listdiseaseid", listdiseaseid);
		model.addAttribute("listdisease", listdisease);
		model.addAttribute("year", year);
		return "/export/DetailDiseaseInYear";
	}

	@RequestMapping(value = "/export/img", method = RequestMethod.POST)
	public void exportImg(HttpServletResponse response, Model model, @RequestParam("imgid") String imgid) {

		List<ImgSurveyTargetPoint> imgstp = new ArrayList<ImgSurveyTargetPoint>();
		String a = imgid.replace("[", "");
		String b = a.replace("]", "");
		String c = b.replace(" ", "");
		String[] d = c.split(",");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=download.zip");
		response.setStatus(HttpServletResponse.SC_OK);
		for (String e : d) {
			imgstp.add(imgSurveyTargetPointService.findById(Integer.parseInt(e)));
		}

		try (ZipOutputStream zippedOut = new ZipOutputStream(response.getOutputStream())) {
			for (ImgSurveyTargetPoint file : imgstp) {
				FileSystemResource resource = new FileSystemResource(
						externalPath + "/" + "SurveyTargetPoint" + "/" + file.getFilePath());
				ZipEntry e = new ZipEntry(resource.getFilename());
				e.setSize(resource.contentLength());
				e.setTime(System.currentTimeMillis());
				zippedOut.putNextEntry(e);
				StreamUtils.copy(resource.getInputStream(), zippedOut);
				zippedOut.closeEntry();
			}
			zippedOut.finish();
			zippedOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
