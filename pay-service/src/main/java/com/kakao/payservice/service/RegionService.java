package com.kakao.payservice.service;

import com.kakao.payservice.common.PayUtils;
import com.kakao.payservice.dto.RegionDataDto;
import com.kakao.payservice.model.Regions;
import com.kakao.payservice.model.RegionsDetail;
import com.kakao.payservice.repository.RegionsDetailRepository;
import com.kakao.payservice.repository.RegionsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RegionService {

    @Autowired
    private RegionsDetailRepository regionsDetailRepository;

    @Autowired
    private RegionsRepository regionsRepository;

    public void cleanRegionRepository () {
        try {
            List<RegionsDetail> regionsInfList = regionsDetailRepository.findAll();
            List<Regions> regionsList = regionsRepository.findAll();

            log.info("regionsInfList = {}, regionsList = {}", regionsInfList.size(), regionsList.size());

            if (regionsInfList.size() > 0 || regionsList.size() > 0) {
                log.info("data is exist.");

                regionsDetailRepository.deleteAll();
                regionsRepository.deleteAll();
            } else {
                log.info("data is not exist.");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public void updateRegion (RegionDataDto regionDataDto) {
        try {
            Long code = regionsRepository.findByname(regionDataDto.getRegion()).getCode();
            RegionsDetail regionsDetail = regionsDetailRepository.findOne(code);

            regionsDetail.setTarget(regionDataDto.getTarget());
            regionsDetail.setUsage(regionDataDto.getUsage());
            regionsDetail.setStrLimit(regionDataDto.getLimit());

            Long numLimit = PayUtils.parseLimit(regionDataDto.getLimit());
            regionsDetail.setNumLimit(numLimit);

            regionsDetail.setRate(regionDataDto.getRate());

            Double numRate = PayUtils.parseRate(regionDataDto.getRate());
            regionsDetail.setNumRate(numRate);

            regionsDetail.setInstitute(regionDataDto.getInstitute());
            regionsDetail.setMgmt(regionsDetail.getMgmt());
            regionsDetail.setReception(regionsDetail.getReception());

            regionsDetailRepository.save(regionsDetail);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public List<RegionDataDto> selectRegionList () {
        List<RegionDataDto> result = new ArrayList<>();
        List<RegionsDetail> regionsList = regionsDetailRepository.findAll();

        log.info("regionsList = {}", regionsList.size());

        try {
            regionsList.forEach(regionDetail -> {
                result.add(RegionDataDto.builder()
                        .region(regionDetail.getRegions().getName())
                        .target(regionDetail.getTarget())
                        .usage(regionDetail.getUsage())
                        .limit(regionDetail.getStrLimit())
                        .rate(regionDetail.getRate())
                        .institute(regionDetail.getInstitute())
                        .mgmt(regionDetail.getMgmt())
                        .reception(regionDetail.getReception()).build());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public RegionDataDto selectOneByRegion (String region) {
        RegionDataDto regionResponse = new RegionDataDto();

        try {
            Long code = regionsRepository.findByname(region).getCode();
            RegionsDetail regionDetail = regionsDetailRepository.findOne(code);

            regionResponse = RegionDataDto.builder()
                    .region(regionDetail.getRegions().getName())
                    .target(regionDetail.getTarget())
                    .usage(regionDetail.getUsage())
                    .limit(regionDetail.getStrLimit())
                    .rate(regionDetail.getRate())
                    .institute(regionDetail.getInstitute())
                    .mgmt(regionDetail.getMgmt())
                    .reception(regionDetail.getReception()).build();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return regionResponse;
    }

    public List<RegionDataDto> selectLimitSortedRegions (int count) {
        List<RegionDataDto> result = new ArrayList<>();
        List<RegionsDetail> regionsList = regionsDetailRepository.findAll(new Sort(Sort.Direction.DESC, "numLimit"));

        if (regionsList.size() > count) {
            regionsList = regionsList.subList(0, count);
        }

        try {
            regionsList.forEach(regionDetail -> {
                result.add(RegionDataDto.builder()
                        .region(regionDetail.getRegions().getName())
                        .target(regionDetail.getTarget())
                        .usage(regionDetail.getUsage())
                        .limit(regionDetail.getStrLimit())
                        .rate(regionDetail.getRate())
                        .institute(regionDetail.getInstitute())
                        .mgmt(regionDetail.getMgmt())
                        .reception(regionDetail.getReception()).build());
            });

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return result;
    }

    public RegionDataDto selectMinRate () {
        RegionDataDto regionResponse = new RegionDataDto();

        try {
            List<RegionsDetail> regionsList = regionsDetailRepository.findAll(new Sort(Sort.Direction.ASC, "numRate"));

            RegionsDetail regionDetail = regionsList.get(0);

            regionResponse = RegionDataDto.builder()
                    .region(regionDetail.getRegions().getName())
                    .target(regionDetail.getTarget())
                    .usage(regionDetail.getUsage())
                    .limit(regionDetail.getStrLimit())
                    .rate(regionDetail.getRate())
                    .institute(regionDetail.getInstitute())
                    .mgmt(regionDetail.getMgmt())
                    .reception(regionDetail.getReception()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return regionResponse;
    }
}
