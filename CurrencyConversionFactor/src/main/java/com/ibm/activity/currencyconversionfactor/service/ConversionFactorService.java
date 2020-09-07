package com.ibm.activity.currencyconversionfactor.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.activity.currencyconversionfactor.domain.ConversionFactorEntity;
import com.ibm.activity.currencyconversionfactor.dto.ConversionFactorDTO;
import com.ibm.activity.currencyconversionfactor.dto.ConversionFactorMapper;
import com.ibm.activity.currencyconversionfactor.exception.ResourceCreationException;
import com.ibm.activity.currencyconversionfactor.exception.ResourceNotFoundException;
import com.ibm.activity.currencyconversionfactor.exception.ResourceUpdateException;
import com.ibm.activity.currencyconversionfactor.repository.ConversionFactorRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ConversionFactorService implements IConversionFactorService {

	@Autowired
	ConversionFactorRepository coFactorRepository;

	@Autowired
	ConversionFactorMapper conversionFactorMapper;

	@Override
	@HystrixCommand(fallbackMethod = "fallBackCall")
	public ConversionFactorDTO getCoFactorByCountryCode(String countryCode) throws ResourceNotFoundException {

		Optional<ConversionFactorEntity> coFactorEntity = coFactorRepository.getCoFactorByCountryCode(countryCode);
		if (coFactorEntity.isPresent()) {
			return conversionFactorMapper.convertConversionFactorToConversionDTO(coFactorEntity.get());
		} else {
			throw new ResourceNotFoundException(countryCode);
		}

	}

	@Override
	public ConversionFactorDTO addCoFactor(ConversionFactorDTO coFactorDTO) throws ResourceCreationException {
		ConversionFactorEntity factorEntity = conversionFactorMapper
				.convertConversionFactorDTOToConversionFactor(coFactorDTO);
		try {
			return conversionFactorMapper
					.convertConversionFactorToConversionDTO(coFactorRepository.saveAndFlush(factorEntity));
		} catch (Exception divex) {
			throw new ResourceCreationException(divex);
		}
	}

	@Override
	public ConversionFactorDTO updateCoFactor(String countryCode, ConversionFactorDTO coFactorDTO)
			throws ResourceUpdateException, ResourceNotFoundException {

		Optional<ConversionFactorEntity> coFactorEntity = coFactorRepository.getCoFactorByCountryCode(countryCode);
		if (coFactorEntity.isPresent()) {
			ConversionFactorEntity conversionDetailsEntity = coFactorEntity.get();
			conversionDetailsEntity.setConversionFactor(coFactorDTO.getConversionFactor());
			conversionDetailsEntity.setCountryCode(coFactorDTO.getCountryCode());
			ConversionFactorEntity updatedCoFactor = coFactorRepository.save(conversionDetailsEntity);
			return conversionFactorMapper.convertConversionFactorToConversionDTO(updatedCoFactor);
		} else {
			throw new ResourceUpdateException(countryCode);
		}
	}

	public ConversionFactorDTO fallBackCall(String countryCode) {
		ConversionFactorDTO dfaultDto = new ConversionFactorDTO();
		dfaultDto.setId(100L);
		dfaultDto.setCountryCode(countryCode);
		dfaultDto.setConversionFactor(0.00);
		return dfaultDto;
	}
}
