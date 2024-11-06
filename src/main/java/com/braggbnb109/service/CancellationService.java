package com.braggbnb109.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb109.domain.Cancellation;
import com.braggbnb109.dto.CancellationDTO;
import com.braggbnb109.dto.CancellationSearchDTO;
import com.braggbnb109.dto.CancellationPageDTO;
import com.braggbnb109.dto.CancellationConvertCriteriaDTO;
import com.braggbnb109.service.GenericService;
import com.braggbnb109.dto.common.RequestDTO;
import com.braggbnb109.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CancellationService extends GenericService<Cancellation, Integer> {

	List<Cancellation> findAll();

	ResultDTO addCancellation(CancellationDTO cancellationDTO, RequestDTO requestDTO);

	ResultDTO updateCancellation(CancellationDTO cancellationDTO, RequestDTO requestDTO);

    Page<Cancellation> getAllCancellations(Pageable pageable);

    Page<Cancellation> getAllCancellations(Specification<Cancellation> spec, Pageable pageable);

	ResponseEntity<CancellationPageDTO> getCancellations(CancellationSearchDTO cancellationSearchDTO);
	
	List<CancellationDTO> convertCancellationsToCancellationDTOs(List<Cancellation> cancellations, CancellationConvertCriteriaDTO convertCriteria);

	CancellationDTO getCancellationDTOById(Integer cancellationId);







}





