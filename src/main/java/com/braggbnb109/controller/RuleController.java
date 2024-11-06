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

import com.braggbnb109.domain.Rule;
import com.braggbnb109.dto.RuleDTO;
import com.braggbnb109.dto.RuleSearchDTO;
import com.braggbnb109.dto.RulePageDTO;
import com.braggbnb109.service.RuleService;
import com.braggbnb109.dto.common.RequestDTO;
import com.braggbnb109.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/rule")
@RestController
public class RuleController {

	private final static Logger logger = LoggerFactory.getLogger(RuleController.class);

	@Autowired
	RuleService ruleService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Rule> getAll() {

		List<Rule> rules = ruleService.findAll();
		
		return rules;	
	}

	@GetMapping(value = "/{ruleId}")
	@ResponseBody
	public RuleDTO getRule(@PathVariable Integer ruleId) {
		
		return (ruleService.getRuleDTOById(ruleId));
	}

 	@RequestMapping(value = "/addRule", method = RequestMethod.POST)
	public ResponseEntity<?> addRule(@RequestBody RuleDTO ruleDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = ruleService.addRule(ruleDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/rules")
	public ResponseEntity<RulePageDTO> getRules(RuleSearchDTO ruleSearchDTO) {
 
		return ruleService.getRules(ruleSearchDTO);
	}	

	@RequestMapping(value = "/updateRule", method = RequestMethod.POST)
	public ResponseEntity<?> updateRule(@RequestBody RuleDTO ruleDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = ruleService.updateRule(ruleDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
