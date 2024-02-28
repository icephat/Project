package org.cassava.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.cassava.model.Refreshtoken;
import org.cassava.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface RefreshTokenRepository extends CrudRepository<Refreshtoken, Integer> {

	Optional<Refreshtoken> findByToken(String token);

	@Modifying
	int deleteByUser(User user);
	
	
	Optional<Refreshtoken> findByUser(User user);

}
