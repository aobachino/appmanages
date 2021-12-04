package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Industry;

@Repository
public interface IndustryRepository extends JpaRepository<Industry, Integer> {

	List<Industry> findByIndustryId(int id);
}
