package com.ibm.activity.convertcurrency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.activity.convertcurrency.dto.ConvertCurrencyDTO;
import com.ibm.activity.convertcurrency.exception.ResourceNotFoundException;
import com.ibm.activity.convertcurrency.service.ConvertCurrencyService;

@RequestMapping("convertcurrency")
@RestController
public class ConvertCurrencyController {

	@Autowired
	ConvertCurrencyService convertCurrencyService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/convert")
	ResponseEntity<ConvertCurrencyDTO> convertedCurrency(@RequestParam(value = "countryCode") String countryCode,
			@RequestParam(value = "amount") Double amount) throws ResourceNotFoundException {
		ConvertCurrencyDTO dto = convertCurrencyService.convertedCurrency(countryCode, amount);
		return ResponseEntity.ok().body(dto);
	}

}
