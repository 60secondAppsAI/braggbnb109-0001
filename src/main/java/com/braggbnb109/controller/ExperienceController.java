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

import com.braggbnb109.domain.Experience;
import com.braggbnb109.dto.ExperienceDTO;
import com.braggbnb109.dto.ExperienceSearchDTO;
import com.braggbnb109.dto.ExperiencePageDTO;
import com.braggbnb109.service.ExperienceService;
import com.braggbnb109.dto.common.RequestDTO;
import com.braggbnb109.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/experience")
@RestController
public class ExperienceController {

	private final static Logger logger = LoggerFactory.getLogger(ExperienceController.class);

	@Autowired
	ExperienceService experienceService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Experience> getAll() {

		List<Experience> experiences = experienceService.findAll();
		
		return experiences;	
	}

	@GetMapping(value = "/{experienceId}")
	@ResponseBody
	public ExperienceDTO getExperience(@PathVariable Integer experienceId) {
		
		return (experienceService.getExperienceDTOById(experienceId));
	}

 	@RequestMapping(value = "/addExperience", method = RequestMethod.POST)
	public ResponseEntity<?> addExperience(@RequestBody ExperienceDTO experienceDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = experienceService.addExperience(experienceDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/experiences")
	public ResponseEntity<ExperiencePageDTO> getExperiences(ExperienceSearchDTO experienceSearchDTO) {
 
		return experienceService.getExperiences(experienceSearchDTO);
	}	

	@RequestMapping(value = "/updateExperience", method = RequestMethod.POST)
	public ResponseEntity<?> updateExperience(@RequestBody ExperienceDTO experienceDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = experienceService.updateExperience(experienceDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
