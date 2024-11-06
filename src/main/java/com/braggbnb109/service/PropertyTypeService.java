package com.braggbnb109.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb109.domain.PropertyType;
import com.braggbnb109.dto.PropertyTypeDTO;
import com.braggbnb109.dto.PropertyTypeSearchDTO;
import com.braggbnb109.dto.PropertyTypePageDTO;
import com.braggbnb109.dto.PropertyTypeConvertCriteriaDTO;
import com.braggbnb109.service.GenericService;
import com.braggbnb109.dto.common.RequestDTO;
import com.braggbnb109.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PropertyTypeService extends GenericService<PropertyType, Integer> {

	List<PropertyType> findAll();

	ResultDTO addPropertyType(PropertyTypeDTO propertyTypeDTO, RequestDTO requestDTO);

	ResultDTO updatePropertyType(PropertyTypeDTO propertyTypeDTO, RequestDTO requestDTO);

    Page<PropertyType> getAllPropertyTypes(Pageable pageable);

    Page<PropertyType> getAllPropertyTypes(Specification<PropertyType> spec, Pageable pageable);

	ResponseEntity<PropertyTypePageDTO> getPropertyTypes(PropertyTypeSearchDTO propertyTypeSearchDTO);
	
	List<PropertyTypeDTO> convertPropertyTypesToPropertyTypeDTOs(List<PropertyType> propertyTypes, PropertyTypeConvertCriteriaDTO convertCriteria);

	PropertyTypeDTO getPropertyTypeDTOById(Integer propertyTypeId);







}





