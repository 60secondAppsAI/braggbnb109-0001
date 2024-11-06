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
import com.braggbnb109.dao.PropertyDAO;
import com.braggbnb109.domain.Property;
import com.braggbnb109.dto.PropertyDTO;
import com.braggbnb109.dto.PropertySearchDTO;
import com.braggbnb109.dto.PropertyPageDTO;
import com.braggbnb109.dto.PropertyConvertCriteriaDTO;
import com.braggbnb109.dto.common.RequestDTO;
import com.braggbnb109.dto.common.ResultDTO;
import com.braggbnb109.service.PropertyService;
import com.braggbnb109.util.ControllerUtils;





@Service
public class PropertyServiceImpl extends GenericServiceImpl<Property, Integer> implements PropertyService {

    private final static Logger logger = LoggerFactory.getLogger(PropertyServiceImpl.class);

	@Autowired
	PropertyDAO propertyDao;

	


	@Override
	public GenericDAO<Property, Integer> getDAO() {
		return (GenericDAO<Property, Integer>) propertyDao;
	}
	
	public List<Property> findAll () {
		List<Property> propertys = propertyDao.findAll();
		
		return propertys;	
		
	}

	public ResultDTO addProperty(PropertyDTO propertyDTO, RequestDTO requestDTO) {

		Property property = new Property();

		property.setPropertyId(propertyDTO.getPropertyId());


		property.setAddress(propertyDTO.getAddress());


		property.setCity(propertyDTO.getCity());


		property.setState(propertyDTO.getState());


		property.setPostalCode(propertyDTO.getPostalCode());


		property.setCountry(propertyDTO.getCountry());


		property.setLatitude(propertyDTO.getLatitude());


		property.setLongitude(propertyDTO.getLongitude());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		property = propertyDao.save(property);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Property> getAllPropertys(Pageable pageable) {
		return propertyDao.findAll(pageable);
	}

	public Page<Property> getAllPropertys(Specification<Property> spec, Pageable pageable) {
		return propertyDao.findAll(spec, pageable);
	}

	public ResponseEntity<PropertyPageDTO> getPropertys(PropertySearchDTO propertySearchDTO) {
	
			Integer propertyId = propertySearchDTO.getPropertyId(); 
 			String address = propertySearchDTO.getAddress(); 
 			String city = propertySearchDTO.getCity(); 
 			String state = propertySearchDTO.getState(); 
 			String postalCode = propertySearchDTO.getPostalCode(); 
 			String country = propertySearchDTO.getCountry(); 
   			String sortBy = propertySearchDTO.getSortBy();
			String sortOrder = propertySearchDTO.getSortOrder();
			String searchQuery = propertySearchDTO.getSearchQuery();
			Integer page = propertySearchDTO.getPage();
			Integer size = propertySearchDTO.getSize();

	        Specification<Property> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, propertyId, "propertyId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, address, "address"); 
			
			spec = ControllerUtils.andIfNecessary(spec, city, "city"); 
			
			spec = ControllerUtils.andIfNecessary(spec, state, "state"); 
			
			spec = ControllerUtils.andIfNecessary(spec, postalCode, "postalCode"); 
			
			spec = ControllerUtils.andIfNecessary(spec, country, "country"); 
			
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("address")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("city")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("state")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("postalCode")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("country")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Property> propertys = this.getAllPropertys(spec, pageable);
		
		//System.out.println(String.valueOf(propertys.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(propertys.getTotalPages()));
		
		List<Property> propertysList = propertys.getContent();
		
		PropertyConvertCriteriaDTO convertCriteria = new PropertyConvertCriteriaDTO();
		List<PropertyDTO> propertyDTOs = this.convertPropertysToPropertyDTOs(propertysList,convertCriteria);
		
		PropertyPageDTO propertyPageDTO = new PropertyPageDTO();
		propertyPageDTO.setPropertys(propertyDTOs);
		propertyPageDTO.setTotalElements(propertys.getTotalElements());
		return ResponseEntity.ok(propertyPageDTO);
	}

	public List<PropertyDTO> convertPropertysToPropertyDTOs(List<Property> propertys, PropertyConvertCriteriaDTO convertCriteria) {
		
		List<PropertyDTO> propertyDTOs = new ArrayList<PropertyDTO>();
		
		for (Property property : propertys) {
			propertyDTOs.add(convertPropertyToPropertyDTO(property,convertCriteria));
		}
		
		return propertyDTOs;

	}
	
	public PropertyDTO convertPropertyToPropertyDTO(Property property, PropertyConvertCriteriaDTO convertCriteria) {
		
		PropertyDTO propertyDTO = new PropertyDTO();
		
		propertyDTO.setPropertyId(property.getPropertyId());

	
		propertyDTO.setAddress(property.getAddress());

	
		propertyDTO.setCity(property.getCity());

	
		propertyDTO.setState(property.getState());

	
		propertyDTO.setPostalCode(property.getPostalCode());

	
		propertyDTO.setCountry(property.getCountry());

	
		propertyDTO.setLatitude(property.getLatitude());

	
		propertyDTO.setLongitude(property.getLongitude());

	

		
		return propertyDTO;
	}

	public ResultDTO updateProperty(PropertyDTO propertyDTO, RequestDTO requestDTO) {
		
		Property property = propertyDao.getById(propertyDTO.getPropertyId());

		property.setPropertyId(ControllerUtils.setValue(property.getPropertyId(), propertyDTO.getPropertyId()));

		property.setAddress(ControllerUtils.setValue(property.getAddress(), propertyDTO.getAddress()));

		property.setCity(ControllerUtils.setValue(property.getCity(), propertyDTO.getCity()));

		property.setState(ControllerUtils.setValue(property.getState(), propertyDTO.getState()));

		property.setPostalCode(ControllerUtils.setValue(property.getPostalCode(), propertyDTO.getPostalCode()));

		property.setCountry(ControllerUtils.setValue(property.getCountry(), propertyDTO.getCountry()));

		property.setLatitude(ControllerUtils.setValue(property.getLatitude(), propertyDTO.getLatitude()));

		property.setLongitude(ControllerUtils.setValue(property.getLongitude(), propertyDTO.getLongitude()));



        property = propertyDao.save(property);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public PropertyDTO getPropertyDTOById(Integer propertyId) {
	
		Property property = propertyDao.getById(propertyId);
			
		
		PropertyConvertCriteriaDTO convertCriteria = new PropertyConvertCriteriaDTO();
		return(this.convertPropertyToPropertyDTO(property,convertCriteria));
	}







}
