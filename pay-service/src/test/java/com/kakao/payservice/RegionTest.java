package com.kakao.payservice;

import com.kakao.payservice.dto.RegionDataDto;
import com.kakao.payservice.service.FileService;
import com.kakao.payservice.service.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RegionTest {

    @Autowired
    private RegionService regionService;

    @Autowired
    private FileService fileService;

    @Before
    public void uploadFileTest() {
        regionService.cleanRegionRepository();

        Path filePath = Paths.get("data/data_201612.csv");
        String fileName = "data_201612.csv";
        String originalFileName = "data_201612.csv";
        String contentType = "text/plain";
        byte[] content = null;

        try {
            content = Files.readAllBytes(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        MockMultipartFile multipartFile = new MockMultipartFile(fileName, originalFileName, contentType, content);

        fileService.uploadFileAndSaveRepository(multipartFile);
    }

    @Test
    public void selectRegionListTest() {
        List<RegionDataDto> regionList = regionService.selectRegionList();

        assertNotNull(regionList);
        assertThat(regionList.size(), greaterThan(0));
    }

    @Test
    public void selectOneByRegionTest() {
        RegionDataDto region = regionService.selectOneByRegion("강릉시");

        assertNotNull(region);
        assertEquals(region.getRegion(), "강릉시");
    }

    @Test
    public void selectLimitSortedRegionsTest() {
        List<RegionDataDto> regionList = regionService.selectLimitSortedRegions(3);

        assertNotNull(regionList);
        assertThat(regionList.size(), is(3));
    }

    @Test
    public void selectMinRateTest() {
        RegionDataDto region = regionService.selectMinRate();

        assertNotNull(region);
    }

    @Test
    public void updateRegionTest() {

        RegionDataDto region = regionService.selectOneByRegion("강릉시");

        assertNotNull(region);
        assertEquals(region.getRate(), "3%");

        region.setRate("5%");

        regionService.updateRegion(region);

        assertNotNull(region);
        assertEquals(region.getRate(), "5%");
    }
}
