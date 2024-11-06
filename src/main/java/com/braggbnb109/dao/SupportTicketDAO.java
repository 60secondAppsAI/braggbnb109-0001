package com.braggbnb109.dao;

import java.util.List;

import com.braggbnb109.dao.GenericDAO;
import com.braggbnb109.domain.SupportTicket;





public interface SupportTicketDAO extends GenericDAO<SupportTicket, Integer> {
  
	List<SupportTicket> findAll();
	






}


