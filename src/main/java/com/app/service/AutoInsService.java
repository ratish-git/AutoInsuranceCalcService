package com.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.dto.ResponseInfo;
import com.app.exception.ApplicationException;

@Service
public class AutoInsService {

	public List<String> getAllAvailableVin() {
		return new ArrayList<String>(allVinDetails().keySet());
	}

	public HashMap<String, Integer> allVinDetails() {

		HashMap<String, Integer> database = new HashMap<>();

		database.put("VIN-CN-0001-ON", 1120);
		database.put("VIN-CN-0002-BC", 3120);
		database.put("VIN-CN-0003-QC", 1400);
		database.put("VIN-CN-0004-SK", 120);
		return database;

	}
	
	


	public ResponseEntity<ResponseInfo> calculateInsurance(String vin, Double km) throws ApplicationException {

		if (!getAllAvailableVin().stream().anyMatch(vin::contains)) {
			throw new ApplicationException(HttpStatus.BAD_REQUEST, "error-001", "VIN not found-Try a registered VIN",
					"", null);
		}

		ResponseEntity<ResponseInfo> responseInfo = ResponseEntity.ok(new ResponseInfo());
		responseInfo.getBody().setMilesCovered(km);
		responseInfo.getBody().setTotalInsurance(responseInfo.getBody().getBaseFare() + km * 3);

		return responseInfo;
	}

	public ResponseEntity<ResponseInfo> calculateOldInsurance(String vin, Double oldkmReading, Double newkmReading)
			throws ApplicationException {
		if (!getAllAvailableVin().stream().anyMatch(vin::contains)) {
			throw new ApplicationException(HttpStatus.BAD_REQUEST, "error-001", "VIN not found-Try a registered VIN",
					"", null);
		}

		if (newkmReading < oldkmReading) {
			throw new ApplicationException(HttpStatus.BAD_REQUEST, "error-002", "Old KM can not be greater than New KM",
					"", null);
		}

		ResponseEntity<ResponseInfo> responseInfo = ResponseEntity.ok(new ResponseInfo());
		responseInfo.getBody().setMilesCovered(newkmReading - oldkmReading);
		responseInfo.getBody().setTotalInsurance(
				responseInfo.getBody().getBaseFare() + responseInfo.getBody().getMilesCovered() * 3);
		responseInfo.getBody().setVin(vin);
		return responseInfo;
	}

	public ResponseEntity<ResponseInfo> getOldReadingByVinNumber(String vin) throws ApplicationException {
		if (!getAllAvailableVin().stream().anyMatch(vin::contains)) {
			throw new ApplicationException(HttpStatus.BAD_REQUEST, "error-001", "VIN not found-Try a registered VIN",
					"", null);
		}
		ResponseEntity<ResponseInfo> responseInfo = ResponseEntity.ok(new ResponseInfo());
		responseInfo.getBody().setOldReading(allVinDetails().get(vin));
		responseInfo.getBody().setVin(vin);

		return responseInfo;
	}
}
