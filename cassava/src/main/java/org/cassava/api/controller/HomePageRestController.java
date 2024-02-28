package org.cassava.api.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.cassava.api.util.model.Response;
import org.cassava.model.Disease;
import org.cassava.model.ImgDisease;
import org.cassava.model.ImgNaturalEnemy;
import org.cassava.model.ImgPest;
import org.cassava.model.ImgVariety;
import org.cassava.model.NaturalEnemy;
import org.cassava.model.Pathogen;
import org.cassava.model.Pest;
import org.cassava.model.Variety;
import org.cassava.model.dto.HomePageDTO;
import org.cassava.model.dto.HomePageDiseaseDTO;
import org.cassava.model.dto.HomePageNaturalEnemyDTO;
import org.cassava.model.dto.HomePagePestDTO;
import org.cassava.model.dto.HomePageVarietyDTO;
import org.cassava.model.dto.NoImage;
import org.cassava.services.DiseaseService;
import org.cassava.services.FieldService;
import org.cassava.services.ImgDiseaseService;
import org.cassava.services.ImgNaturalEnemyService;
import org.cassava.services.ImgPestService;
import org.cassava.services.ImgVarietyService;
import org.cassava.services.NaturalEnemyService;
import org.cassava.services.OrganizationService;
import org.cassava.services.PestService;
import org.cassava.services.UserService;
import org.cassava.services.VarietyService;
import org.cassava.util.ImageBase64Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/v2/api/")
@CrossOrigin("http://localhost:8081")
public class HomePageRestController {
	@Value("${external.resoures.path}")
	private String externalPath;

	@Autowired
	private VarietyService varietyService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private DiseaseService diseaseService;
	@Autowired
	private PestService pestService;
	@Autowired
	private NaturalEnemyService naturalEnemyService;
	@Autowired
	private UserService userService;
	@Autowired
	private ImgVarietyService imgVarietyService;
	@Autowired
	private ImgDiseaseService imgDiseaseService;
	@Autowired
	private ImgNaturalEnemyService imgNaturalEnemyService;
	@Autowired
	private ImgPestService imgPestService;
	@Autowired
	private FieldService fieldService;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response<ObjectNode>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Response<ObjectNode> res = new Response<>();
		res.setHttpStatus(HttpStatus.BAD_REQUEST);

		ObjectMapper mapper = new ObjectMapper();

