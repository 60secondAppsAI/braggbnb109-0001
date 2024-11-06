package com.braggbnb109.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.braggbnb109.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.braggbnb109.domain.PropertyType;
import com.braggbnb109.dto.PropertyTypeDTO;
import com.braggbnb109.dto.PropertyTypeSearchDTO;
import com.braggbnb109.dto.PropertyTypePageDTO;
import com.braggbnb109.service.PropertyTypeService;
import com.braggbnb109.dto.common.RequestDTO;
import com.braggbnb109.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/propertyType")
@RestController
public class PropertyTypeController {

	private final static Logger logger = LoggerFactory.getLogger(PropertyTypeController.class);

	@Autowired
	PropertyTypeService propertyTypeService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<PropertyType> getAll() {

		List<PropertyType> propertyTypes = propertyTypeService.findAll();
		
		return propertyTypes;	
	}

	@GetMapping(value = "/{propertyTypeId}")
	@ResponseBody
	public PropertyTypeDTO getPropertyType(@PathVariable Integer propertyTypeId) {
		
		return (propertyTypeService.getPropertyTypeDTOById(propertyTypeId));
	}

 	@RequestMapping(value = "/addPropertyType", method = RequestMethod.POST)
	public ResponseEntity<?> addPropertyType(@RequestBody PropertyTypeDTO propertyTypeDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = propertyTypeService.addPropertyType(propertyTypeDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/propertyTypes")
	public ResponseEntity<PropertyTypePageDTO> getPropertyTypes(PropertyTypeSearchDTO propertyTypeSearchDTO) {
 
		return propertyTypeService.getPropertyTypes(propertyTypeSearchDTO);
	}	

	@RequestMapping(value = "/updatePropertyType", method = RequestMethod.POST)
	public ResponseEntity<?> updatePropertyType(@RequestBody PropertyTypeDTO propertyTypeDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = propertyTypeService.updatePropertyType(propertyTypeDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
