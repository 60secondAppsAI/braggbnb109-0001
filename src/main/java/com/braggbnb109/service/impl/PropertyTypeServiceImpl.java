package com.braggbnb109.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.braggbnb109.dao.GenericDAO;
import com.braggbnb109.service.GenericService;
import com.braggbnb109.service.impl.GenericServiceImpl;
import com.braggbnb109.dao.PropertyTypeDAO;
import com.braggbnb109.domain.PropertyType;
import com.braggbnb109.dto.PropertyTypeDTO;
import com.braggbnb109.dto.PropertyTypeSearchDTO;
import com.braggbnb109.dto.PropertyTypePageDTO;
import com.braggbnb109.dto.PropertyTypeConvertCriteriaDTO;
import com.braggbnb109.dto.common.RequestDTO;
import com.braggbnb109.dto.common.ResultDTO;
import com.braggbnb109.service.PropertyTypeService;
import com.braggbnb109.util.ControllerUtils;





@Service
public class PropertyTypeServiceImpl extends GenericServiceImpl<PropertyType, Integer> implements PropertyTypeService {

    private final static Logger logger = LoggerFactory.getLogger(PropertyTypeServiceImpl.class);

	@Autowired
	PropertyTypeDAO propertyTypeDao;

	


	@Override
	public GenericDAO<PropertyType, Integer> getDAO() {
		return (GenericDAO<PropertyType, Integer>) propertyTypeDao;
	}
	
	public List<PropertyType> findAll () {
		List<PropertyType> propertyTypes = propertyTypeDao.findAll();
		
		return propertyTypes;	
		
	}

	public ResultDTO addPropertyType(PropertyTypeDTO propertyTypeDTO, RequestDTO requestDTO) {

		PropertyType propertyType = new PropertyType();

		propertyType.setPropertyTypeId(propertyTypeDTO.getPropertyTypeId());


		propertyType.setName(propertyTypeDTO.getName());


		propertyType.setDescription(propertyTypeDTO.getDescription());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		propertyType = propertyTypeDao.save(propertyType);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<PropertyType> getAllPropertyTypes(Pageable pageable) {
		return propertyTypeDao.findAll(pageable);
	}

	public Page<PropertyType> getAllPropertyTypes(Specification<PropertyType> spec, Pageable pageable) {
		return propertyTypeDao.findAll(spec, pageable);
	}

	public ResponseEntity<PropertyTypePageDTO> getPropertyTypes(PropertyTypeSearchDTO propertyTypeSearchDTO) {
	
			Integer propertyTypeId = propertyTypeSearchDTO.getPropertyTypeId(); 
 			String name = propertyTypeSearchDTO.getName(); 
 			String description = propertyTypeSearchDTO.getDescription(); 
 			String sortBy = propertyTypeSearchDTO.getSortBy();
			String sortOrder = propertyTypeSearchDTO.getSortOrder();
			String searchQuery = propertyTypeSearchDTO.getSearchQuery();
			Integer page = propertyTypeSearchDTO.getPage();
			Integer size = propertyTypeSearchDTO.getSize();

	        Specification<PropertyType> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, propertyTypeId, "propertyTypeId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<PropertyType> propertyTypes = this.getAllPropertyTypes(spec, pageable);
		
		//System.out.println(String.valueOf(propertyTypes.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(propertyTypes.getTotalPages()));
		
		List<PropertyType> propertyTypesList = propertyTypes.getContent();
		
		PropertyTypeConvertCriteriaDTO convertCriteria = new PropertyTypeConvertCriteriaDTO();
		List<PropertyTypeDTO> propertyTypeDTOs = this.convertPropertyTypesToPropertyTypeDTOs(propertyTypesList,convertCriteria);
		
		PropertyTypePageDTO propertyTypePageDTO = new PropertyTypePageDTO();
		propertyTypePageDTO.setPropertyTypes(propertyTypeDTOs);
		propertyTypePageDTO.setTotalElements(propertyTypes.getTotalElements());
		return ResponseEntity.ok(propertyTypePageDTO);
	}

	public List<PropertyTypeDTO> convertPropertyTypesToPropertyTypeDTOs(List<PropertyType> propertyTypes, PropertyTypeConvertCriteriaDTO convertCriteria) {
		
		List<PropertyTypeDTO> propertyTypeDTOs = new ArrayList<PropertyTypeDTO>();
		
		for (PropertyType propertyType : propertyTypes) {
			propertyTypeDTOs.add(convertPropertyTypeToPropertyTypeDTO(propertyType,convertCriteria));
		}
		
		return propertyTypeDTOs;

	}
	
	public PropertyTypeDTO convertPropertyTypeToPropertyTypeDTO(PropertyType propertyType, PropertyTypeConvertCriteriaDTO convertCriteria) {
		
		PropertyTypeDTO propertyTypeDTO = new PropertyTypeDTO();
		
		propertyTypeDTO.setPropertyTypeId(propertyType.getPropertyTypeId());

	
		propertyTypeDTO.setName(propertyType.getName());

	
		propertyTypeDTO.setDescription(propertyType.getDescription());

	

		
		return propertyTypeDTO;
	}

	public ResultDTO updatePropertyType(PropertyTypeDTO propertyTypeDTO, RequestDTO requestDTO) {
		
		PropertyType propertyType = propertyTypeDao.getById(propertyTypeDTO.getPropertyTypeId());

		propertyType.setPropertyTypeId(ControllerUtils.setValue(propertyType.getPropertyTypeId(), propertyTypeDTO.getPropertyTypeId()));

		propertyType.setName(ControllerUtils.setValue(propertyType.getName(), propertyTypeDTO.getName()));

		propertyType.setDescription(ControllerUtils.setValue(propertyType.getDescription(), propertyTypeDTO.getDescription()));



        propertyType = propertyTypeDao.save(propertyType);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public PropertyTypeDTO getPropertyTypeDTOById(Integer propertyTypeId) {
	
		PropertyType propertyType = propertyTypeDao.getById(propertyTypeId);
			
		
		PropertyTypeConvertCriteriaDTO convertCriteria = new PropertyTypeConvertCriteriaDTO();
		return(this.convertPropertyTypeToPropertyTypeDTO(propertyType,convertCriteria));
	}







}
