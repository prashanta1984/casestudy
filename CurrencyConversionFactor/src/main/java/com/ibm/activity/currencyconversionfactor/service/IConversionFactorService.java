package com.ibm.activity.currencyconversionfactor.service;

import com.ibm.activity.currencyconversionfactor.dto.ConversionFactorDTO;
import com.ibm.activity.currencyconversionfactor.exception.ResourceCreationException;
import com.ibm.activity.currencyconversionfactor.exception.ResourceNotFoundException;
import com.ibm.activity.currencyconversionfactor.exception.ResourceUpdateException;

public interface IConversionFactorService {
	
	public ConversionFactorDTO getCoFactorByCountryCode(String countryCode) throws ResourceNotFoundException;
	public ConversionFactorDTO addCoFactor(ConversionFactorDTO coFactorDTO) throws ResourceCreationException;
	public ConversionFactorDTO updateCoFactor(String countryCode, ConversionFactorDTO coFactorDTO) throws ResourceUpdateException, ResourceNotFoundException;
}
