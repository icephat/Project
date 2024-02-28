package org.cassava.web.controller;

import java.io.File;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.cassava.model.Disease;
import org.cassava.model.ImgSurveyTargetPoint;
import org.cassava.model.User;
import org.cassava.model.dto.ImageDTO;
import org.cassava.model.dto.SelectedImgSurveyTargetPointDTO;
import org.cassava.services.DiseaseService;
import org.cassava.services.ImgSurveyTargetPointService;
import org.cassava.services.UserService;
import org.cassava.util.MvcHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApproveController {

	@Value("${external.resoures.path}")
	private String externalPath;

	@Autowired
	private DiseaseService diseaseService;

	@Autowired
	private UserService userService;

	@Autowired
	private ImgSurveyTargetPointService imgSurveyTargetPointService;

	@RequestMapping(value = { "/approve/index", "/approve/index/find", "/approve/" }, method = RequestMethod.GET)
	public String approveIndex(Model model, @ModelAttribute("name") String name) {
		List<Disease> diseases;
		if (name == null) {
			diseases = diseaseService.findAll();
		} else {
			diseases = diseaseService.findByTargetofsurveyName(name);
		}

		List<ImageDTO> images = new ArrayList<ImageDTO>();
		List<Integer> approve = new ArrayList<Integer>();
		List<Integer> all = new ArrayList<Integer>();
		List<Double> percent = new ArrayList<Double>();
		Random r = new Random();
		for (Disease disease : diseases) {
			ImageDTO imageDTO = new ImageDTO();
			int size = disease.getImgdiseases().size();
			if (size > 0) {
				String path = externalPath + File.separator + "Disease" + File.separator
						+ disease.getImgdiseases().get(r.nextInt(size)).getFilePath();
				boolean checkFileExist = new File(path).exists();
				if (!checkFileExist) {
					imageDTO.setImage(externalPath + File.separator + "no-image.jpg");
				} else {
					imageDTO.setImage(path);
				}
			} else {
				imageDTO.setImage(externalPath + File.separator + "no-image.jpg");
			}
			images.add(imageDTO);

			int numApprove = imgSurveyTargetPointService.findByDiseaseAndStatus(disease, "Approved").size();
			int numReject = imgSurveyTargetPointService.findByDiseaseAndStatus(disease, "Reject").size();
			int numWaiting = imgSurveyTargetPointService.findByDiseaseAndStatus(disease, "Waiting").size();
			int numAll = numWaiting + numApprove + numReject;

			approve.add(numApprove + numReject);
			all.add(numAll);
			if (numAll == 0)
				percent.add((double) 0);
			else
				percent.add(Math.floor((numApprove + numReject) * 10000 / (float) (numAll)) / 100);
		}

		model.addAttribute("approve", approve);
		model.addAttribute("all", all);
		model.addAttribute("percent", percent);
		model.addAttribute("diseases", diseases);
		model.addAttribute("images", images);
		return "/approve/approveIndex";
	}

	@RequestMapping(value = "/approve/edit/{id}/page/{page}/value/{value}", method = RequestMethod.POST)
	public String approvecheckList(
			@ModelAttribute("SelectedImgSurveyTargetPointDTO") SelectedImgSurveyTargetPointDTO selectedImgSurveyTargetPointDTO,
			@PathVariable("id") int id, @PathVariable("page") int page, @PathVariable("value") int value,
			@ModelAttribute("status1") String status, Model model, Authentication authentication) {
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/approve/";
		}
		Date datenow = new Date(System.currentTimeMillis());
		if (selectedImgSurveyTargetPointDTO.getCheck() != null) {
			int i = 0;
			for (Boolean check : selectedImgSurveyTargetPointDTO.getCheck()) {
				if (check != null) {
					ImgSurveyTargetPoint imgSurveyTargetPoint = imgSurveyTargetPointService
							.findById(selectedImgSurveyTargetPointDTO.getImgSurveyTargetPoint().get(i));
					if (status.equals("อนุมัติ")) {
						imgSurveyTargetPoint.setApproveStatus("Approved");
					} else if (status.equals("ไม่อนุมัติ")) {
						imgSurveyTargetPoint.setApproveStatus("Reject");
					}
					imgSurveyTargetPoint.setApproveDate(datenow);
					User user = userService.findByUsername(MvcHelper.getUsername(authentication));
					imgSurveyTargetPoint.setUserByApproveBy(user);
					imgSurveyTargetPointService.save(imgSurveyTargetPoint);

					if (status.equals("ลบรูป")) {
						File fileimagesPath = new File(externalPath + File.separator + "SurveyTargetPoint"
								+ File.separator + imgSurveyTargetPoint.getFilePath());
						File fileimagesPaths = new File(externalPath + File.separator + "s_SurveyTargetPoint"
								+ File.separator + imgSurveyTargetPoint.getFilePathS());
						if (fileimagesPath.delete()) {
							System.out.println("Deleted the file: " + fileimagesPath.getName());
						} else {
							System.out.println("Failed to delete the file.");
						}
						if (fileimagesPaths.delete()) {
							System.out.println("Deleted the file: " + fileimagesPaths.getName());
						} else {
							System.out.println("Failed to delete the file.");
						}
						imgSurveyTargetPoint.setApproveStatus("Delete");
						// User user =
						// userService.findByUsername(MvcHelper.getUsername(authentication));
						imgSurveyTargetPoint.setUserByApproveBy(user);
						imgSurveyTargetPointService.save(imgSurveyTargetPoint);
						// imgSurveyTargetPointService.deleteById(imgSurveyTargetPoint.getImgSurveyTargetPointId());
					}
				}
				i++;

			}
		}

		model.addAttribute("id", id);
		model.addAttribute("value", value);
		model.addAttribute("page", page);

		model.addAttribute("nameDisease", disease.getTargetofsurvey().getName());

		int MaxSize = (int) Math.ceil(imgSurveyTargetPointService.findByDiseaseAndStatus(disease, "Waiting").size());
		model.addAttribute("MaxSize", MaxSize);

		model.addAttribute("nameDisease", disease.getTargetofsurvey().getName());
		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatter);

		List<Integer> sizePageList = new ArrayList<Integer>();
		sizePageList.add(6);
		sizePageList.add(12);
		sizePageList.add(24);
		sizePageList.add(48);
		model.addAttribute("sizePages", sizePageList);
		return "/approve/approveDetail";
	}

	@RequestMapping(value = "/approve/success/edit/{id}/page/{page}/value/{value}", method = RequestMethod.POST)
	public String approverejectcheckList(
			@ModelAttribute("SelectedImgSurveyTargetPointDTO") SelectedImgSurveyTargetPointDTO selectedImgSurveyTargetPointDTO,
			@PathVariable("id") int id, @PathVariable("page") int page, @PathVariable("value") int value,
			@ModelAttribute("status0") String status, Model model, Authentication authentication) {
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/approve/";
		}
		Date datenow = new Date(System.currentTimeMillis());
		if (selectedImgSurveyTargetPointDTO.getCheck() != null) {
			int i = 0;
			for (Boolean check : selectedImgSurveyTargetPointDTO.getCheck()) {

				if (check != null) {
					ImgSurveyTargetPoint imgSurveyTargetPoint = imgSurveyTargetPointService
							.findById(selectedImgSurveyTargetPointDTO.getImgSurveyTargetPoint().get(i));
					if (status.equals("ไม่อนุมัติ")) {
						imgSurveyTargetPoint.setApproveStatus("Reject");
					}
					imgSurveyTargetPoint.setApproveDate(datenow);
					User user = userService.findByUsername(MvcHelper.getUsername(authentication));
					imgSurveyTargetPoint.setUserByApproveBy(user);
					imgSurveyTargetPointService.save(imgSurveyTargetPoint);
					if (status.equals("ลบรูป")) {
						File fileimagesPath = new File(externalPath + File.separator + "SurveyTargetPoint"
								+ File.separator + imgSurveyTargetPoint.getFilePath());
						File fileimagesPaths = new File(externalPath + File.separator + "s_SurveyTargetPoint"
								+ File.separator + imgSurveyTargetPoint.getFilePathS());
						if (fileimagesPath.delete()) {
							System.out.println("Deleted the file: " + fileimagesPath.getName());
						} else {
							System.out.println("Failed to delete the file.");
						}
						if (fileimagesPaths.delete()) {
							System.out.println("Deleted the file: " + fileimagesPaths.getName());
						} else {
							System.out.println("Failed to delete the file.");
						}
						imgSurveyTargetPoint.setApproveStatus("Delete");
						// User user =
						// userService.findByUsername(MvcHelper.getUsername(authentication));
						imgSurveyTargetPoint.setUserByApproveBy(user);
						imgSurveyTargetPointService.save(imgSurveyTargetPoint);
						// imgSurveyTargetPointService.deleteById(imgSurveyTargetPoint.getImgSurveyTargetPointId());
					}
				}
				i++;

			}
		}

		model.addAttribute("id", id);
		model.addAttribute("value", value);
		model.addAttribute("page", page);

		String nameDisease = disease.getTargetofsurvey().getName();
		model.addAttribute("nameDisease", nameDisease);
		Format formatterTH = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatterTH);
		List<Integer> sizePageList = new ArrayList<Integer>();
		sizePageList.add(6);
		sizePageList.add(12);
		sizePageList.add(24);
		sizePageList.add(48);
		model.addAttribute("sizePages", sizePageList);
		int MaxSize = (int) Math.ceil(imgSurveyTargetPointService.findByDiseaseAndStatus(disease, "Approved").size());
		model.addAttribute("MaxSize", MaxSize);
		return "/approve/approveSuccess";
	}

	@RequestMapping(value = "/approve/detail/edit/{id}/{idimg}/page/{page}/value/{value}", method = RequestMethod.POST)
	public String approveApproved(String type, @PathVariable("page") int page, @PathVariable("value") int value,
			@PathVariable("id") int id, @PathVariable("idimg") int idimg, Model model, Authentication authentication) {
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/approve/";
		}
		Date datenow = new Date(System.currentTimeMillis());
		ImgSurveyTargetPoint imgSurveyTargetPointChange = imgSurveyTargetPointService.findById(idimg);
		if (type.equals("Delete")) {
			File fileimagesPath = new File(externalPath + File.separator + "SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPointService.findById(idimg).getFilePath());
			File fileimagesPaths = new File(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPointService.findById(idimg).getFilePathS());
			if (fileimagesPath.delete()) {
				System.out.println("Deleted the file: " + fileimagesPath.getName());
			} else {
				System.out.println("Failed to delete the file.");
			}
			if (fileimagesPaths.delete()) {
				System.out.println("Deleted the file: " + fileimagesPaths.getName());
			} else {
				System.out.println("Failed to delete the file.");
			}
			imgSurveyTargetPointChange.setApproveStatus("Delete");
			User user = userService.findByUsername(MvcHelper.getUsername(authentication));
			// User user =
			// userService.findByUsername(MvcHelper.getUsername(authentication));
			imgSurveyTargetPointChange.setUserByApproveBy(user);
			imgSurveyTargetPointChange.setApproveDate(datenow);
			imgSurveyTargetPointService.save(imgSurveyTargetPointChange);
			// imgSurveyTargetPointService.deleteById(idimg);
		} else {

			if (type.equals("Approved")) {
				imgSurveyTargetPointChange.setApproveStatus("Approved");
			} else {
				imgSurveyTargetPointChange.setApproveStatus("Reject");
			}
			imgSurveyTargetPointChange.setApproveDate(datenow);
			User user = userService.findByUsername(MvcHelper.getUsername(authentication));
			imgSurveyTargetPointChange.setUserByApproveBy(user);
			imgSurveyTargetPointService.save(imgSurveyTargetPointChange);
		}
		String Waiting = new String("Waiting");

		List<ImgSurveyTargetPoint> imgSurveyTargetPoints = imgSurveyTargetPointService
				.findByDiseaseAndStatus(diseaseService.findById(id), Waiting, page, value);
		List<ImageDTO> images = new ArrayList<ImageDTO>();
		List<ImageDTO> dtosOriginalSize = new ArrayList<ImageDTO>();
		for (ImgSurveyTargetPoint imgSurveyTargetPoint : imgSurveyTargetPoints) {

			ImageDTO imageDTOs = new ImageDTO();
			imageDTOs.setImage(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePathS());
			images.add(imageDTOs);
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(externalPath + File.separator + "SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePath());
			dtosOriginalSize.add(imageDTO);
		}
		model.addAttribute("dtosOriginalSizeList", dtosOriginalSize);
		model.addAttribute("dtoList", images);

		model.addAttribute("img", imgSurveyTargetPoints);

		model.addAttribute("id", id);
		int MaxSize = (int) Math.ceil(imgSurveyTargetPointService.findByDiseaseAndStatus(disease, "Waiting").size());
		model.addAttribute("MaxSize", MaxSize);
		model.addAttribute("sizenow", MaxSize);
		model.addAttribute("value", value);
		model.addAttribute("page", page);
		int firstItem = ((page - 1) * value + 1);
		model.addAttribute("firstItem", firstItem);
		int lastItem = (page * value);
		if (lastItem > MaxSize) {
			lastItem = MaxSize;
		}
		model.addAttribute("lastItem", lastItem);
		String nameDisease = disease.getTargetofsurvey().getName();
		model.addAttribute("nameDisease", nameDisease);
		Format formatterTH = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatterTH);
		return "/approve/approvetable";
	}

	@RequestMapping(value = "/approve/reject/edit/{id}/{idimg}/page/{page}/value/{value}", method = RequestMethod.POST)
	public String approveReject(String type, @PathVariable("page") int page, @PathVariable("value") int value,
			@PathVariable("id") int id, @PathVariable("idimg") int idimg, Model model, Authentication authentication) {
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/approve/";
		}
		Date datenow = new Date(System.currentTimeMillis());
		ImgSurveyTargetPoint imgSurveyTargetPointChange = imgSurveyTargetPointService.findById(idimg);
		if (type.equals("Delete")) {
			File fileimagesPath = new File(externalPath + File.separator + "SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPointService.findById(idimg).getFilePath());
			File fileimagesPaths = new File(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPointService.findById(idimg).getFilePathS());
			if (fileimagesPath.delete()) {
				System.out.println("Deleted the file: " + fileimagesPath.getName());
			} else {
				System.out.println("Failed to delete the file.");
			}
			if (fileimagesPaths.delete()) {
				System.out.println("Deleted the file: " + fileimagesPaths.getName());
			} else {
				System.out.println("Failed to delete the file.");
			}
			imgSurveyTargetPointChange.setApproveStatus("Delete");
			User user = userService.findByUsername(MvcHelper.getUsername(authentication));
			imgSurveyTargetPointChange.setUserByApproveBy(user);
			imgSurveyTargetPointChange.setApproveDate(datenow);
			imgSurveyTargetPointService.save(imgSurveyTargetPointChange);
			// imgSurveyTargetPointService.deleteById(idimg);
		}
		String Reject = new String("Reject");

		List<ImgSurveyTargetPoint> imgSurveyTargetPoints = imgSurveyTargetPointService
				.findByDiseaseAndStatus(diseaseService.findById(id), Reject, page, value);
		List<ImageDTO> images = new ArrayList<ImageDTO>();
		List<ImageDTO> dtosOriginalSize = new ArrayList<ImageDTO>();
		for (ImgSurveyTargetPoint imgSurveyTargetPoint : imgSurveyTargetPoints) {

			ImageDTO imageDTOs = new ImageDTO();
			imageDTOs.setImage(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePathS());
			images.add(imageDTOs);
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(externalPath + File.separator + "SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePath());
			dtosOriginalSize.add(imageDTO);
		}
		model.addAttribute("dtosOriginalSizeList", dtosOriginalSize);
		model.addAttribute("dtoList", images);

		model.addAttribute("img", imgSurveyTargetPoints);

		model.addAttribute("id", id);
		int MaxSize = (int) Math.ceil(imgSurveyTargetPointService.findByDiseaseAndStatus(disease, "Reject").size());
		model.addAttribute("MaxSize", MaxSize);
		model.addAttribute("sizenow", MaxSize);
		model.addAttribute("value", value);
		model.addAttribute("page", page);
		int firstItem = ((page - 1) * value + 1);
		model.addAttribute("firstItem", firstItem);
		int lastItem = (page * value);
		if (lastItem > MaxSize) {
			lastItem = MaxSize;
		}
		model.addAttribute("lastItem", lastItem);
		String nameDisease = disease.getTargetofsurvey().getName();
		model.addAttribute("nameDisease", nameDisease);
		Format formatterTH = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatterTH);
		return "/approve/approveRejecttable";
	}

	@RequestMapping(value = "/approve/success/edit/{id}/{idimg}/page/{page}/value/{value}", method = RequestMethod.POST)
	public String approvesuccess(String type, @PathVariable("page") int page, @PathVariable("value") int value,
			@PathVariable("id") int id, @PathVariable("idimg") int idimg, Model model, Authentication authentication) {
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/approve/";
		}
		Date datenow = new Date(System.currentTimeMillis());
		ImgSurveyTargetPoint imgSurveyTargetPointChange = imgSurveyTargetPointService.findById(idimg);
		if (type.equals("Delete")) {
			File fileimagesPath = new File(externalPath + File.separator + "SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPointService.findById(idimg).getFilePath());
			File fileimagesPaths = new File(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPointService.findById(idimg).getFilePathS());
			if (fileimagesPath.delete()) {
				System.out.println("Deleted the file: " + fileimagesPath.getName());
			} else {
				System.out.println("Failed to delete the file.");
			}
			if (fileimagesPaths.delete()) {
				System.out.println("Deleted the file: " + fileimagesPaths.getName());
			} else {
				System.out.println("Failed to delete the file.");
			}
			imgSurveyTargetPointChange.setApproveStatus("Delete");
			User user = userService.findByUsername(MvcHelper.getUsername(authentication));
			imgSurveyTargetPointChange.setUserByApproveBy(user);
			imgSurveyTargetPointChange.setApproveDate(datenow);
			imgSurveyTargetPointService.save(imgSurveyTargetPointChange);
			// imgSurveyTargetPointService.deleteById(idimg);
		} else {

			if (type.equals("Reject")) {
				imgSurveyTargetPointChange.setApproveStatus("Reject");
			}
			imgSurveyTargetPointChange.setApproveDate(datenow);
			User user = userService.findByUsername(MvcHelper.getUsername(authentication));
			imgSurveyTargetPointChange.setUserByApproveBy(user);
			imgSurveyTargetPointService.save(imgSurveyTargetPointChange);
		}
		String Approved = new String("Approved");

		List<ImgSurveyTargetPoint> imgSurveyTargetPoints = imgSurveyTargetPointService
				.findByDiseaseAndStatus(diseaseService.findById(id), Approved, page, value);
		List<ImageDTO> images = new ArrayList<ImageDTO>();
		List<ImageDTO> dtosOriginalSize = new ArrayList<ImageDTO>();
		for (ImgSurveyTargetPoint imgSurveyTargetPoint : imgSurveyTargetPoints) {

			ImageDTO imageDTOs = new ImageDTO();
			imageDTOs.setImage(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePathS());
			images.add(imageDTOs);
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(externalPath + File.separator + "SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePath());
			dtosOriginalSize.add(imageDTO);
		}
		model.addAttribute("dtosOriginalSizeList", dtosOriginalSize);
		model.addAttribute("dtoList", images);

		model.addAttribute("img", imgSurveyTargetPoints);

		model.addAttribute("id", id);
		int MaxSize = (int) Math.ceil(imgSurveyTargetPointService.findByDiseaseAndStatus(disease, "Approved").size());
		model.addAttribute("MaxSize", MaxSize);
		model.addAttribute("sizenow", MaxSize);
		model.addAttribute("value", value);
		model.addAttribute("page", page);
		int firstItem = ((page - 1) * value + 1);
		model.addAttribute("firstItem", firstItem);
		int lastItem = (page * value);
		if (lastItem > MaxSize) {
			lastItem = MaxSize;
		}
		model.addAttribute("lastItem", lastItem);
		String nameDisease = disease.getTargetofsurvey().getName();
		model.addAttribute("nameDisease", nameDisease);
		Format formatterTH = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatterTH);
		return "/approve/approveSuccesstable";
	}

	@RequestMapping(value = "/approve/detail/edit/date/{id}/{idimg}/page/{page}/value/{value}", method = RequestMethod.POST)
	public String approveApprovedDate(String type, Date dateInfoStart, Date dateInfoEnd, @PathVariable("page") int page,
			@PathVariable("value") int value, @PathVariable("id") int id, @PathVariable("idimg") int idimg, Model model,
			Authentication authentication) {
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/approve/";
		}
		Date datenow = new Date(System.currentTimeMillis());
		Boolean statusDate = true;
		ImgSurveyTargetPoint imgSurveyTargetPointChange = imgSurveyTargetPointService.findById(idimg);
		if (type.equals("Delete")) {
			File fileimagesPath = new File(externalPath + File.separator + "SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPointService.findById(idimg).getFilePath());
			File fileimagesPaths = new File(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPointService.findById(idimg).getFilePathS());
			if (fileimagesPath.delete()) {
				System.out.println("Deleted the file: " + fileimagesPath.getName());
			} else {
				System.out.println("Failed to delete the file.");
			}
			if (fileimagesPaths.delete()) {
				System.out.println("Deleted the file: " + fileimagesPaths.getName());
			} else {
				System.out.println("Failed to delete the file.");
			}
			imgSurveyTargetPointChange.setApproveStatus("Delete");
			User user = userService.findByUsername(MvcHelper.getUsername(authentication));
			imgSurveyTargetPointChange.setUserByApproveBy(user);
			imgSurveyTargetPointChange.setApproveDate(datenow);
			imgSurveyTargetPointService.save(imgSurveyTargetPointChange);
			// imgSurveyTargetPointService.deleteById(idimg);
		} else {

			if (type.equals("Approved")) {
				imgSurveyTargetPointChange.setApproveStatus("Approved");
			} else {
				imgSurveyTargetPointChange.setApproveStatus("Reject");
			}
			imgSurveyTargetPointChange.setApproveDate(datenow);
			User user = userService.findByUsername(MvcHelper.getUsername(authentication));
			imgSurveyTargetPointChange.setUserByApproveBy(user);
			imgSurveyTargetPointService.save(imgSurveyTargetPointChange);
		}
		String Waiting = new String("Waiting");

		List<ImgSurveyTargetPoint> imgSurveyTargetPoints = imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(id, Waiting, dateInfoStart, dateInfoEnd, page, value);
		List<ImageDTO> images = new ArrayList<ImageDTO>();
		List<ImageDTO> dtosOriginalSize = new ArrayList<ImageDTO>();
		for (ImgSurveyTargetPoint imgSurveyTargetPoint : imgSurveyTargetPoints) {

			ImageDTO imageDTOs = new ImageDTO();
			imageDTOs.setImage(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePathS());
			images.add(imageDTOs);
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(externalPath + File.separator + "SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePath());
			dtosOriginalSize.add(imageDTO);
		}
		model.addAttribute("dtosOriginalSizeList", dtosOriginalSize);
		model.addAttribute("dtoList", images);

		model.addAttribute("statusDate", statusDate);
		Format formatter = new SimpleDateFormat("MM/dd/yyyy", new Locale("en", "US"));
		model.addAttribute("formatter", formatter);
		model.addAttribute("img", imgSurveyTargetPoints);

		model.addAttribute("id", id);
		int MaxSize = (int) Math.ceil(imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(id, Waiting, dateInfoStart, dateInfoEnd).size());
		model.addAttribute("MaxSize", MaxSize);
		model.addAttribute("sizenow", MaxSize);
		model.addAttribute("value", value);
		model.addAttribute("page", page);
		int firstItem = ((page - 1) * value + 1);
		model.addAttribute("firstItem", firstItem);
		int lastItem = (page * value);
		if (lastItem > MaxSize) {
			lastItem = MaxSize;
		}
		model.addAttribute("lastItem", lastItem);
		model.addAttribute("dateInfoStart", dateInfoStart);
		model.addAttribute("dateInfoEnd", dateInfoEnd);
		String nameDisease = disease.getTargetofsurvey().getName();
		model.addAttribute("nameDisease", nameDisease);
		Format formatterTH = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatterTH);
		return "/approve/approvetable";
	}

	@RequestMapping(value = "/approve/reject/edit/date/{id}/{idimg}/page/{page}/value/{value}", method = RequestMethod.POST)
	public String approveRejectDate(String type, Date dateInfoStart, Date dateInfoEnd, @PathVariable("page") int page,
			@PathVariable("value") int value, @PathVariable("id") int id, @PathVariable("idimg") int idimg, Model model,
			Authentication authentication) {
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/approve/";
		}
		Date datenow = new Date(System.currentTimeMillis());
		Boolean statusDate = true;
		ImgSurveyTargetPoint imgSurveyTargetPointChange = imgSurveyTargetPointService.findById(idimg);
		if (type.equals("Delete")) {
			File fileimagesPath = new File(externalPath + File.separator + "SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPointService.findById(idimg).getFilePath());
			File fileimagesPaths = new File(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPointService.findById(idimg).getFilePathS());
			if (fileimagesPath.delete()) {
				System.out.println("Deleted the file: " + fileimagesPath.getName());
			} else {
				System.out.println("Failed to delete the file.");
			}
			if (fileimagesPaths.delete()) {
				System.out.println("Deleted the file: " + fileimagesPaths.getName());
			} else {
				System.out.println("Failed to delete the file.");
			}
			imgSurveyTargetPointChange.setApproveStatus("Delete");
			User user = userService.findByUsername(MvcHelper.getUsername(authentication));
			imgSurveyTargetPointChange.setUserByApproveBy(user);
			imgSurveyTargetPointChange.setApproveDate(datenow);
			imgSurveyTargetPointService.save(imgSurveyTargetPointChange);
			// imgSurveyTargetPointService.deleteById(idimg);
		}
		String Reject = new String("Reject");

		List<ImgSurveyTargetPoint> imgSurveyTargetPoints = imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(id, Reject, dateInfoStart, dateInfoEnd, page, value);
		List<ImageDTO> images = new ArrayList<ImageDTO>();
		List<ImageDTO> dtosOriginalSize = new ArrayList<ImageDTO>();
		for (ImgSurveyTargetPoint imgSurveyTargetPoint : imgSurveyTargetPoints) {

			ImageDTO imageDTOs = new ImageDTO();
			imageDTOs.setImage(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePathS());
			images.add(imageDTOs);
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(externalPath + File.separator + "SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePath());
			dtosOriginalSize.add(imageDTO);
		}
		model.addAttribute("dtosOriginalSizeList", dtosOriginalSize);
		model.addAttribute("dtoList", images);

		model.addAttribute("statusDate", statusDate);
		Format formatter = new SimpleDateFormat("MM/dd/yyyy", new Locale("en", "US"));
		model.addAttribute("formatter", formatter);
		model.addAttribute("img", imgSurveyTargetPoints);

		model.addAttribute("id", id);
		int MaxSize = (int) Math.ceil(imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(id, Reject, dateInfoStart, dateInfoEnd).size());
		model.addAttribute("MaxSize", MaxSize);
		model.addAttribute("sizenow", MaxSize);
		model.addAttribute("value", value);
		model.addAttribute("page", page);
		int firstItem = ((page - 1) * value + 1);
		model.addAttribute("firstItem", firstItem);
		int lastItem = (page * value);
		if (lastItem > MaxSize) {
			lastItem = MaxSize;
		}
		model.addAttribute("lastItem", lastItem);
		model.addAttribute("dateInfoStart", dateInfoStart);
		model.addAttribute("dateInfoEnd", dateInfoEnd);
		String nameDisease = disease.getTargetofsurvey().getName();
		model.addAttribute("nameDisease", nameDisease);
		Format formatterTH = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatterTH);
		return "/approve/approveRejecttable";
	}

	@RequestMapping(value = "/approve/success/edit/date/{id}/{idimg}/page/{page}/value/{value}", method = RequestMethod.POST)
	public String approveSuccessDate(String type, Date dateInfoStart, Date dateInfoEnd, @PathVariable("page") int page,
			@PathVariable("value") int value, @PathVariable("id") int id, @PathVariable("idimg") int idimg, Model model,
			Authentication authentication) {
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/approve/";
		}
		Date datenow = new Date(System.currentTimeMillis());
		Boolean statusDate = true;
		ImgSurveyTargetPoint imgSurveyTargetPointChange = imgSurveyTargetPointService.findById(idimg);
		if (type.equals("Delete")) {
			File fileimagesPath = new File(externalPath + File.separator + "SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPointService.findById(idimg).getFilePath());
			File fileimagesPaths = new File(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPointService.findById(idimg).getFilePathS());
			if (fileimagesPath.delete()) {
				System.out.println("Deleted the file: " + fileimagesPath.getName());
			} else {
				System.out.println("Failed to delete the file.");
			}
			if (fileimagesPaths.delete()) {
				System.out.println("Deleted the file: " + fileimagesPaths.getName());
			} else {
				System.out.println("Failed to delete the file.");
			}
			imgSurveyTargetPointChange.setApproveStatus("Delete");
			User user = userService.findByUsername(MvcHelper.getUsername(authentication));
			imgSurveyTargetPointChange.setUserByApproveBy(user);
			imgSurveyTargetPointChange.setApproveDate(datenow);
			imgSurveyTargetPointService.save(imgSurveyTargetPointChange);
			// imgSurveyTargetPointService.deleteById(idimg);
		} else {

			if (type.equals("Reject")) {
				imgSurveyTargetPointChange.setApproveStatus("Reject");
			}
			imgSurveyTargetPointChange.setApproveDate(datenow);
			User user = userService.findByUsername(MvcHelper.getUsername(authentication));
			imgSurveyTargetPointChange.setUserByApproveBy(user);
			imgSurveyTargetPointService.save(imgSurveyTargetPointChange);
		}
		String Approved = new String("Approved");

		List<ImgSurveyTargetPoint> imgSurveyTargetPoints = imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(id, Approved, dateInfoStart, dateInfoEnd, page, value);
		List<ImageDTO> images = new ArrayList<ImageDTO>();
		List<ImageDTO> dtosOriginalSize = new ArrayList<ImageDTO>();
		for (ImgSurveyTargetPoint imgSurveyTargetPoint : imgSurveyTargetPoints) {

			ImageDTO imageDTOs = new ImageDTO();
			imageDTOs.setImage(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePathS());
			images.add(imageDTOs);
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(externalPath + File.separator + "SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePath());
			dtosOriginalSize.add(imageDTO);
		}
		model.addAttribute("dtosOriginalSizeList", dtosOriginalSize);
		model.addAttribute("dtoList", images);

		model.addAttribute("statusDate", statusDate);
		Format formatter = new SimpleDateFormat("MM/dd/yyyy", new Locale("en", "US"));
		model.addAttribute("formatter", formatter);
		model.addAttribute("img", imgSurveyTargetPoints);

		model.addAttribute("id", id);
		int MaxSize = (int) Math.ceil(imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(id, Approved, dateInfoStart, dateInfoEnd).size());
		model.addAttribute("MaxSize", MaxSize);
		model.addAttribute("sizenow", MaxSize);
		model.addAttribute("value", value);
		model.addAttribute("page", page);

		int firstItem = ((page - 1) * value + 1);
		model.addAttribute("firstItem", firstItem);
		int lastItem = (page * value);
		if (lastItem > MaxSize) {
			lastItem = MaxSize;
		}
		model.addAttribute("lastItem", lastItem);
		model.addAttribute("dateInfoStart", dateInfoStart);
		model.addAttribute("dateInfoEnd", dateInfoEnd);
		String nameDisease = disease.getTargetofsurvey().getName();
		model.addAttribute("nameDisease", nameDisease);
		Format formatterTH = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatterTH);
		return "/approve/approveSuccesstable";
	}

	@RequestMapping(value = "/approve/success/{id}", method = RequestMethod.GET)
	public String approveSuccess(@PathVariable("id") int id, Model model, Authentication authentication) {
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/approve/";
		}
		int page = 1;
		int value = 5;

		model.addAttribute("id", id);
		model.addAttribute("value", value);
		model.addAttribute("page", page);

		String nameDisease = disease.getTargetofsurvey().getName();
		model.addAttribute("nameDisease", nameDisease);
		Format formatterTH = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatterTH);
		int MaxSize = (int) Math.ceil(imgSurveyTargetPointService.findByDiseaseAndStatus(disease, "Approved").size());
		model.addAttribute("MaxSize", MaxSize);
		List<Integer> sizePageList = new ArrayList<Integer>();
		sizePageList.add(6);
		sizePageList.add(12);
		sizePageList.add(24);
		sizePageList.add(48);
		model.addAttribute("sizePages", sizePageList);
		return "/approve/approveSuccess";
	}

	@RequestMapping(value = "/approve/success/date/list/{id}/page/{page}/value/{value}", method = RequestMethod.POST)
	public String approveeditsuccesstabledate(Date Date, @PathVariable("page") int page,
			@PathVariable("value") int value, @PathVariable("id") int id, Date dateInfoStart, Date dateInfoEnd,
			Model model, Authentication authentication) {
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/approve/";
		}
		String Approved = new String("Approved");
		List<ImgSurveyTargetPoint> imgSurveyTargetPoints = imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(id, Approved, dateInfoStart, dateInfoEnd, page, value);

		List<ImageDTO> images = new ArrayList<ImageDTO>();
		List<ImageDTO> dtosOriginalSize = new ArrayList<ImageDTO>();
		for (ImgSurveyTargetPoint imgSurveyTargetPoint : imgSurveyTargetPoints) {

			ImageDTO imageDTOs = new ImageDTO();
			imageDTOs.setImage(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePathS());
			images.add(imageDTOs);
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(externalPath + File.separator + "SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePath());
			dtosOriginalSize.add(imageDTO);
		}
		model.addAttribute("dtosOriginalSizeList", dtosOriginalSize);
		model.addAttribute("dtoList", images);

		Format formatter = new SimpleDateFormat("MM/dd/yyyy", new Locale("en", "US"));

		model.addAttribute("formatter", formatter);
		model.addAttribute("img", imgSurveyTargetPoints);
		model.addAttribute("dtoList", images);
		model.addAttribute("id", id);
		model.addAttribute("dateInfoStart", dateInfoStart);
		model.addAttribute("dateInfoEnd", dateInfoEnd);
		model.addAttribute("value", value);
		model.addAttribute("page", page);
		model.addAttribute("nameDisease", disease.getTargetofsurvey().getName());
		Format formatterTH = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatterTH);
		int MaxSize = (int) Math.ceil(imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(id, Approved, dateInfoStart, dateInfoEnd).size());
		model.addAttribute("MaxSize", MaxSize);
		int firstItem = ((page - 1) * value + 1);
		model.addAttribute("firstItem", firstItem);
		int lastItem = (page * value);
		if (lastItem > MaxSize) {
			lastItem = MaxSize;
		}
		model.addAttribute("lastItem", lastItem);
		List<Integer> sizePageList = new ArrayList<Integer>();
		sizePageList.add(6);
		sizePageList.add(12);
		sizePageList.add(24);
		sizePageList.add(48);
		model.addAttribute("sizePages", sizePageList);
		return "/approve/approveSuccesstable";
	}

	@RequestMapping(value = "/approve/success/list/{id}/page/{page}/value/{value}", method = RequestMethod.POST)
	public String approveSuccessPagetable(@PathVariable("id") int id, @PathVariable("page") int page,
			@ModelAttribute("value") int value, Model model, Authentication authentication) {

		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/approve/";
		}
		List<ImgSurveyTargetPoint> imgSurveyTargetPoints = imgSurveyTargetPointService.findByDiseaseAndStatus(disease,
				"Approved", page, value);

		List<ImageDTO> images = new ArrayList<ImageDTO>();
		List<ImageDTO> dtosOriginalSize = new ArrayList<ImageDTO>();
		for (ImgSurveyTargetPoint imgSurveyTargetPoint : imgSurveyTargetPoints) {

			ImageDTO imageDTOs = new ImageDTO();
			imageDTOs.setImage(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePathS());
			images.add(imageDTOs);
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(externalPath + File.separator + "SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePath());
			dtosOriginalSize.add(imageDTO);
		}
		model.addAttribute("dtosOriginalSizeList", dtosOriginalSize);
		model.addAttribute("dtoList", images);

		model.addAttribute("img", imgSurveyTargetPoints);
		model.addAttribute("dtoList", images);
		model.addAttribute("id", id);
		model.addAttribute("value", value);
		model.addAttribute("page", page);
		model.addAttribute("nameDisease", disease.getTargetofsurvey().getName());
		Format formatterTH = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatterTH);
		int MaxSize = (int) Math.ceil(imgSurveyTargetPointService.findByDiseaseAndStatus(disease, "Approved").size());
		model.addAttribute("MaxSize", MaxSize);
		int firstItem = ((page - 1) * value + 1);
		model.addAttribute("firstItem", firstItem);
		int lastItem = (page * value);
		if (lastItem > MaxSize) {
			lastItem = MaxSize;
		}
		model.addAttribute("lastItem", lastItem);
		List<Integer> sizePageList = new ArrayList<Integer>();
		sizePageList.add(6);
		sizePageList.add(12);
		sizePageList.add(24);
		sizePageList.add(48);
		model.addAttribute("sizePages", sizePageList);

		return "/approve/approveSuccesstable";
	}

	@RequestMapping(value = "/approve/reject/list/{id}/page/{page}/value/{value}", method = RequestMethod.POST)
	public String approveeditreject(@PathVariable("id") int id, @PathVariable("page") int page,
			@PathVariable("value") int value, Model model, Authentication authentication) {

		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/approve/";
		}
		List<ImgSurveyTargetPoint> imgSurveyTargetPoints = imgSurveyTargetPointService.findByDiseaseAndStatus(disease,
				"Reject", page, value);

		List<ImageDTO> images = new ArrayList<ImageDTO>();
		List<ImageDTO> dtosOriginalSize = new ArrayList<ImageDTO>();
		for (ImgSurveyTargetPoint imgSurveyTargetPoint : imgSurveyTargetPoints) {

			ImageDTO imageDTOs = new ImageDTO();
			imageDTOs.setImage(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePathS());
			images.add(imageDTOs);
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(externalPath + File.separator + "SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePath());
			dtosOriginalSize.add(imageDTO);
		}
		model.addAttribute("dtosOriginalSizeList", dtosOriginalSize);
		model.addAttribute("dtoList", images);

		model.addAttribute("img", imgSurveyTargetPoints);
		model.addAttribute("dtoList", images);
		model.addAttribute("id", id);
		model.addAttribute("value", value);
		model.addAttribute("page", page);
		int MaxSize = (int) Math.ceil(imgSurveyTargetPointService.findByDiseaseAndStatus(disease, "Reject").size());
		model.addAttribute("MaxSize", MaxSize);
		int firstItem = ((page - 1) * value + 1);
		model.addAttribute("firstItem", firstItem);
		int lastItem = (page * value);
		if (lastItem > MaxSize) {
			lastItem = MaxSize;
		}
		model.addAttribute("lastItem", lastItem);
		List<Integer> sizePageList = new ArrayList<Integer>();
		sizePageList.add(6);
		sizePageList.add(12);
		sizePageList.add(24);
		sizePageList.add(48);
		model.addAttribute("sizePages", sizePageList);
		Format formatterTH = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatterTH);
		Format formatter = new SimpleDateFormat("MM/dd/yyyy", new Locale("en", "US"));
		model.addAttribute("formatter", formatter);
		String nameDisease = disease.getTargetofsurvey().getName();
		model.addAttribute("nameDisease", nameDisease);
		return "/approve/approveRejecttable";
	}

	@RequestMapping(value = "/approve/reject/{id}", method = RequestMethod.GET)
	public String approveSuccessl(@PathVariable("id") int id, Model model, Authentication authentication) {
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/approve/";
		}
		int page = 1;
		int value = 5;

		model.addAttribute("id", id);
		model.addAttribute("value", value);
		model.addAttribute("page", page);
		int MaxSize = (int) Math.ceil(imgSurveyTargetPointService.findByDiseaseAndStatus(disease, "Reject").size());
		model.addAttribute("MaxSize", MaxSize);
		List<Integer> sizePageList = new ArrayList<Integer>();
		sizePageList.add(6);
		sizePageList.add(12);
		sizePageList.add(24);
		sizePageList.add(48);
		model.addAttribute("sizePages", sizePageList);
		model.addAttribute("nameDisease", disease.getTargetofsurvey().getName());
		return "/approve/approveReject";
	}

	@RequestMapping(value = "/approve/detail/{id}", method = RequestMethod.GET)
	public String approveDetail(@PathVariable("id") int id, Model model, Authentication authentication) {
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/approve/";
		}
		int page = 1;
		int value = 5;

		List<ImgSurveyTargetPoint> imgSurveyTargetPoints = imgSurveyTargetPointService.findByDiseaseAndStatus(disease,
				"Waiting", page, value);
		List<ImageDTO> images = new ArrayList<ImageDTO>();
		List<ImageDTO> dtosOriginalSize = new ArrayList<ImageDTO>();
		for (ImgSurveyTargetPoint imgSurveyTargetPoint : imgSurveyTargetPoints) {
			ImageDTO imageDTOs = new ImageDTO();
			imageDTOs.setImage(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePathS());
			images.add(imageDTOs);
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(externalPath + File.separator + "SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePath());
			dtosOriginalSize.add(imageDTO);
		}
		model.addAttribute("dtosOriginalSizeList", dtosOriginalSize);
		model.addAttribute("dtoList", images);

		model.addAttribute("MaxSize",
				(int) Math.ceil(imgSurveyTargetPointService.findByDiseaseAndStatus(disease, "Waiting").size()));

		model.addAttribute("img", imgSurveyTargetPoints);
		model.addAttribute("dtoList", images);
		model.addAttribute("id", id);
		model.addAttribute("value", value);
		model.addAttribute("page", page);
		String nameDisease = disease.getTargetofsurvey().getName();
		model.addAttribute("nameDisease", nameDisease);
		List<Integer> sizePageList = new ArrayList<Integer>();
		sizePageList.add(6);
		sizePageList.add(12);
		sizePageList.add(24);
		sizePageList.add(48);
		model.addAttribute("sizePages", sizePageList);
		return "/approve/approveDetail";
	}

	@RequestMapping(value = "/approve/detail/list/{id}/page/{page}/value/{value}", method = RequestMethod.POST)
	public String approveDetailPageP(@PathVariable("id") int id, @PathVariable("page") int page,
			@PathVariable("value") int value, Model model, Authentication authentication) {
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/approve/";
		}
		List<ImgSurveyTargetPoint> imgSurveyTargetPoints = imgSurveyTargetPointService.findByDiseaseAndStatus(disease,
				"Waiting", page, value);

		List<ImageDTO> images = new ArrayList<ImageDTO>();
		List<ImageDTO> dtosOriginalSize = new ArrayList<ImageDTO>();
		for (ImgSurveyTargetPoint imgSurveyTargetPoint : imgSurveyTargetPoints) {

			ImageDTO imageDTOs = new ImageDTO();
			imageDTOs.setImage(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePathS());
			images.add(imageDTOs);
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(externalPath + File.separator + "SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePath());
			dtosOriginalSize.add(imageDTO);
		}
		model.addAttribute("dtosOriginalSizeList", dtosOriginalSize);
		model.addAttribute("dtoList", images);
		model.addAttribute("img", imgSurveyTargetPoints);

		int MaxSize = (int) Math.ceil(imgSurveyTargetPointService.findByDiseaseAndStatus(disease, "Waiting").size());
		model.addAttribute("MaxSize", MaxSize);
		model.addAttribute("id", id);
		model.addAttribute("value", value);
		model.addAttribute("page", page);
		int firstItem = ((page - 1) * value + 1);
		model.addAttribute("firstItem", firstItem);
		int lastItem = (page * value);
		if (lastItem > MaxSize) {
			lastItem = MaxSize;
		}
		model.addAttribute("lastItem", lastItem);

		String nameDisease = disease.getTargetofsurvey().getName();
		model.addAttribute("nameDisease", nameDisease);
		Format formatterTH = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatterTH);
		return "/approve/approvetable";
	}

	@RequestMapping(value = "/approve/find/{id}/page/{page}/value/{value}", method = RequestMethod.POST)
	public String approvefind(@PathVariable("page") int page, @PathVariable("value") int value,
			@PathVariable("id") int id, Date dateInfoStart, Date dateInfoEnd, Model model,
			Authentication authentication) {
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/approve/";
		}
		Boolean statusDate = true;
		List<ImageDTO> images = new ArrayList<ImageDTO>();
		String Waiting = new String("Waiting");
		List<ImgSurveyTargetPoint> imgSurveyTargetPoints = imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(id, Waiting, dateInfoStart, dateInfoEnd, page, value);

		Format formatter = new SimpleDateFormat("MM/dd/yyyy", new Locale("en", "US"));
		model.addAttribute("formatter", formatter);
		model.addAttribute("img", imgSurveyTargetPoints);
		model.addAttribute("dtoList", images);
		model.addAttribute("id", id);
		int MaxSize = (int) Math.ceil(imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(id, Waiting, dateInfoStart, dateInfoEnd).size());
		model.addAttribute("MaxSize", MaxSize);
		model.addAttribute("value", value);
		model.addAttribute("page", page);
		model.addAttribute("statusDate", statusDate);
		model.addAttribute("dateInfoStart", dateInfoStart);
		model.addAttribute("dateInfoEnd", dateInfoEnd);
		model.addAttribute("nameDisease", disease.getTargetofsurvey().getName());
		Format formatterTH = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatterTH);
		List<Integer> sizePageList = new ArrayList<Integer>();
		sizePageList.add(6);
		sizePageList.add(12);
		sizePageList.add(24);
		sizePageList.add(48);
		model.addAttribute("sizePages", sizePageList);
		return "/approve/approveDetail";
	}

	@RequestMapping(value = "/approve/detail/date/list/{id}/page/{page}/value/{value}", method = RequestMethod.POST)
	public String approvefindtable(Date Date, @PathVariable("page") int page, @PathVariable("value") int value,
			@PathVariable("id") int id, Date dateInfoStart, Date dateInfoEnd, Model model,
			Authentication authentication) {
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/approve/";
		}
		Boolean statusDate = true;
		String Waiting = new String("Waiting");
		List<ImgSurveyTargetPoint> imgSurveyTargetPoints = imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(id, Waiting, dateInfoStart, dateInfoEnd, page, value);
		List<ImageDTO> images = new ArrayList<ImageDTO>();
		List<ImageDTO> dtosOriginalSize = new ArrayList<ImageDTO>();
		for (ImgSurveyTargetPoint imgSurveyTargetPoint : imgSurveyTargetPoints) {

			ImageDTO imageDTOs = new ImageDTO();
			imageDTOs.setImage(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePathS());
			images.add(imageDTOs);
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(externalPath + File.separator + "SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePath());
			dtosOriginalSize.add(imageDTO);
		}
		model.addAttribute("dtosOriginalSizeList", dtosOriginalSize);
		model.addAttribute("dtoList", images);

		Format formatter = new SimpleDateFormat("MM/dd/yyyy", new Locale("en", "US"));
		model.addAttribute("statusDate", statusDate);
		model.addAttribute("formatter", formatter);
		model.addAttribute("img", imgSurveyTargetPoints);
		model.addAttribute("dtoList", images);
		model.addAttribute("id", id);
		int MaxSize = (int) Math.ceil(imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(id, Waiting, dateInfoStart, dateInfoEnd).size());
		model.addAttribute("MaxSize", MaxSize);
		model.addAttribute("value", value);
		model.addAttribute("page", page);
		int firstItem = ((page - 1) * value + 1);
		model.addAttribute("firstItem", firstItem);
		int lastItem = (page * value);
		if (lastItem > MaxSize) {
			lastItem = MaxSize;
		}
		model.addAttribute("lastItem", lastItem);
		model.addAttribute("dateInfoStart", dateInfoStart);
		model.addAttribute("dateInfoEnd", dateInfoEnd);
		String nameDisease = disease.getTargetofsurvey().getName();
		model.addAttribute("nameDisease", nameDisease);
		Format formatterTH = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatterTH);
		return "/approve/approvetable";
	}

	@RequestMapping(value = "/approve/reject/date/list/{id}/page/{page}/value/{value}", method = RequestMethod.POST)
	public String approvefindrejecttable(Date Date, @PathVariable("page") int page, @PathVariable("value") int value,
			@PathVariable("id") int id, Date dateInfoStart, Date dateInfoEnd, Model model,
			Authentication authentication) {
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/approve/";
		}
		Boolean statusDate = true;
		String Reject = new String("Reject");
		List<ImgSurveyTargetPoint> imgSurveyTargetPoints = imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(id, Reject, dateInfoStart, dateInfoEnd, page, value);

		List<ImageDTO> images = new ArrayList<ImageDTO>();
		List<ImageDTO> dtosOriginalSize = new ArrayList<ImageDTO>();
		for (ImgSurveyTargetPoint imgSurveyTargetPoint : imgSurveyTargetPoints) {

			ImageDTO imageDTOs = new ImageDTO();
			imageDTOs.setImage(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePathS());
			images.add(imageDTOs);
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(externalPath + File.separator + "SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePath());
			dtosOriginalSize.add(imageDTO);
		}
		model.addAttribute("dtosOriginalSizeList", dtosOriginalSize);
		model.addAttribute("dtoList", images);

		Format formatter = new SimpleDateFormat("MM/dd/yyyy", new Locale("en", "US"));
		model.addAttribute("statusDate", statusDate);
		model.addAttribute("formatter", formatter);
		model.addAttribute("img", imgSurveyTargetPoints);
		model.addAttribute("dtoList", images);
		model.addAttribute("id", id);
		model.addAttribute("dateInfoStart", dateInfoStart);
		model.addAttribute("dateInfoEnd", dateInfoEnd);
		int MaxSize = (int) Math.ceil(imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(id, Reject, dateInfoStart, dateInfoEnd).size());
		model.addAttribute("MaxSize", MaxSize);
		int firstItem = ((page - 1) * value + 1);
		model.addAttribute("firstItem", firstItem);
		int lastItem = (page * value);
		if (lastItem > MaxSize) {
			lastItem = MaxSize;
		}
		model.addAttribute("lastItem", lastItem);
		model.addAttribute("value", value);
		model.addAttribute("page", page);

		model.addAttribute("nameDisease", disease.getTargetofsurvey().getName());
		Format formatterTH = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatterTH);
		return "/approve/approveRejecttable";
	}

	@RequestMapping(value = "/approve/edit/date/{id}/page/{page}/value/{value}", method = RequestMethod.POST)
	public String approvecheckListDate(@ModelAttribute("dateInfoStart") Date dateInfoStart,
			@ModelAttribute("dateInfoEnd") Date dateInfoEnd,
			@ModelAttribute("SelectedImgSurveyTargetPointDTO") SelectedImgSurveyTargetPointDTO surveyTargetPointDTO,
			@PathVariable("id") int id, @PathVariable("page") int page, @PathVariable("value") int value,
			@ModelAttribute("status1") String status, Model model, Authentication authentication) {
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/approve/";
		}
		Boolean statusDate = true;
		Date datenow = new Date(System.currentTimeMillis());
		if (surveyTargetPointDTO.getCheck() != null) {
			int i = 0;
			for (Boolean check : surveyTargetPointDTO.getCheck()) {

				if (check != null) {
					ImgSurveyTargetPoint imgSurveyTargetPoint = imgSurveyTargetPointService
							.findById(surveyTargetPointDTO.getImgSurveyTargetPoint().get(i));
					if (status.equals("อนุมัติ")) {
						imgSurveyTargetPoint.setApproveStatus("Approved");
					} else if (status.equals("ไม่อนุมัติ")) {
						imgSurveyTargetPoint.setApproveStatus("Reject");
					}
					imgSurveyTargetPoint.setApproveDate(datenow);
					User user = userService.findByUsername(MvcHelper.getUsername(authentication));
					imgSurveyTargetPoint.setUserByApproveBy(user);
					imgSurveyTargetPointService.save(imgSurveyTargetPoint);

					if (status.equals("ลบรูป")) {
						File fileimagesPath = new File(externalPath + File.separator + "SurveyTargetPoint"
								+ File.separator + imgSurveyTargetPoint.getFilePath());
						File fileimagesPaths = new File(externalPath + File.separator + "s_SurveyTargetPoint"
								+ File.separator + imgSurveyTargetPoint.getFilePathS());
						if (fileimagesPath.delete()) {
							System.out.println("Deleted the file: " + fileimagesPath.getName());
						} else {
							System.out.println("Failed to delete the file.");
						}
						if (fileimagesPaths.delete()) {
							System.out.println("Deleted the file: " + fileimagesPaths.getName());
						} else {
							System.out.println("Failed to delete the file.");
						}
						imgSurveyTargetPoint.setApproveStatus("Delete");
						// User user =
						// userService.findByUsername(MvcHelper.getUsername(authentication));
						imgSurveyTargetPoint.setUserByApproveBy(user);
						imgSurveyTargetPointService.save(imgSurveyTargetPoint);
						// imgSurveyTargetPointService.deleteById(imgSurveyTargetPoint.getImgSurveyTargetPointId());
					}
				}
				i++;
			}
		}

		model.addAttribute("statusDate", statusDate);
		model.addAttribute("id", id);
		model.addAttribute("value", value);
		model.addAttribute("page", page);

		model.addAttribute("dateInfoStart", dateInfoStart);
		model.addAttribute("dateInfoEnd", dateInfoEnd);
		Format formatter = new SimpleDateFormat("MM/dd/yyyy", new Locale("en", "US"));
		model.addAttribute("formatter", formatter);
		model.addAttribute("nameDisease", disease.getTargetofsurvey().getName());
		Format formatterTH = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatterTH);
		List<Integer> sizePageList = new ArrayList<Integer>();
		sizePageList.add(6);
		sizePageList.add(12);
		sizePageList.add(24);
		sizePageList.add(48);
		model.addAttribute("sizePages", sizePageList);
		int MaxSize = (int) Math.ceil(imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(id, "Waiting", dateInfoStart, dateInfoEnd).size());
		model.addAttribute("MaxSize", MaxSize);
		return "/approve/approveDetail";
	}

	@RequestMapping(value = "/approve/success/edit/date/{id}/page/{page}/value/{value}", method = RequestMethod.POST)
	public String approverejectcheckListDate(@ModelAttribute("dateInfoStart") Date dateInfoStart,
			@ModelAttribute("dateInfoEnd") Date dateInfoEnd,
			@ModelAttribute("DTO") SelectedImgSurveyTargetPointDTO selectedImgSurveyTargetPointDTO,
			@PathVariable("id") int id, @PathVariable("page") int page, @PathVariable("value") int value,
			@ModelAttribute("status0") String status, Model model, Authentication authentication) {
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/approve/";
		}
		Boolean statusDate = true;
		Date datenow = new Date(System.currentTimeMillis());
		if (selectedImgSurveyTargetPointDTO.getCheck() != null) {
			int i = 0;
			for (Boolean check : selectedImgSurveyTargetPointDTO.getCheck()) {

				if (check != null) {
					ImgSurveyTargetPoint imgSurveyTargetPoint = imgSurveyTargetPointService
							.findById(selectedImgSurveyTargetPointDTO.getImgSurveyTargetPoint().get(i));
					if (status.equals("ไม่อนุมัติ")) {
						imgSurveyTargetPoint.setApproveStatus("Reject");
					}
					imgSurveyTargetPoint.setApproveDate(datenow);
					User user = userService.findByUsername(MvcHelper.getUsername(authentication));
					imgSurveyTargetPoint.setUserByApproveBy(user);
					imgSurveyTargetPointService.save(imgSurveyTargetPoint);
					if (status.equals("ลบรูป")) {
						File fileimagesPath = new File(externalPath + File.separator + "SurveyTargetPoint"
								+ File.separator + imgSurveyTargetPoint.getFilePath());
						File fileimagesPaths = new File(externalPath + File.separator + "s_SurveyTargetPoint"
								+ File.separator + imgSurveyTargetPoint.getFilePathS());
						if (fileimagesPath.delete()) {
							System.out.println("Deleted the file: " + fileimagesPath.getName());
						} else {
							System.out.println("Failed to delete the file.");
						}
						if (fileimagesPaths.delete()) {
							System.out.println("Deleted the file: " + fileimagesPaths.getName());
						} else {
							System.out.println("Failed to delete the file.");
						}
						imgSurveyTargetPoint.setApproveStatus("Delete");
						// User user =
						// userService.findByUsername(MvcHelper.getUsername(authentication));
						imgSurveyTargetPoint.setUserByApproveBy(user);
						imgSurveyTargetPointService.save(imgSurveyTargetPoint);
						// imgSurveyTargetPointService.deleteById(imgSurveyTargetPoint.getImgSurveyTargetPointId());
					}
				}
				i++;

			}
		}

		model.addAttribute("statusDate", statusDate);
		model.addAttribute("id", id);
		model.addAttribute("value", value);
		model.addAttribute("page", page);

		model.addAttribute("dateInfoStart", dateInfoStart);
		model.addAttribute("dateInfoEnd", dateInfoEnd);
		Format formatter = new SimpleDateFormat("MM/dd/yyyy", new Locale("en", "US"));
		model.addAttribute("formatter", formatter);
		String nameDisease = disease.getTargetofsurvey().getName();
		model.addAttribute("nameDisease", nameDisease);
		Format formatterTH = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatterTH);
		List<Integer> sizePageList = new ArrayList<Integer>();
		sizePageList.add(6);
		sizePageList.add(12);
		sizePageList.add(24);
		sizePageList.add(48);
		model.addAttribute("sizePages", sizePageList);
		int MaxSize = (int) Math.ceil(imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(id, "Approved", dateInfoStart, dateInfoEnd).size());
		model.addAttribute("MaxSize", MaxSize);
		return "/approve/approveSuccess";
	}

	@RequestMapping(value = "/approve/find/success/{id}/page/{page}/value/{value}", method = RequestMethod.POST)
	public String approvefindSuccessP(@PathVariable("page") int page, @PathVariable("value") int value,
			@PathVariable("id") int id, Date dateInfoStart, Date dateInfoEnd, Model model,
			Authentication authentication) {
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/approve/";
		}
		String Approved = new String("Approved");

		Boolean statusDate = true;
		List<ImageDTO> images = new ArrayList<ImageDTO>();
		List<ImgSurveyTargetPoint> imgSurveyTargetPoints = imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(id, Approved, dateInfoStart, dateInfoEnd, page, value);

		Format formatter = new SimpleDateFormat("MM/dd/yyyy", new Locale("en", "US"));
		model.addAttribute("formatter", formatter);
		model.addAttribute("img", imgSurveyTargetPoints);
		model.addAttribute("dtoList", images);
		model.addAttribute("id", id);
		model.addAttribute("dateInfoStart", dateInfoStart);
		model.addAttribute("dateInfoEnd", dateInfoEnd);
		int MaxSize = (int) Math.ceil(imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(id, Approved, dateInfoStart, dateInfoEnd).size());
		model.addAttribute("MaxSize", MaxSize);
		model.addAttribute("value", value);
		model.addAttribute("page", page);
		model.addAttribute("statusDate", statusDate);
		model.addAttribute("nameDisease", disease.getTargetofsurvey().getName());
		Format formatterTH = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatterTH);
		List<Integer> sizePageList = new ArrayList<Integer>();
		sizePageList.add(6);
		sizePageList.add(12);
		sizePageList.add(24);
		sizePageList.add(48);
		model.addAttribute("sizePages", sizePageList);
		return "/approve/approveSuccess";
	}

	@RequestMapping(value = "/approve/find/reject/{id}/page/{page}/value/{value}", method = RequestMethod.POST)
	public String approvefindReject(@PathVariable("page") int page, @PathVariable("value") int value,
			@PathVariable("id") int id, Date dateInfoStart, Date dateInfoEnd, Model model,
			Authentication authentication) {
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/approve/";
		}

		Boolean statusDate = true;
		String Reject = new String("Reject");

		Format formatter = new SimpleDateFormat("MM/dd/yyyy", new Locale("en", "US"));
		model.addAttribute("statusDate", statusDate);
		model.addAttribute("formatter", formatter);
		model.addAttribute("dateInfoStart", dateInfoStart);
		model.addAttribute("dateInfoEnd", dateInfoEnd);
		model.addAttribute("value", value);
		model.addAttribute("page", page);

		String nameDisease = disease.getTargetofsurvey().getName();
		model.addAttribute("nameDisease", nameDisease);
		Format formatterTH = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatterTH);
		int MaxSize = (int) Math.ceil(imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(id, Reject, dateInfoStart, dateInfoEnd).size());
		model.addAttribute("MaxSize", MaxSize);
		List<Integer> sizePageList = new ArrayList<Integer>();
		sizePageList.add(6);
		sizePageList.add(12);
		sizePageList.add(24);
		sizePageList.add(48);
		model.addAttribute("sizePages", sizePageList);

		return "/approve/approveReject";
	}

	@RequestMapping(value = "/approve/delete/{id}/page/{page}/value/{value}", method = RequestMethod.POST)
	public String approveDeletecheckList(
			@ModelAttribute("SelectedImgSurveyTargetPointDTO") SelectedImgSurveyTargetPointDTO selectedImgSurveyTargetPointDTO,
			@PathVariable("id") int id, @PathVariable("page") int page, @PathVariable("value") int value,
			@ModelAttribute("status0") String status, Model model, Authentication authentication) {
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/approve/";
		}
		if (selectedImgSurveyTargetPointDTO.getCheck() != null) {
			int i = 0;
			for (Boolean check : selectedImgSurveyTargetPointDTO.getCheck()) {
				if (check != null) {
					ImgSurveyTargetPoint imgSurveyTargetPoint = imgSurveyTargetPointService
							.findById(selectedImgSurveyTargetPointDTO.getImgSurveyTargetPoint().get(i));
					File fileimagesPath = new File(externalPath + File.separator + "SurveyTargetPoint" + File.separator
							+ imgSurveyTargetPoint.getFilePath());
					File fileimagesPaths = new File(externalPath + File.separator + "s_SurveyTargetPoint"
							+ File.separator + imgSurveyTargetPoint.getFilePathS());
					if (fileimagesPath.delete()) {
						System.out.println("Deleted the file: " + fileimagesPath.getName());
					} else {
						System.out.println("Failed to delete the file.");
					}
					if (fileimagesPaths.delete()) {
						System.out.println("Deleted the file: " + fileimagesPaths.getName());
					} else {
						System.out.println("Failed to delete the file.");
					}
					imgSurveyTargetPoint.setApproveStatus("Delete");
					User user = userService.findByUsername(MvcHelper.getUsername(authentication));
					imgSurveyTargetPoint.setUserByApproveBy(user);
					imgSurveyTargetPointService.save(imgSurveyTargetPoint);
					// imgSurveyTargetPointService.deleteById(imgSurveyTargetPoint.getImgSurveyTargetPointId());
				}
				i++;

			}
		}

		model.addAttribute("id", id);
		model.addAttribute("value", value);
		model.addAttribute("page", page);
		model.addAttribute("nameDisease", disease.getTargetofsurvey().getName());
		Format formatterTH = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatterTH);
		List<Integer> sizePageList = new ArrayList<Integer>();
		sizePageList.add(6);
		sizePageList.add(12);
		sizePageList.add(24);
		sizePageList.add(48);
		model.addAttribute("sizePages", sizePageList);
		int MaxSize = (int) Math
				.ceil(imgSurveyTargetPointService.findByDiseaseAndStatus(diseaseService.findById(id), "Reject").size());
		model.addAttribute("MaxSize", MaxSize);
		return "/approve/approveReject";
	}

	@RequestMapping(value = { "/approve/index/total/{id}" }, method = RequestMethod.GET)
	@ResponseBody
	public int indexTableTotal(@PathVariable("id") int id, @RequestParam(value = "key", required = false) String key,
			Authentication authentication, Model model) {
		// User user =
		// imgSurveyTargetPointService.findById(MvcHelper.getUsername(authentication));
		int totalItem;
		// if (key==null || key=="") {
		if (diseaseService.findById(id) != null) {
			totalItem = imgSurveyTargetPointService.findByDiseaseAndStatus(diseaseService.findById(id), "Waiting")
					.size();
		} else {
			totalItem = 0;
		}
		// surveyService.findByUserInField(user).size();
		// }
		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatter", formatter);
		return totalItem;
	}

	@RequestMapping(value = { "/approve/index/total/date/{id}" }, method = RequestMethod.GET)
	@ResponseBody
	public int indexTableTotalDate(Date dateInfoStart, Date dateInfoEnd, @PathVariable("id") int id,
			@RequestParam(value = "key", required = false) String key, Authentication authentication, Model model) {
		// User user =
		// imgSurveyTargetPointService.findById(MvcHelper.getUsername(authentication));
		int totalItem;
		// if (key==null || key=="") {
		totalItem = imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(id, "Waiting", dateInfoStart, dateInfoEnd).size();
		// surveyService.findByUserInField(user).size();
		// }
		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatter", formatter);
		return totalItem;
	}

	@RequestMapping(value = { "/approve/index/page/{id}/{page}/value/{value}" }, method = RequestMethod.GET)
	public String indexTable(@PathVariable("id") int id, @PathVariable("page") int page,
			@PathVariable("value") int value, @RequestParam(value = "key", required = false) String key,
			Authentication authentication, Model model) {
		
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/approve/";
		}
		List<ImgSurveyTargetPoint> imgSurveyTargetPoints = imgSurveyTargetPointService.findByDiseaseAndStatus(disease,
				"Waiting", page, value);

		List<ImageDTO> images = new ArrayList<ImageDTO>();
		List<ImageDTO> dtosOriginalSize = new ArrayList<ImageDTO>();
		for (ImgSurveyTargetPoint imgSurveyTargetPoint : imgSurveyTargetPoints) {

			ImageDTO imageDTOs = new ImageDTO();
			imageDTOs.setImage(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePathS());
			images.add(imageDTOs);
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(externalPath + File.separator + "SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePath());
			dtosOriginalSize.add(imageDTO);
		}
		model.addAttribute("dtosOriginalSizeList", dtosOriginalSize);
		model.addAttribute("dtoList", images);
		model.addAttribute("img", imgSurveyTargetPoints);
		// List<Survey> surveys;
		// if (key==null || key=="") {
		// surveys = surveyService.findByUserInField(user, page, value);
		// }
		// else {
		// surveys = surveyService.findByUserInFieldAndKey(user,key, page, value);
		// }
		// model.addAttribute("surveys", surveys);
		int MaxSize = (int) Math.ceil(imgSurveyTargetPointService.findByDiseaseAndStatus(disease, "Waiting").size());
		model.addAttribute("MaxSize", MaxSize);
		model.addAttribute("id", id);
		model.addAttribute("value", value);
		model.addAttribute("page", page);
		int firstItem = ((page - 1) * value + 1);
		model.addAttribute("firstItem", firstItem);
		int lastItem = (page * value);
		if (lastItem > MaxSize) {
			lastItem = MaxSize;
		}
		model.addAttribute("lastItem", lastItem);
		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatter);

		return "/approve/approvetable";
	}

	@RequestMapping(value = { "/approve/index/page/date/{id}/{page}/value/{value}" }, method = RequestMethod.GET)
	public String indexTableDate(Date dateInfoStart, Date dateInfoEnd, @PathVariable("id") int id,
			@PathVariable("page") int page, @PathVariable("value") int value,
			@RequestParam(value = "key", required = false) String key, Authentication authentication, Model model) {
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/approve/";
		}
		Boolean statusDate = true;
		model.addAttribute("statusDate", statusDate);
		
		List<ImgSurveyTargetPoint> imgSurveyTargetPoints = imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(disease, "Waiting", dateInfoStart, dateInfoEnd, page, value);

		List<ImageDTO> images = new ArrayList<ImageDTO>();
		List<ImageDTO> dtosOriginalSize = new ArrayList<ImageDTO>();
		for (ImgSurveyTargetPoint imgSurveyTargetPoint : imgSurveyTargetPoints) {

			ImageDTO imageDTOs = new ImageDTO();
			imageDTOs.setImage(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePathS());
			images.add(imageDTOs);
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(externalPath + File.separator + "SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePath());
			dtosOriginalSize.add(imageDTO);
		}
		model.addAttribute("dtosOriginalSizeList", dtosOriginalSize);
		model.addAttribute("dtoList", images);
		model.addAttribute("img", imgSurveyTargetPoints);
		// List<Survey> surveys;
		// if (key==null || key=="") {
		// surveys = surveyService.findByUserInField(user, page, value);
		// }
		// else {
		// surveys = surveyService.findByUserInFieldAndKey(user,key, page, value);
		// }
		// model.addAttribute("surveys", surveys);
		int MaxSize = imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(id, "Waiting", dateInfoStart, dateInfoEnd).size();
		model.addAttribute("MaxSize", MaxSize);
		model.addAttribute("id", id);
		model.addAttribute("value", value);
		model.addAttribute("page", page);
		int firstItem = ((page - 1) * value + 1);
		model.addAttribute("firstItem", firstItem);
		int lastItem = (page * value);
		if (lastItem > MaxSize) {
			lastItem = MaxSize;
		}
		model.addAttribute("dateInfoStart", dateInfoStart);
		model.addAttribute("dateInfoEnd", dateInfoEnd);
		model.addAttribute("lastItem", lastItem);
		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatter);
		return "/approve/approvetable";
	}

