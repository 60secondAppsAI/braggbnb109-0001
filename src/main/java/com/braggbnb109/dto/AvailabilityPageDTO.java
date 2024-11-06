package com.braggbnb109.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AvailabilityPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<AvailabilityDTO> availabilitys;
}





