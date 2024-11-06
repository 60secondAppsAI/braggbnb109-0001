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

import com.braggbnb109.domain.Preference;
import com.braggbnb109.dto.PreferenceDTO;
import com.braggbnb109.dto.PreferenceSearchDTO;
import com.braggbnb109.dto.PreferencePageDTO;
import com.braggbnb109.service.PreferenceService;
import com.braggbnb109.dto.common.RequestDTO;
import com.braggbnb109.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/preference")
@RestController
public class PreferenceController {

	private final static Logger logger = LoggerFactory.getLogger(PreferenceController.class);

	@Autowired
	PreferenceService preferenceService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Preference> getAll() {

		List<Preference> preferences = preferenceService.findAll();
		
		return preferences;	
	}

	@GetMapping(value = "/{preferenceId}")
	@ResponseBody
	public PreferenceDTO getPreference(@PathVariable Integer preferenceId) {
		
		return (preferenceService.getPreferenceDTOById(preferenceId));
	}

 	@RequestMapping(value = "/addPreference", method = RequestMethod.POST)
	public ResponseEntity<?> addPreference(@RequestBody PreferenceDTO preferenceDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = preferenceService.addPreference(preferenceDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/preferences")
	public ResponseEntity<PreferencePageDTO> getPreferences(PreferenceSearchDTO preferenceSearchDTO) {
 
		return preferenceService.getPreferences(preferenceSearchDTO);
	}	

	@RequestMapping(value = "/updatePreference", method = RequestMethod.POST)
	public ResponseEntity<?> updatePreference(@RequestBody PreferenceDTO preferenceDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = preferenceService.updatePreference(preferenceDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
