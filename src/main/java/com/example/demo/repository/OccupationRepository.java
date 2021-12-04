package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Occupation;

@Repository
public interface OccupationRepository extends JpaRepository<Occupation, Integer> {

	List<Occupation> findByOccupationId(int id);
}
