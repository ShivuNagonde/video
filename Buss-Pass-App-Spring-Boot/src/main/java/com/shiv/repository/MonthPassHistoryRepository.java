package com.shiv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shiv.model.MonthPassHistory;

public interface MonthPassHistoryRepository extends JpaRepository<MonthPassHistory, Integer> {

	Optional<List<MonthPassHistory>> findByAdharNumber(String adharNumber);

}
