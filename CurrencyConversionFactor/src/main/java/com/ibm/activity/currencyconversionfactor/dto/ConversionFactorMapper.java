package com.ibm.activity.currencyconversionfactor.dto;

import org.springframework.stereotype.Component;

import com.ibm.activity.currencyconversionfactor.domain.ConversionFactorEntity;

@Component
public class ConversionFactorMapper {

	public ConversionFactorDTO convertConversionFactorToConversionDTO(ConversionFactorEntity coFactorEntity) {
		ConversionFactorDTO cFactorDTO = new ConversionFactorDTO();
		cFactorDTO.setId(coFactorEntity.getId());
		cFactorDTO.setCountryCode(coFactorEntity.getCountryCode());
		cFactorDTO.setConversionFactor(coFactorEntity.getConversionFactor());

		return cFactorDTO;
	}

	public ConversionFactorEntity convertConversionFactorDTOToConversionFactor(ConversionFactorDTO coFactorDTO) {
		ConversionFactorEntity coFactorEntity = new ConversionFactorEntity();
		coFactorEntity.setId(coFactorDTO.getId());
		coFactorEntity.setCountryCode(coFactorDTO.getCountryCode());
		coFactorEntity.setConversionFactor(coFactorDTO.getConversionFactor());
		return coFactorEntity;
	}
}
