package com.braggbnb109.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb109.domain.Guest;
import com.braggbnb109.dto.GuestDTO;
import com.braggbnb109.dto.GuestSearchDTO;
import com.braggbnb109.dto.GuestPageDTO;
import com.braggbnb109.dto.GuestConvertCriteriaDTO;
import com.braggbnb109.service.GenericService;
import com.braggbnb109.dto.common.RequestDTO;
import com.braggbnb109.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface GuestService extends GenericService<Guest, Integer> {

	List<Guest> findAll();

	ResultDTO addGuest(GuestDTO guestDTO, RequestDTO requestDTO);

	ResultDTO updateGuest(GuestDTO guestDTO, RequestDTO requestDTO);

    Page<Guest> getAllGuests(Pageable pageable);

    Page<Guest> getAllGuests(Specification<Guest> spec, Pageable pageable);

	ResponseEntity<GuestPageDTO> getGuests(GuestSearchDTO guestSearchDTO);
	
	List<GuestDTO> convertGuestsToGuestDTOs(List<Guest> guests, GuestConvertCriteriaDTO convertCriteria);

	GuestDTO getGuestDTOById(Integer guestId);







}





