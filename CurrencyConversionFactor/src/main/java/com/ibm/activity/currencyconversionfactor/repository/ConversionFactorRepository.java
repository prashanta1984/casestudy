package com.ibm.activity.currencyconversionfactor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.activity.currencyconversionfactor.domain.ConversionFactorEntity;
import com.ibm.activity.currencyconversionfactor.exception.ResourceNotFoundException;

@Repository
public interface ConversionFactorRepository extends JpaRepository<ConversionFactorEntity, Long> {

	Optional <ConversionFactorEntity> getCoFactorByCountryCode(String countryCode) throws ResourceNotFoundException;
		
}
