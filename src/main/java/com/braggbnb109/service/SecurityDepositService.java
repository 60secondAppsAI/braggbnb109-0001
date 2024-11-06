package com.braggbnb109.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb109.domain.SecurityDeposit;
import com.braggbnb109.dto.SecurityDepositDTO;
import com.braggbnb109.dto.SecurityDepositSearchDTO;
import com.braggbnb109.dto.SecurityDepositPageDTO;
import com.braggbnb109.dto.SecurityDepositConvertCriteriaDTO;
import com.braggbnb109.service.GenericService;
import com.braggbnb109.dto.common.RequestDTO;
import com.braggbnb109.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SecurityDepositService extends GenericService<SecurityDeposit, Integer> {

	List<SecurityDeposit> findAll();

	ResultDTO addSecurityDeposit(SecurityDepositDTO securityDepositDTO, RequestDTO requestDTO);

	ResultDTO updateSecurityDeposit(SecurityDepositDTO securityDepositDTO, RequestDTO requestDTO);

    Page<SecurityDeposit> getAllSecurityDeposits(Pageable pageable);

    Page<SecurityDeposit> getAllSecurityDeposits(Specification<SecurityDeposit> spec, Pageable pageable);

	ResponseEntity<SecurityDepositPageDTO> getSecurityDeposits(SecurityDepositSearchDTO securityDepositSearchDTO);
	
	List<SecurityDepositDTO> convertSecurityDepositsToSecurityDepositDTOs(List<SecurityDeposit> securityDeposits, SecurityDepositConvertCriteriaDTO convertCriteria);

	SecurityDepositDTO getSecurityDepositDTOById(Integer securityDepositId);







}





