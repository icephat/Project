package org.cassava.web.controller;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.cassava.api.util.model.Response;
import org.cassava.model.Organization;
import org.cassava.model.RegisterCode;
import org.cassava.model.User;
import org.cassava.services.OrganizationService;
import org.cassava.services.RegisterCodeService;
import org.cassava.services.RoleService;
import org.cassava.services.UserService;
import org.cassava.util.ImageBase64Helper;
import org.cassava.util.MvcHelper;
import org.cassava.util.QRCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.zxing.WriterException;

 

@Controller
@RequestMapping("/registfarmercode/")
public class RegisterCodeFarmerController {

	@Autowired
	private RegisterCodeService registcodeService;

	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = {"index",""}, method = RequestMethod.GET)
	public String registCodeIndex(Model model, Authentication authentication) {
		Format formatter = new SimpleDateFormat("dd MMMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatter",formatter);		

		List<RegisterCode> registercodes =null;
			
		User u = userService.findByUsername(MvcHelper.getUsername(authentication));	
		
		if(roleService.findByUserIdAndRoleName(u.getUserId(),"systemAdmin") != null)
		{
			registercodes = registcodeService.findAllFarmerOrder();
			model.addAttribute("registcodes",registercodes);			
			model.addAttribute("organizations", organizationService.findAll());
		}		
		else 
		{			
			registercodes = u.getStaff().getOrganization().getRegistercodes();
			model.addAttribute("registcodes",registercodes);			
			List<Organization> organizations = new ArrayList<Organization>();
			organizations.add(u.getStaff().getOrganization());			
			model.addAttribute("organizations", organizations);
		}
		List<Integer> percentList = new ArrayList<Integer>();
		List<String> statusList = new ArrayList<String>();
		List<String> codes = new ArrayList<String>();
				
		String status;
		String checkStatus;
		for (RegisterCode registercode : registercodes) {	
			percentList.add((int) (((float)registercode.getNumUseRegist()/(float)registercode.getNumUserPermit())*100));
			String code = registercode.getCode();
			
			byte[] image = new byte[0];
			 try {
				image = QRCodeGenerator.getQRCodeImage(code,250,250);
				codes.add(ImageBase64Helper.toImageBase64(image));
			} catch (WriterException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			 
			checkStatus= registcodeService.checkStatusByCode(registercode);
			if(checkStatus.equals("Expire"))
				status = "หมดอายุ";			
			else if(checkStatus.equals("NotDue"))
				status = "ยังไม่เริ่มใช้งาน";			
			else if (checkStatus.equals("Exceed"))			
				status = "ใช้ครบแล้ว";
			else
				status = "กำลังใช้งาน";

			statusList.add(status);
		}
		model.addAttribute("percentList",percentList);	
		model.addAttribute("statusList",statusList);	
		model.addAttribute("codes",codes);	

		return "registCode/registCodeFarmerIndex";
	}

	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String deleteRegistCode(@PathVariable("id") int id,Model model, Authentication authentication) {
		
		User u = userService.findByUsername(MvcHelper.getUsername(authentication));	
		RegisterCode registercode= registcodeService.findById(id);
		if(roleService.findByUserIdAndRoleName(u.getUserId(),"systemAdmin") != null || registercode.getOrganization().getOrganizationId().equals(u.getStaff().getOrganization().getOrganizationId()))		
			registcodeService.deleteById(id);
		return "redirect:/registfarmercode/";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> createRegistCode(@ModelAttribute("registercode") RegisterCode registercode, Model model,BindingResult bindingResult, Authentication authentication) {		
		//registercode.setCode(registercode.getCode().trim());
		Date expDate = registercode.getExpireDate();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(expDate);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		expDate = calendar.getTime();
		registercode.setExpireDate(expDate);
		ResponseEntity<Response<ObjectNode>> responseEntity = validate(registercode);
		User u = userService.findByUsername(MvcHelper.getUsername(authentication));
		if (responseEntity.getStatusCode() == HttpStatus.OK && (roleService.findByUserIdAndRoleName(u.getUserId(),"systemAdmin") != null || registercode.getOrganization().getOrganizationId().equals(u.getStaff().getOrganization().getOrganizationId()))) {
			String code = "FM";
		Date date = new Date();
		Random rn = new Random();
		for (int i = 0; i < 10; i++) {
			int x = rn.nextInt(9) + 1;
			code = code + String.valueOf(x);
		}
		registercode.setCode(code);	
		registercode.setCreateDate(date);
		registercode.setUser(u);
		
		
		registcodeService.save(registercode);

		LocalDateTime dt = LocalDateTime.now();				
		Date dt2 = new Date();
		dt2 = Date.from(dt.atZone(ZoneId.systemDefault()).minusDays(15).toInstant());	
		registcodeService.deleteByTypeAndExpiredate("Farmer",dt2);
		}
		return responseEntity;
	}
	public static boolean isNumeric(String string) {
	    int intValue;
			
	    if(string == null || string.equals("")) {
	        System.out.println("String cannot be parsed, it is null or empty.");
	        return false;
	    }
	    
	    try {
	        intValue = Integer.parseInt(string);
	        return true;
	    } catch (NumberFormatException e) {
	        System.out.println("Input String cannot be parsed to Integer.");
	    }
	    return false;
	}
	private	ResponseEntity<Response<ObjectNode>> validate(RegisterCode registercode){
		ObjectNode responObject = new ObjectMapper().createObjectNode();
		if (!(registercode.getNumUserPermit()>0&&registercode.getNumUserPermit()<=100)) {
			responObject.put("numUserPermitCheck", "กรุณากรอกจำนวนระหว่าง 1 - 100");		
		}
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		
		if (!(today).before((registercode.getExpireDate()))) {
			responObject.put("date", "กรุณากรอกวันหมดอายุให้ถูกต้อง");		
		}	
		Response<ObjectNode> res = new Response<>();		
		if (responObject.size()==0) 
			res.setHttpStatus(HttpStatus.OK);	
		else {
			res.setHttpStatus(HttpStatus.BAD_REQUEST);			
			res.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
			res.setBody(responObject);
		}	
		return new ResponseEntity<Response<ObjectNode>>(res, res.getHttpStatus());
	}
}
