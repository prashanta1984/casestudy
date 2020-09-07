package com.ibm.activity.convertcurrency.service;

import com.ibm.activity.convertcurrency.dto.ConvertCurrencyDTO;

public interface IConvertCurrency {
	public ConvertCurrencyDTO convertedCurrency(String countryCode, Double amount);
}
