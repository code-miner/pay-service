package com.kakao.payservice.repository;

import com.kakao.payservice.model.Regions;
import com.kakao.payservice.model.RegionsDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionsDetailRepository extends JpaRepository<RegionsDetail, Long> {
	RegionsDetail findByRegions(Regions regions);
}