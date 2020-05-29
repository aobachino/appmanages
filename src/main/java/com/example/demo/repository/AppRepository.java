package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.App;

@Repository
public interface AppRepository  extends JpaRepository<App, Integer> {

	App findByAppId(int id);
}
