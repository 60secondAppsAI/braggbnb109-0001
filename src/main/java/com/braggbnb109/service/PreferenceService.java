package com.braggbnb109.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb109.domain.Preference;
import com.braggbnb109.dto.PreferenceDTO;
import com.braggbnb109.dto.PreferenceSearchDTO;
import com.braggbnb109.dto.PreferencePageDTO;
import com.braggbnb109.dto.PreferenceConvertCriteriaDTO;
import com.braggbnb109.service.GenericService;
import com.braggbnb109.dto.common.RequestDTO;
import com.braggbnb109.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PreferenceService extends GenericService<Preference, Integer> {

	List<Preference> findAll();

	ResultDTO addPreference(PreferenceDTO preferenceDTO, RequestDTO requestDTO);

	ResultDTO updatePreference(PreferenceDTO preferenceDTO, RequestDTO requestDTO);

    Page<Preference> getAllPreferences(Pageable pageable);

    Page<Preference> getAllPreferences(Specification<Preference> spec, Pageable pageable);

	ResponseEntity<PreferencePageDTO> getPreferences(PreferenceSearchDTO preferenceSearchDTO);
	
	List<PreferenceDTO> convertPreferencesToPreferenceDTOs(List<Preference> preferences, PreferenceConvertCriteriaDTO convertCriteria);

	PreferenceDTO getPreferenceDTOById(Integer preferenceId);







}





