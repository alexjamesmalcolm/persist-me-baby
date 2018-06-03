package com.alexjamesmalcolm.persistmebaby.customuser;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CustomUserRepository extends CrudRepository<CustomUser, Long> {
	Optional<CustomUser> findByGoogleName(String googleName);
}
