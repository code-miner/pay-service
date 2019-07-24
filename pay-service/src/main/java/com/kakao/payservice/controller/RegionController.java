package com.kakao.payservice.controller;

import com.kakao.payservice.dto.RegionDataDto;
import com.kakao.payservice.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/region")
@Api(tags = "region")
public class RegionController {

	@Autowired
	private RegionService regionService;

	@PostMapping("update-region")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ApiOperation(value = "${RegionController.update}")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Something went wrong"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "The user doesn't exist"),
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public RegionDataDto updateReion(@RequestBody RegionDataDto regionDataDto) {
		regionService.updateRegion(regionDataDto);

		return regionDataDto;
	}

	@GetMapping("/list")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
	@ApiOperation(value = "${RegionController.list}")
	@ApiResponses(value = {//
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 404, message = "The user doesn't exist"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public List<RegionDataDto> selectList() {
		return regionService.selectRegionList();
	}

	@GetMapping("/one/{region}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
	@ApiOperation(value = "${RegionController.selectOne}")
	@ApiResponses(value = {//
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 404, message = "The user doesn't exist"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public RegionDataDto selectOne(@PathVariable String region) {
		return regionService.selectOneByRegion(region);
	}

	@GetMapping("/sort/{count}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
	@ApiOperation(value = "${RegionController.selectLimitSortedRegions}")
	@ApiResponses(value = {//
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 404, message = "The user doesn't exist"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public List<RegionDataDto> selectLimitSortedRegions(@PathVariable int count) {
		return regionService.selectLimitSortedRegions(count);
	}

	@GetMapping("/get-min-rate")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
	@ApiOperation(value = "${RegionController.selectMinRate}")
	@ApiResponses(value = {//
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 404, message = "The user doesn't exist"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public RegionDataDto selectMinRate() {
		return regionService.selectMinRate();
	}
}
