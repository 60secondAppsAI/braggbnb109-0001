package com.braggbnb109.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb109.domain.Experience;
import com.braggbnb109.dto.ExperienceDTO;
import com.braggbnb109.dto.ExperienceSearchDTO;
import com.braggbnb109.dto.ExperiencePageDTO;
import com.braggbnb109.dto.ExperienceConvertCriteriaDTO;
import com.braggbnb109.service.GenericService;
import com.braggbnb109.dto.common.RequestDTO;
import com.braggbnb109.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ExperienceService extends GenericService<Experience, Integer> {

	List<Experience> findAll();

	ResultDTO addExperience(ExperienceDTO experienceDTO, RequestDTO requestDTO);

	ResultDTO updateExperience(ExperienceDTO experienceDTO, RequestDTO requestDTO);

    Page<Experience> getAllExperiences(Pageable pageable);

    Page<Experience> getAllExperiences(Specification<Experience> spec, Pageable pageable);

	ResponseEntity<ExperiencePageDTO> getExperiences(ExperienceSearchDTO experienceSearchDTO);
	
	List<ExperienceDTO> convertExperiencesToExperienceDTOs(List<Experience> experiences, ExperienceConvertCriteriaDTO convertCriteria);

	ExperienceDTO getExperienceDTOById(Integer experienceId);







}





