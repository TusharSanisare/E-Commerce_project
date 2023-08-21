package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.User;

public interface UserPerository extends JpaRepository<User,Integer>{

	Optional<User> findUserByEmail(String email);
}
