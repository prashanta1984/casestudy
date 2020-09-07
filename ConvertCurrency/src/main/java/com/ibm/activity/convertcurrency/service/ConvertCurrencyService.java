package com.ibm.activity.convertcurrency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.activity.convertcurrency.client.ConvertCurrencyRestClient;
import com.ibm.activity.convertcurrency.dto.ConvertCurrencyDTO;
import com.ibm.activity.convertcurrency.exception.ResourceNotFoundException;

@Service
public class ConvertCurrencyService {

	@Autowired
	ConvertCurrencyRestClient restClient;

	public ConvertCurrencyDTO convertedCurrency(String countryCode, Double amount) throws ResourceNotFoundException {
		ConvertCurrencyDTO responseDto = restClient.getConversionFactor(countryCode);

		if (responseDto.getId() > 0) {
			Double convertedAmount = responseDto.getConversionFactor() * amount;
			responseDto.setConvertedAmount(convertedAmount);
			return responseDto;
		} else {
			throw new ResourceNotFoundException(countryCode);
		}
	}

}
