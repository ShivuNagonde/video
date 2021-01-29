package com.shiv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shiv.model.YearPassHistory;

public interface YearPassHistoryRepository extends JpaRepository<YearPassHistory, Integer> {

	Optional<List<YearPassHistory>> findByAdharNumber(String adharNumber);

}
