package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ResponseInfo;
import com.app.exception.ApplicationException;
import com.app.service.AutoInsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/autoIns")
public class AutoInsController {

	@Autowired
	AutoInsService service;

	// getAll VIN numbers registered for insurance
	@GetMapping("/getAllAvailableVin")
	public List<String> getAllAvailableVin() throws ApplicationException {
		System.out.println("*********************........Inside getAllAvailableVin........*********************");
		return service.getAllAvailableVin();
	}

	@ApiOperation(value = "This Api is used to get Details by VIN Number", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Not Valid"), @ApiResponse(code = 401, message = "Not Authorized"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping("/oldReading")
	public ResponseEntity<ResponseInfo> getOldReadingByVinNumber(
			@ApiParam(value = "17 digit Vehicle Identification Number", required = true) @RequestHeader(name = "vin") String vin)
			throws ApplicationException {
		System.out.println("*********************........Inside calculateIns........*********************");

		return service.getOldReadingByVinNumber(vin);
	}

	// calculateInsurance for new VIN numbers registered
	@ApiOperation(value = "This Api is used to calculate Insurance for new Car", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Not Valid"), @ApiResponse(code = 401, message = "Not Authorized"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping("/calculateIns")
	public ResponseEntity<ResponseInfo> calculateIns(
			@ApiParam(value = "17 digit Vehicle Identification Number", required = true) @RequestHeader(name = "vin") String vin,
			@ApiParam(value = "Miles covered in KM", required = true) @RequestHeader(name = "KM") Double km)
			throws ApplicationException {
		System.out.println("*********************........Inside calculateIns........*********************");

		return service.calculateInsurance(vin, km);
	}

	// calculateInsurance for old VIN numbers registered
	@ApiOperation(value = "This Api is used to calculate Insurance for old Car", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Not Valid"), @ApiResponse(code = 401, message = "Not Authorized"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping("/calculateOldIns")
	public ResponseEntity<ResponseInfo> calculateoldIns(
			@ApiParam(value = "17 digit Vehicle Identification Number", required = true) @RequestHeader(name = "vin") String vin,
			@ApiParam(value = "Old KM Reading", required = true) @RequestHeader(name = "OldKM") Double oldkmReading,
			@ApiParam(value = "New KM Reading covered", required = true) @RequestHeader(name = "NewKM") Double newkmReading)
			throws ApplicationException {
		System.out.println("*********************........Inside calculateoldIns........*********************");

		return service.calculateOldInsurance(vin, oldkmReading, newkmReading);
	}

}