		ObjectNode responObject = mapper.createObjectNode();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldname = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			responObject.put(fieldname, errorMessage);
		});
		res.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
		res.setBody(responObject);
		return new ResponseEntity<Response<ObjectNode>>(res, res.getHttpStatus());
	}

	@GetMapping("/variety")
	public ResponseEntity<Response<List<HomePageDTO>>> findAllVariety() {
		Response<List<HomePageDTO>> res = new Response();
		try {
			List<Variety> varieties = varietyService.findAll();
			List<HomePageDTO> homePageDTOs = new ArrayList<HomePageDTO>();
			for (Variety variety : varieties) {
				String path = new String();
				String path64 = NoImage.noImageBase64;
				if (!(variety.getImgvarieties().isEmpty())) {
					List<ImgVariety> imgVarieties = variety.getImgvarieties();
					Random rand = new Random();
					int index = rand.nextInt(imgVarieties.size());
					ImgVariety imgVariety = imgVarieties.get(index);
					path = externalPath + File.separator + "Variety" + File.separator + imgVariety.getFilePath();
				}
				boolean checkFileExist = new File(path).exists();
				if (checkFileExist) {
					path64 = ImageBase64Helper.toImageBase64(path);
					HomePageDTO homePageDTO = new HomePageDTO(variety.getVarietyId(), variety.getName(), path64);
					homePageDTOs.add(homePageDTO);
				}

			}
			res.setBody(homePageDTOs);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<HomePageDTO>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<HomePageDTO>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping("/disease")
	public ResponseEntity<Response<List<HomePageDTO>>> findAllDisease() {
		Response<List<HomePageDTO>> res = new Response();
		try {
			List<Disease> diseases = diseaseService.findAll();
			List<HomePageDTO> homePageDTOs = new ArrayList<HomePageDTO>();
			for (Disease disease : diseases) {

				String path = new String();
				String path64 = NoImage.noImageBase64;
				if (!disease.getImgdiseases().isEmpty()) {
					List<ImgDisease> imgDieases = disease.getImgdiseases();
					Random rand = new Random();
					int index = rand.nextInt(imgDieases.size());
					ImgDisease imgDiease = imgDieases.get(index);
					path = externalPath + File.separator + "Disease" + File.separator + imgDiease.getFilePath();

				}
				boolean checkFileExist = new File(path).exists();
				if (checkFileExist) {
					path64 = ImageBase64Helper.toImageBase64(path);
					String name = disease.getTargetofsurvey().getName();
					HomePageDTO homePageDTO = new HomePageDTO(disease.getDiseaseId(), name, path64);
					homePageDTOs.add(homePageDTO);
				}

				

			}
			res.setBody(homePageDTOs);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<HomePageDTO>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<HomePageDTO>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping("/pest")
	public ResponseEntity<Response<List<HomePageDTO>>> findSampleOfPest() {
		Response<List<HomePageDTO>> res = new Response();
		try {
			List<Pest> pests = pestService.findAll();
			List<HomePageDTO> homePageDTOs = new ArrayList<HomePageDTO>();
			for (Pest pest : pests) {

				String path = new String();
				String path64 = NoImage.noImageBase64;

				if (!(pest.getImgpests().isEmpty())) {
					List<ImgPest> imgPests = pest.getImgpests();
					Random rand = new Random();
					int index = rand.nextInt(imgPests.size());
					ImgPest imgPest = imgPests.get(index);
					path = externalPath + File.separator + "Pest" + File.separator + imgPest.getFilePath();
				}
				boolean checkFileExist = new File(path).exists();
				if (checkFileExist) {
					path64 = ImageBase64Helper.toImageBase64(path);
					HomePageDTO homePageDTO = new HomePageDTO(pest.getPestId(), pest.getName(), path64);
					homePageDTOs.add(homePageDTO);
				}

				

			}
			res.setBody(homePageDTOs);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<HomePageDTO>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<HomePageDTO>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping("/naturalenemy")
	public ResponseEntity<Response<List<HomePageDTO>>> findSampleOfNaturalEnemy() {
		Response<List<HomePageDTO>> res = new Response();
		try {
			List<NaturalEnemy> naturalEnemies = naturalEnemyService.findAll();
			List<HomePageDTO> homePageDTOs = new ArrayList<HomePageDTO>();
			for (NaturalEnemy naturalEnemy : naturalEnemies) {

				String path = new String();
				String path64 = NoImage.noImageBase64;

				if (!(naturalEnemy.getImgnaturalenemies().isEmpty())) {
					List<ImgNaturalEnemy> imgNaturalEnemies = naturalEnemy.getImgnaturalenemies();
					Random rand = new Random();
					int index = rand.nextInt(imgNaturalEnemies.size());
					ImgNaturalEnemy imgNaturalEnemy = imgNaturalEnemies.get(index);
					path = externalPath + File.separator + "Naturalenemy" + File.separator
							+ imgNaturalEnemy.getFilePath();
				}
				boolean checkFileExist = new File(path).exists();
				if (checkFileExist) {
					path64 = ImageBase64Helper.toImageBase64(path);
					String name = naturalEnemy.getTargetofsurvey().getName();
					HomePageDTO homePageDTO = new HomePageDTO(naturalEnemy.getNaturalEnemyId(), name, path64);
					homePageDTOs.add(homePageDTO);
				}

				

			}
			res.setBody(homePageDTOs);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<HomePageDTO>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<HomePageDTO>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping("/varietyid/{varietyId}")
	public ResponseEntity<Response<HomePageVarietyDTO>> findVarietyId(@PathVariable("varietyId") int varietyId) {
		Response<HomePageVarietyDTO> res = new Response();
		try {
			Variety variety = varietyService.findById(varietyId);
			HomePageVarietyDTO result = new HomePageVarietyDTO();
			List<ImgVariety> imgVarieties = variety.getImgvarieties();
			List<String> image = new ArrayList<String>();
			for (ImgVariety imgVariety : imgVarieties) {
				String path = externalPath + File.separator + "Variety" + File.separator + imgVariety.getFilePath();
				String pathBase64 = new String();
				boolean checkFileExist = new File(path).exists();
				if (checkFileExist) {
					pathBase64 = ImageBase64Helper.toImageBase64(path);
				}
				image.add(pathBase64);
			}
			result.setName(variety.getName());
			result.setVariety(variety);
			result.setImage(image);
			res.setBody(result);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<HomePageVarietyDTO>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<HomePageVarietyDTO>>(res, res.getHttpStatus());
		}
	}

	@GetMapping("/diseaseid/{diseaseId}")
	public ResponseEntity<Response<HomePageDiseaseDTO>> findDiseaseById(@PathVariable("diseaseId") int diseaseId) {
		Response<HomePageDiseaseDTO> res = new Response();
		try {
			Disease disease = diseaseService.findById(diseaseId);
			String name = disease.getTargetofsurvey().getName();
			HomePageDiseaseDTO result = new HomePageDiseaseDTO();
			List<String> image = new ArrayList<String>();
			List<ImgDisease> imgDieases = disease.getImgdiseases();
			List<Pathogen> pathogens = disease.getPathogens();
			List<String> pathogenName = new ArrayList<String>();
			List<String> pathogenTypeName = new ArrayList<String>();
			List<Pest> pests = disease.getPests();
			List<String> pestName = new ArrayList<String>();
			List<String> pestsciName = new ArrayList<String>();
			for (ImgDisease imgDiease : imgDieases) {
				String path = externalPath + File.separator + "Disease" + File.separator + imgDiease.getFilePath();
				String pathBase64 = new String();
				boolean checkFileExist = new File(path).exists();
				if (checkFileExist) {
					pathBase64 = ImageBase64Helper.toImageBase64(path);
				}
				image.add(pathBase64);
			}

			for (Pathogen pathogen : pathogens) {
				pathogenName.add(pathogen.getScientificName());
				pathogenTypeName.add(pathogen.getPathogentype().getName());
			}

			for (Pest pest : pests) {
				pestName.add(pest.getName());
				pestsciName.add(pest.getScientificName());
			}

			result.setName(name);
			result.setDisease(disease);
			result.setImage(image);
			result.setPathogenName(pathogenName);
			result.setPathogenTypeName(pathogenTypeName);
			result.setPestName(pestName);
			result.setPestSciName(pestsciName);
			res.setBody(result);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<HomePageDiseaseDTO>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<HomePageDiseaseDTO>>(res, res.getHttpStatus());
		}
	}

	@GetMapping("/pestid/{pestId}")
	public ResponseEntity<Response<HomePagePestDTO>> findPestById(@PathVariable("pestId") int pestId) {
		Response<HomePagePestDTO> res = new Response();
		try {
			Pest pest = pestService.findById(pestId);
			String name = pest.getName();
			HomePagePestDTO result = new HomePagePestDTO();
			List<String> image = new ArrayList<String>();
			List<ImgPest> imgPests = pestService.findById(pestId).getImgpests();
			for (ImgPest img : imgPests) {
				String path = externalPath + File.separator + "Pest" + File.separator + img.getFilePath();
				String pathBase64 = new String();
				boolean checkFileExist = new File(path).exists();
				if (checkFileExist) {
					pathBase64 = ImageBase64Helper.toImageBase64(path);
				}
				image.add(pathBase64);
			}
			result.setName(name);
			result.setPestManagement(pest.getPestmanagements());
			result.setImage(image);
			res.setBody(result);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<HomePagePestDTO>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<HomePagePestDTO>>(res, res.getHttpStatus());
		}
	}

	@GetMapping("/naturalenemyid/{naturalenemyId}")
	public ResponseEntity<Response<HomePageNaturalEnemyDTO>> findNaturalEnemyById(
			@PathVariable("naturalenemyId") int naturalenemyId) {
		Response<HomePageNaturalEnemyDTO> res = new Response();
		try {
			NaturalEnemy naturalEnemy = naturalEnemyService.findById(naturalenemyId);
			String name = naturalEnemy.getTargetofsurvey().getName();
			HomePageNaturalEnemyDTO result = new HomePageNaturalEnemyDTO();
			List<String> image = new ArrayList<String>();
			List<ImgNaturalEnemy> imgNaturalEnemies = naturalEnemyService.findById(naturalenemyId)
					.getImgnaturalenemies();
			for (ImgNaturalEnemy img : imgNaturalEnemies) {
				String path = externalPath + File.separator + "Naturalenemy" + File.separator + img.getFilePath();
				String pathBase64 = new String();
				boolean checkFileExist = new File(path).exists();
				if (checkFileExist) {
					pathBase64 = ImageBase64Helper.toImageBase64(path);
				}
				image.add(pathBase64);
			}
			result.setName(name);
			result.setNaturalEnemy(naturalEnemy);
			result.setImage(image);
			res.setBody(result);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<HomePageNaturalEnemyDTO>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<HomePageNaturalEnemyDTO>>(res, res.getHttpStatus());
		}
	}

	@GetMapping("/countuser")
	public ResponseEntity<Response<List<Integer>>> countUser() {
		Response<List<Integer>> res = new Response();
		try {
			List<Integer> result = new ArrayList<Integer>();
			result.add(organizationService.findAll().size());
			result.add(userService.countFarmer());
			result.add(userService.countStaff());
			result.add(fieldService.findAll().size());

			res.setBody(result);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Integer>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Integer>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping("/variety/image/page/{page}/value/{value}")
	public ResponseEntity<Response<List<HomePageDTO>>> findImageVariety(@PathVariable("page") int page,
			@PathVariable("value") int value) {
		Response<List<HomePageDTO>> res = new Response();
		try {
			List<ImgVariety> imgVarieties = imgVarietyService.findAllByPagination(page, value);
			List<HomePageDTO> homePageDTOs = new ArrayList<HomePageDTO>();
			for (ImgVariety imgVariety : imgVarieties) {
				HomePageDTO homePageDTO = new HomePageDTO();
				String path = externalPath + File.separator + "Variety" + File.separator + imgVariety.getFilePath();
				boolean checkFileExist = new File(path).exists();
				if (checkFileExist) {
					homePageDTO.setImage(path);
					homePageDTO.setName(imgVariety.getVariety().getName());
					homePageDTO.setId(imgVariety.getVariety().getVarietyId());
					homePageDTOs.add(homePageDTO);
				}
			}
			res.setBody(homePageDTOs);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<HomePageDTO>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<HomePageDTO>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping("/disease/image/page/{page}/value/{value}")
	public ResponseEntity<Response<List<HomePageDTO>>> findImageDisease(@PathVariable("page") int page,
			@PathVariable("value") int value) {
		Response<List<HomePageDTO>> res = new Response();
		try {
			List<ImgDisease> imgDiseases = imgDiseaseService.findAllByPagination(page, value);
			List<HomePageDTO> homePageDTOs = new ArrayList<HomePageDTO>();
			for (ImgDisease imgDisease : imgDiseases) {
				HomePageDTO homePageDTO = new HomePageDTO();
				String path = externalPath + File.separator + "Disease" + File.separator + imgDisease.getFilePath();
				boolean checkFileExist = new File(path).exists();
				if (checkFileExist) {
					homePageDTO.setImage(path);
					homePageDTO.setName(imgDisease.getDisease().getTargetofsurvey().getName());
					homePageDTO.setId(imgDisease.getDisease().getDiseaseId());
					homePageDTOs.add(homePageDTO);
				}
			}
			res.setBody(homePageDTOs);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<HomePageDTO>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<HomePageDTO>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping("/naturalenemy/image/page/{page}/value/{value}")
	public ResponseEntity<Response<List<HomePageDTO>>> findImageNaturalEnemy(@PathVariable("page") int page,
			@PathVariable("value") int value) {
		Response<List<HomePageDTO>> res = new Response();
		try {
			List<ImgNaturalEnemy> imgNaturalEnemies = imgNaturalEnemyService.findAllByPagination(page, value);
			List<HomePageDTO> homePageDTOs = new ArrayList<HomePageDTO>();
			for (ImgNaturalEnemy imgNaturalEnemy : imgNaturalEnemies) {
				HomePageDTO homePageDTO = new HomePageDTO();
				String path = externalPath + File.separator + "Naturalenemy" + File.separator
						+ imgNaturalEnemy.getFilePath();
				boolean checkFileExist = new File(path).exists();
				if (checkFileExist) {
					homePageDTO.setImage(path);
					homePageDTO.setName(imgNaturalEnemy.getNaturalenemy().getTargetofsurvey().getName());
					homePageDTO.setId(imgNaturalEnemy.getNaturalenemy().getNaturalEnemyId());
					homePageDTOs.add(homePageDTO);
				}
			}
			res.setBody(homePageDTOs);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<HomePageDTO>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<HomePageDTO>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping("/pest/image/page/{page}/value/{value}")
	public ResponseEntity<Response<List<HomePageDTO>>> findAllImagePest(@PathVariable("page") int page,
			@PathVariable("value") int value) {
		Response<List<HomePageDTO>> res = new Response();
		try {
			List<ImgPest> imgPests = imgPestService.findAllByPagination(page, value);
			List<HomePageDTO> homePageDTOs = new ArrayList<HomePageDTO>();
			for (ImgPest imgPest : imgPests) {
				HomePageDTO homePageDTO = new HomePageDTO();
				String path = externalPath + File.separator + "Pest" + File.separator + imgPest.getFilePath();
				boolean checkFileExist = new File(path).exists();
				if (checkFileExist) {
					homePageDTO.setImage(path);
					homePageDTO.setName(imgPest.getPest().getName());
					homePageDTO.setId(imgPest.getPest().getPestId());
					homePageDTOs.add(homePageDTO);
				}
			}
			res.setBody(homePageDTOs);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<HomePageDTO>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<HomePageDTO>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping("/images")
	public ResponseEntity<Response<List<HomePageDTO>>> findAllImage() {
		Response<List<HomePageDTO>> res = new Response();
		try {
			List<HomePageDTO> homePageDTOs = new ArrayList<>();

			List<ImgPest> imgPests = imgPestService.findAllByPagination(1, 2);
			for (ImgPest imgPest : imgPests) {
				HomePageDTO homePageDTO = new HomePageDTO();
				String path = externalPath + File.separator + "Pest" + File.separator + imgPest.getFilePath();
				boolean checkFileExist = new File(path).exists();
				if (checkFileExist) {
					homePageDTO.setImage(path);
					homePageDTO.setName(imgPest.getPest().getName());
					homePageDTO.setId(imgPest.getPest().getPestId());
					homePageDTOs.add(homePageDTO);
				}
			}

			List<ImgDisease> imgDiseases = imgDiseaseService.findAllByPagination(1, 2);
			for (ImgDisease imgDisease : imgDiseases) {
				HomePageDTO homePageDTO = new HomePageDTO();
				String path = externalPath + File.separator + "Disease" + File.separator + imgDisease.getFilePath();
				boolean checkFileExist = new File(path).exists();
				if (checkFileExist) {
					homePageDTO.setImage(path);
					homePageDTO.setName(imgDisease.getDisease().getTargetofsurvey().getName());
					homePageDTO.setId(imgDisease.getDisease().getDiseaseId());
					homePageDTOs.add(homePageDTO);
				}
			}

			List<ImgVariety> imgVarieties = imgVarietyService.findAllByPagination(1, 2);
			for (ImgVariety imgVariety : imgVarieties) {
				HomePageDTO homePageDTO = new HomePageDTO();
				String path = externalPath + File.separator + "Variety" + File.separator + imgVariety.getFilePath();
				boolean checkFileExist = new File(path).exists();
				if (checkFileExist) {
					homePageDTO.setImage(path);
					homePageDTO.setName(imgVariety.getVariety().getName());
					homePageDTO.setId(imgVariety.getVariety().getVarietyId());
					homePageDTOs.add(homePageDTO);
				}
			}

			List<ImgNaturalEnemy> imgNaturalEnemies = imgNaturalEnemyService.findAllByPagination(1, 2);
			for (ImgNaturalEnemy imgNaturalEnemy : imgNaturalEnemies) {
				HomePageDTO homePageDTO = new HomePageDTO();
				String path = externalPath + File.separator + "Naturalenemy" + File.separator
						+ imgNaturalEnemy.getFilePath();
				boolean checkFileExist = new File(path).exists();
				if (checkFileExist) {
					homePageDTO.setImage(path);
					homePageDTO.setName(imgNaturalEnemy.getNaturalenemy().getTargetofsurvey().getName());
					homePageDTO.setId(imgNaturalEnemy.getNaturalenemy().getNaturalEnemyId());
					homePageDTOs.add(homePageDTO);
				}
			}

			res.setBody(homePageDTOs);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<HomePageDTO>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<HomePageDTO>>>(res, res.getHttpStatus());
		}
	}

}
