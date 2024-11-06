package com.braggbnb109.dao;

import java.util.List;

import com.braggbnb109.dao.GenericDAO;
import com.braggbnb109.domain.Booking;





public interface BookingDAO extends GenericDAO<Booking, Integer> {
  
	List<Booking> findAll();
	






}


