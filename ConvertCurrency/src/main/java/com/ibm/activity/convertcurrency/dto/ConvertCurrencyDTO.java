package com.ibm.activity.convertcurrency.dto;

public class ConvertCurrencyDTO {

	private Long id;

	private Double convertedAmount;

	private String countryCode;

	private Double conversionFactor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getConvertedAmount() {
		return convertedAmount;
	}

	public void setConvertedAmount(Double convertedAmount) {
		this.convertedAmount = convertedAmount;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Double getConversionFactor() {
		return conversionFactor;
	}

	public void setConversionFactor(Double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}
}
