package org.cassava.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cassava.model.Messagereceiver;
import org.cassava.model.User;
import org.cassava.model.dto.FieldAvgPercentAndDamageLevelDTO;
import org.cassava.model.dto.MessageReceiverDTO;
import org.cassava.model.dto.PlantingMonthAreaDTO;
import org.cassava.model.dto.PlantingMonthDTO;
import org.cassava.model.dto.PlantingYearDTO;
import org.cassava.services.FarmerService;
import org.cassava.services.FieldService;
import org.cassava.services.MessageService;
import org.cassava.services.MessagereceiverService;
import org.cassava.services.OrganizationService;
import org.cassava.services.PlantingService;
import org.cassava.services.RoleService;
import org.cassava.services.StaffService;
import org.cassava.services.UserService;
import org.cassava.util.MvcHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@Autowired
	MessageService messageService;

	@Autowired
	MessagereceiverService messagereceiverService;

	@Autowired
	UserService userService;

	@Autowired
	OrganizationService organizationService;

	@Autowired
	StaffService staffService;

	@Autowired
	FarmerService farmerService;

	@Autowired
	FieldService fieldService;

	@Autowired
	PlantingService plantingService;

	@Autowired
	RoleService roleService;

	public int countAmountField(String username, String role) {

		int countAmount = 0;
		User user = userService.findByUsername(username);

		if (role.equals("systemAdmin")) {
			countAmount = fieldService.findAll().size();
		} else {
			countAmount = fieldService.findByUserInField(user.getUserId()).size();
		}

		return countAmount;
	}

	public ArrayList<Double> sumAmountPieField(String username, String role) {

		ArrayList<Double> sumFields = new ArrayList<Double>();
		User user = userService.findByUsername(username);

		// List<Field> fields = fieldService.findByUserInField(user.getUserId());
		if (role.equals("farmer")) {
			List<Object> fields = fieldService.findNumberOfAllFieldInRegionByUserInField(username);
			for (Object object : fields) {
				Object[] ob = (Object[]) object;

				sumFields.add(Double.parseDouble(ob[1].toString()));

			}
		} else if (role.equals("staff")) {
			List<Object> fields = fieldService.findNumberOfAllFieldInRegionByUserInField(username);
			for (Object object : fields) {
				Object[] ob = (Object[]) object;

				sumFields.add(Double.parseDouble(ob[1].toString()));

			}
		} else if (role.equals("systemAdmin")) {
			List<Object> fields = fieldService.findNumberOfAllFieldInRegion();
			for (Object object : fields) {
				Object[] ob = (Object[]) object;

				sumFields.add(Double.parseDouble(ob[1].toString()));

			}
		} else if (role.equals("fieldRegistrar")) {
			List<Object> fields = fieldService.findNumberOfAllFieldInRegionByOrganization(username);
			for (Object object : fields) {
				Object[] ob = (Object[]) object;

				sumFields.add(Double.parseDouble(ob[1].toString()));

			}
		}

		return sumFields;
	}

	public ArrayList<String> chartPieRegion(String username, String role) {
		// chart Pie
		User user = userService.findByUsername(username);
		ArrayList<String> nameRegions = new ArrayList<String>();
		if (role.equals("farmer")) {
			for (Object object : fieldService.findNumberOfAllFieldInRegionByUserInField(username)) {
				Object[] ob = (Object[]) object;

				nameRegions.add(ob[0].toString());

			}
		} else if (role.equals("staff")) {
			for (Object object : fieldService.findNumberOfAllFieldInRegionByUserInField(username)) {
				Object[] ob = (Object[]) object;

				nameRegions.add(ob[0].toString());

			}
		} else if (role.equals("systemAdmin")) {
			for (Object object : fieldService.findNumberOfAllFieldInRegion()) {
				Object[] ob = (Object[]) object;

				nameRegions.add(ob[0].toString());

			}
		} else if (role.equals("fieldRegistrar")) {
			for (Object object : fieldService.findNumberOfAllFieldInRegionByUserInField(username)) {
				Object[] ob = (Object[]) object;

				nameRegions.add(ob[0].toString());

			}
		}

		return nameRegions;
	}

	// linechart1 plantingChart5Year(Year)
	public ArrayList<Integer> plantingChartFiveYear(String username, String role) {

		ArrayList<Integer> years = new ArrayList<Integer>();

		int year = (new Date().getYear() + 1900) - 4;

		if (role.equals("farmer") || role.equals("staff")) {
			for (int i = year; i < year + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService
						.findNumberOfAllPlantingInFiveYearByOrganizationAndUserInField(username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						years.add(i);
						// plantingChartYearDTO.getAmounts().add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					years.add(i);
					// plantingChartYearDTO.getAmounts().add(0.0);
				}
				checkYear = 0;

			}
		} else if (role.equals("systemAdmin")) {
			for (int i = year; i < year + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findNumberOfAllPlantingInFiveYear()) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						years.add(i);
						// plantingChartYearDTO.getAmounts().add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					years.add(i);
					// plantingChartYearDTO.getAmounts().add(0.0);
				}
				checkYear = 0;

			}
		} else if (role.equals("fieldRegistrar")) {
			for (int i = year; i < year + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findNumberOfAllPlantingInFiveYearByOrganization(username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						years.add(i);
						// plantingChartYearDTO.getAmounts().add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					years.add(i);
					// plantingChartYearDTO.getAmounts().add(0.0);
				}
				checkYear = 0;

			}
		}

		return years;
	}

	// linechart1 plantingChart5Year(Year)
	public ArrayList<Double> plantingChartFiveMonth(String username, String role) {

		ArrayList<Double> amounts = new ArrayList<Double>();

		int year = (new Date().getYear() + 1900) - 4;

		if (role.equals("farmer") || role.equals("staff")) {
			for (int i = year; i < year + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService
						.findNumberOfAllPlantingInFiveYearByOrganizationAndUserInField(username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						amounts.add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					amounts.add(0.0);
				}
				checkYear = 0;

			}
		} else if (role.equals("systemAdmin")) {
			for (int i = year; i < year + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findNumberOfAllPlantingInFiveYear()) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						amounts.add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					amounts.add(0.0);
				}
				checkYear = 0;

			}
		} else if (role.equals("fieldRegistrar")) {
			for (int i = year; i < year + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findNumberOfAllPlantingInFiveYearByOrganization(username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						amounts.add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					amounts.add(0.0);
				}
				checkYear = 0;

			}
		}

		return amounts;
	}

	// chart Disease lineChart2(Year)
	public ArrayList<Integer> plantingDiseaseChartFiveYear(String username, String role) {

		ArrayList<Integer> years = new ArrayList<Integer>();
		int year2 = (new Date().getYear() + 1900) - 4;

		if (role.equals("farmer") || role.equals("staff")) {
			for (int i = year2; i < year2 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService
						.findNumberOfAllDiseaseInFiveYearByOrganizationAndUserInField("Complete", username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						years.add(i);
						// plantingChartYearDTO2.getAmounts().add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					years.add(i);
					// plantingChartYearDTO2.getAmounts().add(0.0);
				}
				checkYear = 0;

			}
		} else if (role.equals("systemAdmin")) {
			for (int i = year2; i < year2 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findNumberOfAllDiseaseInFiveYear("Complete")) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						years.add(i);
						// plantingChartYearDTO2.getAmounts().add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					years.add(i);
					// plantingChartYearDTO2.getAmounts().add(0.0);
				}
				checkYear = 0;

			}
		} else if (role.equals("fieldRegistrar")) {
			for (int i = year2; i < year2 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findNumberOfAllDiseaseInFiveYearByOrganization("Complete",
						username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						years.add(i);
						// plantingChartYearDTO2.getAmounts().add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					years.add(i);
					// plantingChartYearDTO2.getAmounts().add(0.0);
				}
				checkYear = 0;

			}
		}

		return years;

	}

	// chart Disease lineChart2(Month)
	public ArrayList<Double> plantingDiseaseChartFiveMonth(String username, String role) {

		ArrayList<Double> amounts = new ArrayList<Double>();
		int year2 = (new Date().getYear() + 1900) - 4;

		if (role.equals("farmer") || role.equals("staff")) {
			for (int i = year2; i < year2 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService
						.findNumberOfAllDiseaseInFiveYearByOrganizationAndUserInField("Complete", username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						amounts.add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					amounts.add(0.0);
				}
				checkYear = 0;

			}
		} else if (role.equals("systemAdmin")) {
			for (int i = year2; i < year2 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findNumberOfAllDiseaseInFiveYear("Complete")) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						amounts.add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					amounts.add(0.0);
				}
				checkYear = 0;

			}
		} else if (role.equals("fieldRegistrar")) {
			for (int i = year2; i < year2 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findNumberOfAllDiseaseInFiveYearByOrganization("Complete",
						username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						amounts.add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					amounts.add(0.0);
				}
				checkYear = 0;

			}
		}

		return amounts;

	}

	// chart NaturalEnemy lineChart2
	public ArrayList<Integer> plantingChartYearNaturalEnemyYear(String username, String role) {

		ArrayList<Integer> years = new ArrayList<Integer>();
		int year3 = (new Date().getYear() + 1900) - 4;

		if (role.equals("farmer") || role.equals("staff")) {
			for (int i = year3; i < year3 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findNumberOfAllNaturalEnemyInFiveYearByUser("Complete",
						username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						years.add(i);
						// plantingChartYearNaturalEnemy.getAmounts().add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					years.add(i);
					// plantingChartYearNaturalEnemy.getAmounts().add(0.0);
				}
				checkYear = 0;

			}
		} else if (role.equals("systemAdmin")) {
			for (int i = year3; i < year3 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findNumberOfAllNaturalEnemyInFiveYear("Complete")) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						years.add(i);
						// plantingChartYearNaturalEnemy.getAmounts().add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					years.add(i);
					// plantingChartYearNaturalEnemy.getAmounts().add(0.0);
				}
				checkYear = 0;

			}
		} else if (role.equals("fieldRegistrar")) {
			for (int i = year3; i < year3 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findNumberOfAllNaturalEnemyInFiveYearByOrganization("Complete",
						username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						years.add(i);
						// plantingChartYearNaturalEnemy.getAmounts().add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					years.add(i);
					// plantingChartYearNaturalEnemy.getAmounts().add(0.0);
				}
				checkYear = 0;

			}
		}

		return years;
	}

	// chart NaturalEnemy lineChart2
	public ArrayList<Double> plantingChartYearNaturalEnemyMonth(String username, String role) {

		ArrayList<Double> amounts = new ArrayList<Double>();
		int year3 = (new Date().getYear() + 1900) - 4;

		if (role.equals("farmer") || role.equals("staff")) {
			for (int i = year3; i < year3 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findNumberOfAllNaturalEnemyInFiveYearByUser("Complete",
						username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						amounts.add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					amounts.add(0.0);
				}
				checkYear = 0;

			}
		} else if (role.equals("systemAdmin")) {
			for (int i = year3; i < year3 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findNumberOfAllNaturalEnemyInFiveYear("Complete")) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						amounts.add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					amounts.add(0.0);
				}
				checkYear = 0;

			}
		} else if (role.equals("fieldRegistrar")) {
			for (int i = year3; i < year3 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findNumberOfAllNaturalEnemyInFiveYearByOrganization("Complete",
						username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						amounts.add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					amounts.add(0.0);
				}
				checkYear = 0;

			}
		}

		return amounts;
	}

	// chart Pest lineChart2 (Year)
	public ArrayList<Integer> plantingChartYearPestYear(String username, String role) {

		ArrayList<Integer> years = new ArrayList<Integer>();
		int year4 = (new Date().getYear() + 1900) - 4;

		if (role.equals("farmer") || role.equals("staff")) {
			for (int i = year4; i < year4 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findNumberOfAllPestInFiveYearByUser("Complete", username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						years.add(i);
						// plantingChartYearPest.getAmounts().add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					years.add(i);
					// plantingChartYearPest.getAmounts().add(0.0);
				}
				checkYear = 0;

			}
		} else if (role.equals("systemAdmin")) {
			for (int i = year4; i < year4 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findNumberOfAllPestInFiveYear("Complete")) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						years.add(i);
						// plantingChartYearPest.getAmounts().add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					years.add(i);
					// plantingChartYearPest.getAmounts().add(0.0);
				}
				checkYear = 0;

			}
		} else if (role.equals("fieldRegistrar")) {
			for (int i = year4; i < year4 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findNumberOfAllPestInFiveYearByOrganization("Complete",
						username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						years.add(i);
						// plantingChartYearPest.getAmounts().add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					years.add(i);
					// plantingChartYearPest.getAmounts().add(0.0);
				}
				checkYear = 0;

			}
		}

		return years;
	}

	// chart Pest lineChart2 (Year)
	public ArrayList<Double> plantingChartYearPestMonth(String username, String role) {

		ArrayList<Double> amounts = new ArrayList<Double>();
		int year4 = (new Date().getYear() + 1900) - 4;

		if (role.equals("farmer") || role.equals("staff")) {
			for (int i = year4; i < year4 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findNumberOfAllPestInFiveYearByUser("Complete", username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						amounts.add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					amounts.add(0.0);
				}
				checkYear = 0;

			}
		} else if (role.equals("systemAdmin")) {
			for (int i = year4; i < year4 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findNumberOfAllPestInFiveYear("Complete")) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						amounts.add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					amounts.add(0.0);
				}
				checkYear = 0;

			}
		} else if (role.equals("fieldRegistrar")) {
			for (int i = year4; i < year4 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findNumberOfAllPestInFiveYearByOrganization("Complete",
						username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						amounts.add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					amounts.add(0.0);
				}
				checkYear = 0;

			}
		}

		return amounts;
	}

	// chart Disease lineChart5 (Year) จำนวนการสำรวจ (ครั้ง)
	public ArrayList<Integer> plantingChartYearDiseaseYear(String username, String role) {

		ArrayList<Integer> years = new ArrayList<Integer>();
		int year5 = (new Date().getYear() + 1900) - 4;

		if (role.equals("farmer") || role.equals("staff")) {
			for (int i = year5; i < year5 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findCountDiseaseInFiveYearByUser("Complete", username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						years.add(i);
						// plantingChartYearDisease.getAmounts().add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					years.add(i);
					// plantingChartYearDisease.getAmounts().add(0.0);
				}
				checkYear = 0;

			}
		} else if (role.equals("systemAdmin")) {
			for (int i = year5; i < year5 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findCountDiseaseInFiveYear("Complete")) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						years.add(i);
						// plantingChartYearDisease.getAmounts().add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					years.add(i);
					// plantingChartYearDisease.getAmounts().add(0.0);
				}
				checkYear = 0;

			}
		} else if (role.equals("fieldRegistrar")) {
			for (int i = year5; i < year5 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findCountDiseaseInFiveYearByOrganization("Complete", username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						years.add(i);
						// plantingChartYearDisease.getAmounts().add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					years.add(i);
					// plantingChartYearDisease.getAmounts().add(0.0);
				}
				checkYear = 0;

			}
		}

		return years;
	}

	// chart Disease lineChart5 (Month) จำนวนการสำรวจ (ครั้ง)
	public ArrayList<Double> plantingChartYearDiseaseMonth(String username, String role) {

		ArrayList<Double> amounts = new ArrayList<Double>();
		int year5 = (new Date().getYear() + 1900) - 4;

		if (role.equals("farmer") || role.equals("staff")) {
			for (int i = year5; i < year5 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findCountDiseaseInFiveYearByUser("Complete", username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						amounts.add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					amounts.add(0.0);
				}
				checkYear = 0;

			}
		} else if (role.equals("systemAdmin")) {
			for (int i = year5; i < year5 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findCountDiseaseInFiveYear("Complete")) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						amounts.add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					amounts.add(0.0);
				}
				checkYear = 0;

			}
		} else if (role.equals("fieldRegistrar")) {
			for (int i = year5; i < year5 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findCountDiseaseInFiveYearByOrganization("Complete", username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						amounts.add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					amounts.add(0.0);
				}
				checkYear = 0;

			}
		}

		return amounts;
	}

	// chart NaturalEnemy lineChart5 (Year) จำนวนการสำรวจ (ครั้ง)
	public ArrayList<Integer> plantingChartYearNaturalEnemyC5Year(String username, String role) {

		ArrayList<Integer> years = new ArrayList<Integer>();
		int year6 = (new Date().getYear() + 1900) - 4;

		if (role.equals("farmer") || role.equals("staff")) {
			for (int i = year6; i < year6 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findCountNaturalEnemyInFiveYearByUser("Complete", username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						years.add(i);
						// plantingChartYearNaturalEnemyC5.getAmounts().add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					years.add(i);
					// plantingChartYearNaturalEnemyC5.getAmounts().add(0.0);
				}
				checkYear = 0;

			}
		} else if (role.equals("systemAdmin")) {
			for (int i = year6; i < year6 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findCountNaturalEnemyInFiveYear("Complete")) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						years.add(i);
						// plantingChartYearNaturalEnemyC5.getAmounts().add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					years.add(i);
					// plantingChartYearNaturalEnemyC5.getAmounts().add(0.0);
				}
				checkYear = 0;

			}
		} else if (role.equals("fieldRegistrar")) {
			for (int i = year6; i < year6 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findCountNaturalEnemyInFiveYearByOrganization("Complete",
						username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						years.add(i);
						// plantingChartYearNaturalEnemyC5.getAmounts().add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					years.add(i);
					// plantingChartYearNaturalEnemyC5.getAmounts().add(0.0);
				}
				checkYear = 0;

			}
		}
		return years;
	}

	// chart NaturalEnemy lineChart5 (Year) จำนวนการสำรวจ (ครั้ง)
	public ArrayList<Double> plantingChartYearNaturalEnemyC5Month(String username, String role) {

		ArrayList<Double> amounts = new ArrayList<Double>();
		int year6 = (new Date().getYear() + 1900) - 4;

		if (role.equals("farmer") || role.equals("staff")) {
			for (int i = year6; i < year6 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findCountNaturalEnemyInFiveYearByUser("Complete", username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						amounts.add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					amounts.add(0.0);
				}
				checkYear = 0;

			}
		} else if (role.equals("systemAdmin")) {
			for (int i = year6; i < year6 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findCountNaturalEnemyInFiveYear("Complete")) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						amounts.add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					amounts.add(0.0);
				}
				checkYear = 0;

			}
		} else if (role.equals("fieldRegistrar")) {
			for (int i = year6; i < year6 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findCountNaturalEnemyInFiveYearByOrganization("Complete",
						username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						amounts.add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					amounts.add(0.0);
				}
				checkYear = 0;

			}
		}
		return amounts;
	}

	// chart Pest lineChart5 Year จำนวนการสำรวจ (ครั้ง)
	public ArrayList<Integer> plantingChartYearPestC5Year(String username, String role) {
		ArrayList<Integer> years = new ArrayList<Integer>();
		int year7 = (new Date().getYear() + 1900) - 4;

		if (role.equals("farmer") || role.equals("staff")) {
			for (int i = year7; i < year7 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findCountPestInFiveYearByUser("Complete", username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						years.add(i);
						// plantingChartYearPestC5.getAmounts().add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					years.add(i);
					// plantingChartYearPestC5.getAmounts().add(0.0);
				}
				checkYear = 0;
			}
		} else if (role.equals("systemAdmin")) {
			for (int i = year7; i < year7 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findCountPestInFiveYear("Complete")) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						years.add(i);
						// plantingChartYearPestC5.getAmounts().add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					years.add(i);
					// plantingChartYearPestC5.getAmounts().add(0.0);
				}
				checkYear = 0;
			}
		} else if (role.equals("fieldRegistrar")) {
			for (int i = year7; i < year7 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findCountPestInFiveYearByOrganization("Complete", username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						years.add(i);
						// plantingChartYearPestC5.getAmounts().add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					years.add(i);
					// lantingChartYearPestC5.getAmounts().add(0.0);
				}
				checkYear = 0;
			}
		}

		return years;
	}

	public ArrayList<Double> plantingChartYearPestC5Month(String username, String role) {
		ArrayList<Double> amounts = new ArrayList<Double>();
		int year7 = (new Date().getYear() + 1900) - 4;

		if (role.equals("farmer") || role.equals("staff")) {
			for (int i = year7; i < year7 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findCountPestInFiveYearByUser("Complete", username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						amounts.add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					amounts.add(0.0);
				}
				checkYear = 0;
			}
		} else if (role.equals("systemAdmin")) {
			for (int i = year7; i < year7 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findCountPestInFiveYear("Complete")) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						amounts.add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					amounts.add(0.0);
				}
				checkYear = 0;
			}
		} else if (role.equals("fieldRegistrar")) {
			for (int i = year7; i < year7 + 5; i++) {
				int checkYear = 0;
				for (Object object : plantingService.findCountPestInFiveYearByOrganization("Complete", username)) {
					Object[] ob = (Object[]) object;
					if (i == Integer.parseInt(ob[0].toString())) {
						// plantingChartYearDTO.getYears().add(Integer.parseInt(ob[0].toString()));
						amounts.add(Double.parseDouble(ob[1].toString()));
						checkYear = 1;
					}

				}
				if (checkYear == 0) {
					amounts.add(0.0);
				}
				checkYear = 0;
			}
		}

		return amounts;
	}

	// ---- 2 ----
	public ArrayList<String> plantingRegionChart(ArrayList<Integer> months2, ArrayList<Integer> years2, String username,
			String role) {
		// chart PieRegion2

		ArrayList<String> regions = new ArrayList<String>();
		User user = userService.findByUsername(username);
		if (role.equals("farmer") || role.equals("staff")) {
			if (plantingService.findNumberOfAllPlantingByMonthAndYearAndUserInfieldInRegion(months2, years2,
					username) != null) {
				for (Object object : plantingService
						.findNumberOfAllPlantingByMonthAndYearAndUserInfieldInRegion(months2, years2, username)) {
					Object[] ob = (Object[]) object;

					regions.add(ob[0].toString());
					// plantingRegionChart.getAmountFields().add(Double.parseDouble(ob[1].toString()));

				}
			} else {
				return regions;
			}

		} else if (role.equals("systemAdmin")) {
			if (plantingService.findNumberOfAllPlantingByMonthAndYearInRegion(months2, years2) != null) {
				for (Object object : plantingService.findNumberOfAllPlantingByMonthAndYearInRegion(months2, years2)) {
					Object[] ob = (Object[]) object;

					regions.add(ob[0].toString());
					// plantingRegionChart.getAmountFields().add(Double.parseDouble(ob[1].toString()));

				}
			} else {
				return regions;
			}

		} else if (role.equals("fieldRegistrar")) {
			if (plantingService.findNumberOfAllPlantingByMonthAndYearandOrganizationInRegion(months2, years2,
					user.getStaff()) != null) {
				for (Object object : plantingService.findNumberOfAllPlantingByMonthAndYearandOrganizationInRegion(
						months2, years2, user.getStaff())) {
					Object[] ob = (Object[]) object;

					regions.add(ob[0].toString());
					// plantingRegionChart.getAmountFields().add(Double.parseDouble(ob[1].toString()));

				}
			} else {
				return regions;
			}
		}

		return regions;
	}

	public ArrayList<Double> sumAmountPieRegion2(ArrayList<Integer> months2, ArrayList<Integer> years2, String username,
			String role) {

		ArrayList<Double> amountPieRegion2 = new ArrayList<Double>();
		User user = userService.findByUsername(username);
		if (role.equals("farmer") || role.equals("staff")) {
			if (plantingService.findNumberOfAllPlantingByMonthAndYearAndUserInfieldInRegion(months2, years2,
					username) != null) {
				for (Object object : plantingService
						.findNumberOfAllPlantingByMonthAndYearAndUserInfieldInRegion(months2, years2, username)) {
					Object[] ob = (Object[]) object;

					amountPieRegion2.add(Double.parseDouble(ob[1].toString()));

				}
			} else {
				return amountPieRegion2;
			}

		} else if (role.equals("systemAdmin")) {
			if (plantingService.findNumberOfAllPlantingByMonthAndYearInRegion(months2, years2) != null) {
				for (Object object : plantingService.findNumberOfAllPlantingByMonthAndYearInRegion(months2, years2)) {
					Object[] ob = (Object[]) object;

					amountPieRegion2.add(Double.parseDouble(ob[1].toString()));

				}
			} else {
				return amountPieRegion2;
			}

		} else if (role.equals("fieldRegistrar")) {
			if (plantingService.findNumberOfAllPlantingByMonthAndYearandOrganizationInRegion(months2, years2,
					user.getStaff()) != null) {
				for (Object object : plantingService.findNumberOfAllPlantingByMonthAndYearandOrganizationInRegion(
						months2, years2, user.getStaff())) {
					Object[] ob = (Object[]) object;

					amountPieRegion2.add(Double.parseDouble(ob[1].toString()));

				}
			} else {
				return amountPieRegion2;
			}
		}

		return amountPieRegion2;
	}

	public ArrayList<String> varietyPieChart(ArrayList<Integer> months2, ArrayList<Integer> years2, String username,
			String role) {

		ArrayList<String> names = new ArrayList<String>();
		User user = userService.findByUsername(username);

		if (role.equals("farmer") || role.equals("staff")) {
			for (Object object : plantingService.findNumberOfAllPlantingByMonthAndYearAndUserInfieldInVariety(months2,
					years2, username)) {
				Object[] ob = (Object[]) object;

				names.add(ob[0].toString());
				// varietyChart.getAmountFields().add(Double.parseDouble(ob[1].toString()));

			}
		} else if (role.equals("systemAdmin")) {
			for (Object object : plantingService.findNumberOfAllPlantingByMonthAndYearInVariety(months2, years2)) {
				Object[] ob = (Object[]) object;

				names.add(ob[0].toString());
				// varietyChart.getAmountFields().add(Double.parseDouble(ob[1].toString()));

			}
		} else if (role.equals("fieldRegistrar")) {
			for (Object object : plantingService.findNumberOfAllPlantingByMonthAndYearandOrganizationInVariety(months2,
					years2, user.getStaff())) {
				Object[] ob = (Object[]) object;

				names.add(ob[0].toString());
				// varietyChart.getAmountFields().add(Double.parseDouble(ob[1].toString()));

			}
		}

		return names;
	}

	public ArrayList<Double> sumVarietyPieChart(ArrayList<Integer> months2, ArrayList<Integer> years2, String username,
			String role) {

		ArrayList<Double> amountFields = new ArrayList<Double>();
		User user = userService.findByUsername(username);

		if (role.equals("farmer") || role.equals("staff")) {
			for (Object object : plantingService.findNumberOfAllPlantingByMonthAndYearAndUserInfieldInVariety(months2,
					years2, username)) {
				Object[] ob = (Object[]) object;

				amountFields.add(Double.parseDouble(ob[1].toString()));

			}
		} else if (role.equals("systemAdmin")) {
			for (Object object : plantingService.findNumberOfAllPlantingByMonthAndYearInVariety(months2, years2)) {
				Object[] ob = (Object[]) object;

				amountFields.add(Double.parseDouble(ob[1].toString()));

			}
		} else if (role.equals("fieldRegistrar")) {
			for (Object object : plantingService.findNumberOfAllPlantingByMonthAndYearandOrganizationInVariety(months2,
					years2, user.getStaff())) {
				Object[] ob = (Object[]) object;
				amountFields.add(Double.parseDouble(ob[1].toString()));

			}
		}

		return amountFields;
	}

	// chart lineChart3
	// YearNow
	public PlantingMonthAreaDTO plantingMonthAndYearArea(int year, String username, String role) {
		User user = userService.findByUsername(username);
		PlantingMonthAreaDTO plantingMonthAreaDTO = new PlantingMonthAreaDTO();
		List<Object> objects = new ArrayList<>();
		if (role.equals("farmer") || role.equals("staff")) {
			objects = plantingService.findNumberOfPlantingAllMonthInThreeYearByUserInfield(year, username);
		} else if (role.equals("systemAdmin")) {
			objects = plantingService.findNumberOfPlantingAllMonthInThreeYear(year);
		} else if (role.equals("fieldRegistrar")) {
			objects = plantingService.findNumberOfPlantingAllMonthInThreeYearByOrganization(year, user.getStaff());
		}

		if (objects.size() == 0) {
			plantingMonthAreaDTO.getYears().add(year + 543);
			for (int i = 0; i < 12; i++) {
				plantingMonthAreaDTO.getNums().add(0.0);
			}
		} else {
			for (Object object : objects) {
				Object[] ob = (Object[]) object;

				plantingMonthAreaDTO.getYears().add(Integer.parseInt(ob[0].toString()) + 543);

				String m2 = "";
				if (Integer.parseInt(ob[1].toString()) == 1) {
					m2 = "มกราคม";
				} else if (Integer.parseInt(ob[1].toString()) == 2) {
					m2 = "กุมภาพันธ์";
				} else if (Integer.parseInt(ob[1].toString()) == 3) {
					m2 = "มีนาคม";
				} else if (Integer.parseInt(ob[1].toString()) == 4) {
					m2 = "เมษายน";
				} else if (Integer.parseInt(ob[1].toString()) == 5) {
					m2 = "พฤษภาคม";
				} else if (Integer.parseInt(ob[1].toString()) == 6) {
					m2 = "มิถุนายน";
				} else if (Integer.parseInt(ob[1].toString()) == 7) {
					m2 = "กรกฏาคม";
				} else if (Integer.parseInt(ob[1].toString()) == 8) {
					m2 = "สิงหาคม";
				} else if (Integer.parseInt(ob[1].toString()) == 9) {
					m2 = "กันยายน";
				} else if (Integer.parseInt(ob[1].toString()) == 10) {
					m2 = "ตุลาคม";
				} else if (Integer.parseInt(ob[1].toString()) == 11) {
					m2 = "พฤศจิกายน";
				} else if (Integer.parseInt(ob[1].toString()) == 12) {
					m2 = "ธันวาคม";
				}
				plantingMonthAreaDTO.getMonths().add(m2);

				if (ob[2].toString() != null) {
					plantingMonthAreaDTO.getNums().add(Double.parseDouble(ob[2].toString()));
				} else {
					plantingMonthAreaDTO.getNums().add(0.0);
				}

			}
		}
		return plantingMonthAreaDTO;
	}

	public ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsDiseases(ArrayList<Integer> months2,
			ArrayList<Integer> years2, String username, String role) {

		ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsDiseases = new ArrayList<FieldAvgPercentAndDamageLevelDTO>();
		List<Object> objects = new ArrayList<>();
		if (role.equals("farmer") || role.equals("staff")) {
			objects = fieldService.findAvgPercentAndDamageLeveLHasDiseaseByMonthsAndYearsByUserInField(months2, years2,
					username);
		} else if (role.equals("systemAdmin")) {
			objects = fieldService.findAvgPercentAndDamageLeveLHasDiseaseByMonthsAndYears(months2, years2);
		} else if (role.equals("fieldRegistrar")) {
			objects = fieldService.findAvgPercentAndDamageLeveLHasDiseaseByMonthsAndYearsByOrganization(months2, years2,
					username);
		}
		for (Object object : objects) {
			Object[] ob = (Object[]) object;
			FieldAvgPercentAndDamageLevelDTO percentAndDamageLevelDTO = new FieldAvgPercentAndDamageLevelDTO();
			percentAndDamageLevelDTO.setNameOfAvgs(ob[0].toString());

			percentAndDamageLevelDTO
					.setDamageLevels((float) (Math.floor((Float.parseFloat((ob[1].toString()))) * 100) / 100));
			percentAndDamageLevelDTO
					.setAvgPercents((float) (Math.floor((Float.parseFloat((ob[2].toString()))) * 100) / 100));
			avgPercentsDiseases.add(percentAndDamageLevelDTO);

		}
		return avgPercentsDiseases;

	}

	public ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsPests(ArrayList<Integer> months2,
			ArrayList<Integer> years2, String username, String role) {
		ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsPests = new ArrayList<FieldAvgPercentAndDamageLevelDTO>();
		List<Object> objects = new ArrayList<>();
		if (role.equals("farmer") || role.equals("staff")) {
			objects = fieldService.findAvgPercentAndDamageLeveLHasPestByMonthsAndYearsByUserInField(months2, years2,
					username);
		} else if (role.equals("systemAdmin")) {
			objects = fieldService.findAvgPercentAndDamageLeveLHasPestByMonthsAndYears(months2, years2);
		} else if (role.equals("fieldRegistrar")) {
			objects = fieldService.findAvgPercentAndDamageLeveLHasPestByMonthsAndYearsByOrganization(months2, years2,
					username);
		}
		for (Object object : objects) {
			Object[] ob = (Object[]) object;
			FieldAvgPercentAndDamageLevelDTO percentAndDamageLevelDTO = new FieldAvgPercentAndDamageLevelDTO();
			percentAndDamageLevelDTO.setNameOfAvgs(ob[0].toString());
			percentAndDamageLevelDTO
					.setDamageLevels((float) (Math.floor((Float.parseFloat((ob[1].toString()))) * 100) / 100));
			percentAndDamageLevelDTO
					.setAvgPercents((float) (Math.floor((Float.parseFloat((ob[2].toString()))) * 100) / 100));
			avgPercentsPests.add(percentAndDamageLevelDTO);

		}
		return avgPercentsPests;

	}

	public ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsNaturalEnemys(ArrayList<Integer> months2,
			ArrayList<Integer> years2, String username, String role) {
		// --- NaturalEnemy ----
		ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsNaturalEnemys = new ArrayList<FieldAvgPercentAndDamageLevelDTO>();
		List<Object> objects = new ArrayList<>();
		if (role.equals("farmer") || role.equals("staff")) {
			objects = fieldService.findAvgPercentAndDamageLeveLHasNaturalEnemyByMonthsAndYearsByUserInField(months2,
					years2, username);
		} else if (role.equals("systemAdmin")) {
			objects = fieldService.findAvgPercentAndDamageLeveLHasNaturalEnemyByMonthsAndYears(months2, years2);
		} else if (role.equals("fieldRegistrar")) {
			objects = fieldService.findAvgPercentAndDamageLeveLHasNaturalEnemyByMonthsAndYearsByOrganization(months2,
					years2, username);
		}

		for (Object object : objects) {
			Object[] ob = (Object[]) object;
			FieldAvgPercentAndDamageLevelDTO percentAndDamageLevelDTO = new FieldAvgPercentAndDamageLevelDTO();
			percentAndDamageLevelDTO.setNameOfAvgs(ob[0].toString());
			percentAndDamageLevelDTO
					.setDamageLevels((float) (Math.floor((Float.parseFloat((ob[1].toString()))) * 100) / 100));
			percentAndDamageLevelDTO
					.setAvgPercents((float) (Math.floor((Float.parseFloat((ob[2].toString()))) * 100) / 100));
			avgPercentsNaturalEnemys.add(percentAndDamageLevelDTO);

		}
		return avgPercentsNaturalEnemys;
	}

	@RequestMapping(value = "/home/systemadmin", method = RequestMethod.GET)
	public String homeSystemAdminIndex(Model m, Authentication authentication) {
		// --- chart ---
		String role = "";
		// --- chart ajax (1) ----

		ArrayList<PlantingYearDTO> years = new ArrayList<>();
		Year ynewEn = Year.now();
		String ynewTh = ynewEn.hashCode() + "543";
		
		if (plantingService.findAllYearInLastUpdateDate().size() > 0) {
			for (int y : plantingService.findAllYearInLastUpdateDate()) {
				String y2 = "";
				y2 = y + 543 + "";

				years.add(new PlantingYearDTO(y2, y));
			}
		} else {
			years.add(new PlantingYearDTO(ynewTh, ynewEn.hashCode()));
			
		}

		m.addAttribute("years", years);

		ArrayList<PlantingMonthDTO> months = new ArrayList<>();
		for (int mon : plantingService.findAllMonthInLastUpdateDate()) {
			String m2 = "";
			if (mon == 1) {
				m2 = "มกราคม";
			} else if (mon == 2) {
				m2 = "กุมภาพันธ์";
			} else if (mon == 3) {
				m2 = "มีนาคม";
			} else if (mon == 4) {
				m2 = "เมษายน";
			} else if (mon == 5) {
				m2 = "พฤษภาคม";
			} else if (mon == 6) {
				m2 = "มิถุนายน";
			} else if (mon == 7) {
				m2 = "กรกฏาคม";
			} else if (mon == 8) {
				m2 = "สิงหาคม";
			} else if (mon == 9) {
				m2 = "กันยายน";
			} else if (mon == 10) {
				m2 = "ตุลาคม";
			} else if (mon == 11) {
				m2 = "พฤศจิกายน";
			} else if (mon == 12) {
				m2 = "ธันวาคม";
			}
			months.add(new PlantingMonthDTO(m2, mon));

		}
		m.addAttribute("months", months);

		ArrayList<Integer> months2 = new ArrayList<>();
		months2.addAll(plantingService.findAllMonthInLastUpdateDate());
		ArrayList<Integer> years2 = new ArrayList<>();
		if (plantingService.findAllYearInLastUpdateDate().size() > 0) {
			years2.addAll(plantingService.findAllYearInLastUpdateDate());
		} else {
			years2.add(ynewEn.hashCode());
		}

		String username = MvcHelper.getUsername(authentication);
		User user = userService.findByUsername(username);

		role = "systemAdmin";
		// count
		int amountOrg = organizationService.findAll().size();
		m.addAttribute("amountOrg", amountOrg);
		List<String> status = new ArrayList<>();
		status.add("active");
		status.add("inactive");
		int amountStaff = staffService.findByListStatus(status).size();
		m.addAttribute("amountStaff", amountStaff);

		int amountFarmer = farmerService.findByListStatus(status).size();
		m.addAttribute("amountFarmer", amountFarmer);

		int amountField = countAmountField(username, "systemAdmin");
		m.addAttribute("amountField", amountField);

		// chart Pie
		ArrayList<Double> sumFields = new ArrayList<Double>();
		ArrayList<String> regionNames = new ArrayList<String>();
		// 1590. /// ArrayList<Double> amountFields = new ArrayList<Double>();
		regionNames = chartPieRegion(username, "systemAdmin");
		sumFields = sumAmountPieField(username, "systemAdmin");
		Double sum = 0.0;
		for (Double s : sumFields) {
			sum += s;
		}
		m.addAttribute("sum", Math.floor(sum * 100) / 100);
		m.addAttribute("sumFields", sumFields);
		m.addAttribute("regions", regionNames);

		// chart lineChart1
		ArrayList<Integer> yearsLineChart1 = plantingChartFiveYear(username, "systemAdmin");
		ArrayList<Double> amountsLineChart1 = plantingChartFiveMonth(username, "systemAdmin");
		m.addAttribute("yearsLineChart1", yearsLineChart1);
		m.addAttribute("amountsLineChart1", amountsLineChart1);

		// chart lineChart2
		ArrayList<Integer> yearsLineChart2 = plantingDiseaseChartFiveYear(username, "systemAdmin");
		ArrayList<Double> amountsLineChart2 = plantingDiseaseChartFiveMonth(username, "systemAdmin");
		m.addAttribute("yearsLineChart2", yearsLineChart2);
		m.addAttribute("amountsLineChart2", amountsLineChart2);

		// chart lineChart3
		ArrayList<Integer> yearsLineChart3 = plantingChartYearNaturalEnemyYear(username, "systemAdmin");
		ArrayList<Double> amountsLineChart3 = plantingChartYearNaturalEnemyMonth(username, "systemAdmin");
		m.addAttribute("yearsLineChart3", yearsLineChart3);
		m.addAttribute("amountsLineChart3", amountsLineChart3);

		// chart lineChart4
		ArrayList<Integer> yearsLineChart4 = plantingChartYearPestYear(username, "systemAdmin");
		ArrayList<Double> amountsLineChart4 = plantingChartYearPestMonth(username, "systemAdmin");
		m.addAttribute("yearsLineChart4", yearsLineChart4);
		m.addAttribute("amountsLineChart4", amountsLineChart4);

		// chart Disease lineChart5 จำนวนการสำรวจ (ครั้ง)
		// Disease
		ArrayList<Integer> yearsDiseaseLineChart5 = plantingChartYearDiseaseYear(username, "systemAdmin");
		ArrayList<Double> amountsDiseaseLineChart5 = plantingChartYearDiseaseMonth(username, "systemAdmin");
		m.addAttribute("yearsDiseaseLineChart5", yearsDiseaseLineChart5);
		m.addAttribute("amountsDiseaseLineChart5", amountsDiseaseLineChart5);

		// NaturalEnemy
		ArrayList<Integer> yearsNaturalEnemyLineChart5 = plantingChartYearNaturalEnemyC5Year(username, "systemAdmin");
		ArrayList<Double> amountsNaturalEnemyLineChart5 = plantingChartYearNaturalEnemyC5Month(username, "systemAdmin");
		m.addAttribute("yearsNaturalEnemyLineChart5", yearsNaturalEnemyLineChart5);
		m.addAttribute("amountsNaturalEnemyLineChart5", amountsNaturalEnemyLineChart5);

		// Pest
		ArrayList<Integer> yearsPestLineChart5 = plantingChartYearPestC5Year(username, "systemAdmin");
		ArrayList<Double> amountsPestLineChart5 = plantingChartYearPestC5Month(username, "systemAdmin");
		m.addAttribute("yearsPestLineChart5", yearsPestLineChart5);
		m.addAttribute("amountsPestLineChart5", amountsPestLineChart5);

		// ---- 2 ----
		double countPlantingYearMonth = 0;
		if (plantingService.findNumberOfAllPlantingByMonthAndYear(months2, years2) != null) {
			countPlantingYearMonth = (float) (Math
					.floor(plantingService.findNumberOfAllPlantingByMonthAndYear(months2, years2)) * 100) / 100;

		}
		int countPlanting = 0;
		if (plantingService.findCountPlantingByMonthAndYear(months2, years2) != null) {
			countPlanting = plantingService.findCountPlantingByMonthAndYear(months2, years2);
		}
		m.addAttribute("countPlantingYearMonth", countPlantingYearMonth);
		m.addAttribute("countPlanting", countPlanting);

		// chart PieRegion2
		ArrayList<String> plantingRegionPieRegion2 = plantingRegionChart(months2, years2, username, "systemAdmin");
		ArrayList<Double> sumPieRegion2 = new ArrayList<Double>();
		int checkDataRegionChart = 1;
		if (plantingRegionPieRegion2.size() == 0) {
			checkDataRegionChart = 0;
		}
		sumPieRegion2 = sumAmountPieRegion2(months2, years2, username, "systemAdmin");
		m.addAttribute("checkDataRegionChart", checkDataRegionChart);
		m.addAttribute("plantingRegionPieRegion2", plantingRegionPieRegion2);
		m.addAttribute("sumPieRegion2", sumPieRegion2);

		// chart PieRegion2
		ArrayList<String> pieNameVarietyCharts = varietyPieChart(months2, years2, username, "systemAdmin");
		ArrayList<Double> sumPieVarietyCharts = new ArrayList<Double>();
		int checkDataVarietyChart = 1;
		if (pieNameVarietyCharts.size() == 0) {
			checkDataVarietyChart = 0;
		}
		sumPieVarietyCharts = sumVarietyPieChart(months2, years2, username, "systemAdmin");
		m.addAttribute("checkDataVarietyChart", checkDataVarietyChart);
		m.addAttribute("pieNameVarietyCharts", pieNameVarietyCharts);
		m.addAttribute("sumPieVarietyCharts", sumPieVarietyCharts);

		// chart lineChart3
		// YearNow
		int year8 = (new Date().getYear() + 1900);
		PlantingMonthAreaDTO plantingMonthAreaDTO = plantingMonthAndYearArea(year8, username, "systemAdmin");
		m.addAttribute("plantingMonthArea", plantingMonthAreaDTO);

		// YearNow-1
		int year9 = year8 - 1;
		PlantingMonthAreaDTO plantingMonthAreaDTO2 = plantingMonthAndYearArea(year9, username, "systemAdmin");
		m.addAttribute("plantingMonthArea2", plantingMonthAreaDTO2);

		// YearNow-2
		int year10 = year9 - 1;
		PlantingMonthAreaDTO plantingMonthAreaDTO3 = plantingMonthAndYearArea(year10, username, "systemAdmin");
		m.addAttribute("plantingMonthArea3", plantingMonthAreaDTO3);

		// ---- survey ---
		// Disease
		int countDiseaseYearMonth = 0;
		if (fieldService.findNumberOfFieldHasDiseaseByMonthsAndYears(months2, years2) != null) {
			countDiseaseYearMonth = fieldService.findNumberOfFieldHasDiseaseByMonthsAndYears(months2, years2);
		}
		m.addAttribute("countDiseaseYearMonth", countDiseaseYearMonth);
		// Pest
		int countPestYearMonth = 0;
		if (fieldService.findNumberOfFieldHasPestByMonthsAndYears(months2, years2) != null) {
			countPestYearMonth = fieldService.findNumberOfFieldHasPestByMonthsAndYears(months2, years2);
		}
		m.addAttribute("countPestYearMonth", countPestYearMonth);
		// NaturalEnemy
		int countNaturalEnemyYearMonth = 0;
		if (fieldService.findNumberOfFieldHasNaturalEnemyByMonthsAndYears(months2, years2) != null) {
			countNaturalEnemyYearMonth = fieldService.findNumberOfFieldHasNaturalEnemyByMonthsAndYears(months2, years2);
		}
		m.addAttribute("countNaturalEnemyYearMonth", countNaturalEnemyYearMonth);

		// % AvgPercentAndDamageLevel
		// --- Diseases ---
		ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsDiseases = avgPercentsDiseases(months2, years2, username,
				"systemAdmin");
		m.addAttribute("checkDisease", avgPercentsDiseases.size());
		m.addAttribute("avgPercentsDiseases", avgPercentsDiseases);

		// --- Pest ----
		ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsPests = avgPercentsPests(months2, years2, username,
				"systemAdmin");
		m.addAttribute("checkPest", avgPercentsPests.size());
		m.addAttribute("avgPercentsPests", avgPercentsPests);

		// --- NaturalEnemy ----
		ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsNaturalEnemys = avgPercentsNaturalEnemys(months2, years2,
				username, "systemAdmin");
		m.addAttribute("checkNaturalEnemy", avgPercentsNaturalEnemys.size());
		m.addAttribute("avgPercentsNaturalEnemys", avgPercentsNaturalEnemys);

		m.addAttribute("role", role);
		return "pages/home";

	}

	@RequestMapping(value = "/home/staff", method = RequestMethod.GET)
	public String homeStaffIndex(Model m, Authentication authentication) {

		// --- chart ---
		String role = "staff";
		// --- chart ajax (1) ----

		ArrayList<PlantingYearDTO> years = new ArrayList<>();
		Year ynewEn = Year.now();
		String ynewTh = ynewEn.hashCode() + "543";
		
		if (plantingService.findAllYearInLastUpdateDate().size() > 0) {
			for (int y : plantingService.findAllYearInLastUpdateDate()) {
				String y2 = "";
				y2 = y + 543 + "";

				years.add(new PlantingYearDTO(y2, y));
			}
		} else {
			years.add(new PlantingYearDTO(ynewTh, ynewEn.hashCode()));
			
		}
		m.addAttribute("years", years);

		ArrayList<PlantingMonthDTO> months = new ArrayList<>();
		for (int mon : plantingService.findAllMonthInLastUpdateDate()) {
			String m2 = "";
			if (mon == 1) {
				m2 = "มกราคม";
			} else if (mon == 2) {
				m2 = "กุมภาพันธ์";
			} else if (mon == 3) {
				m2 = "มีนาคม";
			} else if (mon == 4) {
				m2 = "เมษายน";
			} else if (mon == 5) {
				m2 = "พฤษภาคม";
			} else if (mon == 6) {
				m2 = "มิถุนายน";
			} else if (mon == 7) {
				m2 = "กรกฏาคม";
			} else if (mon == 8) {
				m2 = "สิงหาคม";
			} else if (mon == 9) {
				m2 = "กันยายน";
			} else if (mon == 10) {
				m2 = "ตุลาคม";
			} else if (mon == 11) {
				m2 = "พฤศจิกายน";
			} else if (mon == 12) {
				m2 = "ธันวาคม";
			}
			months.add(new PlantingMonthDTO(m2, mon));

		}
		m.addAttribute("months", months);

		ArrayList<Integer> months2 = new ArrayList<>();
		months2.addAll(plantingService.findAllMonthInLastUpdateDate());
		ArrayList<Integer> years2 = new ArrayList<>();
		if (plantingService.findAllYearInLastUpdateDate().size() > 0) {
			years2.addAll(plantingService.findAllYearInLastUpdateDate());
		} else {
			years2.add(ynewEn.hashCode());
		}

		String username = MvcHelper.getUsername(authentication);
		User user = userService.findByUsername(username);
		int amountField = countAmountField(username, "staff");
		m.addAttribute("amountField", amountField);

		// chart Pie
		ArrayList<Double> sumFields = new ArrayList<Double>();
		ArrayList<String> regionNames = new ArrayList<String>();
		// 1590. /// ArrayList<Double> amountFields = new ArrayList<Double>();
		regionNames = chartPieRegion(username, "staff");
		sumFields = sumAmountPieField(username, "staff");
		Double sum = 0.0;
		for (Double s : sumFields) {
			sum += s;
		}
		m.addAttribute("sum", Math.floor(sum * 100) / 100);
		m.addAttribute("sumFields", sumFields);
		m.addAttribute("regions", regionNames);

		// chart lineChart1
		ArrayList<Integer> yearsLineChart1 = plantingChartFiveYear(username, "staff");
		ArrayList<Double> amountsLineChart1 = plantingChartFiveMonth(username, "staff");
		m.addAttribute("yearsLineChart1", yearsLineChart1);
		m.addAttribute("amountsLineChart1", amountsLineChart1);

		// chart lineChart2
		ArrayList<Integer> yearsLineChart2 = plantingDiseaseChartFiveYear(username, "staff");
		ArrayList<Double> amountsLineChart2 = plantingDiseaseChartFiveMonth(username, "staff");
		m.addAttribute("yearsLineChart2", yearsLineChart2);
		m.addAttribute("amountsLineChart2", amountsLineChart2);

		// chart lineChart3
		ArrayList<Integer> yearsLineChart3 = plantingChartYearNaturalEnemyYear(username, "staff");
		ArrayList<Double> amountsLineChart3 = plantingChartYearNaturalEnemyMonth(username, "staff");
		m.addAttribute("yearsLineChart3", yearsLineChart3);
		m.addAttribute("amountsLineChart3", amountsLineChart3);

		// chart lineChart4
		ArrayList<Integer> yearsLineChart4 = plantingChartYearPestYear(username, "staff");
		ArrayList<Double> amountsLineChart4 = plantingChartYearPestMonth(username, "staff");
		m.addAttribute("yearsLineChart4", yearsLineChart4);
		m.addAttribute("amountsLineChart4", amountsLineChart4);

		// chart Disease lineChart5 จำนวนการสำรวจ (ครั้ง)
		// Disease
		ArrayList<Integer> yearsDiseaseLineChart5 = plantingChartYearDiseaseYear(username, "staff");
		ArrayList<Double> amountsDiseaseLineChart5 = plantingChartYearDiseaseMonth(username, "staff");
		m.addAttribute("yearsDiseaseLineChart5", yearsDiseaseLineChart5);
		m.addAttribute("amountsDiseaseLineChart5", amountsDiseaseLineChart5);

		// NaturalEnemy
		ArrayList<Integer> yearsNaturalEnemyLineChart5 = plantingChartYearNaturalEnemyC5Year(username, "staff");
		ArrayList<Double> amountsNaturalEnemyLineChart5 = plantingChartYearNaturalEnemyC5Month(username, "staff");
		m.addAttribute("yearsNaturalEnemyLineChart5", yearsNaturalEnemyLineChart5);
		m.addAttribute("amountsNaturalEnemyLineChart5", amountsNaturalEnemyLineChart5);

		// Pest
		ArrayList<Integer> yearsPestLineChart5 = plantingChartYearPestC5Year(username, "staff");
		ArrayList<Double> amountsPestLineChart5 = plantingChartYearPestC5Month(username, "staff");
		m.addAttribute("yearsPestLineChart5", yearsPestLineChart5);
		m.addAttribute("amountsPestLineChart5", amountsPestLineChart5);

		// ---- 2 ---
		double countPlantingYearMonth;
		int countPlanting;
		if (plantingService.findNumberOfAllPlantingByMonthAndYearAndUserInfield(months2, years2, username) == null) {
			countPlantingYearMonth = 0;
		} else {
			countPlantingYearMonth = (float) (Math.floor(
					plantingService.findNumberOfAllPlantingByMonthAndYearAndUserInfield(months2, years2, username))
					* 100) / 100;

		}

		if (plantingService.findCountPlantingByMonthAndYearAndUserInfield(months2, years2, username) == null) {
			countPlanting = 0;
		} else {
			countPlanting = plantingService.findCountPlantingByMonthAndYearAndUserInfield(months2, years2, username);
		}

		m.addAttribute("countPlantingYearMonth", countPlantingYearMonth);
		m.addAttribute("countPlanting", countPlanting);

		// chart PieRegion2
		ArrayList<String> plantingRegionPieRegion2 = plantingRegionChart(months2, years2, username, "staff");
		ArrayList<Double> sumPieRegion2 = new ArrayList<Double>();
		int checkDataRegionChart = 1;
		if (plantingRegionPieRegion2.size() == 0) {
			checkDataRegionChart = 0;
		}
		sumPieRegion2 = sumAmountPieRegion2(months2, years2, username, "staff");
		m.addAttribute("checkDataRegionChart", checkDataRegionChart);
		m.addAttribute("plantingRegionPieRegion2", plantingRegionPieRegion2);
		m.addAttribute("sumPieRegion2", sumPieRegion2);

		// chart PieRegion2
		ArrayList<String> pieNameVarietyCharts = varietyPieChart(months2, years2, username, "staff");
		ArrayList<Double> sumPieVarietyCharts = new ArrayList<Double>();
		int checkDataVarietyChart = 1;
		if (pieNameVarietyCharts.size() == 0) {
			checkDataVarietyChart = 0;
		}
		sumPieVarietyCharts = sumVarietyPieChart(months2, years2, username, "staff");
		m.addAttribute("checkDataVarietyChart", checkDataVarietyChart);
		m.addAttribute("pieNameVarietyCharts", pieNameVarietyCharts);
		m.addAttribute("sumPieVarietyCharts", sumPieVarietyCharts);

		// chart lineChart3
		// YearNow
		int year8 = (new Date().getYear() + 1900);
		PlantingMonthAreaDTO plantingMonthAreaDTO = plantingMonthAndYearArea(year8, username, "staff");
		m.addAttribute("plantingMonthArea", plantingMonthAreaDTO);

		// YearNow-1
		int year9 = year8 - 1;
		PlantingMonthAreaDTO plantingMonthAreaDTO2 = plantingMonthAndYearArea(year9, username, "staff");
		m.addAttribute("plantingMonthArea2", plantingMonthAreaDTO2);

		// YearNow-2
		int year10 = year9 - 1;
		PlantingMonthAreaDTO plantingMonthAreaDTO3 = plantingMonthAndYearArea(year10, username, "staff");
		m.addAttribute("plantingMonthArea3", plantingMonthAreaDTO3);

		// ---- survey ---
		// Disease
		int countDiseaseYearMonth = 0;
		if (fieldService.findNumberOfFieldHasDiseaseByMonthsAndYearsByUserInField(months2, years2, username) != null) {
			countDiseaseYearMonth = fieldService.findNumberOfFieldHasDiseaseByMonthsAndYearsByUserInField(months2,
					years2, username);
		}
		m.addAttribute("countDiseaseYearMonth", countDiseaseYearMonth);
		// Pest
		int countPestYearMonth = 0;
		if (fieldService.findNumberOfFieldHasPestByMonthsAndYearsByUserInField(months2, years2, username) != null) {
			countPestYearMonth = fieldService.findNumberOfFieldHasPestByMonthsAndYearsByUserInField(months2, years2,
					username);
		}
		m.addAttribute("countPestYearMonth", countPestYearMonth);
		// NaturalEnemy
		int countNaturalEnemyYearMonth = 0;
		if (fieldService.findNumberOfFieldHasNaturalEnemyByMonthsAndYearsByUserInField(months2, years2,
				username) != null) {
			countNaturalEnemyYearMonth = fieldService
					.findNumberOfFieldHasNaturalEnemyByMonthsAndYearsByUserInField(months2, years2, username);
		}
		m.addAttribute("countNaturalEnemyYearMonth", countNaturalEnemyYearMonth);

		// % AvgPercentAndDamageLevel
		// --- Diseases ---
		ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsDiseases = avgPercentsDiseases(months2, years2, username,
				"staff");
		m.addAttribute("checkDisease", avgPercentsDiseases.size());
		m.addAttribute("avgPercentsDiseases", avgPercentsDiseases);

		// --- Pest ----
		ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsPests = avgPercentsPests(months2, years2, username,
				"staff");
		m.addAttribute("checkPest", avgPercentsPests.size());
		m.addAttribute("avgPercentsPests", avgPercentsPests);

		// --- NaturalEnemy ----
		ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsNaturalEnemys = avgPercentsNaturalEnemys(months2, years2,
				username, "staff");
		m.addAttribute("checkNaturalEnemy", avgPercentsNaturalEnemys.size());
		m.addAttribute("avgPercentsNaturalEnemys", avgPercentsNaturalEnemys);

		m.addAttribute("role", role);
		return "pages/home";
	}

	@RequestMapping(value = "/home/fieldregistrar", method = RequestMethod.GET)
	public String homeFieldRegistrarIndex(Model m, Authentication authentication) {

		// --- chart ---
		String role = "";
		// --- chart ajax (1) ----

		ArrayList<PlantingYearDTO> years = new ArrayList<>();
		Year ynewEn = Year.now();
		String ynewTh = ynewEn.hashCode() + "543";
		
		if (plantingService.findAllYearInLastUpdateDate().size() > 0) {
			for (int y : plantingService.findAllYearInLastUpdateDate()) {
				String y2 = "";
				y2 = y + 543 + "";

				years.add(new PlantingYearDTO(y2, y));
			}
		} else {
			years.add(new PlantingYearDTO(ynewTh, ynewEn.hashCode()));
			
		}
		m.addAttribute("years", years);

		ArrayList<PlantingMonthDTO> months = new ArrayList<>();
		for (int mon : plantingService.findAllMonthInLastUpdateDate()) {
			String m2 = "";
			if (mon == 1) {
				m2 = "มกราคม";
			} else if (mon == 2) {
				m2 = "กุมภาพันธ์";
			} else if (mon == 3) {
				m2 = "มีนาคม";
			} else if (mon == 4) {
				m2 = "เมษายน";
			} else if (mon == 5) {
				m2 = "พฤษภาคม";
			} else if (mon == 6) {
				m2 = "มิถุนายน";
			} else if (mon == 7) {
				m2 = "กรกฏาคม";
			} else if (mon == 8) {
				m2 = "สิงหาคม";
			} else if (mon == 9) {
				m2 = "กันยายน";
			} else if (mon == 10) {
				m2 = "ตุลาคม";
			} else if (mon == 11) {
				m2 = "พฤศจิกายน";
			} else if (mon == 12) {
				m2 = "ธันวาคม";
			}
			months.add(new PlantingMonthDTO(m2, mon));

		}
		m.addAttribute("months", months);

		ArrayList<Integer> months2 = new ArrayList<>();
		months2.addAll(plantingService.findAllMonthInLastUpdateDate());
		ArrayList<Integer> years2 = new ArrayList<>();
		if (plantingService.findAllYearInLastUpdateDate().size() > 0) {
			years2.addAll(plantingService.findAllYearInLastUpdateDate());
		} else {
			years2.add(ynewEn.hashCode());
		}

		String username = MvcHelper.getUsername(authentication);
		User user = userService.findByUsername(username);

		// count
		role = "fieldRegistrar";
		String nameOrg = organizationService.findByStaffUsername(username).getName();
		m.addAttribute("nameOrg", nameOrg);
		List<String> status = new ArrayList<>();
		status.add("active");
		status.add("inactive");
		int amountStaff = staffService
				.findByOrganizationAndStatus(organizationService.findByStaffUsername(username), "active").size();
		m.addAttribute("amountStaff", amountStaff);

		int amountFarmer = farmerService
				.findByOrganizationAndStatus(organizationService.findByStaffUsername(username), "active").size();
		m.addAttribute("amountFarmer", amountFarmer);

		int amountField = fieldService.findByOrganization(organizationService.findByStaffUsername(username)).size();
		m.addAttribute("amountField", amountField);

		// chart Pie
		ArrayList<Double> sumFields = new ArrayList<Double>();
		ArrayList<String> regionNames = new ArrayList<String>();
		// 1590. /// ArrayList<Double> amountFields = new ArrayList<Double>();
		regionNames = chartPieRegion(username, "fieldRegistrar");
		sumFields = sumAmountPieField(username, "fieldRegistrar");
		Double sum = 0.0;
		for (Double s : sumFields) {
			sum += s;
		}
		m.addAttribute("sum", Math.floor(sum * 100) / 100);
		m.addAttribute("sumFields", sumFields);
		m.addAttribute("regions", regionNames);

		// chart lineChart1
		ArrayList<Integer> yearsLineChart1 = plantingChartFiveYear(username, "fieldRegistrar");
		ArrayList<Double> amountsLineChart1 = plantingChartFiveMonth(username, "fieldRegistrar");
		m.addAttribute("yearsLineChart1", yearsLineChart1);
		m.addAttribute("amountsLineChart1", amountsLineChart1);

		// chart lineChart2
		ArrayList<Integer> yearsLineChart2 = plantingDiseaseChartFiveYear(username, "fieldRegistrar");
		ArrayList<Double> amountsLineChart2 = plantingDiseaseChartFiveMonth(username, "fieldRegistrar");
		m.addAttribute("yearsLineChart2", yearsLineChart2);
		m.addAttribute("amountsLineChart2", amountsLineChart2);

		// chart lineChart3
		ArrayList<Integer> yearsLineChart3 = plantingChartYearNaturalEnemyYear(username, "fieldRegistrar");
		ArrayList<Double> amountsLineChart3 = plantingChartYearNaturalEnemyMonth(username, "fieldRegistrar");
		m.addAttribute("yearsLineChart3", yearsLineChart3);
		m.addAttribute("amountsLineChart3", amountsLineChart3);

		// chart lineChart4
		ArrayList<Integer> yearsLineChart4 = plantingChartYearPestYear(username, "fieldRegistrar");
		ArrayList<Double> amountsLineChart4 = plantingChartYearPestMonth(username, "fieldRegistrar");
		m.addAttribute("yearsLineChart4", yearsLineChart4);
		m.addAttribute("amountsLineChart4", amountsLineChart4);

		// chart Disease lineChart5 จำนวนการสำรวจ (ครั้ง)
		// Disease
		ArrayList<Integer> yearsDiseaseLineChart5 = plantingChartYearDiseaseYear(username, "fieldRegistrar");
		ArrayList<Double> amountsDiseaseLineChart5 = plantingChartYearDiseaseMonth(username, "fieldRegistrar");
		m.addAttribute("yearsDiseaseLineChart5", yearsDiseaseLineChart5);
		m.addAttribute("amountsDiseaseLineChart5", amountsDiseaseLineChart5);

		// NaturalEnemy
		ArrayList<Integer> yearsNaturalEnemyLineChart5 = plantingChartYearNaturalEnemyC5Year(username,
				"fieldRegistrar");
		ArrayList<Double> amountsNaturalEnemyLineChart5 = plantingChartYearNaturalEnemyC5Month(username,
				"fieldRegistrar");
		m.addAttribute("yearsNaturalEnemyLineChart5", yearsNaturalEnemyLineChart5);
		m.addAttribute("amountsNaturalEnemyLineChart5", amountsNaturalEnemyLineChart5);

		// Pest
		ArrayList<Integer> yearsPestLineChart5 = plantingChartYearPestC5Year(username, "fieldRegistrar");
		ArrayList<Double> amountsPestLineChart5 = plantingChartYearPestC5Month(username, "fieldRegistrar");
		m.addAttribute("yearsPestLineChart5", yearsPestLineChart5);
		m.addAttribute("amountsPestLineChart5", amountsPestLineChart5);

		// ---- 2 ----
		double countPlantingYearMonth = 0;
		if (plantingService.findNumberOfAllPlantingByMonthAndYearandOrganization(months2, years2,
				user.getStaff()) != null) {
			countPlantingYearMonth = (float) (Math.floor(plantingService
					.findNumberOfAllPlantingByMonthAndYearandOrganization(months2, years2, user.getStaff())) * 100)
					/ 100;

		}

		int countPlanting = 0;
		if (plantingService.findCountPlantingByMonthAndYearandOrganization(months2, years2, user.getStaff()) != null) {
			countPlanting = plantingService.findCountPlantingByMonthAndYearandOrganization(months2, years2,
					user.getStaff());
		}
		m.addAttribute("countPlantingYearMonth", countPlantingYearMonth);
		m.addAttribute("countPlanting", countPlanting);

		// chart PieRegion2
		ArrayList<String> plantingRegionPieRegion2 = plantingRegionChart(months2, years2, username, "fieldRegistrar");
		ArrayList<Double> sumPieRegion2 = new ArrayList<Double>();
		int checkDataRegionChart = 1;
		if (plantingRegionPieRegion2.size() == 0) {
			checkDataRegionChart = 0;
		}
		sumPieRegion2 = sumAmountPieRegion2(months2, years2, username, "fieldRegistrar");
		m.addAttribute("checkDataRegionChart", checkDataRegionChart);
		m.addAttribute("plantingRegionPieRegion2", plantingRegionPieRegion2);
		m.addAttribute("sumPieRegion2", sumPieRegion2);

		// chart PieRegion2
		ArrayList<String> pieNameVarietyCharts = varietyPieChart(months2, years2, username, "fieldRegistrar");
		ArrayList<Double> sumPieVarietyCharts = new ArrayList<Double>();
		int checkDataVarietyChart = 1;
		if (pieNameVarietyCharts.size() == 0) {
			checkDataVarietyChart = 0;
		}
		sumPieVarietyCharts = sumVarietyPieChart(months2, years2, username, "fieldRegistrar");
		m.addAttribute("checkDataVarietyChart", checkDataVarietyChart);
		m.addAttribute("pieNameVarietyCharts", pieNameVarietyCharts);
		m.addAttribute("sumPieVarietyCharts", sumPieVarietyCharts);

		// chart lineChart3
		// YearNow
		int year8 = (new Date().getYear() + 1900);
		PlantingMonthAreaDTO plantingMonthAreaDTO = plantingMonthAndYearArea(year8, username, "fieldRegistrar");
		m.addAttribute("plantingMonthArea", plantingMonthAreaDTO);

		// YearNow-1
		int year9 = year8 - 1;
		PlantingMonthAreaDTO plantingMonthAreaDTO2 = plantingMonthAndYearArea(year9, username, "fieldRegistrar");
		m.addAttribute("plantingMonthArea2", plantingMonthAreaDTO2);

		// YearNow-2
		int year10 = year9 - 1;
		PlantingMonthAreaDTO plantingMonthAreaDTO3 = plantingMonthAndYearArea(year10, username, "fieldRegistrar");
		m.addAttribute("plantingMonthArea3", plantingMonthAreaDTO3);
		// ---- survey ---
		// Disease
		int countDiseaseYearMonth = 0;
		if (fieldService.findNumberOfFieldHasDiseaseByMonthsAndYearsByOrganization(months2, years2, username) != null) {
			countDiseaseYearMonth = fieldService.findNumberOfFieldHasDiseaseByMonthsAndYearsByOrganization(months2,
					years2, username);
		}
		m.addAttribute("countDiseaseYearMonth", countDiseaseYearMonth);
		// Pest
		int countPestYearMonth = 0;
		if (fieldService.findNumberOfFieldHasPestByMonthsAndYearsByOrganization(months2, years2, username) != null) {
			countPestYearMonth = fieldService.findNumberOfFieldHasPestByMonthsAndYearsByOrganization(months2, years2,
					username);
		}
		m.addAttribute("countPestYearMonth", countPestYearMonth);
		// NaturalEnemy
		int countNaturalEnemyYearMonth = 0;
		if (fieldService.findNumberOfFieldHasNaturalEnemyByMonthsAndYearsByOrganization(months2, years2,
				username) != null) {
			countNaturalEnemyYearMonth = fieldService
					.findNumberOfFieldHasNaturalEnemyByMonthsAndYearsByOrganization(months2, years2, username);
		}
		m.addAttribute("countNaturalEnemyYearMonth", countNaturalEnemyYearMonth);

		// % AvgPercentAndDamageLevel
		// --- Diseases ---
		ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsDiseases = avgPercentsDiseases(months2, years2, username,
				"fieldRegistrar");
		m.addAttribute("checkDisease", avgPercentsDiseases.size());
		m.addAttribute("avgPercentsDiseases", avgPercentsDiseases);

		// --- Pest ----
		ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsPests = avgPercentsPests(months2, years2, username,
				"fieldRegistrar");
		m.addAttribute("checkPest", avgPercentsPests.size());
		m.addAttribute("avgPercentsPests", avgPercentsPests);

		// --- NaturalEnemy ----
		ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsNaturalEnemys = avgPercentsNaturalEnemys(months2, years2,
				username, "fieldRegistrar");
		m.addAttribute("checkNaturalEnemy", avgPercentsNaturalEnemys.size());
		m.addAttribute("avgPercentsNaturalEnemys", avgPercentsNaturalEnemys);

		m.addAttribute("role", role);
		return "pages/home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homeIndex(Model m, Authentication authentication) {
		String username = MvcHelper.getUsername(authentication);
		User user = userService.findByUsername(username);

		// alert
		List<Messagereceiver> messageReceiver = messagereceiverService.findByUserIdAndReadStatus(user.getUserId(), "N");
		List<MessageReceiverDTO> messageReceiverDTO = new ArrayList<>();

		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		m.addAttribute("formatter", formatter);
		String dateStr;
		// m.addAttribute("messagereceivers",
		// messagereceiverService.findByUserId(user.getUserId()));

		int check = 0;

		for (Messagereceiver messagereceiver : messageReceiver) {

			int dayDiff = dateDiff(messagereceiver.getMessage().getSendDate(), new Date());

			if (dayDiff < 7 && dayDiff != 0) {
				dateStr = dayDiff + " วันที่ผ่านมา";
			} else if (dayDiff == 0) {
				dateStr = "วันนี้";
			} else if (dayDiff >= 7 && dayDiff <= 30) {
				dateStr = dayDiff / 7 + " สัปดาห์ที่ผ่านมา";
			} else {
				dateStr = "เมื่อวันที่ " + formatter.format(messagereceiver.getMessage().getSendDate());
			}

			messageReceiverDTO.add(new MessageReceiverDTO(messagereceiver.getMessage().getTitle(),
					messagereceiver.getMessage().getText(), dateStr, messagereceiver.getMessage().getType()));
			check++;
			if (check == 5) {
				break;
			}

		}

		m.addAttribute("amount", messagereceiverService.findByUserIdAndReadStatus(user.getUserId(), "N").size());
		m.addAttribute("messageReceiverDTO", messageReceiverDTO);

		// --- chart ---
		String role = "";
		// --- chart ajax (1) ----

		Year ynewEn = Year.now();
		int ynewTh = ynewEn.hashCode() + 543;
		
		ArrayList<PlantingYearDTO> years = new ArrayList<>();
		if (plantingService.findAllYearInLastUpdateDate().size() > 0) {
			for (int y : plantingService.findAllYearInLastUpdateDate()) {
				String y2 = "";
				y2 = y + 543 + "";

				years.add(new PlantingYearDTO(y2, y));
			}
		} else {
			years.add(new PlantingYearDTO(ynewTh+"", ynewEn.hashCode()));
			
		}
		
		m.addAttribute("years", years);

		ArrayList<PlantingMonthDTO> months = new ArrayList<>();
		for (int mon : plantingService.findAllMonthInLastUpdateDate()) {
			String m2 = "";
			if (mon == 1) {
				m2 = "มกราคม";
			} else if (mon == 2) {
				m2 = "กุมภาพันธ์";
			} else if (mon == 3) {
				m2 = "มีนาคม";
			} else if (mon == 4) {
				m2 = "เมษายน";
			} else if (mon == 5) {
				m2 = "พฤษภาคม";
			} else if (mon == 6) {
				m2 = "มิถุนายน";
			} else if (mon == 7) {
				m2 = "กรกฏาคม";
			} else if (mon == 8) {
				m2 = "สิงหาคม";
			} else if (mon == 9) {
				m2 = "กันยายน";
			} else if (mon == 10) {
				m2 = "ตุลาคม";
			} else if (mon == 11) {
				m2 = "พฤศจิกายน";
			} else if (mon == 12) {
				m2 = "ธันวาคม";
			}
			months.add(new PlantingMonthDTO(m2, mon));

		}
		m.addAttribute("months", months);

		ArrayList<Integer> months2 = new ArrayList<>();
		months2.addAll(plantingService.findAllMonthInLastUpdateDate());
		ArrayList<Integer> years2 = new ArrayList<>();
		if (plantingService.findAllYearInLastUpdateDate().size() > 0) {
			years2.addAll(plantingService.findAllYearInLastUpdateDate());
		} else {
			years2.add(ynewEn.hashCode());
		}

		// role farmer
		if (user.getFarmer() != null) {
			role = "farmer";
			// count
			int amountField = countAmountField(username, "farmer");
			m.addAttribute("amountField", amountField);

			// chart Pie
			ArrayList<Double> sumFields = new ArrayList<Double>();
			ArrayList<String> regionNames = new ArrayList<String>();
			// 1590. /// ArrayList<Double> amountFields = new ArrayList<Double>();
			regionNames = chartPieRegion(username, "farmer");
			sumFields = sumAmountPieField(username, "farmer");
			Double sum = 0.0;
			for (Double s : sumFields) {
				sum += s;
			}
			m.addAttribute("sum", Math.floor(sum * 100) / 100);
			m.addAttribute("sumFields", sumFields);
			m.addAttribute("regions", regionNames);

			// chart lineChart1
			ArrayList<Integer> yearsLineChart1 = plantingChartFiveYear(username, "farmer");
			ArrayList<Double> amountsLineChart1 = plantingChartFiveMonth(username, "farmer");
			m.addAttribute("yearsLineChart1", yearsLineChart1);
			m.addAttribute("amountsLineChart1", amountsLineChart1);

			// chart lineChart2
			ArrayList<Integer> yearsLineChart2 = plantingDiseaseChartFiveYear(username, "farmer");
			ArrayList<Double> amountsLineChart2 = plantingDiseaseChartFiveMonth(username, "farmer");
			m.addAttribute("yearsLineChart2", yearsLineChart2);
			m.addAttribute("amountsLineChart2", amountsLineChart2);

			// chart lineChart3
			ArrayList<Integer> yearsLineChart3 = plantingChartYearNaturalEnemyYear(username, "farmer");
			ArrayList<Double> amountsLineChart3 = plantingChartYearNaturalEnemyMonth(username, "farmer");
			m.addAttribute("yearsLineChart3", yearsLineChart3);
			m.addAttribute("amountsLineChart3", amountsLineChart3);

			// chart lineChart4
			ArrayList<Integer> yearsLineChart4 = plantingChartYearPestYear(username, "farmer");
			ArrayList<Double> amountsLineChart4 = plantingChartYearPestMonth(username, "farmer");
			m.addAttribute("yearsLineChart4", yearsLineChart4);
			m.addAttribute("amountsLineChart4", amountsLineChart4);

			// chart Disease lineChart5 จำนวนการสำรวจ (ครั้ง)
			// Disease
			ArrayList<Integer> yearsDiseaseLineChart5 = plantingChartYearDiseaseYear(username, "farmer");
			ArrayList<Double> amountsDiseaseLineChart5 = plantingChartYearDiseaseMonth(username, "farmer");
			m.addAttribute("yearsDiseaseLineChart5", yearsDiseaseLineChart5);
			m.addAttribute("amountsDiseaseLineChart5", amountsDiseaseLineChart5);

			// NaturalEnemy
			ArrayList<Integer> yearsNaturalEnemyLineChart5 = plantingChartYearNaturalEnemyC5Year(username, "farmer");
			ArrayList<Double> amountsNaturalEnemyLineChart5 = plantingChartYearNaturalEnemyC5Month(username, "farmer");
			m.addAttribute("yearsNaturalEnemyLineChart5", yearsNaturalEnemyLineChart5);
			m.addAttribute("amountsNaturalEnemyLineChart5", amountsNaturalEnemyLineChart5);

			// Pest
			ArrayList<Integer> yearsPestLineChart5 = plantingChartYearPestC5Year(username, "farmer");
			ArrayList<Double> amountsPestLineChart5 = plantingChartYearPestC5Month(username, "farmer");
			m.addAttribute("yearsPestLineChart5", yearsPestLineChart5);
			m.addAttribute("amountsPestLineChart5", amountsPestLineChart5);

			// ---- 2 ---
			double countPlantingYearMonth;
			int countPlanting;
			if (plantingService.findNumberOfAllPlantingByMonthAndYearAndUserInfield(months2, years2,
					username) == null) {
				countPlantingYearMonth = 0;
			} else {
				countPlantingYearMonth = (float) (Math.floor(
						plantingService.findNumberOfAllPlantingByMonthAndYearAndUserInfield(months2, years2, username))
						* 100) / 100;

			}

			if (plantingService.findCountPlantingByMonthAndYearAndUserInfield(months2, years2, username) == null) {
				countPlanting = 0;
			} else {
				countPlanting = plantingService.findCountPlantingByMonthAndYearAndUserInfield(months2, years2,
						username);
			}

			m.addAttribute("countPlantingYearMonth", countPlantingYearMonth);
			m.addAttribute("countPlanting", countPlanting);

			// chart PieRegion2
			ArrayList<String> plantingRegionPieRegion2 = plantingRegionChart(months2, years2, username, "farmer");
			ArrayList<Double> sumPieRegion2 = new ArrayList<Double>();
			int checkDataRegionChart = 1;
			if (plantingRegionPieRegion2.size() == 0) {
				checkDataRegionChart = 0;
			}
			sumPieRegion2 = sumAmountPieRegion2(months2, years2, username, "farmer");
			m.addAttribute("checkDataRegionChart", checkDataRegionChart);
			m.addAttribute("plantingRegionPieRegion2", plantingRegionPieRegion2);
			m.addAttribute("sumPieRegion2", sumPieRegion2);

			// chart PieRegion2
			ArrayList<String> pieNameVarietyCharts = varietyPieChart(months2, years2, username, "farmer");
			ArrayList<Double> sumPieVarietyCharts = new ArrayList<Double>();
			int checkDataVarietyChart = 1;
			if (pieNameVarietyCharts.size() == 0) {
				checkDataVarietyChart = 0;
			}
			sumPieVarietyCharts = sumVarietyPieChart(months2, years2, username, "farmer");
			m.addAttribute("checkDataVarietyChart", checkDataVarietyChart);
			m.addAttribute("pieNameVarietyCharts", pieNameVarietyCharts);
			m.addAttribute("sumPieVarietyCharts", sumPieVarietyCharts);

			// chart lineChart3
			// YearNow
			int year8 = (new Date().getYear() + 1900);
			PlantingMonthAreaDTO plantingMonthAreaDTO = plantingMonthAndYearArea(year8, username, "farmer");
			m.addAttribute("plantingMonthArea", plantingMonthAreaDTO);

			// YearNow-1
			int year9 = year8 - 1;
			PlantingMonthAreaDTO plantingMonthAreaDTO2 = plantingMonthAndYearArea(year9, username, "farmer");
			m.addAttribute("plantingMonthArea2", plantingMonthAreaDTO2);

			// YearNow-2
			int year10 = year9 - 1;
			PlantingMonthAreaDTO plantingMonthAreaDTO3 = plantingMonthAndYearArea(year10, username, "farmer");
			m.addAttribute("plantingMonthArea3", plantingMonthAreaDTO3);

			// ---- survey ---
			// Disease
			int countDiseaseYearMonth = 0;
			if (fieldService.findNumberOfFieldHasDiseaseByMonthsAndYearsByUserInField(months2, years2,
					username) != null) {
				countDiseaseYearMonth = fieldService.findNumberOfFieldHasDiseaseByMonthsAndYearsByUserInField(months2,
						years2, username);
			}
			m.addAttribute("countDiseaseYearMonth", countDiseaseYearMonth);
			// Pest
			int countPestYearMonth = 0;
			if (fieldService.findNumberOfFieldHasPestByMonthsAndYearsByUserInField(months2, years2, username) != null) {
				countPestYearMonth = fieldService.findNumberOfFieldHasPestByMonthsAndYearsByUserInField(months2, years2,
						username);
			}
			m.addAttribute("countPestYearMonth", countPestYearMonth);
			// NaturalEnemy
			int countNaturalEnemyYearMonth = 0;
			if (fieldService.findNumberOfFieldHasNaturalEnemyByMonthsAndYearsByUserInField(months2, years2,
					username) != null) {
				countNaturalEnemyYearMonth = fieldService
						.findNumberOfFieldHasNaturalEnemyByMonthsAndYearsByUserInField(months2, years2, username);
			}
			m.addAttribute("countNaturalEnemyYearMonth", countNaturalEnemyYearMonth);

			// % AvgPercentAndDamageLevel
			// --- Diseases ---
			ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsDiseases = avgPercentsDiseases(months2, years2,
					username, "farmer");

			m.addAttribute("checkDisease", avgPercentsDiseases.size());
			m.addAttribute("avgPercentsDiseases", avgPercentsDiseases);

			// --- Pest ----
			ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsPests = avgPercentsPests(months2, years2, username,
					"farmer");
			m.addAttribute("checkPest", avgPercentsPests.size());
			m.addAttribute("avgPercentsPests", avgPercentsPests);

			// --- NaturalEnemy ----
			ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsNaturalEnemys = avgPercentsNaturalEnemys(months2,
					years2, username, "farmer");
			m.addAttribute("checkNaturalEnemy", avgPercentsNaturalEnemys.size());
			m.addAttribute("avgPercentsNaturalEnemys", avgPercentsNaturalEnemys);

		} else if (roleService.findByUserIdAndRoleName(user.getUserId(), "staff") != null) {
			role = "staff";

			if (roleService.findByUserIdAndRoleName(user.getUserId(), "systemAdmin") != null) {
				role = "systemAdmin";
				// count
				int amountOrg = organizationService.findAll().size();
				m.addAttribute("amountOrg", amountOrg);
				List<String> status = new ArrayList<>();
				status.add("active");
				status.add("inactive");
				int amountStaff = staffService.findByListStatus(status).size();
				m.addAttribute("amountStaff", amountStaff);

				int amountFarmer = farmerService.findByListStatus(status).size();
				m.addAttribute("amountFarmer", amountFarmer);

				int amountField = countAmountField(username, "systemAdmin");
				m.addAttribute("amountField", amountField);

				// chart Pie
				ArrayList<Double> sumFields = new ArrayList<Double>();
				ArrayList<String> regionNames = new ArrayList<String>();
				regionNames = chartPieRegion(username, "systemAdmin");
				sumFields = sumAmountPieField(username, "systemAdmin");
				Double sum = 0.0;
				for (Double s : sumFields) {
					sum += s;
				}
				m.addAttribute("sum", Math.floor(sum * 100) / 100);
				m.addAttribute("sumFields", sumFields);
				m.addAttribute("regions", regionNames);

				// chart lineChart1
				ArrayList<Integer> yearsLineChart1 = plantingChartFiveYear(username, "systemAdmin");
				ArrayList<Double> amountsLineChart1 = plantingChartFiveMonth(username, "systemAdmin");
				m.addAttribute("yearsLineChart1", yearsLineChart1);
				m.addAttribute("amountsLineChart1", amountsLineChart1);

				// chart lineChart2
				ArrayList<Integer> yearsLineChart2 = plantingDiseaseChartFiveYear(username, "systemAdmin");
				ArrayList<Double> amountsLineChart2 = plantingDiseaseChartFiveMonth(username, "systemAdmin");
				m.addAttribute("yearsLineChart2", yearsLineChart2);
				m.addAttribute("amountsLineChart2", amountsLineChart2);

				// chart lineChart3
				ArrayList<Integer> yearsLineChart3 = plantingChartYearNaturalEnemyYear(username, "systemAdmin");
				ArrayList<Double> amountsLineChart3 = plantingChartYearNaturalEnemyMonth(username, "systemAdmin");
				m.addAttribute("yearsLineChart3", yearsLineChart3);
				m.addAttribute("amountsLineChart3", amountsLineChart3);

				// chart lineChart4
				ArrayList<Integer> yearsLineChart4 = plantingChartYearPestYear(username, "systemAdmin");
				ArrayList<Double> amountsLineChart4 = plantingChartYearPestMonth(username, "systemAdmin");
				m.addAttribute("yearsLineChart4", yearsLineChart4);
				m.addAttribute("amountsLineChart4", amountsLineChart4);

				// chart Disease lineChart5 จำนวนการสำรวจ (ครั้ง)
				// Disease
				ArrayList<Integer> yearsDiseaseLineChart5 = plantingChartYearDiseaseYear(username, "systemAdmin");
				ArrayList<Double> amountsDiseaseLineChart5 = plantingChartYearDiseaseMonth(username, "systemAdmin");
				m.addAttribute("yearsDiseaseLineChart5", yearsDiseaseLineChart5);
				m.addAttribute("amountsDiseaseLineChart5", amountsDiseaseLineChart5);

				// NaturalEnemy
				ArrayList<Integer> yearsNaturalEnemyLineChart5 = plantingChartYearNaturalEnemyC5Year(username,
						"systemAdmin");
				ArrayList<Double> amountsNaturalEnemyLineChart5 = plantingChartYearNaturalEnemyC5Month(username,
						"systemAdmin");
				m.addAttribute("yearsNaturalEnemyLineChart5", yearsNaturalEnemyLineChart5);
				m.addAttribute("amountsNaturalEnemyLineChart5", amountsNaturalEnemyLineChart5);

				// Pest
				ArrayList<Integer> yearsPestLineChart5 = plantingChartYearPestC5Year(username, "systemAdmin");
				ArrayList<Double> amountsPestLineChart5 = plantingChartYearPestC5Month(username, "systemAdmin");
				m.addAttribute("yearsPestLineChart5", yearsPestLineChart5);
				m.addAttribute("amountsPestLineChart5", amountsPestLineChart5);

				// ---- 2 ----
				double countPlantingYearMonth = 0;
				if (plantingService.findNumberOfAllPlantingByMonthAndYear(months2, years2) != null) {
					countPlantingYearMonth = (float) (Math
							.floor(plantingService.findNumberOfAllPlantingByMonthAndYear(months2, years2)) * 100) / 100;

				}
				int countPlanting = 0;
				if (plantingService.findCountPlantingByMonthAndYear(months2, years2) != null) {
					countPlanting = plantingService.findCountPlantingByMonthAndYear(months2, years2);
				}
				m.addAttribute("countPlantingYearMonth", countPlantingYearMonth);
				m.addAttribute("countPlanting", countPlanting);

				// chart PieRegion2
				ArrayList<String> plantingRegionPieRegion2 = plantingRegionChart(months2, years2, username,
						"systemAdmin");
				ArrayList<Double> sumPieRegion2 = new ArrayList<Double>();
				int checkDataRegionChart = 1;
				if (plantingRegionPieRegion2.size() == 0) {
					checkDataRegionChart = 0;
				}
				sumPieRegion2 = sumAmountPieRegion2(months2, years2, username, "systemAdmin");
				m.addAttribute("checkDataRegionChart", checkDataRegionChart);
				m.addAttribute("plantingRegionPieRegion2", plantingRegionPieRegion2);
				m.addAttribute("sumPieRegion2", sumPieRegion2);

				// chart PieRegion2
				ArrayList<String> pieNameVarietyCharts = varietyPieChart(months2, years2, username, "systemAdmin");
				ArrayList<Double> sumPieVarietyCharts = new ArrayList<Double>();
				int checkDataVarietyChart = 1;
				if (pieNameVarietyCharts.size() == 0) {
					checkDataVarietyChart = 0;
				}
				sumPieVarietyCharts = sumVarietyPieChart(months2, years2, username, "systemAdmin");
				m.addAttribute("checkDataVarietyChart", checkDataVarietyChart);
				m.addAttribute("pieNameVarietyCharts", pieNameVarietyCharts);
				m.addAttribute("sumPieVarietyCharts", sumPieVarietyCharts);

				// chart lineChart3
				// YearNow
				int year8 = (new Date().getYear() + 1900);
				PlantingMonthAreaDTO plantingMonthAreaDTO = plantingMonthAndYearArea(year8, username, "systemAdmin");
				m.addAttribute("plantingMonthArea", plantingMonthAreaDTO);

				// YearNow-1
				int year9 = year8 - 1;
				PlantingMonthAreaDTO plantingMonthAreaDTO2 = plantingMonthAndYearArea(year9, username, "systemAdmin");
				m.addAttribute("plantingMonthArea2", plantingMonthAreaDTO2);

				// YearNow-2
				int year10 = year9 - 1;
				PlantingMonthAreaDTO plantingMonthAreaDTO3 = plantingMonthAndYearArea(year10, username, "systemAdmin");
				m.addAttribute("plantingMonthArea3", plantingMonthAreaDTO3);

				// ---- survey ---
				// Disease

				int countDiseaseYearMonth = 0;
				if (fieldService.findNumberOfFieldHasDiseaseByMonthsAndYears(months2, years2) != null) {
					countDiseaseYearMonth = fieldService.findNumberOfFieldHasDiseaseByMonthsAndYears(months2, years2);
				}
				m.addAttribute("countDiseaseYearMonth", countDiseaseYearMonth);
				// Pest
				int countPestYearMonth = 0;
				if (fieldService.findNumberOfFieldHasPestByMonthsAndYears(months2, years2) != null) {
					countPestYearMonth = fieldService.findNumberOfFieldHasPestByMonthsAndYears(months2, years2);
				}
				m.addAttribute("countPestYearMonth", countPestYearMonth);
				// NaturalEnemy
				int countNaturalEnemyYearMonth = 0;
				if (fieldService.findNumberOfFieldHasNaturalEnemyByMonthsAndYears(months2, years2) != null) {
					countNaturalEnemyYearMonth = fieldService.findNumberOfFieldHasNaturalEnemyByMonthsAndYears(months2,
							years2);
				}
				m.addAttribute("countNaturalEnemyYearMonth", countNaturalEnemyYearMonth);

				// % AvgPercentAndDamageLevel
				// --- Diseases ---
				ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsDiseases = avgPercentsDiseases(months2, years2,
						username, "systemAdmin");
				m.addAttribute("checkDisease", avgPercentsDiseases.size());
				m.addAttribute("avgPercentsDiseases", avgPercentsDiseases);

				// --- Pest ----
				ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsPests = avgPercentsPests(months2, years2,
						username, "systemAdmin");
				m.addAttribute("checkPest", avgPercentsPests.size());
				m.addAttribute("avgPercentsPests", avgPercentsPests);

				// --- NaturalEnemy ----
				ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsNaturalEnemys = avgPercentsNaturalEnemys(months2,
						years2, username, "systemAdmin");
				m.addAttribute("checkNaturalEnemy", avgPercentsNaturalEnemys.size());
				m.addAttribute("avgPercentsNaturalEnemys", avgPercentsNaturalEnemys);

			} else if (roleService.findByUserIdAndRoleName(user.getUserId(), "fieldRegistrar") != null) {

				// count
				role = "fieldRegistrar";
				String nameOrg = organizationService.findByStaffUsername(username).getName();
				m.addAttribute("nameOrg", nameOrg);
				List<String> status = new ArrayList<>();
				status.add("active");
				status.add("inactive");
				int amountStaff = staffService
						.findByOrganizationAndStatus(organizationService.findByStaffUsername(username), "active")
						.size();
				m.addAttribute("amountStaff", amountStaff);

				int amountFarmer = farmerService
						.findByOrganizationAndStatus(organizationService.findByStaffUsername(username), "active")
						.size();
				m.addAttribute("amountFarmer", amountFarmer);

				int amountField = countAmountField(username, "fieldRegistrar");
				m.addAttribute("amountField", amountField);

				// chart Pie
				ArrayList<Double> sumFields = new ArrayList<Double>();
				ArrayList<String> regionNames = new ArrayList<String>();
				// 1590. /// ArrayList<Double> amountFields = new ArrayList<Double>();
				regionNames = chartPieRegion(username, "fieldRegistrar");
				sumFields = sumAmountPieField(username, "fieldRegistrar");
				Double sum = 0.0;
				for (Double s : sumFields) {
					sum += s;
				}
				m.addAttribute("sum", Math.floor(sum * 100) / 100);
				m.addAttribute("sumFields", sumFields);
				m.addAttribute("regions", regionNames);

				// chart lineChart1
				ArrayList<Integer> yearsLineChart1 = plantingChartFiveYear(username, "fieldRegistrar");
				ArrayList<Double> amountsLineChart1 = plantingChartFiveMonth(username, "fieldRegistrar");
				m.addAttribute("yearsLineChart1", yearsLineChart1);
				m.addAttribute("amountsLineChart1", amountsLineChart1);

				// chart lineChart2
				ArrayList<Integer> yearsLineChart2 = plantingDiseaseChartFiveYear(username, "fieldRegistrar");
				ArrayList<Double> amountsLineChart2 = plantingDiseaseChartFiveMonth(username, "fieldRegistrar");
				m.addAttribute("yearsLineChart2", yearsLineChart2);
				m.addAttribute("amountsLineChart2", amountsLineChart2);

				// chart lineChart3
				ArrayList<Integer> yearsLineChart3 = plantingChartYearNaturalEnemyYear(username, "fieldRegistrar");
				ArrayList<Double> amountsLineChart3 = plantingChartYearNaturalEnemyMonth(username, "fieldRegistrar");
				m.addAttribute("yearsLineChart3", yearsLineChart3);
				m.addAttribute("amountsLineChart3", amountsLineChart3);

				// chart lineChart4
				ArrayList<Integer> yearsLineChart4 = plantingChartYearPestYear(username, "fieldRegistrar");
				ArrayList<Double> amountsLineChart4 = plantingChartYearPestMonth(username, "fieldRegistrar");
				m.addAttribute("yearsLineChart4", yearsLineChart4);
				m.addAttribute("amountsLineChart4", amountsLineChart4);

				// chart Disease lineChart5 จำนวนการสำรวจ (ครั้ง)
				// Disease
				ArrayList<Integer> yearsDiseaseLineChart5 = plantingChartYearDiseaseYear(username, "fieldRegistrar");
				ArrayList<Double> amountsDiseaseLineChart5 = plantingChartYearDiseaseMonth(username, "fieldRegistrar");
				m.addAttribute("yearsDiseaseLineChart5", yearsDiseaseLineChart5);
				m.addAttribute("amountsDiseaseLineChart5", amountsDiseaseLineChart5);

				// NaturalEnemy
				ArrayList<Integer> yearsNaturalEnemyLineChart5 = plantingChartYearNaturalEnemyC5Year(username,
						"fieldRegistrar");
				ArrayList<Double> amountsNaturalEnemyLineChart5 = plantingChartYearNaturalEnemyC5Month(username,
						"fieldRegistrar");
				m.addAttribute("yearsNaturalEnemyLineChart5", yearsNaturalEnemyLineChart5);
				m.addAttribute("amountsNaturalEnemyLineChart5", amountsNaturalEnemyLineChart5);

				// Pest
				ArrayList<Integer> yearsPestLineChart5 = plantingChartYearPestC5Year(username, "fieldRegistrar");
				ArrayList<Double> amountsPestLineChart5 = plantingChartYearPestC5Month(username, "fieldRegistrar");
				m.addAttribute("yearsPestLineChart5", yearsPestLineChart5);
				m.addAttribute("amountsPestLineChart5", amountsPestLineChart5);

				// ---- 2 ----
				double countPlantingYearMonth = 0;
				if (plantingService.findNumberOfAllPlantingByMonthAndYearandOrganization(months2, years2,
						user.getStaff()) != null) {
					countPlantingYearMonth = (float) (Math.floor(plantingService
							.findNumberOfAllPlantingByMonthAndYearandOrganization(months2, years2, user.getStaff()))
							* 100) / 100;

				}

				int countPlanting = 0;
				if (plantingService.findCountPlantingByMonthAndYearandOrganization(months2, years2,
						user.getStaff()) != null) {
					countPlanting = plantingService.findCountPlantingByMonthAndYearandOrganization(months2, years2,
							user.getStaff());
				}
				m.addAttribute("countPlantingYearMonth", countPlantingYearMonth);
				m.addAttribute("countPlanting", countPlanting);

				// chart PieRegion2
				ArrayList<String> plantingRegionPieRegion2 = plantingRegionChart(months2, years2, username,
						"fieldRegistrar");
				ArrayList<Double> sumPieRegion2 = new ArrayList<Double>();
				int checkDataRegionChart = 1;
				if (plantingRegionPieRegion2.size() == 0) {
					checkDataRegionChart = 0;
				}
				sumPieRegion2 = sumAmountPieRegion2(months2, years2, username, "fieldRegistrar");
				m.addAttribute("checkDataRegionChart", checkDataRegionChart);
				m.addAttribute("plantingRegionPieRegion2", plantingRegionPieRegion2);
				m.addAttribute("sumPieRegion2", sumPieRegion2);

				// chart PieRegion2
				ArrayList<String> pieNameVarietyCharts = varietyPieChart(months2, years2, username, "fieldRegistrar");
				ArrayList<Double> sumPieVarietyCharts = new ArrayList<Double>();
				int checkDataVarietyChart = 1;
				if (pieNameVarietyCharts.size() == 0) {
					checkDataVarietyChart = 0;
				}
				sumPieVarietyCharts = sumVarietyPieChart(months2, years2, username, "fieldRegistrar");
				m.addAttribute("checkDataVarietyChart", checkDataVarietyChart);
				m.addAttribute("pieNameVarietyCharts", pieNameVarietyCharts);
				m.addAttribute("sumPieVarietyCharts", sumPieVarietyCharts);

				// chart lineChart3
				// YearNow
				int year8 = (new Date().getYear() + 1900);
				PlantingMonthAreaDTO plantingMonthAreaDTO = plantingMonthAndYearArea(year8, username, "fieldRegistrar");
				m.addAttribute("plantingMonthArea", plantingMonthAreaDTO);

				// YearNow-1
				int year9 = year8 - 1;
				PlantingMonthAreaDTO plantingMonthAreaDTO2 = plantingMonthAndYearArea(year9, username,
						"fieldRegistrar");
				m.addAttribute("plantingMonthArea2", plantingMonthAreaDTO2);

				// YearNow-2
				int year10 = year9 - 1;
				PlantingMonthAreaDTO plantingMonthAreaDTO3 = plantingMonthAndYearArea(year10, username,
						"fieldRegistrar");
				m.addAttribute("plantingMonthArea3", plantingMonthAreaDTO3);
				// ---- survey ---
				// Disease
				int countDiseaseYearMonth = 0;
				if (fieldService.findNumberOfFieldHasDiseaseByMonthsAndYearsByOrganization(months2, years2,
						username) != null) {
					countDiseaseYearMonth = fieldService
							.findNumberOfFieldHasDiseaseByMonthsAndYearsByOrganization(months2, years2, username);
				}
				m.addAttribute("countDiseaseYearMonth", countDiseaseYearMonth);
				// Pest
				int countPestYearMonth = 0;
				if (fieldService.findNumberOfFieldHasPestByMonthsAndYearsByOrganization(months2, years2,
						username) != null) {
					countPestYearMonth = fieldService.findNumberOfFieldHasPestByMonthsAndYearsByOrganization(months2,
							years2, username);
				}
				m.addAttribute("countPestYearMonth", countPestYearMonth);
				// NaturalEnemy
				int countNaturalEnemyYearMonth = 0;
				if (fieldService.findNumberOfFieldHasNaturalEnemyByMonthsAndYearsByOrganization(months2, years2,
						username) != null) {
					countNaturalEnemyYearMonth = fieldService
							.findNumberOfFieldHasNaturalEnemyByMonthsAndYearsByOrganization(months2, years2, username);
				}
				m.addAttribute("countNaturalEnemyYearMonth", countNaturalEnemyYearMonth);

				// % AvgPercentAndDamageLevel
				// --- Diseases ---
				ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsDiseases = avgPercentsDiseases(months2, years2,
						username, "fieldRegistrar");
				m.addAttribute("checkDisease", avgPercentsDiseases.size());
				m.addAttribute("avgPercentsDiseases", avgPercentsDiseases);

				// --- Pest ----
				ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsPests = avgPercentsPests(months2, years2,
						username, "fieldRegistrar");
				m.addAttribute("checkPest", avgPercentsPests.size());
				m.addAttribute("avgPercentsPests", avgPercentsPests);

				// --- NaturalEnemy ----
				ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsNaturalEnemys = avgPercentsNaturalEnemys(months2,
						years2, username, "fieldRegistrar");
				m.addAttribute("checkNaturalEnemy", avgPercentsNaturalEnemys.size());
				m.addAttribute("avgPercentsNaturalEnemys", avgPercentsNaturalEnemys);

			} else {
				int amountField = countAmountField(username, "staff");
				m.addAttribute("amountField", amountField);

				// chart Pie
				ArrayList<Double> sumFields = new ArrayList<Double>();
				ArrayList<String> regionNames = new ArrayList<String>();
				// 1590. /// ArrayList<Double> amountFields = new ArrayList<Double>();
				regionNames = chartPieRegion(username, "staff");
				sumFields = sumAmountPieField(username, "staff");
				Double sum = 0.0;
				for (Double s : sumFields) {
					sum += s;
				}
				m.addAttribute("sum", Math.floor(sum * 100) / 100);
				m.addAttribute("sumFields", sumFields);
				m.addAttribute("regions", regionNames);

				// chart lineChart1
				ArrayList<Integer> yearsLineChart1 = plantingChartFiveYear(username, "fieldRegistrar");
				ArrayList<Double> amountsLineChart1 = plantingChartFiveMonth(username, "fieldRegistrar");
				m.addAttribute("yearsLineChart1", yearsLineChart1);
				m.addAttribute("amountsLineChart1", amountsLineChart1);

				// chart lineChart2
				ArrayList<Integer> yearsLineChart2 = plantingDiseaseChartFiveYear(username, "staff");
				ArrayList<Double> amountsLineChart2 = plantingDiseaseChartFiveMonth(username, "staff");
				m.addAttribute("yearsLineChart2", yearsLineChart2);
				m.addAttribute("amountsLineChart2", amountsLineChart2);

				// chart lineChart3
				ArrayList<Integer> yearsLineChart3 = plantingChartYearNaturalEnemyYear(username, "staff");
				ArrayList<Double> amountsLineChart3 = plantingChartYearNaturalEnemyMonth(username, "staff");
				m.addAttribute("yearsLineChart3", yearsLineChart3);
				m.addAttribute("amountsLineChart3", amountsLineChart3);

				// chart lineChart4
				ArrayList<Integer> yearsLineChart4 = plantingChartYearPestYear(username, "staff");
				ArrayList<Double> amountsLineChart4 = plantingChartYearPestMonth(username, "staff");
				m.addAttribute("yearsLineChart4", yearsLineChart4);
				m.addAttribute("amountsLineChart4", amountsLineChart4);

				// chart Disease lineChart5 จำนวนการสำรวจ (ครั้ง)
				// Disease
				ArrayList<Integer> yearsDiseaseLineChart5 = plantingChartYearDiseaseYear(username, "staff");
				ArrayList<Double> amountsDiseaseLineChart5 = plantingChartYearDiseaseMonth(username, "staff");
				m.addAttribute("yearsDiseaseLineChart5", yearsDiseaseLineChart5);
				m.addAttribute("amountsDiseaseLineChart5", amountsDiseaseLineChart5);

				// NaturalEnemy
				ArrayList<Integer> yearsNaturalEnemyLineChart5 = plantingChartYearNaturalEnemyC5Year(username, "staff");
				ArrayList<Double> amountsNaturalEnemyLineChart5 = plantingChartYearNaturalEnemyC5Month(username,
						"staff");
				m.addAttribute("yearsNaturalEnemyLineChart5", yearsNaturalEnemyLineChart5);
				m.addAttribute("amountsNaturalEnemyLineChart5", amountsNaturalEnemyLineChart5);

				// Pest
				ArrayList<Integer> yearsPestLineChart5 = plantingChartYearPestC5Year(username, "staff");
				ArrayList<Double> amountsPestLineChart5 = plantingChartYearPestC5Month(username, "staff");
				m.addAttribute("yearsPestLineChart5", yearsPestLineChart5);
				m.addAttribute("amountsPestLineChart5", amountsPestLineChart5);

				// ---- 2 ---
				double countPlantingYearMonth;
				int countPlanting;
				if (plantingService.findNumberOfAllPlantingByMonthAndYearAndUserInfield(months2, years2,
						username) == null) {
					countPlantingYearMonth = 0;
				} else {
					countPlantingYearMonth = (float) (Math.floor(plantingService
							.findNumberOfAllPlantingByMonthAndYearAndUserInfield(months2, years2, username)) * 100)
							/ 100;

				}

				if (plantingService.findCountPlantingByMonthAndYearAndUserInfield(months2, years2, username) == null) {
					countPlanting = 0;
				} else {
					countPlanting = plantingService.findCountPlantingByMonthAndYearAndUserInfield(months2, years2,
							username);
				}

				m.addAttribute("countPlantingYearMonth", countPlantingYearMonth);
				m.addAttribute("countPlanting", countPlanting);

				// chart PieRegion2
				ArrayList<String> plantingRegionPieRegion2 = plantingRegionChart(months2, years2, username, "staff");
				ArrayList<Double> sumPieRegion2 = new ArrayList<Double>();
				int checkDataRegionChart = 1;
				if (plantingRegionPieRegion2.size() == 0) {
					checkDataRegionChart = 0;
				}
				sumPieRegion2 = sumAmountPieRegion2(months2, years2, username, "staff");
				m.addAttribute("checkDataRegionChart", checkDataRegionChart);
				m.addAttribute("plantingRegionPieRegion2", plantingRegionPieRegion2);
				m.addAttribute("sumPieRegion2", sumPieRegion2);

				// chart PieRegion2
				ArrayList<String> pieNameVarietyCharts = varietyPieChart(months2, years2, username, "staff");
				ArrayList<Double> sumPieVarietyCharts = new ArrayList<Double>();
				int checkDataVarietyChart = 1;
				if (pieNameVarietyCharts.size() == 0) {
					checkDataVarietyChart = 0;
				}
				sumPieVarietyCharts = sumVarietyPieChart(months2, years2, username, "staff");
				m.addAttribute("checkDataVarietyChart", checkDataVarietyChart);
				m.addAttribute("pieNameVarietyCharts", pieNameVarietyCharts);
				m.addAttribute("sumPieVarietyCharts", sumPieVarietyCharts);

				// chart lineChart3
				// YearNow
				int year8 = (new Date().getYear() + 1900);
				PlantingMonthAreaDTO plantingMonthAreaDTO = plantingMonthAndYearArea(year8, username, "staff");
				m.addAttribute("plantingMonthArea", plantingMonthAreaDTO);

				// YearNow-1
				int year9 = year8 - 1;
				PlantingMonthAreaDTO plantingMonthAreaDTO2 = plantingMonthAndYearArea(year9, username, "staff");
				m.addAttribute("plantingMonthArea2", plantingMonthAreaDTO2);

				// YearNow-2
				int year10 = year9 - 1;
				PlantingMonthAreaDTO plantingMonthAreaDTO3 = plantingMonthAndYearArea(year10, username, "staff");
				m.addAttribute("plantingMonthArea3", plantingMonthAreaDTO3);

				// ---- survey ---
				// Disease
				int countDiseaseYearMonth = 0;
				if (fieldService.findNumberOfFieldHasDiseaseByMonthsAndYearsByUserInField(months2, years2,
						username) != null) {
					countDiseaseYearMonth = fieldService
							.findNumberOfFieldHasDiseaseByMonthsAndYearsByUserInField(months2, years2, username);
				}
				m.addAttribute("countDiseaseYearMonth", countDiseaseYearMonth);
				// Pest
				int countPestYearMonth = 0;
				if (fieldService.findNumberOfFieldHasPestByMonthsAndYearsByUserInField(months2, years2,
						username) != null) {
					countPestYearMonth = fieldService.findNumberOfFieldHasPestByMonthsAndYearsByUserInField(months2,
							years2, username);
				}
				m.addAttribute("countPestYearMonth", countPestYearMonth);
				// NaturalEnemy
				int countNaturalEnemyYearMonth = 0;
				if (fieldService.findNumberOfFieldHasNaturalEnemyByMonthsAndYearsByUserInField(months2, years2,
						username) != null) {
					countNaturalEnemyYearMonth = fieldService
							.findNumberOfFieldHasNaturalEnemyByMonthsAndYearsByUserInField(months2, years2, username);
				}
				m.addAttribute("countNaturalEnemyYearMonth", countNaturalEnemyYearMonth);

				// % AvgPercentAndDamageLevel
				// --- Diseases ---
				ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsDiseases = avgPercentsDiseases(months2, years2,
						username, "staff");
				m.addAttribute("checkDisease", avgPercentsDiseases.size());
				m.addAttribute("avgPercentsDiseases", avgPercentsDiseases);

				// --- Pest ----
				ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsPests = avgPercentsPests(months2, years2,
						username, "staff");
				m.addAttribute("checkPest", avgPercentsPests.size());
				m.addAttribute("avgPercentsPests", avgPercentsPests);

				// --- NaturalEnemy ----
				ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsNaturalEnemys = avgPercentsNaturalEnemys(months2,
						years2, username, "staff");
				m.addAttribute("checkNaturalEnemy", avgPercentsNaturalEnemys.size());
				m.addAttribute("avgPercentsNaturalEnemys", avgPercentsNaturalEnemys);

			}

		}
		m.addAttribute("role", role);
		return "pages/home";

	}

	@RequestMapping(value = "/fieldchart/{month}/{year}/{yearAll}", method = RequestMethod.GET)
	public String dailyField(@PathVariable("month") int month, @PathVariable("year") int year,
			@PathVariable("yearAll") int yearAll, Authentication authentication, Model model) {

		String username = MvcHelper.getUsername(authentication);
		User user = userService.findByUsername(username);
		ArrayList<Integer> months = new ArrayList<>();
		ArrayList<Integer> years = new ArrayList<>();
		if (month == 0) {
			months.addAll(plantingService.findAllMonthInLastUpdateDate());

			if (yearAll == 0) {
				years.addAll(plantingService.findAllYearInLastUpdateDate());
			} else {
				years.add(yearAll);
			}

		} else {
			months.add(month);
			if (year == 0) {
				years.addAll(plantingService.findAllYearInLastUpdateDate());
			} else {
				years.add(year);
			}
		}

		if (user.getFarmer() != null) {
			// ---- survey ---
			// Disease
			int countDiseaseYearMonth = 0;
			if (fieldService.findNumberOfFieldHasDiseaseByMonthsAndYearsByUserInField(months, years,
					username) != null) {
				countDiseaseYearMonth = fieldService.findNumberOfFieldHasDiseaseByMonthsAndYearsByUserInField(months,
						years, username);
			}
			model.addAttribute("countDiseaseYearMonth", countDiseaseYearMonth);
			// Pest
			int countPestYearMonth = 0;
			if (fieldService.findNumberOfFieldHasPestByMonthsAndYearsByUserInField(months, years, username) != null) {
				countPestYearMonth = fieldService.findNumberOfFieldHasPestByMonthsAndYearsByUserInField(months, years,
						username);
			}
			model.addAttribute("countPestYearMonth", countPestYearMonth);
			// NaturalEnemy
			int countNaturalEnemyYearMonth = 0;
			if (fieldService.findNumberOfFieldHasNaturalEnemyByMonthsAndYearsByUserInField(months, years,
					username) != null) {
				countNaturalEnemyYearMonth = fieldService
						.findNumberOfFieldHasNaturalEnemyByMonthsAndYearsByUserInField(months, years, username);
			}
			model.addAttribute("countNaturalEnemyYearMonth", countNaturalEnemyYearMonth);

			// % AvgPercentAndDamageLevel
			// --- Diseases ---
			ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsDiseases = avgPercentsDiseases(months, years,
					username, "farmer");
			model.addAttribute("checkDisease", avgPercentsDiseases.size());
			model.addAttribute("avgPercentsDiseases", avgPercentsDiseases);

			// --- Pest ----
			ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsPests = avgPercentsPests(months, years, username,
					"farmer");
			model.addAttribute("checkPest", avgPercentsPests.size());
			model.addAttribute("avgPercentsPests", avgPercentsPests);

			// --- NaturalEnemy ----
			ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsNaturalEnemys = avgPercentsNaturalEnemys(months,
					years, username, "farmer");
			model.addAttribute("checkNaturalEnemy", avgPercentsNaturalEnemys.size());
			model.addAttribute("avgPercentsNaturalEnemys", avgPercentsNaturalEnemys);
		} else if (user.getStaff() != null) {
			if (roleService.findByUserIdAndRoleName(user.getUserId(), "systemAdmin") != null) {
				// Disease
				int countDiseaseYearMonth = 0;
				if (fieldService.findNumberOfFieldHasDiseaseByMonthsAndYears(months, years) != null) {
					countDiseaseYearMonth = fieldService.findNumberOfFieldHasDiseaseByMonthsAndYears(months, years);
				}
				model.addAttribute("countDiseaseYearMonth", countDiseaseYearMonth);
				// Pest
				int countPestYearMonth = 0;
				if (fieldService.findNumberOfFieldHasPestByMonthsAndYears(months, years) != null) {
					countPestYearMonth = fieldService.findNumberOfFieldHasPestByMonthsAndYears(months, years);
				}
				model.addAttribute("countPestYearMonth", countPestYearMonth);
				// NaturalEnemy
				int countNaturalEnemyYearMonth = 0;
				if (fieldService.findNumberOfFieldHasNaturalEnemyByMonthsAndYears(months, years) != null) {
					countNaturalEnemyYearMonth = fieldService.findNumberOfFieldHasNaturalEnemyByMonthsAndYears(months,
							years);
				}
				model.addAttribute("countNaturalEnemyYearMonth", countNaturalEnemyYearMonth);

				// % AvgPercentAndDamageLevel
				// --- Diseases ---
				ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsDiseases = avgPercentsDiseases(months, years,
						username, "systemAdmin");
				model.addAttribute("checkDisease", avgPercentsDiseases.size());
				model.addAttribute("avgPercentsDiseases", avgPercentsDiseases);

				// --- Pest ----
				ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsPests = avgPercentsPests(months, years, username,
						"systemAdmin");
				model.addAttribute("checkPest", avgPercentsPests.size());
				model.addAttribute("avgPercentsPests", avgPercentsPests);

				// --- NaturalEnemy ----
				ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsNaturalEnemys = avgPercentsNaturalEnemys(months,
						years, username, "systemAdmin");
				model.addAttribute("checkNaturalEnemy", avgPercentsNaturalEnemys.size());
				model.addAttribute("avgPercentsNaturalEnemys", avgPercentsNaturalEnemys);
			} else if (roleService.findByUserIdAndRoleName(user.getUserId(), "fieldRegistrar") != null) {
				// Disease
				int countDiseaseYearMonth = 0;
				if (fieldService.findNumberOfFieldHasDiseaseByMonthsAndYearsByOrganization(months, years,
						username) != null) {
					countDiseaseYearMonth = fieldService
							.findNumberOfFieldHasDiseaseByMonthsAndYearsByOrganization(months, years, username);
				}
				model.addAttribute("countDiseaseYearMonth", countDiseaseYearMonth);
				// Pest
				int countPestYearMonth = 0;
				if (fieldService.findNumberOfFieldHasPestByMonthsAndYearsByOrganization(months, years,
						username) != null) {
					countPestYearMonth = fieldService.findNumberOfFieldHasPestByMonthsAndYearsByOrganization(months,
							years, username);
				}
				model.addAttribute("countPestYearMonth", countPestYearMonth);

				// NaturalEnemy
				int countNaturalEnemyYearMonth = 0;
				if (fieldService.findNumberOfFieldHasNaturalEnemyByMonthsAndYearsByOrganization(months, years,
						username) != null) {
					countNaturalEnemyYearMonth = fieldService
							.findNumberOfFieldHasNaturalEnemyByMonthsAndYearsByOrganization(months, years, username);
				}
				model.addAttribute("countNaturalEnemyYearMonth", countNaturalEnemyYearMonth);

				// % AvgPercentAndDamageLevel
				// --- Diseases ---
				ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsDiseases = avgPercentsDiseases(months, years,
						username, "fieldRegistrar");
				model.addAttribute("checkDisease", avgPercentsDiseases.size());
				model.addAttribute("avgPercentsDiseases", avgPercentsDiseases);

				// --- Pest ----
				ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsPests = avgPercentsPests(months, years, username,
						"fieldRegistrar");
				model.addAttribute("checkPest", avgPercentsPests.size());
				model.addAttribute("avgPercentsPests", avgPercentsPests);

				// --- NaturalEnemy ----
				ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsNaturalEnemys = avgPercentsNaturalEnemys(months,
						years, username, "fieldRegistrar");
				model.addAttribute("checkNaturalEnemy", avgPercentsNaturalEnemys.size());
				model.addAttribute("avgPercentsNaturalEnemys", avgPercentsNaturalEnemys);
			} else {
				// Disease
				int countDiseaseYearMonth = 0;
				if (fieldService.findNumberOfFieldHasDiseaseByMonthsAndYearsByUserInField(months, years,
						username) != null) {
					countDiseaseYearMonth = fieldService
							.findNumberOfFieldHasDiseaseByMonthsAndYearsByUserInField(months, years, username);
				}
				model.addAttribute("countDiseaseYearMonth", countDiseaseYearMonth);
				// Pest
				int countPestYearMonth = 0;
				if (fieldService.findNumberOfFieldHasPestByMonthsAndYearsByUserInField(months, years,
						username) != null) {
					countPestYearMonth = fieldService.findNumberOfFieldHasPestByMonthsAndYearsByUserInField(months,
							years, username);
				}
				model.addAttribute("countPestYearMonth", countPestYearMonth);
				// NaturalEnemy
				int countNaturalEnemyYearMonth = 0;
				if (fieldService.findNumberOfFieldHasNaturalEnemyByMonthsAndYearsByUserInField(months, years,
						username) != null) {
					countNaturalEnemyYearMonth = fieldService
							.findNumberOfFieldHasNaturalEnemyByMonthsAndYearsByUserInField(months, years, username);
				}
				model.addAttribute("countNaturalEnemyYearMonth", countNaturalEnemyYearMonth);

				// % AvgPercentAndDamageLevel
				// --- Diseases ---
				ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsDiseases = avgPercentsDiseases(months, years,
						username, "staff");
				model.addAttribute("checkDisease", avgPercentsDiseases.size());
				model.addAttribute("avgPercentsDiseases", avgPercentsDiseases);

				// --- Pest ----
				ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsPests = avgPercentsPests(months, years, username,
						"staff");
				model.addAttribute("checkPest", avgPercentsPests.size());
				model.addAttribute("avgPercentsPests", avgPercentsPests);

				// --- NaturalEnemy ----
				ArrayList<FieldAvgPercentAndDamageLevelDTO> avgPercentsNaturalEnemys = avgPercentsNaturalEnemys(months,
						years, username, "staff");
				model.addAttribute("checkNaturalEnemy", avgPercentsNaturalEnemys.size());
				model.addAttribute("avgPercentsNaturalEnemys", avgPercentsNaturalEnemys);
			}
		}

		return "pages/fieldhome";
	}

	@RequestMapping(value = "/planting/{month}/{year}/{yearAll}", method = RequestMethod.GET)
	public String dailyPlanting(@PathVariable("month") int month, @PathVariable("year") int year,
			@PathVariable("yearAll") int yearAll, Authentication authentication, Model model) {
		String username = MvcHelper.getUsername(authentication);
		User user = userService.findByUsername(username);
		ArrayList<Integer> months = new ArrayList<>();
		ArrayList<Integer> years = new ArrayList<>();
		if (month == 0) {
			months.addAll(plantingService.findAllMonthInLastUpdateDate());

			if (yearAll == 0) {
				years.addAll(plantingService.findAllYearInLastUpdateDate());
			} else {
				years.add(yearAll);
			}

		} else {
			months.add(month);
			if (year == 0) {
				years.addAll(plantingService.findAllYearInLastUpdateDate());
			} else {
				years.add(year);
			}
		}

		int check = 1;
		double countPlantingYearMonth = 0;
		int countPlanting = 0;
		if (user.getFarmer() != null) {
			if (plantingService.findNumberOfAllPlantingByMonthAndYearAndUserInfield(months, years, username) == null) {
				countPlantingYearMonth = 0;
			} else {
				countPlantingYearMonth = (float) (Math.floor(
						plantingService.findNumberOfAllPlantingByMonthAndYearAndUserInfield(months, years, username))
						* 100) / 100;

			}

			if (plantingService.findCountPlantingByMonthAndYearAndUserInfield(months, years, username) == 0) {
				countPlanting = 0;
			} else {
				countPlanting = plantingService.findCountPlantingByMonthAndYearAndUserInfield(months, years, username);
			}
		} else if (user.getStaff() != null) {

			if (roleService.findByUserIdAndRoleName(user.getUserId(), "systemAdmin") != null) {

				if (plantingService.findNumberOfAllPlantingByMonthAndYear(months, years) == null) {
					countPlantingYearMonth = 0;
				} else {
					countPlantingYearMonth = plantingService.findNumberOfAllPlantingByMonthAndYear(months, years);
				}

				if (plantingService.findCountPlantingByMonthAndYear(months, years) == null) {
					countPlanting = 0;
				} else {
					countPlanting = plantingService.findCountPlantingByMonthAndYear(months, years);
				}

			} else if (roleService.findByUserIdAndRoleName(user.getUserId(), "fieldRegistrar") != null) {

				if (plantingService.findNumberOfAllPlantingByMonthAndYearandOrganization(months, years,
						user.getStaff()) == null) {
					countPlantingYearMonth = 0;
				} else {
					countPlantingYearMonth = plantingService
							.findNumberOfAllPlantingByMonthAndYearandOrganization(months, years, user.getStaff());
				}

				if (plantingService.findCountPlantingByMonthAndYearandOrganization(months, years,
						user.getStaff()) == null) {
					countPlanting = 0;
				} else {
					countPlanting = plantingService.findCountPlantingByMonthAndYearandOrganization(months, years,
							user.getStaff());
				}

			} else {

				if (plantingService.findNumberOfAllPlantingByMonthAndYearAndUserInfield(months, years,
						username) == null) {
					countPlantingYearMonth = 0;
				} else {
					countPlantingYearMonth = plantingService.findNumberOfAllPlantingByMonthAndYearAndUserInfield(months,
							years, username);
				}

				if (plantingService.findCountPlantingByMonthAndYearAndUserInfield(months, years, username) == null) {
					countPlanting = 0;
				} else {
					countPlanting = plantingService.findCountPlantingByMonthAndYearAndUserInfield(months, years,
							username);
				}
			}

		}

		model.addAttribute("countPlantingYearMonth", countPlantingYearMonth);
		model.addAttribute("countPlanting", countPlanting);

		// chart PieRegion2
		ArrayList<String> plantingRegionPieRegion2 = new ArrayList<>();
		ArrayList<Double> sumPieRegion2 = new ArrayList<Double>();
		int checkDataRegionChart = 1;
		if (user.getFarmer() != null) {
			if (plantingService.findNumberOfAllPlantingByMonthAndYearAndUserInfieldInRegion(months, years, username)
					.size() == 0) {
				checkDataRegionChart = 0;

			} else {
				plantingRegionPieRegion2 = plantingRegionChart(months, years, username, "farmer");
				sumPieRegion2 = sumAmountPieRegion2(months, years, username, "farmer");
			}
		} else if (user.getStaff() != null) {
			if (roleService.findByUserIdAndRoleName(user.getUserId(), "systemAdmin") != null) {

				if (plantingService.findNumberOfAllPlantingByMonthAndYearInRegion(months, years).size() == 0) {
					checkDataRegionChart = 0;

				} else {
					plantingRegionPieRegion2 = plantingRegionChart(months, years, username, "systemAdmin");
					sumPieRegion2 = sumAmountPieRegion2(months, years, username, "systemAdmin");
				}
			} else if (roleService.findByUserIdAndRoleName(user.getUserId(), "fieldRegistrar") != null) {

				if (plantingService
						.findNumberOfAllPlantingByMonthAndYearandOrganizationInRegion(months, years, user.getStaff())
						.size() == 0) {
					checkDataRegionChart = 0;
				} else {
					plantingRegionPieRegion2 = plantingRegionChart(months, years, username, "fieldRegistrar");
					sumPieRegion2 = sumAmountPieRegion2(months, years, username, "fieldRegistrar");
				}
			} else {
				if (plantingService.findNumberOfAllPlantingByMonthAndYearAndUserInfieldInRegion(months, years, username)
						.size() == 0) {
					checkDataRegionChart = 0;

				} else {
					plantingRegionPieRegion2 = plantingRegionChart(months, years, username, "staff");
					sumPieRegion2 = sumAmountPieRegion2(months, years, username, "staff");
				}
			}

		}
		model.addAttribute("checkDataRegionChart", checkDataRegionChart);
		model.addAttribute("plantingRegionPieRegion2", plantingRegionPieRegion2);
		model.addAttribute("sumPieRegion2", sumPieRegion2);

		// chart varietyChart
		ArrayList<String> pieNameVarietyCharts = new ArrayList<>();
		ArrayList<Double> sumPieVarietyCharts = new ArrayList<Double>();
		int checkDataVarietyChart = 1;
		if (user.getFarmer() != null) {
			if (plantingService.findNumberOfAllPlantingByMonthAndYearAndUserInfieldInVariety(months, years, username)
					.size() == 0) {
				checkDataVarietyChart = 0;
			} else {
				pieNameVarietyCharts = varietyPieChart(months, years, username, "farmer");
				sumPieVarietyCharts = sumVarietyPieChart(months, years, username, "farmer");
			}
		} else if (user.getStaff() != null) {

			if (roleService.findByUserIdAndRoleName(user.getUserId(), "systemAdmin") != null) {
				if (plantingService.findNumberOfAllPlantingByMonthAndYearInVariety(months, years).size() == 0) {
					checkDataVarietyChart = 0;
				} else {
					pieNameVarietyCharts = varietyPieChart(months, years, username, "systemAdmin");
					sumPieVarietyCharts = sumVarietyPieChart(months, years, username, "systemAdmin");
				}
			} else if (roleService.findByUserIdAndRoleName(user.getUserId(), "fieldRegistrar") != null) {

				if (plantingService
						.findNumberOfAllPlantingByMonthAndYearandOrganizationInVariety(months, years, user.getStaff())
						.size() == 0) {

					checkDataVarietyChart = 0;
				} else {
					pieNameVarietyCharts = varietyPieChart(months, years, username, "fieldRegistrar");
					sumPieVarietyCharts = sumVarietyPieChart(months, years, username, "fieldRegistrar");

				}
			} else {
				if (plantingService
						.findNumberOfAllPlantingByMonthAndYearAndUserInfieldInVariety(months, years, username)
						.size() == 0) {
					checkDataVarietyChart = 0;
				} else {
					pieNameVarietyCharts = varietyPieChart(months, years, username, "staff");
					sumPieVarietyCharts = sumVarietyPieChart(months, years, username, "staff");
				}
			}

		}
		model.addAttribute("checkDataVarietyChart", checkDataVarietyChart);
		model.addAttribute("pieNameVarietyCharts", pieNameVarietyCharts);
		model.addAttribute("sumPieVarietyCharts", sumPieVarietyCharts);

		// chart lineChart3

		int yearCheck = 0;
		if (yearAll == 0 && month == 0) {
			int year2 = (new Date().getYear() + 1900);
			yearCheck = year2;

		} else if (yearAll > 0 && month == 0) {
			yearCheck = yearAll;

		} else {
			yearCheck = year;

		}
		// chart lineChart3
		// YearNow

		PlantingMonthAreaDTO plantingMonthAreaDTO = new PlantingMonthAreaDTO();
		if (user.getFarmer() != null) {
			plantingMonthAreaDTO = plantingMonthAndYearArea(yearCheck, username, "farmer");

		} else if (user.getStaff() != null) {
			if (roleService.findByUserIdAndRoleName(user.getUserId(), "systemAdmin") != null) {

				plantingMonthAreaDTO = plantingMonthAndYearArea(yearCheck, username, "systemAdmin");
			} else if (roleService.findByUserIdAndRoleName(user.getUserId(), "fieldRegistrar") != null) {
				plantingMonthAreaDTO = plantingMonthAndYearArea(yearCheck, username, "fieldRegistrar");
			} else {
				plantingMonthAreaDTO = plantingMonthAndYearArea(yearCheck, username, "staff");
			}
		}
		model.addAttribute("plantingMonthArea", plantingMonthAreaDTO);

		// YearNow-1

		PlantingMonthAreaDTO plantingMonthAreaDTO2 = new PlantingMonthAreaDTO();
		if (user.getFarmer() != null) {
			plantingMonthAreaDTO2 = plantingMonthAndYearArea(yearCheck - 1, username, "farmer");
		} else if (user.getStaff() != null) {
			if (roleService.findByUserIdAndRoleName(user.getUserId(), "systemAdmin") != null) {

				plantingMonthAreaDTO2 = plantingMonthAndYearArea(yearCheck - 1, username, "systemAdmin");
			} else if (roleService.findByUserIdAndRoleName(user.getUserId(), "fieldRegistrar") != null) {
				plantingMonthAreaDTO2 = plantingMonthAndYearArea(yearCheck - 1, username, "fieldRegistrar");
			} else {
				plantingMonthAreaDTO2 = plantingMonthAndYearArea(yearCheck - 1, username, "staff");
			}
		}
		model.addAttribute("plantingMonthArea2", plantingMonthAreaDTO2);

		// yearNow-2
		PlantingMonthAreaDTO plantingMonthAreaDTO3 = new PlantingMonthAreaDTO();
		if (user.getFarmer() != null) {
			plantingMonthAreaDTO3 = plantingMonthAndYearArea(yearCheck - 2, username, "farmer");

		} else if (user.getStaff() != null) {
			if (roleService.findByUserIdAndRoleName(user.getUserId(), "systemAdmin") != null) {

				plantingMonthAreaDTO3 = plantingMonthAndYearArea(yearCheck - 2, username, "systemAdmin");
			} else if (roleService.findByUserIdAndRoleName(user.getUserId(), "fieldRegistrar") != null) {
				plantingMonthAreaDTO3 = plantingMonthAndYearArea(yearCheck - 2, username, "fieldRegistrar");
			} else {
				plantingMonthAreaDTO3 = plantingMonthAndYearArea(yearCheck - 2, username, "staff");
			}
		}
		model.addAttribute("plantingMonthArea3", plantingMonthAreaDTO3);

		return "pages/plantinghome";
	}

	public static int dateDiff(Date startDate, Date endDate) {

		DateFormat df = new SimpleDateFormat("d MMM yyyy");

		try {
			Date startdate = startDate;

			Date enddate = endDate;

			long diff = enddate.getTime() - startdate.getTime();

			int dayDiff = (int) (diff / (24 * 60 * 60 * 1000));
			return dayDiff;
		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		return 0;

	}

	@RequestMapping(value = "/alert/index", method = RequestMethod.GET)
	public String alertIndex(Model m, Authentication authentication) {

		String username = MvcHelper.getUsername(authentication);
		User user = userService.findByUsername(username);
		List<Messagereceiver> messagereceivers = messagereceiverService.findByUserId(user.getUserId());

		List<MessageReceiverDTO> messageReceiverDTO = new ArrayList<>();
		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		m.addAttribute("formatter", formatter);
		String dateStr;
		for (Messagereceiver messagereceiver : messagereceiverService.findByUserId(user.getUserId())) {
			int dayDiff = dateDiff(messagereceiver.getMessage().getSendDate(), new Date());

			if (dayDiff < 7 && dayDiff != 0) {
				dateStr = dayDiff + " วันที่ผ่านมา";
			} else if (dayDiff == 0) {
				dateStr = "วันนี้";
			} else if (dayDiff >= 7 && dayDiff <= 30) {
				dateStr = dayDiff / 7 + " สัปดาห์ที่ผ่านมา";
			} else {

				dateStr = "เมื่อวันที่ " + formatter.format(messagereceiver.getMessage().getSendDate());
			}

			messageReceiverDTO.add(new MessageReceiverDTO(messagereceiver.getMessage().getTitle(),
					messagereceiver.getMessage().getText(), dateStr, messagereceiver.getMessage().getType()));

		}
		m.addAttribute("messageReceiverDTO", messageReceiverDTO);

		for (Messagereceiver messagereceiver2 : messagereceivers) {
			messagereceiver2.setReadStatus("Y");
			messagereceiverService.save(messagereceiver2);
		}

		// m.addAttribute("messagereceivers", messagereceivers);

		return "alert/index";
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ResponseEntity<Resource> download() throws IOException {

		Resource r = new ClassPathResource("USERMANUAL_WEB_APPLICATION.pdf");
		File file = r.getFile();
	    FileInputStream f = new FileInputStream(r.getFile());
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=user_manual.pdf");
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		InputStreamResource resource = new InputStreamResource(f);
		return ResponseEntity.ok().headers(headers).contentLength(file.length())
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Authentication authentication) {

		if (authentication != null) {
			model.addAttribute("message", "Hi " + MvcHelper.getUsername(authentication)
					+ "<br> You do not have permission to access this page!");
		} else {
			model.addAttribute("msg", "You do not have permission to access this page!");
		}
		return "error/403";
	}

	@RequestMapping(value = "/inactive", method = RequestMethod.GET)
	public String registCodeInactive(Model m, Authentication authentication, HttpServletRequest request,
			HttpServletResponse response) {

		String userName = MvcHelper.getUsername(authentication);
		// User user = userService.findByUsername(userName);
		m.addAttribute("message", userName + "<br> บัญชีผู้ใช้ถูกพักการใช้งาน");
		m.addAttribute("subMessage", "กรุณาติดต่อเจ้าหน้าที่");
		clearSession(authentication, request, response);
		return "register/registerStatus";
	}

	private void clearSession(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		Cookie[] cookies = request.getCookies();

		if (cookies != null)
			for (Cookie cookie : cookies) {

				cookie.setValue("");
				cookie.setPath("/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);

			}

		request.getSession();
	}

}
