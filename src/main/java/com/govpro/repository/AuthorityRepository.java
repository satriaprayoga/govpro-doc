package com.govpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.govpro.domain.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{

}
