package com.braggbnb109.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb109.domain.SupportTicket;
import com.braggbnb109.dto.SupportTicketDTO;
import com.braggbnb109.dto.SupportTicketSearchDTO;
import com.braggbnb109.dto.SupportTicketPageDTO;
import com.braggbnb109.dto.SupportTicketConvertCriteriaDTO;
import com.braggbnb109.service.GenericService;
import com.braggbnb109.dto.common.RequestDTO;
import com.braggbnb109.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SupportTicketService extends GenericService<SupportTicket, Integer> {

	List<SupportTicket> findAll();

	ResultDTO addSupportTicket(SupportTicketDTO supportTicketDTO, RequestDTO requestDTO);

	ResultDTO updateSupportTicket(SupportTicketDTO supportTicketDTO, RequestDTO requestDTO);

    Page<SupportTicket> getAllSupportTickets(Pageable pageable);

    Page<SupportTicket> getAllSupportTickets(Specification<SupportTicket> spec, Pageable pageable);

	ResponseEntity<SupportTicketPageDTO> getSupportTickets(SupportTicketSearchDTO supportTicketSearchDTO);
	
	List<SupportTicketDTO> convertSupportTicketsToSupportTicketDTOs(List<SupportTicket> supportTickets, SupportTicketConvertCriteriaDTO convertCriteria);

	SupportTicketDTO getSupportTicketDTOById(Integer supportTicketId);







}





