package org.cassava.web.controller;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.cassava.api.util.model.Response;
import org.cassava.model.Field;
import org.cassava.model.Planting;
import org.cassava.model.PlantingCassavaVariety;
import org.cassava.model.PlantingCassavaVarietyId;
import org.cassava.model.User;
import org.cassava.model.Variety;
import org.cassava.model.dto.PlantingDTO;
import org.cassava.services.FieldService;
import org.cassava.services.HerbicideService;
import org.cassava.services.PlantService;
import org.cassava.services.PlantingCassavaVarietyService;
import org.cassava.services.PlantingService;
import org.cassava.services.UserService;
import org.cassava.services.VarietyService;
import org.cassava.util.MvcHelper;
import org.cassava.web.controller.binder.VarietyBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
public class PlantingController {

	@Value("${external.resoures.path}")
	private String externalPath;

	@Autowired
	private PlantingService plantingService;

	@Autowired
	private FieldService fieldService;

	@Autowired
	private PlantService plantService;

	@Autowired
	private VarietyService varietyService;

	@Autowired
	private UserService userService;

	@Autowired
	private HerbicideService herbicideService;

	@Autowired
	private VarietyBinder varietyBinder;

	@Autowired
	private PlantingCassavaVarietyService plantingCassavaVarietyService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAutoGrowCollectionLimit(1024);
		binder.registerCustomEditor(Variety.class, this.varietyBinder);

	}

	public void setPageIndex(int sizeItem, Model model, int pageCurrent, int pageSize) {
		int firstItem = 0, lastItem = 0;
		if (sizeItem > 0) {
			firstItem = (pageSize * (pageCurrent - 1)) + 1;
			lastItem = firstItem + sizeItem - 1;
		}
		model.addAttribute("firstItem", firstItem);
		model.addAttribute("lastItem", lastItem);
	}

	@RequestMapping(value = { "/planting/index", "/planting/", "/planting" }, method = RequestMethod.GET)
	public String plantingIndex(Model model, Authentication authentication) {

		model.addAttribute("sizePages", Arrays.asList(10, 25, 50));
		return "planting/plantingIndex";
	}

	@RequestMapping(value = "/planting/page/{page}/value/{value}", method = RequestMethod.GET)
	public String selectPlantingPage(@PathVariable("page") int page, @PathVariable("value") int value,
			@RequestParam(value = "key", required = false) String key,
			@RequestParam(value = "startDate", required = false) Date startDate,
			@RequestParam(value = "endDate", required = false) Date endDate, Authentication authentication,
			Model model) {
		User user = userService.findByUsername(MvcHelper.getUsername(authentication));

		List<Planting> plantings;
		if ((key == null || key == "") && (startDate == null || endDate == null)) {
			plantings = plantingService.findByUserInField(user, page, value);
		} else {
			plantings = plantingService.findByUserInFieldAndKeyAndDate(user, key, startDate, endDate, page, value);
		}
		model.addAttribute("plantings", plantings);
		setPageIndex(plantings.size(), model, page, value);
		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatter", formatter);
		return "/planting/plantingTable";
	}

	@RequestMapping(value = { "/planting/total" }, method = RequestMethod.GET)
	@ResponseBody
	public int selectPlantingTableTotal(@RequestParam(value = "key", required = false) String key,
			@RequestParam(value = "startDate", required = false) Date startDate,
			@RequestParam(value = "endDate", required = false) Date endDate, Authentication authentication,
			Model model) {
		User user = userService.findByUsername(MvcHelper.getUsername(authentication));
		int totalItem;
		if ((key == null || key == "") && (startDate == null || endDate == null)) {
			totalItem = plantingService.findByUserInField(user).size();
		} else {
			totalItem = plantingService.findByUserInFieldAndKeyAndDate(user, key, startDate, endDate).size();
		}
		return totalItem;
	}

	@RequestMapping(value = "/planting/plantingByFieldId/{id}", method = RequestMethod.GET)
	public String plantingByFeildId(@PathVariable("id") int id, Model model, Authentication authentication) {

		User user = userService.findByUsername(MvcHelper.getUsername(authentication));
		List<Planting> plantings = plantingService.findByFieldIdAndUser(id, user.getUsername());

		model.addAttribute("plantings", plantings);

		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatter", formatter);

		model.addAttribute("page", "plantingByFieldId");

		return "planting/plantingByFieldId";
	}

	@RequestMapping(value = "/planting/create/{id}", method = RequestMethod.GET)
	public String plantingCreate(@PathVariable("id") int id, Model model) {
		Field field = fieldService.findById(id);
		if (field == null) {
			return "redirect:/planting/";
		}
		PlantingDTO plantingDTO = new PlantingDTO();
		plantingDTO.setVarieties(varietyService.findAll());

		model.addAttribute("field", field);

		model.addAttribute("plants", plantService.findAll());
		model.addAttribute("varieties", varietyService.findAll());
		model.addAttribute("herbicides", herbicideService.findAll());

		List<String> previousPlantList = new ArrayList<String>();
		previousPlantList.add("มันสำปะหลัง");
		previousPlantList.add("อ้อย");
		previousPlantList.add("สับปะรด");
		previousPlantList.add("อื่นๆ");
		model.addAttribute("previousPlants", previousPlantList);

		List<String> besidePlantList = new ArrayList<String>();
		besidePlantList.add("มันสำปะหลัง");
		besidePlantList.add("อ้อย");
		besidePlantList.add("สับปะรด");
		besidePlantList.add("ยางพารา");
		besidePlantList.add("อื่นๆ");
		model.addAttribute("besidePlants", besidePlantList);

		List<String> stemSourceList = new ArrayList<String>();
		stemSourceList.add("ซื้อ");
		stemSourceList.add("เก็บท่อนพันธุ์เอง");
		model.addAttribute("stemSources", stemSourceList);

		List<String> plantsNameList = new ArrayList<String>();
		plantsNameList.add("มันสำปะหลัง");
		model.addAttribute("plantsNames", plantsNameList);

		List<String> varietysNameList = new ArrayList<String>();
		varietysNameList.add("ระยอง1");
		varietysNameList.add("ระยอง2");
		varietysNameList.add("ระยอง3");
		model.addAttribute("varietysNames", varietysNameList);

		List<String> numTillageList = new ArrayList<String>();
		numTillageList.add("1");
		numTillageList.add("2");
		numTillageList.add("3");
		numTillageList.add("4");
		model.addAttribute("numTillages", numTillageList);

		List<String> soilAmendmentsList = new ArrayList<String>();
		soilAmendmentsList.add("กากมันสำปะหลัง");
		soilAmendmentsList.add("มูลไก่แกลบ");
		soilAmendmentsList.add("ปูนขาว");
		soilAmendmentsList.add("อื่นๆ");
		model.addAttribute("soilAmendmentss", soilAmendmentsList);

		List<String> soakingStemChemicalList = new ArrayList<String>();
		soakingStemChemicalList.add("ไทอะมีโทแซม");
		soakingStemChemicalList.add("อิมิดราคลอพริด");
		soakingStemChemicalList.add("มาลาไทออน");
		soakingStemChemicalList.add("ไม่แช่");
		model.addAttribute("soakingStemChemicals", soakingStemChemicalList);

		List<String> weedingMonth1List = new ArrayList<String>();
		weedingMonth1List.add("1");
		weedingMonth1List.add("2");
		weedingMonth1List.add("3");
		weedingMonth1List.add("4");
		weedingMonth1List.add("5");
		weedingMonth1List.add("6");
		weedingMonth1List.add("7");
		weedingMonth1List.add("8");
		weedingMonth1List.add("9");
		weedingMonth1List.add("10");
		weedingMonth1List.add("11");
		weedingMonth1List.add("12");
		model.addAttribute("weedingMonth1s", weedingMonth1List);

		List<String> weedingMonth2List = new ArrayList<String>();
		weedingMonth2List.add("1");
		weedingMonth2List.add("2");
		weedingMonth2List.add("3");
		weedingMonth2List.add("4");
		weedingMonth2List.add("5");
		weedingMonth2List.add("6");
		weedingMonth2List.add("7");
		weedingMonth2List.add("8");
		weedingMonth2List.add("9");
		weedingMonth2List.add("10");
		weedingMonth2List.add("11");
		weedingMonth2List.add("12");
		model.addAttribute("weedingMonth2s", weedingMonth2List);

		List<String> weedingMonth3List = new ArrayList<String>();
		weedingMonth3List.add("1");
		weedingMonth3List.add("2");
		weedingMonth3List.add("3");
		weedingMonth3List.add("4");
		weedingMonth3List.add("5");
		weedingMonth3List.add("6");
		weedingMonth3List.add("7");
		weedingMonth3List.add("8");
		weedingMonth3List.add("9");
		weedingMonth3List.add("10");
		weedingMonth3List.add("11");
		weedingMonth3List.add("12");
		model.addAttribute("weedingMonth3s", weedingMonth3List);

		List<String> diseaseManagementList = new ArrayList<String>();
		diseaseManagementList.add("ใช้สารเคมี");
		diseaseManagementList.add("ชีววิธี");
		diseaseManagementList.add("วิธีกล");
		diseaseManagementList.add("ไม่จัดการ");
		model.addAttribute("diseaseManagements", diseaseManagementList);

		List<String> pestManagementList = new ArrayList<String>();
		pestManagementList.add("ใช้สารเคมี");
		pestManagementList.add("ชีววิธี");
		pestManagementList.add("วิธีกล");
		pestManagementList.add("ไม่จัดการ");
		model.addAttribute("pestManagements", pestManagementList);

		return "planting/plantingCreate";
	}

	@RequestMapping(value = "/planting/createbyfieldId/{id}/page/{page}", method = RequestMethod.GET)
	public String plantingCreatebyfieldId(@PathVariable("id") int id, @PathVariable(value = "page") String page,
			Model model) {
		Field field = fieldService.findById(id);
		if (field == null) {
			return "redirect:/planting/";
		}
		PlantingDTO plantingDTO = new PlantingDTO();
		plantingDTO.setVarieties(varietyService.findAll());

		model.addAttribute("field", field);

		model.addAttribute("plants", plantService.findAll());
		model.addAttribute("varieties", varietyService.findAll());
		model.addAttribute("herbicides", herbicideService.findAll());

		List<String> previousPlantList = new ArrayList<String>();
		previousPlantList.add("มันสำปะหลัง");
		previousPlantList.add("อ้อย");
		previousPlantList.add("สับปะรด");
		previousPlantList.add("อื่นๆ");
		model.addAttribute("previousPlants", previousPlantList);

		List<String> besidePlantList = new ArrayList<String>();
		besidePlantList.add("มันสำปะหลัง");
		besidePlantList.add("อ้อย");
		besidePlantList.add("สับปะรด");
		besidePlantList.add("ยางพารา");
		besidePlantList.add("อื่นๆ");
		model.addAttribute("besidePlants", besidePlantList);

		List<String> stemSourceList = new ArrayList<String>();
		stemSourceList.add("ซื้อ");
		stemSourceList.add("เก็บท่อนพันธุ์เอง");
		model.addAttribute("stemSources", stemSourceList);

		List<String> plantsNameList = new ArrayList<String>();
		plantsNameList.add("มันสำปะหลัง");
		model.addAttribute("plantsNames", plantsNameList);

		List<String> varietysNameList = new ArrayList<String>();
		varietysNameList.add("ระยอง1");
		varietysNameList.add("ระยอง2");
		varietysNameList.add("ระยอง3");
		model.addAttribute("varietysNames", varietysNameList);

		List<String> numTillageList = new ArrayList<String>();
		numTillageList.add("1");
		numTillageList.add("2");
		numTillageList.add("3");
		numTillageList.add("4");
		model.addAttribute("numTillages", numTillageList);

		List<String> soilAmendmentsList = new ArrayList<String>();
		soilAmendmentsList.add("กากมันสำปะหลัง");
		soilAmendmentsList.add("มูลไก่แกลบ");
		soilAmendmentsList.add("ปูนขาว");
		soilAmendmentsList.add("อื่นๆ");
		model.addAttribute("soilAmendmentss", soilAmendmentsList);

		List<String> soakingStemChemicalList = new ArrayList<String>();
		soakingStemChemicalList.add("ไทอะมีโทแซม");
		soakingStemChemicalList.add("อิมิดราคลอพริด");
		soakingStemChemicalList.add("มาลาไทออน");
		soakingStemChemicalList.add("ไม่แช่");
		model.addAttribute("soakingStemChemicals", soakingStemChemicalList);

		List<String> weedingMonth1List = new ArrayList<String>();
		weedingMonth1List.add("1");
		weedingMonth1List.add("2");
		weedingMonth1List.add("3");
		weedingMonth1List.add("4");
		weedingMonth1List.add("5");
		weedingMonth1List.add("6");
		weedingMonth1List.add("7");
		weedingMonth1List.add("8");
		weedingMonth1List.add("9");
		weedingMonth1List.add("10");
		weedingMonth1List.add("11");
		weedingMonth1List.add("12");
		model.addAttribute("weedingMonth1s", weedingMonth1List);

		List<String> weedingMonth2List = new ArrayList<String>();
		weedingMonth2List.add("1");
		weedingMonth2List.add("2");
		weedingMonth2List.add("3");
		weedingMonth2List.add("4");
		weedingMonth2List.add("5");
		weedingMonth2List.add("6");
		weedingMonth2List.add("7");
		weedingMonth2List.add("8");
		weedingMonth2List.add("9");
		weedingMonth2List.add("10");
		weedingMonth2List.add("11");
		weedingMonth2List.add("12");
		model.addAttribute("weedingMonth2s", weedingMonth2List);

		List<String> weedingMonth3List = new ArrayList<String>();
		weedingMonth3List.add("1");
		weedingMonth3List.add("2");
		weedingMonth3List.add("3");
		weedingMonth3List.add("4");
		weedingMonth3List.add("5");
		weedingMonth3List.add("6");
		weedingMonth3List.add("7");
		weedingMonth3List.add("8");
		weedingMonth3List.add("9");
		weedingMonth3List.add("10");
		weedingMonth3List.add("11");
		weedingMonth3List.add("12");
		model.addAttribute("weedingMonth3s", weedingMonth3List);

		List<String> diseaseManagementList = new ArrayList<String>();
		diseaseManagementList.add("ใช้สารเคมี");
		diseaseManagementList.add("ชีววิธี");
		diseaseManagementList.add("วิธีกล");
		diseaseManagementList.add("ไม่จัดการ");
		model.addAttribute("diseaseManagements", diseaseManagementList);

		List<String> pestManagementList = new ArrayList<String>();
		pestManagementList.add("ใช้สารเคมี");
		pestManagementList.add("ชีววิธี");
		pestManagementList.add("วิธีกล");
		pestManagementList.add("ไม่จัดการ");
		model.addAttribute("pestManagements", pestManagementList);
		model.addAttribute("page", page);
		
		return "planting/plantingCreateByFieldId";
	}

	@RequestMapping(value = "/planting/createbyfieldId/save", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> plantingSaveByFieldId(
			@ModelAttribute("plantingDTO") PlantingDTO plantingDTO, Model model, Authentication authentication)
			throws Exception {
		Planting p = plantingDTO.getPlanting();
		p.setCode(p.getCode().trim());
		p.setName(p.getName().trim());
		p.setSecondaryPlantType(p.getSecondaryPlantType().trim());
		p.setSecondaryPlantVariety(p.getSecondaryPlantVariety().trim());
		p.setFertilizerFormular1(p.getFertilizerFormular1().trim());
		p.setFertilizerFormular2(p.getFertilizerFormular2().trim());
		p.setFertilizerFormular3(p.getFertilizerFormular3().trim());
		p.setNote(p.getNote().trim());
		//if(p.getPreviousPlant()!=null)
		p.setPreviousPlantOther(p.getPreviousPlantOther().trim());
		p.setBesidePlantOther(p.getBesidePlantOther().trim());
		p.setSoilAmendmentsOther(p.getSoilAmendmentsOther().trim());
		if(p.getHerbicideByWeedingChemical1()!=null)
		p.setWeedingChemicalOther1(p.getWeedingChemicalOther1().trim());
		if(p.getHerbicideByWeedingChemical2()!=null)
			p.setWeedingChemicalOther2(p.getWeedingChemicalOther2().trim());
		if(p.getHerbicideByWeedingChemical3()!=null)
				p.setWeedingChemicalOther3(p.getWeedingChemicalOther3().trim());
		
		// System.out.println("ID
		// filed"+plantingDTO.getPlanting().getField().getFieldId());
		if (plantingDTO.getPlantingDatePrimaryPlantPlanting().length() != 0) {
			Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(plantingDTO.getPlantingDatePrimaryPlantPlanting());
			plantingDTO.getPlanting().setPrimaryPlantPlantingDate(date2);
		}
		ResponseEntity<Response<ObjectNode>> responseEntity = validate(plantingDTO);
		if (responseEntity.getStatusCode() == HttpStatus.OK) {

			if (plantingDTO.getPlantingDatePrimaryPlantHarvest().length() != 0) {
				Date date3 = new SimpleDateFormat("yyyy-MM-dd").parse(plantingDTO.getPlantingDatePrimaryPlantHarvest());
				p.setPrimaryPlantHarvestDate(date3);
			}
			if (plantingDTO.getPlantingDateSecondaryPlantPlanting().length() != 0) {
				Date date4 = new SimpleDateFormat("yyyy-MM-dd")
						.parse(plantingDTO.getPlantingDateSecondaryPlantPlanting());
				p.setSecondaryPlantPlantingDate(date4);
			}
			if (plantingDTO.getPlantingDateSecondaryPlantHarvest().length() != 0) {
				Date date5 = new SimpleDateFormat("yyyy-MM-dd")
						.parse(plantingDTO.getPlantingDateSecondaryPlantHarvest());
				p.setSecondaryPlantHarvestDate(date5);
			}
			if (plantingDTO.getPlantingDateFertilizerDate1().length() != 0) {
				Date date6 = new SimpleDateFormat("yyyy-MM-dd").parse(plantingDTO.getPlantingDateFertilizerDate1());
				p.setFertilizerDate1(date6);
			}
			if (plantingDTO.getPlantingDateFertilizerDate2().length() != 0) {
				Date date7 = new SimpleDateFormat("yyyy-MM-dd").parse(plantingDTO.getPlantingDateFertilizerDate2());
				p.setFertilizerDate2(date7);
			}
			if (plantingDTO.getPlantingDateFertilizerDate3().length() != 0) {
				Date date8 = new SimpleDateFormat("yyyy-MM-dd").parse(plantingDTO.getPlantingDateFertilizerDate3());
				p.setFertilizerDate3(date8);
			}

			User user = userService.findByUsername(MvcHelper.getUsername(authentication));
			p.setUserByCreateBy(user);
			p.setUserByLastUpdateBy(user);
			p.setCreateDate(new Date());
			p.setLastUpdateDate(new Date());

			if (p.getDiseaseManagement().equals(""))
				p.setDiseaseManagement(null);
			if (p.getPestManagement().equals(""))
				p.setPestManagement(null);

			if (p.getHerbicideByWeedingChemical1().getHerbicideId() == null)
				p.setHerbicideByWeedingChemical1(null);
			if (p.getHerbicideByWeedingChemical2().getHerbicideId() == null)
				p.setHerbicideByWeedingChemical2(null);
			if (p.getHerbicideByWeedingChemical3().getHerbicideId() == null)
				p.setHerbicideByWeedingChemical3(null);

			plantingService.save(p);
			for (int i = 0; i < plantingDTO.getVarieties().size(); i++) {
				Variety v = varietyService.findByName(plantingDTO.getVarieties().get(i).getName());
				PlantingCassavaVarietyId pvi = new PlantingCassavaVarietyId();
				pvi.setPlantingId(p.getPlantingId());
				pvi.setVarietyId(v.getVarietyId());

				PlantingCassavaVariety pv = new PlantingCassavaVariety();
				pv.setId(pvi);
				pv.setPlanting(plantingDTO.getPlanting());
				pv.setVariety(v);
				plantingCassavaVarietyService.save(pv);
			}
		}
		return responseEntity;
	}

	private ResponseEntity<Response<ObjectNode>> validate(PlantingDTO plantingDTO) {
		ObjectNode responObject = new ObjectMapper().createObjectNode();

		List<Variety> listv = plantingDTO.getVarieties();
		if (listv == null) {
			responObject.put("varieties", "กรุณาเลือกชนิดพันธุ์หลัก");
		}
		if (plantingDTO.getPlanting().getSize() == null || (plantingDTO.getPlanting().getSize() <= 0)) {
			responObject.put("size", "กรุณากรอกพื้นที่มากกว่า 0 ");
		}
		if (plantingDTO.getPlanting().getPreviousPlant()!=null&&plantingDTO.getPlanting().getPreviousPlant().equals("อื่นๆ") && (plantingDTO.getPlanting().getPreviousPlantOther()==null||plantingDTO.getPlanting().getPreviousPlantOther().length() <2||plantingDTO.getPlanting().getPreviousPlantOther().length() >25)) {
			//System.out.println("previousPlant");
			responObject.put("previousPlant", "ความยาวตั้งแต่ 2 - 25 ตัวอักษร");
		}
		if (plantingDTO.getPlanting().getBesidePlant()!=null&&plantingDTO.getPlanting().getBesidePlant().equals("อื่นๆ") &&( plantingDTO.getPlanting().getBesidePlantOther()==null||plantingDTO.getPlanting().getBesidePlantOther().length() <2||plantingDTO.getPlanting().getBesidePlantOther().length() >25) ){
			responObject.put("besidePlant", "ความยาวตั้งแต่ 2 - 25 ตัวอักษร");
		}
		if (plantingDTO.getPlanting().getSoilAmendments()!=null&&plantingDTO.getPlanting().getSoilAmendments().equals("อื่นๆ") &&(plantingDTO.getPlanting().getSoilAmendmentsOther()==null|| plantingDTO.getPlanting().getSoilAmendmentsOther().length() <2||plantingDTO.getPlanting().getSoilAmendmentsOther().length() >25) ){
			responObject.put("soilAmendments", "ความยาวตั้งแต่ 2 - 25 ตัวอักษร");
		}
		// Planting plantingIn =
		// plantingService.findByCode(plantingDTO.getPlanting().getCode());
//		Planting plantingIn = plantingService.findByOrganizationAndCode(fieldService
//				.findById(plantingDTO.getPlanting().getField().getFieldId()).getOrganization().getOrganizationId(),
//				plantingDTO.getPlanting().getCode());
		//System.out.println(plantingDTO.getPlanting().getCode()+"=="+plantingIn.getCode());
		//System.out.println(plantingDTO.getPlanting().getPlantingId()+"=="+plantingIn.getPlantingId());
//		if (plantingIn != null && plantingIn.getPlantingId() != plantingDTO.getPlanting().getPlantingId()&& plantingIn.getCode().equals(plantingDTO.getPlanting().getCode())&&plantingIn.getPlantingId()!=null ) {
//			responObject.put("code", "ชื่อนี้ถูกใช้แล้ว");
//		}
		
//		System.out.println(plantingDTO.getPlanting().getPlantingId()+"code "+plantingDTO.getPlanting().getCode()+"org "+fieldService.findById(plantingDTO.getPlanting().getField().getFieldId()).getOrganization().getOrganizationId());
//		System.out.println(plantingService.findByOrganizationAndCode(fieldService.findById(plantingDTO.getPlanting().getField().getFieldId()).getOrganization().getOrganizationId(),
//				plantingDTO.getPlanting().getCode()));
		if (plantingDTO.getPlanting().getPlantingId()==null&&plantingService.findByOrganizationAndCode(fieldService.findById(plantingDTO.getPlanting().getField().getFieldId()).getOrganization().getOrganizationId(),
				plantingDTO.getPlanting().getCode())!=null) {
			responObject.put("code", "ชื่อนี้ถูกใช้แล้ว");
		}
		if (plantingDTO.getPlanting().getPlantingId()!=null&&plantingService.findByOrganizationAndCodeAndPlantingId(fieldService.findById(plantingDTO.getPlanting().getField().getFieldId()).getOrganization().getOrganizationId(),
				plantingDTO.getPlanting().getCode(),plantingDTO.getPlanting().getPlantingId())>0) {
			responObject.put("code", "ชื่อนี้ถูกใช้แล้ว");
		}
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Planting>> violations = validator.validate(plantingDTO.getPlanting());
		HashMap<String, List<String>> errorMessages = new HashMap<String, List<String>>();
		if (violations.size() > 0) {
			violations.stream().forEach((ConstraintViolation<?> error) -> {
				String key = error.getPropertyPath().toString();
				String message = error.getMessage();
				List<String> list = null;
				if (errorMessages.containsKey(key)) {
					list = errorMessages.get(key);
				} else {
					list = new ArrayList<String>();
				}
				list.add(message);
				errorMessages.put(key, list);
			});
			for (String key : errorMessages.keySet()) {
				List<String> sortedList = errorMessages.get(key);
				Collections.sort(sortedList);
				Iterator<String> itorError = sortedList.iterator();
				StringBuilder sb = new StringBuilder(itorError.next());
				while (itorError.hasNext()) {
					sb.append(", ").append(itorError.next());
				}
				responObject.put(key, sb.toString());
			}
		}
		Response<ObjectNode> res = new Response<>();
		if (responObject.size() == 0)
			res.setHttpStatus(HttpStatus.OK);
		else {
			res.setHttpStatus(HttpStatus.BAD_REQUEST);
			res.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
			res.setBody(responObject);
		}
		return new ResponseEntity<Response<ObjectNode>>(res, res.getHttpStatus());
	}

	@RequestMapping(value = "/planting/save", method = RequestMethod.POST)
	public String plantingSave(@ModelAttribute("plantingDTO") PlantingDTO plantingDTO, Model model,
			Authentication authentication) throws Exception {

		Planting p = plantingDTO.getPlanting();
		if (plantingDTO.getPlantingDatePrimaryPlantPlanting() != "") {
			Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(plantingDTO.getPlantingDatePrimaryPlantPlanting());
			p.setPrimaryPlantPlantingDate(date2);
		}
		if (plantingDTO.getPlantingDatePrimaryPlantHarvest() != "") {
			Date date3 = new SimpleDateFormat("yyyy-MM-dd").parse(plantingDTO.getPlantingDatePrimaryPlantHarvest());
			p.setPrimaryPlantHarvestDate(date3);
		}
		if (plantingDTO.getPlantingDateSecondaryPlantPlanting() != "") {
			Date date4 = new SimpleDateFormat("yyyy-MM-dd").parse(plantingDTO.getPlantingDateSecondaryPlantPlanting());
			p.setSecondaryPlantPlantingDate(date4);
		}
		if (plantingDTO.getPlantingDateSecondaryPlantHarvest() != "") {
			Date date5 = new SimpleDateFormat("yyyy-MM-dd").parse(plantingDTO.getPlantingDateSecondaryPlantHarvest());
			p.setSecondaryPlantHarvestDate(date5);
		}
		if (plantingDTO.getPlantingDateFertilizerDate1() != "") {
			Date date6 = new SimpleDateFormat("yyyy-MM-dd").parse(plantingDTO.getPlantingDateFertilizerDate1());
			p.setFertilizerDate1(date6);
		}
		if (plantingDTO.getPlantingDateFertilizerDate2() != "") {
			Date date7 = new SimpleDateFormat("yyyy-MM-dd").parse(plantingDTO.getPlantingDateFertilizerDate2());
			p.setFertilizerDate2(date7);
		}
		if (plantingDTO.getPlantingDateFertilizerDate3() != "") {
			Date date8 = new SimpleDateFormat("yyyy-MM-dd").parse(plantingDTO.getPlantingDateFertilizerDate3());
			p.setFertilizerDate3(date8);
		}

		User user = userService.findByUsername(MvcHelper.getUsername(authentication));
		p.setUserByCreateBy(user);
		p.setUserByLastUpdateBy(user);
		p.setCreateDate(new Date());
		p.setLastUpdateDate(new Date());

		if (p.getDiseaseManagement().equals(""))
			p.setDiseaseManagement(null);
		if (p.getPestManagement().equals(""))
			p.setPestManagement(null);

		if (p.getHerbicideByWeedingChemical1().getHerbicideId() == null)
			p.setHerbicideByWeedingChemical1(null);
		if (p.getHerbicideByWeedingChemical2().getHerbicideId() == null)
			p.setHerbicideByWeedingChemical2(null);
		if (p.getHerbicideByWeedingChemical3().getHerbicideId() == null)
			p.setHerbicideByWeedingChemical3(null);

		plantingService.save(p);
		for (int i = 0; i < plantingDTO.getVarieties().size(); i++) {
			Variety v = varietyService.findByName(plantingDTO.getVarieties().get(i).getName());
			PlantingCassavaVarietyId pvi = new PlantingCassavaVarietyId();
			pvi.setPlantingId(p.getPlantingId());
			pvi.setVarietyId(v.getVarietyId());

			PlantingCassavaVariety pv = new PlantingCassavaVariety();
			pv.setId(pvi);
			pv.setPlanting(plantingDTO.getPlanting());
			pv.setVariety(v);
			plantingCassavaVarietyService.save(pv);
		}
		return "redirect:/planting/";
	}

	@RequestMapping(value = "/planting/update", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> plantingUpdate(@ModelAttribute("plantingDTO") PlantingDTO plantingDTO,
			Model model, Authentication authentication) throws Exception {
		
		/*
		 * Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(plantingDTO.
		 * getPlantingDatePrimaryPlantPlanting()); Date date3 = new
		 * SimpleDateFormat("yyyy-MM-dd").parse(plantingDTO.
		 * getPlantingDatePrimaryPlantHarvest()); Date date4 = new
		 * SimpleDateFormat("yyyy-MM-dd").parse(plantingDTO.
		 * getPlantingDateSecondaryPlantPlanting()); Date date5 = new
		 * SimpleDateFormat("yyyy-MM-dd").parse(plantingDTO.
		 * getPlantingDateSecondaryPlantHarvest()); Date date6 = new
		 * SimpleDateFormat("yyyy-MM-dd").parse(plantingDTO.
		 * getPlantingDateFertilizerDate1()); Date date7 = new
		 * SimpleDateFormat("yyyy-MM-dd").parse(plantingDTO.
		 * getPlantingDateFertilizerDate2()); Date date8 = new
		 * SimpleDateFormat("yyyy-MM-dd").parse(plantingDTO.
		 * getPlantingDateFertilizerDate3());
		 */
		Planting p = plantingDTO.getPlanting();
		p.setCode(p.getCode().trim());
		p.setName(p.getName().trim());
		p.setSecondaryPlantType(p.getSecondaryPlantType().trim());
		p.setSecondaryPlantVariety(p.getSecondaryPlantVariety().trim());
		p.setFertilizerFormular1(p.getFertilizerFormular1().trim());
		p.setFertilizerFormular2(p.getFertilizerFormular2().trim());
		p.setFertilizerFormular3(p.getFertilizerFormular3().trim());
		p.setNote(p.getNote().trim());
		p.setPreviousPlantOther(p.getPreviousPlantOther().trim());
		p.setBesidePlantOther(p.getBesidePlantOther().trim());
		p.setSoilAmendmentsOther(p.getSoilAmendmentsOther().trim());
		if(p.getHerbicideByWeedingChemical1()!=null)
		p.setWeedingChemicalOther1(p.getWeedingChemicalOther1().trim());
		if(p.getHerbicideByWeedingChemical2()!=null)
			p.setWeedingChemicalOther2(p.getWeedingChemicalOther2().trim());
		if(p.getHerbicideByWeedingChemical3()!=null)
				p.setWeedingChemicalOther3(p.getWeedingChemicalOther3().trim());
		
		if (plantingDTO.getPlantingDatePrimaryPlantPlanting().length() != 0) {
			Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(plantingDTO.getPlantingDatePrimaryPlantPlanting());
			plantingDTO.getPlanting().setPrimaryPlantPlantingDate(date2);
		}
		ResponseEntity<Response<ObjectNode>> responseEntity = validate(plantingDTO);
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			//Planting p = plantingDTO.getPlanting();

			/*
			 * p.setPrimaryPlantPlantingDate(date2); p.setPrimaryPlantHarvestDate(date3);
			 * p.setSecondaryPlantPlantingDate(date4);
			 * p.setSecondaryPlantHarvestDate(date5); p.setFertilizerDate1(date6);
			 * p.setFertilizerDate2(date7); p.setFertilizerDate3(date8);
			 */
			if (plantingDTO.getPlantingDatePrimaryPlantHarvest().length() != 0) {
				Date date3 = new SimpleDateFormat("yyyy-MM-dd").parse(plantingDTO.getPlantingDatePrimaryPlantHarvest());
				p.setPrimaryPlantHarvestDate(date3);
			}
			if (plantingDTO.getPlantingDateSecondaryPlantPlanting().length() != 0) {
				Date date4 = new SimpleDateFormat("yyyy-MM-dd")
						.parse(plantingDTO.getPlantingDateSecondaryPlantPlanting());
				p.setSecondaryPlantPlantingDate(date4);
			}
			if (plantingDTO.getPlantingDateSecondaryPlantHarvest().length() != 0) {
				Date date5 = new SimpleDateFormat("yyyy-MM-dd")
						.parse(plantingDTO.getPlantingDateSecondaryPlantHarvest());
				p.setSecondaryPlantHarvestDate(date5);
			}
			if (plantingDTO.getPlantingDateFertilizerDate1().length() != 0) {
				Date date6 = new SimpleDateFormat("yyyy-MM-dd").parse(plantingDTO.getPlantingDateFertilizerDate1());
				p.setFertilizerDate1(date6);
			}
			if (plantingDTO.getPlantingDateFertilizerDate2().length() != 0) {
				Date date7 = new SimpleDateFormat("yyyy-MM-dd").parse(plantingDTO.getPlantingDateFertilizerDate2());
				p.setFertilizerDate2(date7);
			}
			if (plantingDTO.getPlantingDateFertilizerDate3().length() != 0) {
				Date date8 = new SimpleDateFormat("yyyy-MM-dd").parse(plantingDTO.getPlantingDateFertilizerDate3());
				p.setFertilizerDate3(date8);
			}

			int id = p.getPlantingId();
			Planting t = plantingService.findById(id);

			User user = userService.findByUsername(MvcHelper.getUsername(authentication));
			p.setUserByCreateBy(t.getUserByCreateBy());
			p.setUserByLastUpdateBy(user);
			p.setCreateDate(t.getCreateDate());
			p.setLastUpdateDate(new Date());

			if (p.getDiseaseManagement().equals(""))
				p.setDiseaseManagement(null);
			if (p.getPestManagement().equals(""))
				p.setPestManagement(null);

			if (p.getHerbicideByWeedingChemical1().getHerbicideId() == null)
				p.setHerbicideByWeedingChemical1(null);
			if (p.getHerbicideByWeedingChemical2().getHerbicideId() == null)
				p.setHerbicideByWeedingChemical2(null);
			if (p.getHerbicideByWeedingChemical3().getHerbicideId() == null)
				p.setHerbicideByWeedingChemical3(null);

			/*
			 * 
			 * if (!p.getPreviousPlant().equals("อื่นๆ")) p.setPreviousPlantOther(null); if
			 * (!p.getBesidePlant().equals("อื่นๆ")) p.setBesidePlantOther(null);
			 * p.setPrimaryVarietyOther(null); if (!p.getSoilAmendments().equals(null))
			 * p.setSoilAmendmentsOther(null); if
			 * (!p.getHerbicideByWeedingChemical1().getHerbicideId().equals(9))
			 * p.setWeedingChemicalOther1(null); if
			 * (!p.getHerbicideByWeedingChemical2().getHerbicideId().equals(9))
			 * p.setWeedingChemicalOther2(null); if
			 * (!p.getHerbicideByWeedingChemical3().getHerbicideId().equals(9))
			 * p.setWeedingChemicalOther3(null);
			 */
			p = plantingService.save(p);
			List<PlantingCassavaVariety> plantingCassavaVarieties = plantingCassavaVarietyService
					.findByPlantingId(p.getPlantingId());
			//delete difference plantingCassavaVariety
			List<Integer> list1 = new ArrayList<Integer>();
			List<Integer> list2 = new ArrayList<Integer>();
			for (PlantingCassavaVariety pcv : plantingCassavaVarieties) {
				list1.add(pcv.getVariety().getVarietyId());
			}

			if (plantingDTO.getVarieties() != null) {
				for (int i = 0; i < plantingDTO.getVarieties().size(); i++) {
					Variety v = varietyService.findByName(plantingDTO.getVarieties().get(i).getName());
					list2.add(v.getVarietyId());
				}
				list1.removeAll(list2);
				for(Integer varietyId : list1) {
					PlantingCassavaVariety cassavaVariety = plantingCassavaVarietyService.findByVarietyIdAndPlantingId(varietyId, p.getPlantingId());
					plantingCassavaVarietyService.deleteByPlantingCassavaVariety(cassavaVariety);
				}
				
				
				
				//update plantingCassavaVariety
				for (int i = 0; i < plantingDTO.getVarieties().size(); i++) {
					Variety v = varietyService.findByName(plantingDTO.getVarieties().get(i).getName());
					PlantingCassavaVarietyId pvi = new PlantingCassavaVarietyId();
					pvi.setPlantingId(p.getPlantingId());
					pvi.setVarietyId(v.getVarietyId());

					PlantingCassavaVariety pv = new PlantingCassavaVariety();
					pv.setId(pvi);
					pv.setPlanting(plantingDTO.getPlanting());
					pv.setVariety(v);
					plantingCassavaVarietyService.save(pv);
				}
			}

		}

		return responseEntity;
	}

	@RequestMapping(value = "/planting/choose", method = RequestMethod.GET)
	public String plantingChoose(Model model, Authentication authentication) {
		User user = userService.findByUsername(MvcHelper.getUsername(authentication));
		List<Field> fields = fieldService.findByUserInField(user);
		model.addAttribute("fields", fields);
		model.addAttribute("page", "plantingChoose");
		return "planting/plantingChoose";
	}

	@RequestMapping(value = "/planting/edit/{id}", method = RequestMethod.GET)
	public String plantingInfo(@PathVariable("id") int id, Model model) {
		Planting p = plantingService.findById(id);
		if (p == null) {
			return "redirect:/planting";
		}
		PlantingDTO plantingDto = new PlantingDTO();
		plantingDto.setPlanting(plantingService.findById(id));

		/*
		 * plantingDto.setPlantingDateFertilizerDate1(p.getFertilizerDate1().toString())
		 * ;
		 * plantingDto.setPlantingDateFertilizerDate2(p.getFertilizerDate2().toString())
		 * ;
		 * plantingDto.setPlantingDateFertilizerDate3(p.getFertilizerDate3().toString())
		 * ;
		 * plantingDto.setPlantingDatePrimaryPlantHarvest(p.getPrimaryPlantHarvestDate()
		 * .toString());
		 * plantingDto.setPlantingDatePrimaryPlantPlanting(p.getPrimaryPlantPlantingDate
		 * ().toString()); plantingDto.setPlantingDateSecondaryPlantHarvest(p.
		 * getSecondaryPlantHarvestDate().toString());
		 * plantingDto.setPlantingDateSecondaryPlantPlanting(p.
		 * getSecondaryPlantPlantingDate().toString()); for (int i = 0; i <
		 * p.getPlantingcassavavarieties().size(); i++) {
		 * System.out.println(p.getPlantingcassavavarieties().get(i).getVariety().
		 * getName()); };
		 */
		List<String> previousPlantList = new ArrayList<String>();
		previousPlantList.add("มันสำปะหลัง");
		previousPlantList.add("อ้อย");
		previousPlantList.add("สับปะรด");
		previousPlantList.add("อื่นๆ");
		model.addAttribute("previousPlants", previousPlantList);

		List<String> besidePlantList = new ArrayList<String>();
		besidePlantList.add("มันสำปะหลัง");
		besidePlantList.add("อ้อย");
		besidePlantList.add("สับปะรด");
		besidePlantList.add("ยางพารา");
		besidePlantList.add("อื่นๆ");
		model.addAttribute("besidePlants", besidePlantList);

		List<String> plantsNameList = new ArrayList<String>();
		plantsNameList.add("มันสำปะหลัง");
		model.addAttribute("plantsNames", plantsNameList);

		/*
		 * List<String> varietysNameList = new ArrayList<String>();
		 * varietysNameList.add("ระยอง1"); varietysNameList.add("ระยอง2");
		 * varietysNameList.add("ระยอง3"); model.addAttribute("varietysNames",
		 * varietysNameList);
		 */

		List<String> numTillageList = new ArrayList<String>();
		numTillageList.add("1");
		numTillageList.add("2");
		numTillageList.add("3");
		numTillageList.add("4");
		model.addAttribute("numTillages", numTillageList);

		List<String> soilAmendmentsList = new ArrayList<String>();
		soilAmendmentsList.add("กากมันสำปะหลัง");
		soilAmendmentsList.add("มูลไก่แกลบ");
		soilAmendmentsList.add("ปูนขาว");
		soilAmendmentsList.add("อื่นๆ");
		model.addAttribute("soilAmendmentss", soilAmendmentsList);

		List<String> soakingStemChemicalList = new ArrayList<String>();
		soakingStemChemicalList.add("ไทอะมีโทแซม");
		soakingStemChemicalList.add("อิมิดราคลอพริด");
		soakingStemChemicalList.add("มาลาไทออน");
		soakingStemChemicalList.add("ไม่แช่");
		model.addAttribute("soakingStemChemicals", soakingStemChemicalList);

		List<String> weedingMonth1List = new ArrayList<String>();
		weedingMonth1List.add("1");
		weedingMonth1List.add("2");
		weedingMonth1List.add("3");
		weedingMonth1List.add("4");
		weedingMonth1List.add("5");
		weedingMonth1List.add("6");
		weedingMonth1List.add("7");
		weedingMonth1List.add("8");
		weedingMonth1List.add("9");
		weedingMonth1List.add("10");
		weedingMonth1List.add("11");
		weedingMonth1List.add("12");
		model.addAttribute("weedingMonth1s", weedingMonth1List);

		List<String> weedingMonth2List = new ArrayList<String>();
		weedingMonth2List.add("1");
		weedingMonth2List.add("2");
		weedingMonth2List.add("3");
		weedingMonth2List.add("4");
		weedingMonth2List.add("5");
		weedingMonth2List.add("6");
		weedingMonth2List.add("7");
		weedingMonth2List.add("8");
		weedingMonth2List.add("9");
		weedingMonth2List.add("10");
		weedingMonth2List.add("11");
		weedingMonth2List.add("12");
		model.addAttribute("weedingMonth2s", weedingMonth2List);

		List<String> weedingMonth3List = new ArrayList<String>();
		weedingMonth3List.add("1");
		weedingMonth3List.add("2");
		weedingMonth3List.add("3");
		weedingMonth3List.add("4");
		weedingMonth3List.add("5");
		weedingMonth3List.add("6");
		weedingMonth3List.add("7");
		weedingMonth3List.add("8");
		weedingMonth3List.add("9");
		weedingMonth3List.add("10");
		weedingMonth3List.add("11");
		weedingMonth3List.add("12");
		model.addAttribute("weedingMonth3s", weedingMonth3List);

		List<String> diseaseManagementList = new ArrayList<String>();
		diseaseManagementList.add("ใช้สารเคมี");
		diseaseManagementList.add("ชีววิธี");
		diseaseManagementList.add("วิธีกล");
		diseaseManagementList.add("ไม่จัดการ");
		model.addAttribute("diseaseManagements", diseaseManagementList);

		List<String> pestManagementList = new ArrayList<String>();
		pestManagementList.add("ใช้สารเคมี");
		pestManagementList.add("ชีววิธี");
		pestManagementList.add("วิธีกล");
		pestManagementList.add("ไม่จัดการ");
		model.addAttribute("pestManagements", pestManagementList);
		model.addAttribute("plants", plantService.findAll());
		plantingDto.setPlanting(p);
		model.addAttribute("planting", p);
		List<Integer> varietyIdList = new ArrayList<Integer>();
		List<PlantingCassavaVariety> x = p.getPlantingcassavavarieties();
		for (PlantingCassavaVariety px : x) {
			varietyIdList.add(px.getVariety().getVarietyId());
		}
		model.addAttribute("varietyList", varietyService.findAll());
		model.addAttribute("varietyIdList", varietyIdList);
		model.addAttribute("herbicides", herbicideService.findAll());
		model.addAttribute("plantingDto", plantingDto);
		model.addAttribute("field", p.getField());
		model.addAttribute("cassavaVarieties", plantingCassavaVarietyService.findAll());

		return "planting/plantingInfo";
	}

	@RequestMapping(value = "/planting/delete/{id}", method = RequestMethod.GET)
	public String plantingDelete(@PathVariable("id") int id, Model model) {
		if (plantingService.findById(id) != null) {
			plantingService.deleteById(id);
		}
		return "redirect:/planting/";
	}

}
