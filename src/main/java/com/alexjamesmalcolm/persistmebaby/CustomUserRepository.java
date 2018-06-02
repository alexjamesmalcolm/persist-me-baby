package com.alexjamesmalcolm.persistmebaby;

import org.springframework.data.repository.CrudRepository;

public interface CustomUserRepository extends CrudRepository<CustomUser, Long> {

}
