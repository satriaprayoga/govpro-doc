package com.govpro.repository;

import com.govpro.domain.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findOneByLogin(String login);
	
	@EntityGraph(attributePaths="authorities")
	Optional<User> findOneWithAuthoritesByLogin(String login);
}
