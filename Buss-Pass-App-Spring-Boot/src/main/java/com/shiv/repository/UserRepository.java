package com.shiv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shiv.model.DayPassHistory;
import com.shiv.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	boolean existsByEmail(String email);

	boolean existsByPassword(String password);

	User findByEmail(String email);

	Optional<User> findByAdharNumber(String adharNumber);

	

}
