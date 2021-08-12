package com.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ResponseInfo {
	
	private Integer baseFare= 35;
	@JsonInclude(Include.NON_NULL)
	private Double milesCovered;
	@JsonInclude(Include.NON_NULL)
	private Double totalInsurance;
	@JsonInclude(Include.NON_NULL)
	private Integer oldReading;
	@JsonInclude(Include.NON_NULL)
	private String vin;
	
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public Integer getOldReading() {
		return oldReading;
	}
	public void setOldReading(Integer oldReading) {
		this.oldReading = oldReading;
	}
	public Integer getBaseFare() {
		return baseFare;
	}
	public Double getMilesCovered() {
		return milesCovered;
	}
	public void setMilesCovered(Double milesCovered) {
		this.milesCovered = milesCovered;
	}
	public Double getTotalInsurance() {
		return totalInsurance;
	}
	public void setTotalInsurance(Double totalInsurance) {
		this.totalInsurance = totalInsurance;
	}
	

}
