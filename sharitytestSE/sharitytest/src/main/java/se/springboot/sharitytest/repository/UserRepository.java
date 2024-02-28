package se.springboot.sharitytest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import se.springboot.sharitytest.model.User;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("from User u where u.username = :username")
	public User findByUsername(@Param("username") String username);

   
    @Query("from User u where u.role = :role")
	public List<User> findByRole(@Param("role") String role);
}