/////////////////////////////////////////////////////////////////////Success

	@RequestMapping(value = { "/success/index/total/{id}" }, method = RequestMethod.GET)
	@ResponseBody
	public int indexSuccessTotal(@PathVariable("id") int id, @RequestParam(value = "key", required = false) String key,
			Authentication authentication, Model model) {
		
		// User user =
		// imgSurveyTargetPointService.findById(MvcHelper.getUsername(authentication));
		int totalItem;
		// if (key==null || key=="") {
		totalItem = imgSurveyTargetPointService.findByDiseaseAndStatus(diseaseService.findById(id), "Approved").size();
		// surveyService.findByUserInField(user).size();
		// }
		return totalItem;
	}

	@RequestMapping(value = { "/success/index/total/date/{id}" }, method = RequestMethod.GET)
	@ResponseBody
	public int indexSuccessTotalDate(Date dateInfoStart, Date dateInfoEnd, @PathVariable("id") int id,
			@RequestParam(value = "key", required = false) String key, Authentication authentication, Model model) {
		// User user =
		// imgSurveyTargetPointService.findById(MvcHelper.getUsername(authentication));
		int totalItem;
		// if (key==null || key=="") {
		totalItem = imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(id, "Approved", dateInfoStart, dateInfoEnd).size();
		// surveyService.findByUserInField(user).size();
		// }
		return totalItem;
	}

	@RequestMapping(value = { "/success/index/page/{id}/{page}/value/{value}" }, method = RequestMethod.GET)
	public String indexSuccess(@PathVariable("id") int id, @PathVariable("page") int page,
			@PathVariable("value") int value, @RequestParam(value = "key", required = false) String key,
			Authentication authentication, Model model) {
		Disease disease = diseaseService.findById(id);
		List<ImgSurveyTargetPoint> imgSurveyTargetPoints = imgSurveyTargetPointService.findByDiseaseAndStatus(disease,
				"Approved", page, value);

		List<ImageDTO> images = new ArrayList<ImageDTO>();
		List<ImageDTO> dtosOriginalSize = new ArrayList<ImageDTO>();
		for (ImgSurveyTargetPoint imgSurveyTargetPoint : imgSurveyTargetPoints) {

			ImageDTO imageDTOs = new ImageDTO();
			imageDTOs.setImage(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePathS());
			images.add(imageDTOs);
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(externalPath + File.separator + "SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePath());
			dtosOriginalSize.add(imageDTO);
		}
		model.addAttribute("dtosOriginalSizeList", dtosOriginalSize);
		model.addAttribute("dtoList", images);
		model.addAttribute("img", imgSurveyTargetPoints);
		// List<Survey> surveys;
		// if (key==null || key=="") {
		// surveys = surveyService.findByUserInField(user, page, value);
		// }
		// else {
		// surveys = surveyService.findByUserInFieldAndKey(user,key, page, value);
		// }
		// model.addAttribute("surveys", surveys);
		int MaxSize = (int) Math.ceil(imgSurveyTargetPointService.findByDiseaseAndStatus(disease, "Approved").size());
		model.addAttribute("MaxSize", MaxSize);
		model.addAttribute("id", id);
		model.addAttribute("value", value);
		model.addAttribute("page", page);
		int firstItem = ((page - 1) * value + 1);
		model.addAttribute("firstItem", firstItem);
		int lastItem = (page * value);
		if (lastItem > MaxSize) {
			lastItem = MaxSize;
		}
		model.addAttribute("lastItem", lastItem);
		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatter);
		return "/approve/approveSuccesstable";
	}

	@RequestMapping(value = { "/success/index/page/date/{id}/{page}/value/{value}" }, method = RequestMethod.GET)
	public String indexsuccessDate(Date dateInfoStart, Date dateInfoEnd, @PathVariable("id") int id,
			@PathVariable("page") int page, @PathVariable("value") int value,
			@RequestParam(value = "key", required = false) String key, Authentication authentication, Model model) {
		Boolean statusDate = true;
		model.addAttribute("statusDate", statusDate);
		Disease disease = diseaseService.findById(id);
		List<ImgSurveyTargetPoint> imgSurveyTargetPoints = imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(disease, "Approved", dateInfoStart, dateInfoEnd, page, value);

		List<ImageDTO> images = new ArrayList<ImageDTO>();
		List<ImageDTO> dtosOriginalSize = new ArrayList<ImageDTO>();
		for (ImgSurveyTargetPoint imgSurveyTargetPoint : imgSurveyTargetPoints) {

			ImageDTO imageDTOs = new ImageDTO();
			imageDTOs.setImage(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePathS());
			images.add(imageDTOs);
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(externalPath + File.separator + "SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePath());
			dtosOriginalSize.add(imageDTO);
		}
		model.addAttribute("dtosOriginalSizeList", dtosOriginalSize);
		model.addAttribute("dtoList", images);
		model.addAttribute("img", imgSurveyTargetPoints);
		// List<Survey> surveys;
		// if (key==null || key=="") {
		// surveys = surveyService.findByUserInField(user, page, value);
		// }
		// else {
		// surveys = surveyService.findByUserInFieldAndKey(user,key, page, value);
		// }
		// model.addAttribute("surveys", surveys);
		int MaxSize = imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(id, "Approved", dateInfoStart, dateInfoEnd).size();
		model.addAttribute("MaxSize", MaxSize);
		model.addAttribute("id", id);
		model.addAttribute("value", value);
		model.addAttribute("page", page);
		int firstItem = ((page - 1) * value + 1);
		model.addAttribute("firstItem", firstItem);
		int lastItem = (page * value);
		if (lastItem > MaxSize) {
			lastItem = MaxSize;
		}
		model.addAttribute("dateInfoStart", dateInfoStart);
		model.addAttribute("dateInfoEnd", dateInfoEnd);
		model.addAttribute("lastItem", lastItem);
		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatter);
		return "/approve/approveSuccesstable";
	}

