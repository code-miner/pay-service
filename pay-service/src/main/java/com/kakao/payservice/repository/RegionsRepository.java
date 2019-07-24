package com.kakao.payservice.repository;

import com.kakao.payservice.model.Regions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionsRepository extends JpaRepository<Regions, Long> {
	Regions findByname(String name);
	Regions findBycode(Long code);
}