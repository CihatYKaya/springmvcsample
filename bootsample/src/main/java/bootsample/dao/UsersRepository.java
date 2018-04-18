package bootsample.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import bootsample.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
	Optional<Users> findByName(String username);
}
