package com.braggbnb109.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class PropertyDTO {

	private Integer propertyId;

	private String address;

	private String city;

	private String state;

	private String postalCode;

	private String country;

	private Float latitude;

	private Float longitude;






}
