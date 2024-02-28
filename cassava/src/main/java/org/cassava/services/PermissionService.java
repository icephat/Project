package org.cassava.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.cassava.model.Field;
import org.cassava.model.ImgNaturalEnemy;
import org.cassava.model.NaturalEnemy;
import org.cassava.model.Organization;
import org.cassava.model.Permission;
import org.cassava.model.PermissionFile;
import org.cassava.model.Staff;
import org.cassava.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("PermissionService")
@PropertySource(value = "classpath:/application.properties")
public class PermissionService {

	@Value("${external.resoures.path}")
	private String externalPath;

	@Autowired
	private PermissionRepository permissionRepository;

	@Autowired
	private StaffService staffService;

	@Autowired
	private OrganizationService organizationService;

	public Permission save(Permission permission) {
		return permissionRepository.save(permission);
	}

	public String writeFile(MultipartFile file) throws IOException {

		File folder = new File(externalPath + File.separator + "Permission" + File.separator);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		UUID uuid = UUID.randomUUID();

		String filename = uuid.toString();

		String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

		filename = filename + "." + type;

		String path = externalPath + File.separator + "Permission" + File.separator + filename;
		OutputStream outputStream = new FileOutputStream(path);
		outputStream.write(file.getBytes());
		outputStream.close();

		return filename;

	}

	public List<Permission> findByListStatusAndType(List<String> status, String type) {
		List<Permission> permission = (List<Permission>) permissionRepository.findByListStatusAndType(status, type);
		return permission;
	}

	public List<Permission> findByListStatusAndTypeAndLessThanDateExpire(List<String> status, String type, Date date) {
		List<Permission> permission = (List<Permission>) permissionRepository
				.findByListStatusAndTypeAndLessThanDateExpire(status, type, date);
		return permission;
	}

	public List<Permission> findByListStatusAndTypeAndOrganization(List<String> status, String type,
			Organization organization) {
		List<Permission> permission = (List<Permission>) permissionRepository
				.findByListStatusAndTypeAndOrganization(status, type, organization);
		return permission;
	}

	public List<Permission> findByListStatusAndTypeAndOrganization(List<String> status, String type,
			int organizationId) {
		Organization organization = organizationService.findById(organizationId);
		List<Permission> permission = (List<Permission>) permissionRepository
				.findByListStatusAndTypeAndOrganization(status, type, organization);
		return permission;
	}

	public List<Permission> findByStaffAndType(int id, String type) {
		Staff staff = staffService.findById(id);
		List<Permission> permission = (List<Permission>) permissionRepository.findByStaffAndType(staff, type);
		return permission;
	}

	public List<Permission> findByStaffAndType(Staff staff, String type) {
		List<Permission> permission = (List<Permission>) permissionRepository.findByStaffAndType(staff, type);
		return permission;
	}

	public List<Permission> findAll() {
		List<Permission> permission = (List<Permission>) permissionRepository.findAll();
		return permission;
	}

	public Permission findById(int permissionId) {
		return permissionRepository.findById(permissionId).orElse(null);
	}

	public void deleteById(int permissionId) {
		permissionRepository.deleteById(permissionId);
	}

	public List<Permission> findByStaffAndApproveStatusAndOrganization(Staff staff, String type){
		List<Permission> permission = (List<Permission>) permissionRepository.findByStaffAndApproveStatusAndOrganization(staff,type);
		return permission;
	}

	public List<Permission> findByStaff(Staff staff) {
		List<Permission> permission = (List<Permission>) permissionRepository.findByStaff(staff);
		return permission;
	}
}
