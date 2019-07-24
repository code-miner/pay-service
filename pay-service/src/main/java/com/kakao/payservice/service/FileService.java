package com.kakao.payservice.service;

import com.kakao.payservice.common.PayUtils;
import com.kakao.payservice.model.Regions;
import com.kakao.payservice.model.RegionsDetail;
import com.kakao.payservice.repository.RegionsDetailRepository;
import com.kakao.payservice.repository.RegionsRepository;
import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.StringReader;

@Slf4j
@Service
public class FileService {

    @Autowired
    private RegionsRepository regionsRepository;

    @Autowired
    private RegionsDetailRepository regionsDetailRepository;

    public void uploadFileAndSaveRepository (MultipartFile file) {

        try {
            if (file != null) {
                log.info("originalName:" + file.getOriginalFilename());
                log.info("size:" + file.getSize());
                log.info("ContentType:" + file.getContentType());

                CSVReader reader = new CSVReader(new StringReader(new String(file.getBytes(), "EUC-KR")));
                reader.readNext();

                String[] line;
                while ((line = reader.readNext()) != null) {
                    regionsRepository.save(Regions.builder().name(line[1]).build());

                    Regions regions = regionsRepository.findByname(line[1]);

                    Long numLimit = PayUtils.parseLimit(line[4]);
                    Double numRate = PayUtils.parseRate(line[5]);

                    log.info("name : {}, target : {}, usage : {}, numLimit : {}, numRate : {}", line[1], line[2], line[3], numLimit, numRate);

                    regionsDetailRepository
                            .save(RegionsDetail.builder().regions(regions).target(line[2]).usage(line[3]).strLimit(line[4]).numLimit(numLimit)
                                    .rate(line[5]).numRate(numRate).institute(line[6]).mgmt(line[7]).reception(line[8]).build());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
