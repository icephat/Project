package org.cassava.repository;

import org.cassava.model.PermissionFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionFileRepository extends CrudRepository<PermissionFile, Integer> {

}
