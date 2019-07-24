package com.kakao.payservice;

import com.kakao.payservice.dto.RegionDataDto;
import com.kakao.payservice.service.FileService;
import com.kakao.payservice.service.RegionService;
import lombok.extern.slf4j.Slf4j;
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

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileTest {

    @Autowired
    private RegionService regionService;

    @Autowired
    private FileService fileService;

    @Test
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

        List<RegionDataDto> regionList = regionService.selectRegionList();

        assertThat(regionList.size(), greaterThan(0));
    }
}
