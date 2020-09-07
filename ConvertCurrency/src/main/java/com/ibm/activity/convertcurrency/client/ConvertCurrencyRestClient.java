package com.ibm.activity.convertcurrency.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibm.activity.convertcurrency.dto.ConvertCurrencyDTO;


@FeignClient(name="CurrencyConversionFactor") //url="192.168.99.100:8088"
public interface ConvertCurrencyRestClient {

	@GetMapping("/currencyconversion/country/{countryCode}")
	public ConvertCurrencyDTO getConversionFactor(
			@RequestParam(value = "countryCode") String countryCode);
}
