package com.braggbnb109.dao;

import java.util.List;

import com.braggbnb109.dao.GenericDAO;
import com.braggbnb109.domain.Photo;





public interface PhotoDAO extends GenericDAO<Photo, Integer> {
  
	List<Photo> findAll();
	






}


