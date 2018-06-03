package com.alexjamesmalcolm.persistmebaby.customuser;

import org.springframework.data.repository.CrudRepository;

public interface CustomUserRepository extends CrudRepository<CustomUser, Long> {
	CustomUser findByGoogleName(String googleName);
}
