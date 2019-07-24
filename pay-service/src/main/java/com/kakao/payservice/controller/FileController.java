package com.kakao.payservice.controller;

import com.kakao.payservice.service.FileService;
import com.kakao.payservice.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/file")
@Api(tags = "file")
public class FileController {

	@Autowired
	private FileService fileService;

	@Autowired
	private RegionService regionService;

	@PostMapping("/upload")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ApiOperation(value = "${FileController.upload}")
	@ApiResponses(value = {//
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 404, message = "The user doesn't exist"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public String uploadFile(MultipartFile file) throws Exception {
		regionService.cleanRegionRepository();
		fileService.uploadFileAndSaveRepository(file);

		return "success";
	}
}
