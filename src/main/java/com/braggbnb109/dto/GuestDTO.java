package com.braggbnb109.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class GuestDTO {

	private Integer guestId;

	private String name;

	private String email;

	private String phoneNumber;

	private Date registrationDate;






}
