package com.shiv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shiv.model.DayPassHistory;

public interface DayPassHistoryRepository extends JpaRepository<DayPassHistory, Integer> {

	Optional<List<DayPassHistory>> findByAdharNumber(String adharNumber);

}
