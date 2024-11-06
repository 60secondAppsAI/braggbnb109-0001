package com.braggbnb109.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb109.domain.Rule;
import com.braggbnb109.dto.RuleDTO;
import com.braggbnb109.dto.RuleSearchDTO;
import com.braggbnb109.dto.RulePageDTO;
import com.braggbnb109.dto.RuleConvertCriteriaDTO;
import com.braggbnb109.service.GenericService;
import com.braggbnb109.dto.common.RequestDTO;
import com.braggbnb109.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface RuleService extends GenericService<Rule, Integer> {

	List<Rule> findAll();

	ResultDTO addRule(RuleDTO ruleDTO, RequestDTO requestDTO);

	ResultDTO updateRule(RuleDTO ruleDTO, RequestDTO requestDTO);

    Page<Rule> getAllRules(Pageable pageable);

    Page<Rule> getAllRules(Specification<Rule> spec, Pageable pageable);

	ResponseEntity<RulePageDTO> getRules(RuleSearchDTO ruleSearchDTO);
	
	List<RuleDTO> convertRulesToRuleDTOs(List<Rule> rules, RuleConvertCriteriaDTO convertCriteria);

	RuleDTO getRuleDTOById(Integer ruleId);







}





