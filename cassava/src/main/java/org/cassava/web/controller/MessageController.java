package org.cassava.web.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.cassava.model.Disease;
import org.cassava.model.District;
import org.cassava.model.Farmer;
import org.cassava.model.Message;
import org.cassava.model.Messagereceiver;
import org.cassava.model.MessagereceiverId;
import org.cassava.model.Province;
import org.cassava.model.Subdistrict;
import org.cassava.model.User;
import org.cassava.model.dto.MessageDTO;
import org.cassava.model.dto.MessageReceiverDTO;
import org.cassava.services.DiseaseService;
import org.cassava.services.DistrictService;
import org.cassava.services.FarmerService;
import org.cassava.services.MessageService;
import org.cassava.services.MessagereceiverService;
import org.cassava.services.ProvinceService;
import org.cassava.services.SubdistrictService;
import org.cassava.services.UserService;
import org.cassava.util.MvcHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MessageController {

	@Autowired
	private DiseaseService diseaseService;

	@Autowired
	private ProvinceService provinceService;

	@Autowired
	private UserService userService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private SubdistrictService subdistrictService;

	@Autowired
	private DistrictService districtService;

	@Autowired
	private FarmerService farmerService;

	@Autowired
	private MessagereceiverService messagereceiverService;

	@RequestMapping(value = { "/message/index", "/message/", "/message" }, method = RequestMethod.GET)
	public String messageIndex(Model model, Authentication authentication) {

		model.addAttribute("diseases", diseaseService.findAll());

		List<Province> provinces = provinceService.findAll();

		model.addAttribute("provinces", provinces);

		return "message/index";
	}

	@RequestMapping(value = "/message/confirm", method = RequestMethod.POST)
	public String messageConfirm(@ModelAttribute("messageDTO") MessageDTO messageDTO, Model model,
			Authentication authentication) {
		String username = MvcHelper.getUsername(authentication);
		User user = userService.findByUsername(username);
		Province province = provinceService.findById(Integer.parseInt(messageDTO.getProvince().getName()));
		District district = districtService.findById(messageDTO.getDistrict().getDistrictId());
		List<Subdistrict> subdistricts = new ArrayList<Subdistrict>();
		for (int sub : messageDTO.getSubdistrict()) {
			System.out.println(sub + " subb");
			subdistricts.add(subdistrictService.findById(sub));
		}

		model.addAttribute("subdistricts", subdistricts);
		model.addAttribute("district", district);
		model.addAttribute("province", province);

		List<Disease> diseases = new ArrayList<Disease>();
		for (int d : messageDTO.getDiseases()) {
			diseases.add(diseaseService.findById(d));

		}

		Locale locale = new Locale("th", "TH");
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
		String date = dateFormat.format(new Date());

		int amountfarmer = 0;
		for (Subdistrict sub : subdistricts) {
			amountfarmer += farmerService.checkByProvinceAndDistrictAndSubdistrict(province.getProvinceId(),
					district.getDistrictId(), sub.getSubdistrictId());
		}

		model.addAttribute("amountfarmer", amountfarmer);
		model.addAttribute("diseases", diseases);
		model.addAttribute("level", messageDTO.getLevel());
		model.addAttribute("date", date);
		
		
		String diseaseTitle = new String();
		for (Disease d : diseases) {
			diseaseTitle += d.getTargetofsurvey().getName() + " ";
		}
		String title = "แจ้งเตือนการระบาดของโรค";
		String text = "พบการระบาดของโรค" + diseaseTitle + "โดยมีระดับความรุนแรง " + messageDTO.getLevel()
		+ " ในพื้นที่ของท่านโปรดตรวจสอบแปลง และศึกษาข้อมูลเพื่อป้องกัน";
		model.addAttribute("text", text);
		model.addAttribute("title", title + diseaseTitle);
		
		
		
		/*// savemessage
		Message message = new Message();
		message.setTitle(title + diseaseTitle);
		String text = "พบการระบาดของโรค" + diseaseTitle + "โดยมีระดับความรุนแรง " + messageDTO.getLevel()
				+ " ในพื้นที่ของท่านโปรดตรวจสอบแปลง และศึกษาข้อมูลเพื่อป้องกัน";

		message.setText(text);
		message.setType("Alert");
		message.setSendDate(new Date());
		message.setUser(user);
		// messageService.save(message);
		model.addAttribute("message", message);

		// savemessagereceiver

		
		 * List<Farmer> farmers = new ArrayList<Farmer>(); for (Subdistrict sub :
		 * subdistricts) { if (farmers.size() == 0) { farmers =
		 * farmerService.findByProvinceAndDistrictAndSubdistrict(province.getProvinceId(
		 * ), district.getDistrictId(), sub.getSubdistrictId()); } else {
		 * farmers.addAll(farmerService.findByProvinceAndDistrictAndSubdistrict(province
		 * .getProvinceId(), district.getDistrictId(), sub.getSubdistrictId())); }
		 * 
		 * } for (Farmer f : farmers) { if (user.getUserId() != f.getUser().getUserId()
		 * && f.getUser().getUsername()!= null) { Messagereceiver messagereceiver = new
		 * Messagereceiver(); MessagereceiverId messagereceiverId = new
		 * MessagereceiverId(message.getMessageId(), f.getUser().getUserId());
		 * messagereceiver.setId(messagereceiverId);
		 * messagereceiver.setMessage(message); messagereceiver.setReadStatus("N");
		 * messagereceiver.setUser(f.getUser());
		 * messagereceiverService.save(messagereceiver); }
		 * 
		 * System.out.println(f.getUser().getFirstName() + " user"); }
		 */

		return "message/confirm";
	}

	@RequestMapping(value = "/message/send", method = RequestMethod.POST)
	public String messageSend(@ModelAttribute("messageDTO") MessageDTO messageDTO, Model model,
			Authentication authentication) {
		String username = MvcHelper.getUsername(authentication);
		User user = userService.findByUsername(username);
		
		
		Message message = new Message();
		message.setSendDate(new Date());
		message.setUser(user);
		message.setText(messageDTO.getText());
		message.setTitle(messageDTO.getTitle());
		message.setType("Alert");
		messageService.save(message);
		System.out.println(messageDTO.getProvinceId() + "code");
		
		Province province = provinceService.findById(messageDTO.getProvinceId());
		District district = districtService.findById(messageDTO.getDistrictId());
		List<Subdistrict> subdistricts = new ArrayList<Subdistrict>();
		for (int sub : messageDTO.getSubdistrict()) {
			System.out.println(sub + " subb");
			subdistricts.add(subdistrictService.findById(sub));
		}

		// savemessagereceiver

		List<Farmer> farmers = new ArrayList<Farmer>();
		for (Subdistrict sub : subdistricts) {
			if (farmers.size() == 0) {
				farmers = farmerService.findByProvinceAndDistrictAndSubdistrict(province.getProvinceId(),
						district.getDistrictId(), sub.getSubdistrictId());
			} else {
				farmers.addAll(farmerService.findByProvinceAndDistrictAndSubdistrict(province.getProvinceId(),
						district.getDistrictId(), sub.getSubdistrictId()));
			}

		}
		for (Farmer f : farmers) {
			if (user.getUserId() != f.getUser().getUserId() && f.getUser().getUsername() != null) {
				Messagereceiver messagereceiver = new Messagereceiver();
				MessagereceiverId messagereceiverId = new MessagereceiverId(message.getMessageId(),
						f.getUser().getUserId());
				messagereceiver.setId(messagereceiverId);
				messagereceiver.setMessage(message);
				messagereceiver.setReadStatus("N");
				messagereceiver.setUser(f.getUser());
				messagereceiverService.save(messagereceiver);
			}

			System.out.println(f.getUser().getFirstName() + " user");
		}

		return "redirect:/message/index";
	}

	

	@RequestMapping(value = "/message/cancel", method = RequestMethod.GET)
	public String messageSave(Model model, Authentication authentication) {

		return "redirect:/message/index";
	}

}