///////////////////////////////////reject	

	@RequestMapping(value = { "/reject/index/total/{id}" }, method = RequestMethod.GET)
	@ResponseBody
	public int indexrejectTotal(@PathVariable("id") int id, @RequestParam(value = "key", required = false) String key,
			Authentication authentication, Model model) {
		// User user =
		// imgSurveyTargetPointService.findById(MvcHelper.getUsername(authentication));
		int totalItem;
		// if (key==null || key=="") {
		if (diseaseService.findById(id) != null) {
			totalItem = imgSurveyTargetPointService.findByDiseaseAndStatus(diseaseService.findById(id), "Reject").size();
		} else {
			totalItem = 0;
		}
		// surveyService.findByUserInField(user).size();
		// }
		return totalItem;
	}

	@RequestMapping(value = { "/reject/index/total/date/{id}" }, method = RequestMethod.GET)
	@ResponseBody
	public int indexrejectTotalDate(Date dateInfoStart, Date dateInfoEnd, @PathVariable("id") int id,
			@RequestParam(value = "key", required = false) String key, Authentication authentication, Model model) {
		// User user =
		// imgSurveyTargetPointService.findById(MvcHelper.getUsername(authentication));
		int totalItem;
		// if (key==null || key=="") {
		totalItem = imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(id, "Reject", dateInfoStart, dateInfoEnd).size();
		// surveyService.findByUserInField(user).size();
		// }
		return totalItem;
	}

	@RequestMapping(value = { "/reject/index/page/{id}/{page}/value/{value}" }, method = RequestMethod.GET)
	public String indexreject(@PathVariable("id") int id, @PathVariable("page") int page,
			@PathVariable("value") int value, @RequestParam(value = "key", required = false) String key,
			Authentication authentication, Model model) {
		Disease disease = diseaseService.findById(id);
		if(disease == null) {
			return "redirect:/approve/";
		}
		List<ImgSurveyTargetPoint> imgSurveyTargetPoints = imgSurveyTargetPointService.findByDiseaseAndStatus(disease,
				"Reject", page, value);

		List<ImageDTO> images = new ArrayList<ImageDTO>();
		List<ImageDTO> dtosOriginalSize = new ArrayList<ImageDTO>();
		for (ImgSurveyTargetPoint imgSurveyTargetPoint : imgSurveyTargetPoints) {

			ImageDTO imageDTOs = new ImageDTO();
			imageDTOs.setImage(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePathS());
			images.add(imageDTOs);
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(externalPath + File.separator + "SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePath());
			dtosOriginalSize.add(imageDTO);
		}
		model.addAttribute("dtosOriginalSizeList", dtosOriginalSize);
		model.addAttribute("dtoList", images);
		model.addAttribute("img", imgSurveyTargetPoints);
		// List<Survey> surveys;
		// if (key==null || key=="") {
		// surveys = surveyService.findByUserInField(user, page, value);
		// }
		// else {
		// surveys = surveyService.findByUserInFieldAndKey(user,key, page, value);
		// }
		// model.addAttribute("surveys", surveys);
		int MaxSize = (int) Math.ceil(imgSurveyTargetPointService.findByDiseaseAndStatus(disease, "Reject").size());
		model.addAttribute("MaxSize", MaxSize);
		model.addAttribute("id", id);
		model.addAttribute("value", value);
		model.addAttribute("page", page);
		int firstItem = ((page - 1) * value + 1);
		model.addAttribute("firstItem", firstItem);
		int lastItem = (page * value);
		if (lastItem > MaxSize) {
			lastItem = MaxSize;
		}
		model.addAttribute("lastItem", lastItem);
		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatter);
		return "/approve/approveRejecttable";
	}

	@RequestMapping(value = { "/reject/index/page/date/{id}/{page}/value/{value}" }, method = RequestMethod.GET)
	public String indexrejectDate(Date dateInfoStart, Date dateInfoEnd, @PathVariable("id") int id,
			@PathVariable("page") int page, @PathVariable("value") int value,
			@RequestParam(value = "key", required = false) String key, Authentication authentication, Model model) {
		Disease disease = diseaseService.findById(id);
		if(disease == null) {
			return "redirect:/approve/";
		}
		Boolean statusDate = true;
		model.addAttribute("statusDate", statusDate);
		List<ImgSurveyTargetPoint> imgSurveyTargetPoints = imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(disease, "Reject", dateInfoStart, dateInfoEnd, page, value);

		List<ImageDTO> images = new ArrayList<ImageDTO>();
		List<ImageDTO> dtosOriginalSize = new ArrayList<ImageDTO>();
		for (ImgSurveyTargetPoint imgSurveyTargetPoint : imgSurveyTargetPoints) {

			ImageDTO imageDTOs = new ImageDTO();
			imageDTOs.setImage(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePathS());
			images.add(imageDTOs);
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(externalPath + File.separator + "SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePath());
			dtosOriginalSize.add(imageDTO);
		}
		model.addAttribute("dtosOriginalSizeList", dtosOriginalSize);
		model.addAttribute("dtoList", images);
		model.addAttribute("img", imgSurveyTargetPoints);
		// List<Survey> surveys;
		// if (key==null || key=="") {
		// surveys = surveyService.findByUserInField(user, page, value);
		// }
		// else {
		// surveys = surveyService.findByUserInFieldAndKey(user,key, page, value);
		// }
		// model.addAttribute("surveys", surveys);
		int MaxSize = imgSurveyTargetPointService
				.findByDiseaseAndStatusBetweenDate(id, "Reject", dateInfoStart, dateInfoEnd).size();
		model.addAttribute("MaxSize", MaxSize);
		model.addAttribute("id", id);
		model.addAttribute("value", value);
		model.addAttribute("page", page);
		int firstItem = ((page - 1) * value + 1);
		model.addAttribute("firstItem", firstItem);
		int lastItem = (page * value);
		if (lastItem > MaxSize) {
			lastItem = MaxSize;
		}
		model.addAttribute("dateInfoStart", dateInfoStart);
		model.addAttribute("dateInfoEnd", dateInfoEnd);
		model.addAttribute("lastItem", lastItem);
		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatterTH", formatter);
		return "/approve/approveRejecttable";
	}

}