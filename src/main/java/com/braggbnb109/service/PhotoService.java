package com.braggbnb109.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb109.domain.Photo;
import com.braggbnb109.dto.PhotoDTO;
import com.braggbnb109.dto.PhotoSearchDTO;
import com.braggbnb109.dto.PhotoPageDTO;
import com.braggbnb109.dto.PhotoConvertCriteriaDTO;
import com.braggbnb109.service.GenericService;
import com.braggbnb109.dto.common.RequestDTO;
import com.braggbnb109.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PhotoService extends GenericService<Photo, Integer> {

	List<Photo> findAll();

	ResultDTO addPhoto(PhotoDTO photoDTO, RequestDTO requestDTO);

	ResultDTO updatePhoto(PhotoDTO photoDTO, RequestDTO requestDTO);

    Page<Photo> getAllPhotos(Pageable pageable);

    Page<Photo> getAllPhotos(Specification<Photo> spec, Pageable pageable);

	ResponseEntity<PhotoPageDTO> getPhotos(PhotoSearchDTO photoSearchDTO);
	
	List<PhotoDTO> convertPhotosToPhotoDTOs(List<Photo> photos, PhotoConvertCriteriaDTO convertCriteria);

	PhotoDTO getPhotoDTOById(Integer photoId);







}





