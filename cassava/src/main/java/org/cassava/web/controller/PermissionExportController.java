package org.cassava.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cassava.model.Field;
import org.cassava.model.Herbicide;
import org.cassava.model.Permission;
import org.cassava.model.Planting;
import org.cassava.model.PlantingCassavaVariety;
import org.cassava.model.PlantingCassavaVarietyId;
import org.cassava.model.Province;
import org.cassava.model.Request;
import org.cassava.model.RequestDetail;
import org.cassava.model.Role;
import org.cassava.model.Staff;
import org.cassava.model.SurveyTarget;
import org.cassava.model.SurveyTargetPoint;
import org.cassava.model.TargetOfSurvey;
import org.cassava.model.User;
import org.cassava.model.UserInField;
import org.cassava.services.DiseaseService;
import org.cassava.services.FieldService;
import org.cassava.services.NaturalEnemyService;
import org.cassava.services.PermissionProvinceService;
import org.cassava.services.PermissionService;
import org.cassava.services.PermissionTargetOfSurveyService;
import org.cassava.services.PestPhaseSurveyService;
import org.cassava.services.PlantingCassavaVarietyService;
import org.cassava.services.PlantingService;
import org.cassava.services.ProvinceService;
import org.cassava.services.RequestService;
import org.cassava.services.RequestdetailService;
import org.cassava.services.StaffService;
import org.cassava.services.SurveyTargetService;
import org.cassava.services.TargetOfSurveyService;
import org.cassava.services.UserInFieldService;
import org.cassava.services.UserService;
import org.cassava.util.MvcHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PermissionExportController {

	@Autowired
	private RequestService requestService;

	@Autowired
	private RequestdetailService requestdetailService;

	@Autowired
	private UserService userService;

	@Autowired
	private StaffService staffService;

	@Autowired
	private UserInFieldService userInFieldService;

	@Autowired
	private FieldService fieldService;

	@Autowired
	private DiseaseService diseaseService;

	@Autowired
	private ProvinceService provinceService;

	@Autowired
	private PestPhaseSurveyService pestPhaseSurveyService;

	@Autowired
	private TargetOfSurveyService targetOfSurveyService;

	@Autowired
	private NaturalEnemyService naturalEnemyService;

	@Autowired
	private PlantingService plantingService;

	@Autowired
	private PlantingCassavaVarietyService plantingCassavaVarietyService;

	@Autowired
	private SurveyTargetService surveyTargetService;

	@Autowired
	private PermissionTargetOfSurveyService permissionTargetOfSurveyService;

	@Autowired
	private PermissionProvinceService permissionProvinceService;

	@Autowired
	private PermissionService permissionService;

	@RequestMapping(value = { "/permissionexport/index", "/permissionexport/",
			"/permissionexport" }, method = RequestMethod.GET)
	public String Index(Model model, Authentication authentication) {
		Staff staff = staffService.findByUserName(MvcHelper.getUsername(authentication));
		List<Request> requests = requestService.findByUserIdAndType(staff.getStaffId(), "inOrganization");
		model.addAttribute("requests", requests);
		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatter", formatter);
		model.addAttribute("toPage", "index");

		return "/permissionexport/index";
	}

	@RequestMapping(value = "/permissionexport/permissions", method = RequestMethod.GET)
	public String permission(Model model, Authentication authentication) {
		String username = MvcHelper.getUsername(authentication);
		Staff staff = staffService.findByUserName(username);

		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		ArrayList<String> status = new ArrayList<String>();
		status.add("Approve");
		List<Permission> permissions = permissionService.findByStaffAndApproveStatusAndOrganization(staff,
				"inOrganize");
		List<List<Object>> targetOfSurveyAndCounts = new ArrayList<List<Object>>();
		List<List<Province>> provinces = new ArrayList<List<Province>>();

		for (Permission permission : permissions) {

			List<Integer> tosIds = permissionTargetOfSurveyService.findtargetofsurveyIdByPermission(permission);
			List<Integer> provinceIds = permissionProvinceService.findProvinceIdByPermission(permission);

			provinces.add(permissionProvinceService.findProvinceByPermission(permission));

			targetOfSurveyAndCounts.add(targetOfSurveyService
					.findCountSurveyAndTargetOfSurveyByTargetOfSurveyIdAndDateAndProvinceAndStatus(tosIds,
							permission.getDateInfoStart(), permission.getDateInfoEnd(), provinceIds, "Complete"));
		}

		model.addAttribute("now", new Date());
		model.addAttribute("permissions", permissions);
		model.addAttribute("targetOfSurveyAndCounts", targetOfSurveyAndCounts);
		model.addAttribute("provinces", provinces);
		model.addAttribute("formatter", formatter);
		return "/permissionexport/permission";
	}

	@RequestMapping(value = { "/permissionexport/permission/{pid}",
			"/permissionexport/permission/{pid}/{search}" }, method = RequestMethod.POST)
	public String Index(@PathVariable("pid") int id, Model model, Authentication authentication,
			@PathVariable(value = "search", required = false) String search,
			@RequestParam(value = "dateStart", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date datestart,
			@RequestParam(value = "dateEnd", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateend,
			@RequestParam(value = "fieldname", required = false) String fieldname,
			@RequestParam(value = "disease", required = false) List<Integer> diseaseid,
			@RequestParam(value = "naturalenemy", required = false) List<Integer> naturalenemyid,
			@RequestParam(value = "pest", required = false) List<Integer> pestid,
			@RequestParam(value = "province", required = false) List<Integer> provinceid) {

		Permission permission = permissionService.findById(id);
		if (permission == null) {
			return "redirect:/permissionexport/";
		}
		model.addAttribute("permissionId", id);
		model.addAttribute("datestart", permission.getDateInfoStart());
		model.addAttribute("dateend", permission.getDateInfoEnd());
		List<SurveyTarget> serveyTarget = null;

		List<Province> provinces = permissionProvinceService.findProvinceByPermission(permission);
		List<TargetOfSurvey> tosAll = permissionTargetOfSurveyService.findtargetofsurveyByPermission(permission);
		serveyTarget = surveyTargetService.findByTargetOfSurveysAndBetweenDateAndProvincesAndStatus(tosAll,
				permission.getDateInfoStart(), permission.getDateInfoEnd(), provinces, "Complete");

		if (search != null && search.equals("search")) {
			List<Province> pvs = provinceService.findByListId(provinceid);

			List<Integer> tosIds = new ArrayList<Integer>();

			tosIds.addAll(pestid);
			tosIds.addAll(naturalenemyid);
			tosIds.addAll(diseaseid);
			SimpleDateFormat targetDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String datestartselect = targetDateFormat.format(datestart);
			String dateendselect = targetDateFormat.format(dateend);
			model.addAttribute("fieldname", fieldname);
			model.addAttribute("datestartselect", datestartselect);
			model.addAttribute("dateendselect", dateendselect);
			model.addAttribute("datestart", permission.getDateInfoStart());
			model.addAttribute("dateend", permission.getDateInfoEnd());
			model.addAttribute("diseaseid", diseaseid);
			model.addAttribute("naturalenemyid", naturalenemyid);
			model.addAttribute("pestid", pestid);
			model.addAttribute("provinceid", provinceid);

			List<TargetOfSurvey> toss = targetOfSurveyService.findByListTargetOfSurveyId(tosIds);
			List<SurveyTarget> serveyTargetSearch = surveyTargetService
					.findByTargetOfSurveysAndBetweenDateAndProvincesAndStatusAndFieldName(toss,
							permission.getDateInfoStart(), permission.getDateInfoEnd(), pvs, "Complete", fieldname);
			model.addAttribute("listsurveytarget", serveyTargetSearch);
		} else {
			model.addAttribute("diseaseid", diseaseid);
			model.addAttribute("datestartselect", permission.getDateInfoStart());
			model.addAttribute("dateendselect", permission.getDateInfoEnd());
			model.addAttribute("datestart", permission.getDateInfoStart());
			model.addAttribute("dateend", permission.getDateInfoEnd());
			model.addAttribute("listsurveytarget", serveyTarget);
		}
		model.addAttribute("fieldname", fieldname);
		model.addAttribute("listprovince", provinceService.findBySurveyTarget(serveyTarget));
		model.addAttribute("listdisease", targetOfSurveyService.findTypeDiseaseBySurveyTarget(serveyTarget));
		model.addAttribute("listpest", targetOfSurveyService.findTypePestPhaseBySurveyTarget(serveyTarget));
		model.addAttribute("listnaturalenemy", targetOfSurveyService.findTypeNaturalEnemyBySurveyTarget(serveyTarget));
		model.addAttribute("listfield", fieldService.findBySurveyTarget(serveyTarget));

		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatter", formatter);
		return "/permissionexport/permissionRequest";
	}

	@RequestMapping(value = "/permissionexport/requestReject/{id}/{organization}", method = RequestMethod.GET)
	public String RequestReject(Model model, Authentication authentication, @PathVariable("id") int reid,
			@PathVariable("organization") String organization) {
		Request re = requestService.findById(reid);
		if (re == null) {
			return "redirect:/permissionexport/";
		}
		List<String> organizationcheck = new ArrayList<String>();
		Staff staff = userService.findByUsername(MvcHelper.getUsername(authentication)).getStaff();
		organizationcheck.add("กรมส่งเสริมการเกษตร");
		organizationcheck.add("กรมวิชาการเกษตร");
		organizationcheck.add("ภาควิชาโรคพืช มก. กพส.");
		if (organizationcheck.get(0).equals(organization)) {
			re.setStatusDae("Reject");
			re.setStaffByApproveByDae(staff);
		} else if (organizationcheck.get(1).equals(organization)) {
			re.setStatusDa("Reject");
			re.setStaffByApproveByDa(staff);
		} else if (organizationcheck.get(2).equals(organization)) {
			re.setStatusDppatho("Reject");
			re.setStaffByApproveByDpphato(staff);
		}
		List<String> status = new ArrayList<String>();
		status.add(re.getStatusDae());
		status.add(re.getStatusDa());
		status.add(re.getStatusDppatho());
		int checkapprove = 0;
		int checkreject = 0;
		for (int i = 0; i < status.size(); i++) {
			if (status.get(i).equals("Approve")) {
				checkapprove++;
			} else if (status.get(i).equals("Reject")) {
				checkreject++;
			}
		}
		Date date = new Date();
		if (checkreject >= 1) {
			re.setStatus("Reject");
		} else if (checkapprove == 3) {
			re.setStatus("Approve");
		} else {
			re.setStatus("Waiting");
		}
		re.setStaffByApproveBy(staff);
		re.setDateApprove(date);
		requestService.save(re);
		return "redirect:/permissionexport/approve/";
	}

	@RequestMapping(value = "/permissionexport/requestSave", method = RequestMethod.POST)
	public String save(@RequestParam("listsurveyid") List<Integer> listsurveyid, Model model,
			Authentication authentication) {
		User user = userService.findByUsername(MvcHelper.getUsername(authentication));

		Request req = new Request();
		req.setStaffByStaffId(staffService.findById(user.getUserId()));
		req.setType("inOrganization");
		req.setStatus("Waiting");
		req.setStatusDa("Waiting");
		req.setStatusDae("Waiting");
		req.setStatusDppatho("Waiting");
		Date date = new Date();
		req.setDateRequest(date);
		requestService.save(req);
		for (Integer i : listsurveyid) {
			RequestDetail reqde = new RequestDetail();
			reqde.setRequest(req);
			reqde.setSurveyTarget(surveyTargetService.findById(i));
			requestdetailService.save(reqde);
		}
		return "redirect:/permissionexport/";
	}

	@RequestMapping(value = "/permissionexport/requestDelete/{id}", method = RequestMethod.GET)
	public String DeleteRequest(Model model, @PathVariable("id") int reid) {
		List<RequestDetail> re = requestdetailService.findByRequestId(reid);
		for (RequestDetail requestDetail : re) {
			requestdetailService.deleteById(requestDetail.getRequestDetailId());
		}
		requestService.deleteById(reid);
		return "redirect:/permissionexport/ ";
	}

	@RequestMapping(value = "/permissionexport/requestDetail/{id}", method = RequestMethod.POST)
	public String RequestField(Model model, Authentication authentication, @PathVariable("id") int reid,
			@RequestParam(value = "toPage", required = false) String toPage) {
		List<RequestDetail> listred = requestdetailService.findByRequestId(reid);
		model.addAttribute("requestdetails", listred);
		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatter", formatter);
		model.addAttribute("toPage", toPage);
		return "/permissionexport/requestdetail";
	}

	@RequestMapping(value = { "/permissionexport/approve/index", "/permissionexport/approve/",
			"/permissionexport/approve" }, method = RequestMethod.GET)
	public String IndexApprove(Model model, Authentication authentication) {

		Staff staff = staffService.findByUserName(MvcHelper.getUsername(authentication));
		ArrayList<String> status = new ArrayList<String>();
		status.add("Waiting");
		List<Request> requests = requestService.findByOrganizationIdAndTypeAndStatus(staff, "inOrganization",
				status);
		model.addAttribute("requests", requests);
		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatter", formatter);
		return "/permissionexport/approveindex";
	}

	@RequestMapping(value = "/permissionexport/requestApproved", method = RequestMethod.POST)
	public String requestApproved(Model model, Authentication authentication,
			@RequestParam(name = "status") String status) {

		Staff staff = staffService.findByUserName(MvcHelper.getUsername(authentication));
		ArrayList<String> restatus = new ArrayList<String>();
		restatus.add("Approve");
		restatus.add("Reject");
		List<Request> requests = requestService.findByOrganizationIdAndTypeAndStatus(staff, "inOrganization",
				restatus);
		model.addAttribute("requests", requests);
		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatter", formatter);

		return "/permissionexport/requestApproved";
	}

	@RequestMapping(value = "/permissionexport/{statu}/{id}", method = RequestMethod.GET)
	public String RequestApprove(Model model, Authentication authentication, @PathVariable("id") int reid,
			@PathVariable("statu") String statu) {
		
		Request re = requestService.findById(reid);
		if (re == null) {
			return "redirect:/permissionexport/approve/";
		}
		re.setStatus(statu);
		re.setStaffByApproveBy(userService.findByUsername(MvcHelper.getUsername(authentication)).getStaff());
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		Date expireDate = cal.getTime();
		re.setDateApprove(date);
		re.setDateExpire(expireDate);
		requestService.save(re);
		
		return "redirect:/permissionexport/approve/";
	}


	@RequestMapping(value = "/permissionexport/export/{id}", method = RequestMethod.GET)
	public void ExportExcel(HttpServletResponse response, Model model, Authentication authentication,
			@PathVariable("id") int rdid) throws ClassNotFoundException, IOException {
		Workbook workbook = new SXSSFWorkbook();
		Sheet sheet1 = workbook.createSheet("แปลง");
		Row headerRow = sheet1.createRow(0);
		Cell headerCell1 = headerRow.createCell(0);
		headerCell1.setCellValue("รหัสแปลง");
		Cell headerCell2 = headerRow.createCell(1);
		headerCell2.setCellValue("ชื่อแปลง");
		Cell headerCell3 = headerRow.createCell(2);
		headerCell3.setCellValue("หน่วยงาน");
		Cell headerCell4 = headerRow.createCell(3);
		headerCell4.setCellValue("เกษตรกรเจ้าของแปลง");
		Cell headerCell5 = headerRow.createCell(4);
		headerCell5.setCellValue("ที่อยู่แปลง");
		Cell headerCell6 = headerRow.createCell(5);
		headerCell6.setCellValue("ถนน");
		Cell headerCell7 = headerRow.createCell(6);
		headerCell7.setCellValue("หมู่");
		Cell headerCell8 = headerRow.createCell(7);
		headerCell8.setCellValue("ตำบล");
		Cell headerCell9 = headerRow.createCell(8);
		headerCell9.setCellValue("อำเภอ");
		Cell headerCell10 = headerRow.createCell(9);
		headerCell10.setCellValue("จังหวัด");
		Cell headerCell11 = headerRow.createCell(10);
		headerCell11.setCellValue("จุดสังเกตที่อยู่แปลง");
		Cell headerCell12 = headerRow.createCell(11);
		headerCell12.setCellValue("ละติจูด");
		Cell headerCell13 = headerRow.createCell(12);
		headerCell13.setCellValue("ลองจิจูด");
		Cell headerCell14 = headerRow.createCell(13);
		headerCell14.setCellValue("ความสูงจากระดับน้ำทะเล");
		Cell headerCell15 = headerRow.createCell(14);
		headerCell15.setCellValue("ขนาดแปลง");
		Cell headerCell16 = headerRow.createCell(15);
		headerCell16.setCellValue("เจ้าหน้าที่ผู้รับผิดชอบแปลง");

		// Create a new sheet2
		Sheet sheet2 = workbook.createSheet("การเพาะปลูก");
		Row headerRow2 = sheet2.createRow(0);
		Cell header2Cell1 = headerRow2.createCell(0);
		header2Cell1.setCellValue("รหัสแปลง");
		Cell header2Cell2 = headerRow2.createCell(1);
		header2Cell2.setCellValue("รหัสการเพาะปลูก");
		Cell header2Cell3 = headerRow2.createCell(2);
		header2Cell3.setCellValue("ชื่อการเพาะปลูก");
		Cell header2Cell4 = headerRow2.createCell(3);
		header2Cell4.setCellValue("ขนาดแปลง");
		Cell header2Cell5 = headerRow2.createCell(4);
		header2Cell5.setCellValue("พืชที่ปลูกก่อนหน้า");
		Cell header2Cell7 = headerRow2.createCell(5);
		header2Cell7.setCellValue("ชนิดพืชแปลงข้างเคียง");
		Cell header2Cell9 = headerRow2.createCell(6);
		header2Cell9.setCellValue("พันธุ์มันสำปะหลัง");
		Cell header2Cell11 = headerRow2.createCell(7);
		header2Cell11.setCellValue("วันที่ปลูก");
		Cell header2Cell12 = headerRow2.createCell(8);
		header2Cell12.setCellValue("วันที่เก็บเกี่ยว");
		Cell header2Cell13 = headerRow2.createCell(9);
		header2Cell13.setCellValue("พืชรอง");
		Cell header2Cell14 = headerRow2.createCell(10);
		header2Cell14.setCellValue("พันธุ์พืชรอง");
		Cell header2Cell15 = headerRow2.createCell(11);
		header2Cell15.setCellValue("วันที่ปลูกพืชรอง");
		Cell header2Cell16 = headerRow2.createCell(12);
		header2Cell16.setCellValue("วันที่เก็บเกี่ยวพืชรอง");
		Cell header2Cell17 = headerRow2.createCell(13);
		header2Cell17.setCellValue("จำนวนครั้งการไถเตรียมแปลง");
		Cell header2Cell18 = headerRow2.createCell(14);
		header2Cell18.setCellValue("การใช้วัสดุปรับปรุงดิน");
		Cell header2Cell20 = headerRow2.createCell(15);
		header2Cell20.setCellValue("แหล่งท่อนพันธุ์");
		Cell header2Cell21 = headerRow2.createCell(16);
		header2Cell21.setCellValue("สารเคมีกำจัดแมลงสำหรับแช่ท่อนพันธุ์ก่อนปลูก");
		Cell header2Cell22 = headerRow2.createCell(17);
		header2Cell22.setCellValue("วันที่ใส่ปุ๋ยครั้งที่ 1");
		Cell header2Cell23 = headerRow2.createCell(18);
		header2Cell23.setCellValue("ปุ๋ยเกรดที่ใช้ครั้งที่ 1");
		Cell header2Cell24 = headerRow2.createCell(19);
		header2Cell24.setCellValue("วันที่ใส่ปุ๋ยครั้งที่ 2");
		Cell header2Cell25 = headerRow2.createCell(20);
		header2Cell25.setCellValue("ปุ๋ยเกรดที่ใช้ครั้งที่ 2");
		Cell header2Cell26 = headerRow2.createCell(21);
		header2Cell26.setCellValue("วันที่ใส่ปุ๋ยครั้งที่ 3");
		Cell header2Cell27 = headerRow2.createCell(22);
		header2Cell27.setCellValue("ปุ๋ยเกรดที่ใช้ครั้งที่ 3");
		Cell header2Cell28 = headerRow2.createCell(23);
		header2Cell28.setCellValue("เดือนที่กำจัดวัชพืชครั้งที่ 1 ");
		Cell header2Cell29 = headerRow2.createCell(24);
		header2Cell29.setCellValue("สารเคมีที่ใช้กำจัดวัชพืชครั้งที่ 1");
		Cell header2Cell31 = headerRow2.createCell(25);
		header2Cell31.setCellValue("เดือนที่กำจัดวัชพืชครั้งที่ 2");
		Cell header2Cell32 = headerRow2.createCell(26);
		header2Cell32.setCellValue("สารเคมีที่ใช้กำจัดวัชพืชครั้งที่ 2");
		Cell header2Cell34 = headerRow2.createCell(27);
		header2Cell34.setCellValue("เดือนที่กำจัดวัชพืชครั้งที่ 3");
		Cell header2Cell35 = headerRow2.createCell(28);
		header2Cell35.setCellValue("สารเคมีที่ใช้กำจัดวัชพืชครั้งที่ 3");
		Cell header2Cell37 = headerRow2.createCell(29);
		header2Cell37.setCellValue("วิธีการจัดการโรค");
		Cell header2Cell38 = headerRow2.createCell(30);
		header2Cell38.setCellValue("วิธีการจัดการแมลงศัตรู");
		Cell header2Cell39 = headerRow2.createCell(31);
		header2Cell39.setCellValue("หมายเหตุ");

		// Create a new sheet3
		Sheet sheet3 = workbook.createSheet("การสำรวจ");
		Row headerRow3 = sheet3.createRow(0);
		Cell header3Cell1 = headerRow3.createCell(0);
		header3Cell1.setCellValue("รหัสแปลง");
		Cell header3Cell2 = headerRow3.createCell(1);
		header3Cell2.setCellValue("รหัสการเพาะปลูก");
		Cell header3Cell3 = headerRow3.createCell(2);
		header3Cell3.setCellValue("วันที่สำรวจ");
		Cell header3Cell4 = headerRow3.createCell(3);
		header3Cell4.setCellValue("พืชแปลงข้างเคียง");
		Cell header3Cell5 = headerRow3.createCell(4);
		header3Cell5.setCellValue("วัชพืชหลักที่พบ");
		Cell header3Cell6 = headerRow3.createCell(5);
		header3Cell6.setCellValue("อุณหภูมิ");
		Cell header3Cell7 = headerRow3.createCell(6);
		header3Cell7.setCellValue("ความชื้นสัมพัทธ์");
		Cell header3Cell8 = headerRow3.createCell(7);
		header3Cell8.setCellValue("สภาพฝน");
		Cell header3Cell9 = headerRow3.createCell(8);
		header3Cell9.setCellValue("สภาพแดด");
		Cell header3Cell10 = headerRow3.createCell(9);
		header3Cell10.setCellValue("สภาพน้ำค้าง");
		Cell header3Cell11 = headerRow3.createCell(10);
		header3Cell11.setCellValue("ประเภทดิน");
		Cell header3Cell12 = headerRow3.createCell(11);
		header3Cell12.setCellValue("%ความเสียหายจากสารเคมีกำจัดวัชพืช");
		Cell header3Cell13 = headerRow3.createCell(12);
		header3Cell13.setCellValue("หมายเหตุ");
		Cell header3Cell14 = headerRow3.createCell(13);
		header3Cell14.setCellValue("ชื่อเจ้าของภาพ");
		Cell header3Cell15 = headerRow3.createCell(14);
		header3Cell15.setCellValue("ชื่อผู้ถ่ายภาพ");
		Cell header3Cell16 = headerRow3.createCell(15);
		header3Cell16.setCellValue("ประเภท(โรค/แมลงศัตรูพืช/ศัตรูธรรมชาติ)");
		Cell header3Cell17 = headerRow3.createCell(16);
		header3Cell17.setCellValue("ชื่อสิ่งสำรวจ");
		Cell header3Cell18 = headerRow3.createCell(17);
		header3Cell18.setCellValue("% การพบ");
		Cell header3Cell19 = headerRow3.createCell(18);
		header3Cell19.setCellValue("ระดับความรุนแรง");
		int num = 19;
		for (int j = 1; j <= 100; j++, num++) {
			String cell = "#" + j;
			Cell create = headerRow3.createCell(num);
			create.setCellValue(cell);
		}
		// Create a new sheet4
		Sheet sheet4 = workbook.createSheet("รวม");
		Row headerRow4 = sheet4.createRow(0);
		Cell header4Cell1 = headerRow4.createCell(0);
		header4Cell1.setCellValue("รหัสแปลง");
		Cell header4Cell2 = headerRow4.createCell(1);
		header4Cell2.setCellValue("ชื่อแปลง");
		Cell header4Cell3 = headerRow4.createCell(2);
		header4Cell3.setCellValue("หน่วยงาน");
		Cell header4Cell4 = headerRow4.createCell(3);
		header4Cell4.setCellValue("เกษตรกรเจ้าของแปลง");
		Cell header4Cell5 = headerRow4.createCell(4);
		header4Cell5.setCellValue("ที่อยู่แปลง");
		Cell header4Cell6 = headerRow4.createCell(5);
		header4Cell6.setCellValue("ถนน");
		Cell header4Cell7 = headerRow4.createCell(6);
		header4Cell7.setCellValue("หมู่");
		Cell header4Cell8 = headerRow4.createCell(7);
		header4Cell8.setCellValue("ตำบล");
		Cell header4Cell9 = headerRow4.createCell(8);
		header4Cell9.setCellValue("อำเภอ");
		Cell header4Cell10 = headerRow4.createCell(9);
		header4Cell10.setCellValue("จังหวัด");
		Cell header4Cell11 = headerRow4.createCell(10);
		header4Cell11.setCellValue("จุดสังเกตที่อยู่แปลง");
		Cell header4Cell12 = headerRow4.createCell(11);
		header4Cell12.setCellValue("ละติจูด");
		Cell header4Cell13 = headerRow4.createCell(12);
		header4Cell13.setCellValue("ลองจิจูด");
		Cell header4Cell14 = headerRow4.createCell(13);
		header4Cell14.setCellValue("ความสูงจากระดับน้ำทะเล");
		Cell header4Cell15 = headerRow4.createCell(14);
		header4Cell15.setCellValue("ขนาดแปลง");
		Cell header4Cell16 = headerRow4.createCell(15);
		header4Cell16.setCellValue("เจ้าหน้าที่ผู้รับผิดชอบแปลง");
		Cell header4Cell17 = headerRow4.createCell(16);
		header4Cell17.setCellValue("รหัสแปลง");
		Cell header4Cell18 = headerRow4.createCell(17);
		header4Cell18.setCellValue("รหัสการเพาะปลูก");
		Cell header4Cell19 = headerRow4.createCell(18);
		header4Cell19.setCellValue("ชื่อการเพาะปลูก");
		Cell header4Cell20 = headerRow4.createCell(19);
		header4Cell20.setCellValue("ขนาดแปลง");
		Cell header4Cell21 = headerRow4.createCell(20);
		header4Cell21.setCellValue("พืชที่ปลูกก่อนหน้า");
		Cell header4Cell22 = headerRow4.createCell(21);
		header4Cell22.setCellValue("ชนิดพืชแปลงข้างเคียง");
		Cell header4Cell23 = headerRow4.createCell(22);
		header4Cell23.setCellValue("พันธุ์มันสำปะหลัง");
		Cell header4Cell24 = headerRow4.createCell(23);
		header4Cell24.setCellValue("วันที่ปลูก");
		Cell header4Cell25 = headerRow4.createCell(24);
		header4Cell25.setCellValue("วันที่เก็บเกี่ยว");
		Cell header4Cell26 = headerRow4.createCell(25);
		header4Cell26.setCellValue("พืชรอง");
		Cell header4Cell27 = headerRow4.createCell(26);
		header4Cell27.setCellValue("พันธุ์พืชรอง");
		Cell header4Cell28 = headerRow4.createCell(27);
		header4Cell28.setCellValue("วันที่ปลูกพืชรอง");
		Cell header4Cell29 = headerRow4.createCell(28);
		header4Cell29.setCellValue("วันที่เก็บเกี่ยวพืชรอง");
		Cell header4Cell30 = headerRow4.createCell(29);
		header4Cell30.setCellValue("จำนวนครั้งการไถเตรียมแปลง");
		Cell header4Cell31 = headerRow4.createCell(30);
		header4Cell31.setCellValue("การใช้วัสดุปรับปรุงดิน");
		Cell header4Cell32 = headerRow4.createCell(31);
		header4Cell32.setCellValue("แหล่งท่อนพันธุ์");
		Cell header4Cell33 = headerRow4.createCell(32);
		header4Cell33.setCellValue("สารเคมีกำจัดแมลงสำหรับแช่ท่อนพันธุ์ก่อนปลูก");
		Cell header4Cell34 = headerRow4.createCell(33);
		header4Cell34.setCellValue("วันที่ใส่ปุ๋ยครั้งที่ 1");
		Cell header4Cell35 = headerRow4.createCell(34);
		header4Cell35.setCellValue("ปุ๋ยเกรดที่ใช้ครั้งที่ 1");
		Cell header4Cell36 = headerRow4.createCell(35);
		header4Cell36.setCellValue("วันที่ใส่ปุ๋ยครั้งที่ 2");
		Cell header4Cell37 = headerRow4.createCell(36);
		header4Cell37.setCellValue("ปุ๋ยเกรดที่ใช้ครั้งที่ 2");
		Cell header4Cell38 = headerRow4.createCell(37);
		header4Cell38.setCellValue("วันที่ใส่ปุ๋ยครั้งที่ 3");
		Cell header4Cell39 = headerRow4.createCell(38);
		header4Cell39.setCellValue("ปุ๋ยเกรดที่ใช้ครั้งที่ 3");
		Cell header4Cell40 = headerRow4.createCell(39);
		header4Cell40.setCellValue("เดือนที่กำจัดวัชพืชครั้งที่ 1 ");
		Cell header4Cell41 = headerRow4.createCell(40);
		header4Cell41.setCellValue("สารเคมีที่ใช้กำจัดวัชพืชครั้งที่ 1");
		Cell header4Cell42 = headerRow4.createCell(41);
		header4Cell42.setCellValue("เดือนที่กำจัดวัชพืชครั้งที่ 2");
		Cell header4Cell43 = headerRow4.createCell(42);
		header4Cell43.setCellValue("สารเคมีที่ใช้กำจัดวัชพืชครั้งที่ 2");
		Cell header4Cell44 = headerRow4.createCell(43);
		header4Cell44.setCellValue("เดือนที่กำจัดวัชพืชครั้งที่ 3");
		Cell header4Cell45 = headerRow4.createCell(44);
		header4Cell45.setCellValue("สารเคมีที่ใช้กำจัดวัชพืชครั้งที่ 3");
		Cell header4Cell46 = headerRow4.createCell(45);
		header4Cell46.setCellValue("วิธีการจัดการโรค");
		Cell header4Cell47 = headerRow4.createCell(46);
		header4Cell47.setCellValue("วิธีการจัดการแมลงศัตรู");
		Cell header4Cell48 = headerRow4.createCell(47);
		header4Cell48.setCellValue("หมายเหตุ");
		Cell header4Cell56 = headerRow4.createCell(48);
		header4Cell56.setCellValue("รหัสแปลง");
		Cell header4Cell57 = headerRow4.createCell(49);
		header4Cell57.setCellValue("รหัสการเพาะปลูก");
		Cell header4Cell58 = headerRow4.createCell(50);
		header4Cell58.setCellValue("วันที่สำรวจ");
		Cell header4Cell59 = headerRow4.createCell(51);
		header4Cell59.setCellValue("พืชแปลงข้างเคียง");
		Cell header4Cell60 = headerRow4.createCell(52);
		header4Cell60.setCellValue("วัชพืชหลักที่พบ");
		Cell header4Cell61 = headerRow4.createCell(53);
		header4Cell61.setCellValue("อุณหภูมิ");
		Cell header4Cell62 = headerRow4.createCell(54);
		header4Cell62.setCellValue("ความชื้นสัมพัทธ์");
		Cell header4Cell63 = headerRow4.createCell(55);
		header4Cell63.setCellValue("สภาพฝน");
		Cell header4Cell64 = headerRow4.createCell(56);
		header4Cell64.setCellValue("สภาพแดด");
		Cell header4Cell65 = headerRow4.createCell(57);
		header4Cell65.setCellValue("สภาพน้ำค้าง");
		Cell header4Cell66 = headerRow4.createCell(58);
		header4Cell66.setCellValue("ประเภทดิน");
		Cell header4Cell67 = headerRow4.createCell(59);
		header4Cell67.setCellValue("%ความเสียหายจากสารเคมีกำจัดวัชพืช");
		Cell header4Cell68 = headerRow4.createCell(60);
		header4Cell68.setCellValue("หมายเหตุ");
		Cell header4Cell69 = headerRow4.createCell(61);
		header4Cell69.setCellValue("ชื่อเจ้าของภาพ");
		Cell header4Cell70 = headerRow4.createCell(62);
		header4Cell70.setCellValue("ชื่อผู้ถ่ายภาพ");
		Cell header4Cell71 = headerRow4.createCell(63);
		header4Cell71.setCellValue("ประเภท(โรค/แมลงศัตรูพืช/ศัตรูธรรมชาติ)");
		Cell header4Cell72 = headerRow4.createCell(64);
		header4Cell72.setCellValue("ชื่อสิ่งสำรวจ");
		Cell header4Cell73 = headerRow4.createCell(65);
		header4Cell73.setCellValue("% การพบ");
		Cell header4Cell74 = headerRow4.createCell(66);
		header4Cell74.setCellValue("ระดับความรุนแรง");
		num = 67;
		for (int j = 1; j <= 100; j++, num++) {
			String cell = "#" + j;
			Cell create = headerRow4.createCell(num);
			create.setCellValue(cell);
		}

		Sheet sheet5 = workbook.createSheet("สรุป");
		num = 0;
		// List<Row> listrow5 = new ArrayList<Row>() ;
		Row row5_0 = sheet5.createRow(num++);
		Row row5_1 = sheet5.createRow(num++);
		Cell header5Cell1 = row5_1.createCell(0);
		header5Cell1.setCellValue("จำนวนแปลงที่สำรวจทั้งหมด");
		Row row5_2 = sheet5.createRow(num++);
		Cell header5Cell2 = row5_2.createCell(0);
		header5Cell2.setCellValue("จำนวนแปลงที่พบโรค / สิ่งสำรวจ");
		Row row5_3 = sheet5.createRow(num++);
		Cell header5Cell3 = row5_3.createCell(0);
		header5Cell3.setCellValue("รวมพื้นที่สำรวจ (ไร่)");
		Row row5_4 = sheet5.createRow(num++);
		Cell header5Cell4 = row5_4.createCell(0);
		header5Cell4.setCellValue("รวมพื้นที่พบโรค / สิ่งสำรวจ (ไร่)");
		Row row5_5 = sheet5.createRow(num++);
		Cell header5Cell5 = row5_5.createCell(0);
		header5Cell5.setCellValue("เปอร์เซ็นต์พื้นที่พบโรค");
		Row row5_6 = sheet5.createRow(num++);
		Cell header5Cell6 = row5_6.createCell(0);
		header5Cell6.setCellValue("เปอร์เซ็นต์การเกิดความผิดปกติ (จากทุกแปลง)");
		Row row5_7 = sheet5.createRow(num++);
		Cell header5Cell7 = row5_7.createCell(0);
		header5Cell7.setCellValue("เปอร์เซ็นต์การเกิดความผิดปกติ (จากแปลงที่พบโรค)");
		Row row5_8 = sheet5.createRow(num++);
		Cell header5Cell8 = row5_8.createCell(0);
		header5Cell8.setCellValue("ระดับการเกิดโรคเฉลี่ย (จากทุกแปลง)");
		Row row5_9 = sheet5.createRow(num++);
		Cell header5Cell9 = row5_9.createCell(0);
		header5Cell9.setCellValue("ระดับการเกิดโรคเฉลี่ย (จากแปลงที่พบโรค)");
		Row row5_12 = sheet5.createRow(num++);
		Cell header5Cell12 = row5_12.createCell(0);
		header5Cell12.setCellValue("");
		Row row5_13 = sheet5.createRow(num++);
		Cell header5Cell13 = row5_13.createCell(0);
		header5Cell13.setCellValue("พื้นที่ปลูก");
		Row row5_14 = sheet5.createRow(num++);
		Cell header5Cell14 = row5_14.createCell(0);
		header5Cell14.setCellValue("สำรวจ 1 เปอร์เซ็นต์ของพื้นที่ปลูก");
		Row row5_15 = sheet5.createRow(num++);
		Cell header5Cell15 = row5_15.createCell(0);
		header5Cell15.setCellValue("เปอร์เซ็นต์การระบาดทั้งจังหวัด");

		SimpleDateFormat formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));

		// Add the data to the sheet
		int rowNum = 1;
		List<Field> fields = requestdetailService.findFieldByRequestId(rdid);
		
		for (Field field : fields) {
			int fid = field.getFieldId();
			String name = userInFieldService.findByFieldIdAndRoleName(fid).getUser().getFirstName() + " "
					+ userInFieldService.findByFieldIdAndRoleName(fid).getUser().getLastName();
			List<String> role = new ArrayList<String>();
			role.add("staffResponse");
			role.add("staffSurvey");
			List<UserInField> user = userInFieldService.findAllByRolenameAndFieldId(fid, role);
			String Latitude = " ";
			String Longitude = " ";
			if (field.getLatitude() != null) {
			    Latitude = field.getLatitude().toString() ;
			}
			if (field.getLongitude() != null) {
				Longitude = field.getLongitude().toString() ;
			} 
			
			// Create data sheet1
			Row row = sheet1.createRow(rowNum++);
			Cell Cell1 = row.createCell(0);
			Cell1.setCellValue(field.getFieldId());
			Cell Cell2 = row.createCell(1);
			Cell2.setCellValue(field.getName());
			Cell Cell3 = row.createCell(2);
			Cell3.setCellValue(field.getOrganization().getName());
			Cell Cell4 = row.createCell(3);
			Cell4.setCellValue(name);
			Cell Cell5 = row.createCell(4);
			Cell5.setCellValue(field.getAddress());
			Cell Cell6 = row.createCell(5);
			Cell6.setCellValue(field.getRoad());
			Cell Cell7 = row.createCell(6);
			Cell7.setCellValue(field.getMoo().toString());
			Cell Cell8 = row.createCell(7);
			Cell8.setCellValue(field.getSubdistrict().getName());
			Cell Cell9 = row.createCell(8);
			Cell9.setCellValue(field.getSubdistrict().getDistrict().getName());
			Cell Cell10 = row.createCell(9);
			Cell10.setCellValue(field.getSubdistrict().getDistrict().getProvince().getName());
			
			Cell Cell11 = row.createCell(10);
			Cell11.setCellValue(field.getLandmark().toString());
			Cell Cell12 = row.createCell(11);
			Cell12.setCellValue(Latitude);
			Cell Cell13 = row.createCell(12);
			Cell13.setCellValue(Longitude);
			
			Cell Cell14 = row.createCell(13);
			Cell14.setCellValue(field.getMetresAboveSeaLv());
			Cell Cell15 = row.createCell(14);
			Cell15.setCellValue(field.getSize());
			String nameu = "";
			int k = 0;
			if (!user.isEmpty()) {
				for (UserInField u3 : user) {
					String namere = u3.getUser().getFirstName() + " " + u3.getUser().getLastName();
					nameu += namere;
					if (user.size() - 1 != k) {
						nameu += " , ";
						k++;
					}
				}

			}
			Cell Cell16 = row.createCell(15);
			Cell16.setCellValue(nameu);
			
		}
		
		
		List<Planting> plantings = requestdetailService.findPlatingdByRequestId(rdid);
		int rowNum2 = 1;
		for (Planting planting : plantings) {
			List<PlantingCassavaVariety> pvv = plantingCassavaVarietyService.findByPlantingId(planting.getPlantingId());
			Row sheet2row = sheet2.createRow(rowNum2++);
			Cell sheet2Cell1 = sheet2row.createCell(0);
			if (planting.getField().getCode() != null) {
				sheet2Cell1.setCellValue(planting.getField().getCode());
			} else {
				sheet2Cell1.setCellValue("-");
			}

			Cell sheet2Cell2 = sheet2row.createCell(1);
			sheet2Cell2.setCellValue(planting.getCode());
			Cell sheet2Cell3 = sheet2row.createCell(2);
			sheet2Cell3.setCellValue(planting.getName());
			Cell sheet2Cell4 = sheet2row.createCell(3);
			sheet2Cell4.setCellValue(planting.getSize());
			Cell sheet2Cell5 = sheet2row.createCell(4);
			if (!planting.getPreviousPlant().equals("อื่นๆ")) {
				sheet2Cell5.setCellValue(planting.getPreviousPlant());
			} else {
				sheet2Cell5.setCellValue(planting.getPreviousPlantOther());
			}
			Cell sheet2Cell7 = sheet2row.createCell(5);

			if (!planting.getBesidePlant().equals("อื่นๆ")) {
				sheet2Cell7.setCellValue(planting.getBesidePlant());
			} else {
				sheet2Cell7.setCellValue(planting.getBesidePlantOther());
			}
			Cell sheet2Cell9 = sheet2row.createCell(6);
			int k = 0;
			String st = "";
			for (PlantingCassavaVariety pvv2 : pvv) {
				st += pvv2.getVariety().getName();
				if (pvv.size() - 1 != k) {
					st += " , ";
					k++;
				}
			}
			sheet2Cell9.setCellValue(st);
			st = "";

			Cell sheet2Cell11 = sheet2row.createCell(7);
			sheet2Cell11.setCellValue(formatter.format(planting.getPrimaryPlantPlantingDate()));
			Cell sheet2Cell12 = sheet2row.createCell(8);
			Date PrimaryPlantHarvestDate = planting.getPrimaryPlantHarvestDate();
			if (PrimaryPlantHarvestDate != null) {
				sheet2Cell12.setCellValue(formatter.format(planting.getPrimaryPlantHarvestDate()));
			} else {
				sheet2Cell12.setCellValue("-");
			}

			Cell sheet2Cell13 = sheet2row.createCell(9);
			sheet2Cell13.setCellValue(planting.getSecondaryPlantVariety());
			Cell sheet2Cell14 = sheet2row.createCell(10);
			sheet2Cell14.setCellValue(planting.getSecondaryPlantType());
			Cell sheet2Cell15 = sheet2row.createCell(11);
			Date getSecondaryPlantPlantingDate = planting.getSecondaryPlantPlantingDate();
			if (getSecondaryPlantPlantingDate != null) {
				sheet2Cell15.setCellValue(formatter.format(planting.getSecondaryPlantPlantingDate()));
			} else {
				sheet2Cell15.setCellValue("-");
			}

			Cell sheet2Cell16 = sheet2row.createCell(12);
			Date getSecondaryPlantHarvestDate = planting.getSecondaryPlantHarvestDate();
			if (getSecondaryPlantHarvestDate != null) {
				sheet2Cell16.setCellValue(formatter.format(planting.getSecondaryPlantHarvestDate()));
			} else {
				sheet2Cell16.setCellValue("-");
			}

			Cell sheet2Cell17 = sheet2row.createCell(13);
			sheet2Cell17.setCellValue(planting.getNumTillage());
			Cell sheet2Cell18 = sheet2row.createCell(14);
			if (!planting.getSoilAmendments().equals("อื่นๆ")) {
				sheet2Cell18.setCellValue(planting.getSoilAmendments());
			} else {
				sheet2Cell18.setCellValue(planting.getSoilAmendmentsOther());
			}
			Cell sheet2Cell20 = sheet2row.createCell(15);
			sheet2Cell20.setCellValue(planting.getStemSource());
			Cell sheet2Cell21 = sheet2row.createCell(16);
			sheet2Cell21.setCellValue(planting.getSoakingStemChemical());
			Cell sheet2Cell22 = sheet2row.createCell(17);

			Date getFertilizerDate1 = planting.getFertilizerDate1();
			if (getFertilizerDate1 != null) {
				sheet2Cell22.setCellValue(formatter.format(planting.getFertilizerDate1()));
			} else {
				sheet2Cell22.setCellValue("-");
			}

			Cell sheet2Cell23 = sheet2row.createCell(18);
			sheet2Cell23.setCellValue(planting.getFertilizerFormular1());
			Cell sheet2Cell24 = sheet2row.createCell(19);

			Date getFertilizerDate2 = planting.getFertilizerDate2();
			if (getFertilizerDate2 != null) {
				sheet2Cell24.setCellValue(formatter.format(planting.getFertilizerDate2()));
			} else {
				sheet2Cell24.setCellValue("-");
			}

			Cell sheet2Cell25 = sheet2row.createCell(20);
			sheet2Cell25.setCellValue(planting.getFertilizerFormular2());
			Cell sheet2Cell26 = sheet2row.createCell(21);

			Date getFertilizerDate3 = planting.getFertilizerDate3();
			if (getFertilizerDate3 != null) {
				sheet2Cell26.setCellValue(formatter.format(planting.getFertilizerDate3()));
			} else {
				sheet2Cell26.setCellValue("-");
			}

			Cell sheet2Cell27 = sheet2row.createCell(22);
			sheet2Cell27.setCellValue(planting.getFertilizerFormular3());
			Cell sheet2Cell28 = sheet2row.createCell(23);

			if (planting.getWeedingMonth1() == null) {
				sheet2Cell28.setCellValue("-");
			} else {
				sheet2Cell28.setCellValue(planting.getWeedingMonth1());
			}

			Cell sheet2Cell29 = sheet2row.createCell(24);
			Herbicide HerbicideByWeedingChemical1 = planting.getHerbicideByWeedingChemical1();
			if (HerbicideByWeedingChemical1 == null) {
				sheet2Cell29.setCellValue("-");
			} else if (!planting.getHerbicideByWeedingChemical1().getName().equals("อื่นๆ")) {
				sheet2Cell29.setCellValue(planting.getHerbicideByWeedingChemical1().getName());
			} else {
				sheet2Cell29.setCellValue(planting.getWeedingChemicalOther1());
			}
			Cell sheet2Cell31 = sheet2row.createCell(25);
			if (planting.getWeedingMonth2() == null) {
				sheet2Cell31.setCellValue("-");
			} else {
				sheet2Cell31.setCellValue(planting.getWeedingMonth2());
			}

			Cell sheet2Cell32 = sheet2row.createCell(26);
			Herbicide HerbicideByWeedingChemical2 = planting.getHerbicideByWeedingChemical2();
			if (HerbicideByWeedingChemical2 == null) {
				sheet2Cell32.setCellValue("-");
			} else if (!planting.getHerbicideByWeedingChemical2().getName().equals("อื่นๆ")) {
				sheet2Cell32.setCellValue(planting.getHerbicideByWeedingChemical2().getName());
			} else {
				sheet2Cell32.setCellValue(planting.getWeedingChemicalOther2());
			}

			Cell sheet2Cell34 = sheet2row.createCell(27);
			if (planting.getWeedingMonth3() == null) {
				sheet2Cell34.setCellValue("-");
			} else {
				sheet2Cell34.setCellValue(planting.getWeedingMonth3());
			}

			Cell sheet2Cell35 = sheet2row.createCell(28);

			Herbicide HerbicideByWeedingChemical3 = planting.getHerbicideByWeedingChemical3();
			if (HerbicideByWeedingChemical3 == null) {
				sheet2Cell32.setCellValue("-");
			} else if (!planting.getHerbicideByWeedingChemical3().getName().equals("อื่นๆ")) {
				sheet2Cell35.setCellValue(planting.getHerbicideByWeedingChemical3().getName());
			} else {
				sheet2Cell35.setCellValue(planting.getWeedingChemicalOther3());
			}

			Cell sheet2Cell37 = sheet2row.createCell(29);
			sheet2Cell37.setCellValue(planting.getDiseaseManagement());
			Cell sheet2Cell38 = sheet2row.createCell(30);
			sheet2Cell38.setCellValue(planting.getPestManagement());
			Cell sheet2Cell39 = sheet2row.createCell(31);
			sheet2Cell39.setCellValue(planting.getNote());
		}

		// Create data sheet3
		int rowNum3 = 1;
		int sidold = 0;
		String fcode = " ";
		String pcode = " ";
		String sdate = " ";
		String besideplant = " ";
		String weed = " ";
		String tem = " ";
		String humidity = " ";
		String rain = " ";
		String sunlight = " ";
		String dew = " ";
		String soil = " ";
		String damage = " ";
		String note = " ";
		String imgOwner = " ";
		String imgphotographer = " ";
		String fname = " ";
		String organization = " ";
		String name = " ";
		String address = " ";
		String road = " ";
		String moo = " ";
		String subdistrict = " ";
		String district = " ";
		String province = " ";
		String landmark = " ";
		String latitude = " ";
		String longitude = " ";
		String metres = " ";
		String size = " ";
		String nameu = " ";
		String pname = " ";
		String psize = " ";
		String previousplant = " ";
		String casavavi = " ";
		String primaryplantingdate = " ";
		String primaryplantharvest = " ";
		String secondaryvariety = " ";
		String secondaryvarietytype = " ";
		String secondaryplantingdate = " ";
		String secondaryharvestdate = " ";
		String numtillage = " ";
		String soilamendments = " ";
		String stemsource = " ";
		String stemchemical = " ";
		String fertilizerdate1 = " ";
		String fertilizerdate2 = " ";
		String fertilizerdate3 = " ";
		String formular1 = " ";
		String formular2 = " ";
		String formular3 = " ";
		String weeding1 = " ";
		String weeding2 = " ";
		String weeding3 = " ";
		String herbicideweeding1 = " ";
		String herbicideweeding2 = " ";
		String herbicideweeding3 = " ";
		String diseasemanagement = " ";
		String pestmanagement = " ";
		String snote = " ";

		 
		List<RequestDetail> reds = requestdetailService.findByRequestId(rdid);
		for (RequestDetail rqd : reds) {
			int sidnew = rqd.getSurveyTarget().getSurvey().getSurveyId();
			if (sidnew != sidold) {
				// sheet3
				 fcode = " ";
				 pcode = " ";
				 sdate = " ";
				 besideplant = " ";
				 weed = " ";
				 tem = " ";
				 humidity = " ";
				 rain = " ";
				 sunlight = " ";
				 dew = " ";
				 soil = " ";
				 damage = " ";
				 note = " ";
				 imgOwner = " ";
				 imgphotographer = " ";
				 fname = " ";
				 organization = " ";
				 name = " ";
				 address = " ";
				 road = " ";
				 moo = " ";
				 subdistrict = " ";
				 district = " ";
				 province = " ";
				 landmark = " ";
				 latitude = " ";
				 longitude = " ";
				 metres = " ";
				 size = " ";
				 nameu = " ";
				 pname = " ";
				 psize = " ";
				 previousplant = " ";
				 casavavi = " ";
				 primaryplantingdate = " ";
				 primaryplantharvest = " ";
				 secondaryvariety = " ";
				 secondaryvarietytype = " ";
				 secondaryplantingdate = " ";
				 secondaryharvestdate = " ";
				 numtillage = " ";
				 soilamendments = " ";
				 stemsource = " ";
				 stemchemical = " ";
				 fertilizerdate1 = " ";
				 fertilizerdate2 = " ";
				 fertilizerdate3 = " ";
				 formular1 = " ";
				 formular2 = " ";
				 formular3 = " ";
				 weeding1 = " ";
				 weeding2 = " ";
				 weeding3 = " ";
				 herbicideweeding1 = " ";
				 herbicideweeding2 = " ";
				 herbicideweeding3 = " ";
				 diseasemanagement = " ";
				 pestmanagement = " ";
				 snote = " ";
				fcode = rqd.getSurveyTarget().getSurvey().getPlanting().getField().getCode();
				pcode = rqd.getSurveyTarget().getSurvey().getPlanting().getCode();
				sdate = formatter.format(rqd.getSurveyTarget().getSurvey().getDate());
				if (!rqd.getSurveyTarget().getSurvey().getPlanting().getBesidePlant().equals("อื่นๆ")) {
					besideplant = rqd.getSurveyTarget().getSurvey().getPlanting().getBesidePlant();
				} else {
					besideplant = rqd.getSurveyTarget().getSurvey().getPlanting().getBesidePlantOther();
				}
				
				weed = rqd.getSurveyTarget().getSurvey().getWeed();
				tem = String.valueOf(rqd.getSurveyTarget().getSurvey().getTemperature());
				humidity = String.valueOf(rqd.getSurveyTarget().getSurvey().getHumidity());
				rain = rqd.getSurveyTarget().getSurvey().getRainType();
				sunlight = rqd.getSurveyTarget().getSurvey().getSunlightType();
				dew = rqd.getSurveyTarget().getSurvey().getDewType();
				soil = rqd.getSurveyTarget().getSurvey().getSoilType();
				damage = rqd.getSurveyTarget().getSurvey().getPercentDamageFromHerbicide();
				note = rqd.getSurveyTarget().getSurvey().getNote();
				if (rqd.getSurveyTarget().getSurvey().getUserByImgOwner() != null) {
					User user = rqd.getSurveyTarget().getSurvey().getUserByImgOwner();
					imgOwner = user.getFirstName() + " " + user.getLastName();
				} else {
					imgOwner = rqd.getSurveyTarget().getSurvey().getImgOwnerOther();
				}
				if (rqd.getSurveyTarget().getSurvey().getUserByImgPhotographer() != null) {
					User user = rqd.getSurveyTarget().getSurvey().getUserByImgPhotographer();
					imgphotographer = user.getFirstName() + " " + user.getLastName();
				} else {
					imgphotographer = rqd.getSurveyTarget().getSurvey().getImgPhotographerOther();
				}
				// sheet4
				
				fname = rqd.getSurveyTarget().getSurvey().getPlanting().getField().getName();
				organization = rqd.getSurveyTarget().getSurvey().getPlanting().getField().getOrganization().getName();
				int fid = rqd.getSurveyTarget().getSurvey().getPlanting().getField().getFieldId();
				name = userInFieldService.findByFieldIdAndRoleName(fid).getUser().getFirstName() + " "
						+ userInFieldService.findByFieldIdAndRoleName(fid).getUser().getLastName();
				address = rqd.getSurveyTarget().getSurvey().getPlanting().getField().getAddress();
				road = rqd.getSurveyTarget().getSurvey().getPlanting().getField().getRoad();
				moo = rqd.getSurveyTarget().getSurvey().getPlanting().getField().getMoo().toString();
				subdistrict = rqd.getSurveyTarget().getSurvey().getPlanting().getField().getSubdistrict().getName();
				district = rqd.getSurveyTarget().getSurvey().getPlanting().getField().getSubdistrict().getDistrict()
						.getName();
				province = rqd.getSurveyTarget().getSurvey().getPlanting().getField().getSubdistrict().getDistrict()
						.getProvince().getName();
				landmark = rqd.getSurveyTarget().getSurvey().getPlanting().getField().getLandmark();
				if (rqd.getSurveyTarget().getSurvey().getPlanting().getField().getLatitude() != null) {
					   latitude = rqd.getSurveyTarget().getSurvey().getPlanting().getField().getLatitude().toString() ;
				}
				if (rqd.getSurveyTarget().getSurvey().getPlanting().getField().getLongitude() != null) {
					longitude = rqd.getSurveyTarget().getSurvey().getPlanting().getField().getLongitude().toString() ;
				} 
				metres = String
						.valueOf(rqd.getSurveyTarget().getSurvey().getPlanting().getField().getMetresAboveSeaLv());
				size = String.valueOf(rqd.getSurveyTarget().getSurvey().getPlanting().getField().getSize());
				List<String> role = new ArrayList<String>();
				role.add("staffResponse");
				role.add("staffSurvey");
				List<UserInField> user = userInFieldService.findAllByRolenameAndFieldId(fid, role);
				int i = 0;
				nameu = "";
				if (!user.isEmpty()) {
					for (UserInField u3 : user) {
						String namere = u3.getUser().getFirstName() + " " + u3.getUser().getLastName();
						nameu += namere;
						if (user.size() - 1 != i) {
							nameu += " , ";
							i++;
						}
					}

				}
				
				pname = rqd.getSurveyTarget().getSurvey().getPlanting().getName();
				psize = String.valueOf(rqd.getSurveyTarget().getSurvey().getPlanting().getSize());
				if (!rqd.getSurveyTarget().getSurvey().getPlanting().getPreviousPlant().equals("อื่นๆ")) {
					previousplant = rqd.getSurveyTarget().getSurvey().getPlanting().getPreviousPlant();
				} else {
					previousplant = rqd.getSurveyTarget().getSurvey().getPlanting().getPreviousPlantOther();
				}
				List<PlantingCassavaVariety> pvv = plantingCassavaVarietyService
						.findByPlantingId(rqd.getSurveyTarget().getSurvey().getPlanting().getPlantingId());
				int k = 0;
				casavavi = "";
				for (PlantingCassavaVariety pvv2 : pvv) {
					casavavi += pvv2.getVariety().getName();
					if (pvv.size() - 1 != k) {
						casavavi += ",";
						k++;
					}
				}
				primaryplantingdate = formatter
						.format(rqd.getSurveyTarget().getSurvey().getPlanting().getPrimaryPlantPlantingDate());
				if(rqd.getSurveyTarget().getSurvey().getPlanting().getPrimaryPlantHarvestDate()!=null) {
						Date PrimaryPlantHarvestDate = rqd.getSurveyTarget().getSurvey().getPlanting()
						.getPrimaryPlantHarvestDate();
						primaryplantharvest = formatter
						.format(rqd.getSurveyTarget().getSurvey().getPlanting().getPrimaryPlantHarvestDate());
				}
			
				
				secondaryvariety = rqd.getSurveyTarget().getSurvey().getPlanting().getSecondaryPlantVariety();
				secondaryvarietytype = rqd.getSurveyTarget().getSurvey().getPlanting().getSecondaryPlantType();
				
				if(rqd.getSurveyTarget().getSurvey().getPlanting().getSecondaryPlantPlantingDate()!=null) {
					Date getSecondaryPlantPlantingDate = rqd.getSurveyTarget().getSurvey().getPlanting()
							.getSecondaryPlantPlantingDate();
					secondaryplantingdate = formatter
							.format(rqd.getSurveyTarget().getSurvey().getPlanting().getSecondaryPlantPlantingDate());
				}
				numtillage = rqd.getSurveyTarget().getSurvey().getPlanting().getNumTillage();
				if (!rqd.getSurveyTarget().getSurvey().getPlanting().getSoilAmendments().equals("อื่นๆ")) {
					soilamendments = rqd.getSurveyTarget().getSurvey().getPlanting().getSoilAmendments();
				} else {
					soilamendments = rqd.getSurveyTarget().getSurvey().getPlanting().getSoilAmendmentsOther();
				}
				stemsource = rqd.getSurveyTarget().getSurvey().getPlanting().getStemSource();
				stemchemical = rqd.getSurveyTarget().getSurvey().getPlanting().getSoakingStemChemical();
				if(rqd.getSurveyTarget().getSurvey().getPlanting().getFertilizerDate1()!=null) {
					Date getFertilizerDate1 = rqd.getSurveyTarget().getSurvey().getPlanting().getFertilizerDate1();
				fertilizerdate1 = formatter
						.format(rqd.getSurveyTarget().getSurvey().getPlanting().getFertilizerDate1());
				}
				if(rqd.getSurveyTarget().getSurvey().getPlanting().getFertilizerDate2()!=null) {
					fertilizerdate2 = formatter
						.format(rqd.getSurveyTarget().getSurvey().getPlanting().getFertilizerDate2());
				}
				if(rqd.getSurveyTarget().getSurvey().getPlanting().getFertilizerDate3()!=null) {
					fertilizerdate3 = formatter
							.format(rqd.getSurveyTarget().getSurvey().getPlanting().getFertilizerDate3());
				}
				
				formular1 = rqd.getSurveyTarget().getSurvey().getPlanting().getFertilizerFormular1();
				formular2 = rqd.getSurveyTarget().getSurvey().getPlanting().getFertilizerFormular2();
				formular3 = rqd.getSurveyTarget().getSurvey().getPlanting().getFertilizerFormular3();
					
				if(rqd.getSurveyTarget().getSurvey().getPlanting().getWeedingMonth1()!=null) {
					weeding1 = String.valueOf(rqd.getSurveyTarget().getSurvey().getPlanting().getWeedingMonth1()) ;
				}
				if(rqd.getSurveyTarget().getSurvey().getPlanting().getWeedingMonth2()!=null) {
					weeding2 = String.valueOf(rqd.getSurveyTarget().getSurvey().getPlanting().getWeedingMonth2()) ;
				}
				if(rqd.getSurveyTarget().getSurvey().getPlanting().getWeedingMonth3()!=null) {
					weeding3 = String.valueOf(rqd.getSurveyTarget().getSurvey().getPlanting().getWeedingMonth3()) ;
				}
				Herbicide HerbicideByWeedingChemical1 = rqd.getSurveyTarget().getSurvey().getPlanting().getHerbicideByWeedingChemical1();
				if (HerbicideByWeedingChemical1 == null) {
					
				} else if(!HerbicideByWeedingChemical1.getName().equals("อื่นๆ")) {
					herbicideweeding1 = HerbicideByWeedingChemical1.getName();
				}else if (HerbicideByWeedingChemical1.getName().equals("อื่นๆ")) {
					herbicideweeding1 = rqd.getSurveyTarget().getSurvey().getPlanting().getWeedingChemicalOther1() ;
				}
				
				Herbicide HerbicideByWeedingChemical2 = rqd.getSurveyTarget().getSurvey().getPlanting().getHerbicideByWeedingChemical2();
				if (HerbicideByWeedingChemical2 == null) {
					
				} else if(!HerbicideByWeedingChemical2.getName().equals("อื่นๆ")) {
					herbicideweeding2 =HerbicideByWeedingChemical2.getName();
				}else if (HerbicideByWeedingChemical2.getName().equals("อื่นๆ")) {
					herbicideweeding2 = rqd.getSurveyTarget().getSurvey().getPlanting().getWeedingChemicalOther2() ;
				}
				
				Herbicide HerbicideByWeedingChemical3 = rqd.getSurveyTarget().getSurvey().getPlanting().getHerbicideByWeedingChemical3();
				if (HerbicideByWeedingChemical3 == null) {
					
				} else if(!HerbicideByWeedingChemical3.getName().equals("อื่นๆ")) {
					herbicideweeding3 = HerbicideByWeedingChemical3.getName();
				}else if (HerbicideByWeedingChemical3.getName().equals("อื่นๆ")) {
					herbicideweeding3 = rqd.getSurveyTarget().getSurvey().getPlanting().getWeedingChemicalOther3() ;
				}
				
				
				diseasemanagement = rqd.getSurveyTarget().getSurvey().getPlanting().getDiseaseManagement();
				pestmanagement = rqd.getSurveyTarget().getSurvey().getPlanting().getPestManagement();
				snote = rqd.getSurveyTarget().getSurvey().getPlanting().getNote();

			}
			Row sheet3row = sheet3.createRow(rowNum3);
			Row sheet4row = sheet4.createRow(rowNum3++);

			Cell sheet3Cell1 = sheet3row.createCell(0);
			sheet3Cell1.setCellValue(fcode);
			Cell sheet3Cell2 = sheet3row.createCell(1);
			sheet3Cell2.setCellValue(pcode);
			Cell sheet3Cell3 = sheet3row.createCell(2);
			sheet3Cell3.setCellValue(sdate);
			Cell sheet3Cell4 = sheet3row.createCell(3);
			sheet3Cell4.setCellValue(besideplant);
			Cell sheet3Cell5 = sheet3row.createCell(4);
			sheet3Cell5.setCellValue(weed);
			Cell sheet3Cell6 = sheet3row.createCell(5);
			sheet3Cell6.setCellValue(tem);
			Cell sheet3Cell7 = sheet3row.createCell(6);
			sheet3Cell7.setCellValue(humidity);
			Cell sheet3Cell8 = sheet3row.createCell(7);
			sheet3Cell8.setCellValue(rain);
			Cell sheet3Cell9 = sheet3row.createCell(8);
			sheet3Cell9.setCellValue(sunlight);
			Cell sheet3Cell10 = sheet3row.createCell(9);
			sheet3Cell10.setCellValue(dew);
			Cell sheet3Cell11 = sheet3row.createCell(10);
			sheet3Cell11.setCellValue(soil);
			Cell sheet3Cell12 = sheet3row.createCell(11);
			sheet3Cell12.setCellValue(damage);

			Cell sheet3Cell13 = sheet3row.createCell(12);
			sheet3Cell13.setCellValue(note);
			Cell sheet3Cell14 = sheet3row.createCell(13);
			sheet3Cell14.setCellValue(imgOwner);
			Cell sheet3Cell15 = sheet3row.createCell(14);
			sheet3Cell15.setCellValue(imgphotographer);
			Cell sheet4Cell71 = sheet4row.createCell(63);
			Cell sheet3Cell16 = sheet3row.createCell(15);
			if (diseaseService.findByName(rqd.getSurveyTarget().getTargetofsurvey().getName()) != null) {
				sheet3Cell16.setCellValue("โรค");
				sheet4Cell71.setCellValue("โรค");
			} else if (naturalEnemyService.findByName(rqd.getSurveyTarget().getTargetofsurvey().getName()) != null) {
				sheet3Cell16.setCellValue("ศัตรูธรรมชาติ");
				sheet4Cell71.setCellValue("ศัตรูธรรมชาติ");
			} else if (pestPhaseSurveyService.findByName(rqd.getSurveyTarget().getTargetofsurvey().getName()) != null) {
				sheet3Cell16.setCellValue("แมลงศัตรูพืช");
				sheet4Cell71.setCellValue("แมลงศัตรูพืช");
			}

			Cell sheet3Cell17 = sheet3row.createCell(16);
			sheet3Cell17.setCellValue(rqd.getSurveyTarget().getTargetofsurvey().getName());
			Cell sheet3Cell18 = sheet3row.createCell(17);
			sheet3Cell18.setCellValue(rqd.getSurveyTarget().getPercentDamage());
			Cell sheet3Cell19 = sheet3row.createCell(18);
			sheet3Cell19.setCellValue(rqd.getSurveyTarget().getAvgDamageLevel());

			num = 19;
			int numtar2 = 67;
			List<SurveyTargetPoint> ss = rqd.getSurveyTarget().getSurveytargetpoints();
			for (SurveyTargetPoint surveyTargetPoint : ss) {
				Cell create = sheet3row.createCell(num++);
				create.setCellValue(surveyTargetPoint.getValue());
				Cell create2 = sheet4row.createCell(numtar2++);
				create2.setCellValue(surveyTargetPoint.getValue());
			}
			// sheet4
			Cell sheet4Cell1 = sheet4row.createCell(0);
			sheet4Cell1.setCellValue(fcode);
			Cell sheet4Cell2 = sheet4row.createCell(1);
			sheet4Cell2.setCellValue(fname);
			Cell sheet4Cell3 = sheet4row.createCell(2);
			sheet4Cell3.setCellValue(organization);
			Cell sheet4Cell4 = sheet4row.createCell(3);
			sheet4Cell4.setCellValue(name);
			Cell sheet4Cell5 = sheet4row.createCell(4);
			sheet4Cell5.setCellValue(address);
			Cell sheet4Cell6 = sheet4row.createCell(5);
			sheet4Cell6.setCellValue(road);
			Cell sheet4Cell7 = sheet4row.createCell(6);
			sheet4Cell7.setCellValue(moo);
			Cell sheet4Cell8 = sheet4row.createCell(7);
			sheet4Cell8.setCellValue(subdistrict);
			Cell sheet4Cell9 = sheet4row.createCell(8);
			sheet4Cell9.setCellValue(district);
			Cell sheet4Cell10 = sheet4row.createCell(9);
			sheet4Cell10.setCellValue(province);
			Cell sheet4Cell11 = sheet4row.createCell(10);
			sheet4Cell11.setCellValue(landmark);
			Cell sheet4Cell12 = sheet4row.createCell(11);
			sheet4Cell12.setCellValue(latitude);
			Cell sheet4Cell13 = sheet4row.createCell(12);
			sheet4Cell13.setCellValue(longitude);
			Cell sheet4Cell14 = sheet4row.createCell(13);
			sheet4Cell14.setCellValue(metres);
			Cell sheet4Cell15 = sheet4row.createCell(14);
			sheet4Cell15.setCellValue(size);
			Cell Cell16 = sheet4row.createCell(15);
			Cell16.setCellValue(nameu);
			Cell sheet2Cell1 = sheet4row.createCell(16);
			sheet2Cell1.setCellValue(fcode);
			Cell sheet2Cell2 = sheet4row.createCell(17);
			sheet2Cell2.setCellValue(pcode);
			Cell sheet2Cell3 = sheet4row.createCell(18);
			sheet2Cell3.setCellValue(pname);
			Cell sheet2Cell4 = sheet4row.createCell(19);
			sheet2Cell4.setCellValue(psize);
			Cell sheet2Cell5 = sheet4row.createCell(20);
			sheet2Cell5.setCellValue(previousplant);
			Cell sheet2Cell7 = sheet4row.createCell(21);
			sheet2Cell7.setCellValue(besideplant);
			Cell sheet2Cell9 = sheet4row.createCell(22);
			sheet2Cell9.setCellValue(casavavi);
			Cell sheet2Cell11 = sheet4row.createCell(23);
			sheet2Cell11.setCellValue(primaryplantingdate);
			Cell sheet2Cell12 = sheet4row.createCell(24);
			sheet2Cell12.setCellValue(primaryplantharvest);
			Cell sheet2Cell13 = sheet4row.createCell(25);
			sheet2Cell13.setCellValue(secondaryvariety);
			Cell sheet2Cell14 = sheet4row.createCell(26);
			sheet2Cell14.setCellValue(secondaryvarietytype);
			Cell sheet2Cell15 = sheet4row.createCell(27);
			sheet2Cell15.setCellValue(secondaryplantingdate);
			Cell sheet2Cell16 = sheet4row.createCell(28);
			sheet2Cell16.setCellValue(secondaryharvestdate);
			Cell sheet2Cell17 = sheet4row.createCell(29);
			sheet2Cell17.setCellValue(numtillage);
			Cell sheet2Cell18 = sheet4row.createCell(30);
			sheet2Cell18.setCellValue(soilamendments);
			Cell sheet2Cell20 = sheet4row.createCell(31);
			sheet2Cell20.setCellValue(stemsource);
			Cell sheet2Cell21 = sheet4row.createCell(32);
			sheet2Cell21.setCellValue(stemchemical);
			Cell sheet2Cell22 = sheet4row.createCell(33);
			sheet2Cell22.setCellValue(fertilizerdate1);
			Cell sheet2Cell23 = sheet4row.createCell(34);
			sheet2Cell23.setCellValue(formular1);
			Cell sheet2Cell24 = sheet4row.createCell(35);
			sheet2Cell24.setCellValue(fertilizerdate2);
			Cell sheet2Cell25 = sheet4row.createCell(36);
			sheet2Cell25.setCellValue(formular2);
			Cell sheet2Cell26 = sheet4row.createCell(37);
			sheet2Cell26.setCellValue(fertilizerdate3);
			Cell sheet2Cell27 = sheet4row.createCell(38);
			sheet2Cell27.setCellValue(formular3);
			Cell sheet2Cell28 = sheet4row.createCell(39);
			sheet2Cell28.setCellValue(weeding1);
			Cell sheet2Cell29 = sheet4row.createCell(40);
			sheet2Cell29.setCellValue(herbicideweeding1);
			Cell sheet2Cell31 = sheet4row.createCell(41);
			sheet2Cell31.setCellValue(weeding2);
			Cell sheet2Cell32 = sheet4row.createCell(42);
			sheet2Cell32.setCellValue(herbicideweeding2);
			Cell sheet2Cell34 = sheet4row.createCell(43);
			sheet2Cell34.setCellValue(weeding3);
			Cell sheet2Cell35 = sheet4row.createCell(44);
			sheet2Cell35.setCellValue(herbicideweeding3);
			Cell sheet2Cell37 = sheet4row.createCell(45);
			sheet2Cell37.setCellValue(diseasemanagement);
			Cell sheet2Cell38 = sheet4row.createCell(46);
			sheet2Cell38.setCellValue(pestmanagement);
			Cell sheet2Cell39 = sheet4row.createCell(47);
			sheet2Cell39.setCellValue(snote);
			Cell sheet4Cell56 = sheet4row.createCell(48);
			sheet4Cell56.setCellValue(fcode);
			Cell sheet4Cell57 = sheet4row.createCell(49);
			sheet4Cell57.setCellValue(pcode);
			Cell sheet4Cell58 = sheet4row.createCell(50);
			sheet4Cell58.setCellValue(sdate);
			Cell sheet4Cell59 = sheet4row.createCell(51);
			sheet4Cell59.setCellValue(besideplant);
			Cell sheet4Cell60 = sheet4row.createCell(52);
			sheet4Cell60.setCellValue(weed);
			Cell sheet4Cell61 = sheet4row.createCell(53);
			sheet4Cell61.setCellValue(tem);
			Cell sheet4Cell62 = sheet4row.createCell(54);
			sheet4Cell62.setCellValue(humidity);
			Cell sheet4Cell63 = sheet4row.createCell(55);
			sheet4Cell63.setCellValue(rain);
			Cell sheet4Cell64 = sheet4row.createCell(56);
			sheet4Cell64.setCellValue(sunlight);
			Cell sheet4Cell65 = sheet4row.createCell(57);
			sheet4Cell65.setCellValue(dew);
			Cell sheet4Cell66 = sheet4row.createCell(58);
			sheet4Cell66.setCellValue(soil);
			Cell sheet4Cell67 = sheet4row.createCell(59);
			sheet4Cell67.setCellValue(damage);
			Cell sheet4Cell68 = sheet4row.createCell(60);
			sheet4Cell68.setCellValue(note);
			Cell sheet4Cell69 = sheet4row.createCell(61);
			sheet4Cell69.setCellValue(imgOwner);
			Cell sheet4Cell70 = sheet4row.createCell(62);
			sheet4Cell70.setCellValue(imgphotographer);
			Cell sheet4Cell72 = sheet4row.createCell(64);
			sheet4Cell72.setCellValue(rqd.getSurveyTarget().getTargetofsurvey().getName());
			Cell sheet4Cell73 = sheet4row.createCell(65);
			sheet4Cell73.setCellValue(rqd.getSurveyTarget().getPercentDamage());
			Cell sheet4Cell74 = sheet4row.createCell(66);
			sheet4Cell74.setCellValue(rqd.getSurveyTarget().getAvgDamageLevel());

		}

		// Create data sheet4

		int columnNumber = 7;

		int numrow5 = 6;
		List<TargetOfSurvey> listtos = requestService.findtargetOfSurveyByRequestId(rdid);
		for (TargetOfSurvey tos : listtos) {

			int columnNumbernum = columnNumber;
			StringBuilder columnLabel = new StringBuilder();
			List<SurveyTarget> listsurtar = surveyTargetService.findByRequestIdAndTargetOfSurvey(rdid, tos);
			int countfield = surveyTargetService.findNumberOfFieldBySurveyTargets(listsurtar);
			int countfielddi = surveyTargetService.findNumberOfFieldDiseaseBySurveyTargets(listsurtar);
			Float fieldsize = surveyTargetService.findSizePlantingBySurveyTargets(listsurtar);
			Float fieldsizedi = surveyTargetService.findSizePlantingDiseaseBySurveyTargets(listsurtar);
			Float perdi = surveyTargetService.findPercentPlantingDiseaseBySurveyTargets(listsurtar);
			Float danagedi = surveyTargetService.findAvgDamageLevelDiseaseInALLFieldBySurveyTargets(listsurtar);
			Float perdiallfield = surveyTargetService
					.findAvgDamageLevelDiseaseInALLDiseaseFieldBySurveyTargets(listsurtar);
			Float perdialltos = surveyTargetService.findAvgPercentDamageInAllFieldBySurveyTargets(listsurtar);
			Float perdialldi = surveyTargetService.findAvgPercentDamageInAllDiseaseFieldBySurveyTargets(listsurtar);

			while (columnNumbernum > 0) {
				int remainder = (columnNumbernum - 1) % 26;
				char character = (char) (remainder + 'A');
				columnLabel.insert(0, character);
				columnNumbernum = (columnNumbernum - 1) / 26;
			}

			Cell sheet5Cell0 = row5_0.createCell(numrow5);
			sheet5Cell0.setCellValue(tos.getName());
			Cell sheet5Cell1 = row5_1.createCell(numrow5);
			sheet5Cell1.setCellValue(countfield);
			Cell sheet5Cell2 = row5_2.createCell(numrow5);
			sheet5Cell2.setCellValue(countfielddi);
			Cell sheet5Cell3 = row5_3.createCell(numrow5);
			sheet5Cell3.setCellValue(fieldsize);
			Cell sheet5Cell4 = row5_4.createCell(numrow5);
			sheet5Cell4.setCellValue(fieldsizedi);
			Cell sheet5Cell5 = row5_5.createCell(numrow5);
			sheet5Cell5.setCellValue(perdi);
			Cell sheet5Cell6 = row5_6.createCell(numrow5);
			sheet5Cell6.setCellValue(perdialltos);
			Cell sheet5Cell7 = row5_7.createCell(numrow5);
			sheet5Cell7.setCellValue(perdialldi);
			Cell sheet5Cell8 = row5_8.createCell(numrow5);
			sheet5Cell8.setCellValue(danagedi);

			Cell sheet5Cell9 = row5_9.createCell(numrow5);
			sheet5Cell9.setCellValue(perdiallfield);
			Cell sheet5Cell12 = row5_12.createCell(numrow5);
			sheet5Cell12.setCellValue("");
			Cell sheet5Cell13 = row5_13.createCell(numrow5);
			sheet5Cell13.setCellValue(0);
			Cell sheet5Cell14 = row5_14.createCell(numrow5);
			sheet5Cell14.setCellFormula("IFERROR(SUM(" + columnLabel.toString() + 12 + "*1)/100,0)");
			Cell sheet5Cell15 = row5_15.createCell(numrow5++);
			sheet5Cell15.setCellFormula("IFERROR((" + columnLabel + "5 / " + columnLabel + "13) * 100, 0)");
			columnNumber++;
		}

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=data.xlsx");
		OutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		outputStream.flush();
		outputStream.close();
	}

}
