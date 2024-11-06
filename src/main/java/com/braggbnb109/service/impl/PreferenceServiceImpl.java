package com.braggbnb109.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.braggbnb109.dao.GenericDAO;
import com.braggbnb109.service.GenericService;
import com.braggbnb109.service.impl.GenericServiceImpl;
import com.braggbnb109.dao.PreferenceDAO;
import com.braggbnb109.domain.Preference;
import com.braggbnb109.dto.PreferenceDTO;
import com.braggbnb109.dto.PreferenceSearchDTO;
import com.braggbnb109.dto.PreferencePageDTO;
import com.braggbnb109.dto.PreferenceConvertCriteriaDTO;
import com.braggbnb109.dto.common.RequestDTO;
import com.braggbnb109.dto.common.ResultDTO;
import com.braggbnb109.service.PreferenceService;
import com.braggbnb109.util.ControllerUtils;





@Service
public class PreferenceServiceImpl extends GenericServiceImpl<Preference, Integer> implements PreferenceService {

    private final static Logger logger = LoggerFactory.getLogger(PreferenceServiceImpl.class);

	@Autowired
	PreferenceDAO preferenceDao;

	


	@Override
	public GenericDAO<Preference, Integer> getDAO() {
		return (GenericDAO<Preference, Integer>) preferenceDao;
	}
	
	public List<Preference> findAll () {
		List<Preference> preferences = preferenceDao.findAll();
		
		return preferences;	
		
	}

	public ResultDTO addPreference(PreferenceDTO preferenceDTO, RequestDTO requestDTO) {

		Preference preference = new Preference();

		preference.setPreferenceId(preferenceDTO.getPreferenceId());


		preference.setPreferenceType(preferenceDTO.getPreferenceType());


		preference.setValue(preferenceDTO.getValue());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		preference = preferenceDao.save(preference);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Preference> getAllPreferences(Pageable pageable) {
		return preferenceDao.findAll(pageable);
	}

	public Page<Preference> getAllPreferences(Specification<Preference> spec, Pageable pageable) {
		return preferenceDao.findAll(spec, pageable);
	}

	public ResponseEntity<PreferencePageDTO> getPreferences(PreferenceSearchDTO preferenceSearchDTO) {
	
			Integer preferenceId = preferenceSearchDTO.getPreferenceId(); 
 			String preferenceType = preferenceSearchDTO.getPreferenceType(); 
 			String value = preferenceSearchDTO.getValue(); 
 			String sortBy = preferenceSearchDTO.getSortBy();
			String sortOrder = preferenceSearchDTO.getSortOrder();
			String searchQuery = preferenceSearchDTO.getSearchQuery();
			Integer page = preferenceSearchDTO.getPage();
			Integer size = preferenceSearchDTO.getSize();

	        Specification<Preference> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, preferenceId, "preferenceId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, preferenceType, "preferenceType"); 
			
			spec = ControllerUtils.andIfNecessary(spec, value, "value"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("preferenceType")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("value")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Preference> preferences = this.getAllPreferences(spec, pageable);
		
		//System.out.println(String.valueOf(preferences.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(preferences.getTotalPages()));
		
		List<Preference> preferencesList = preferences.getContent();
		
		PreferenceConvertCriteriaDTO convertCriteria = new PreferenceConvertCriteriaDTO();
		List<PreferenceDTO> preferenceDTOs = this.convertPreferencesToPreferenceDTOs(preferencesList,convertCriteria);
		
		PreferencePageDTO preferencePageDTO = new PreferencePageDTO();
		preferencePageDTO.setPreferences(preferenceDTOs);
		preferencePageDTO.setTotalElements(preferences.getTotalElements());
		return ResponseEntity.ok(preferencePageDTO);
	}

	public List<PreferenceDTO> convertPreferencesToPreferenceDTOs(List<Preference> preferences, PreferenceConvertCriteriaDTO convertCriteria) {
		
		List<PreferenceDTO> preferenceDTOs = new ArrayList<PreferenceDTO>();
		
		for (Preference preference : preferences) {
			preferenceDTOs.add(convertPreferenceToPreferenceDTO(preference,convertCriteria));
		}
		
		return preferenceDTOs;

	}
	
	public PreferenceDTO convertPreferenceToPreferenceDTO(Preference preference, PreferenceConvertCriteriaDTO convertCriteria) {
		
		PreferenceDTO preferenceDTO = new PreferenceDTO();
		
		preferenceDTO.setPreferenceId(preference.getPreferenceId());

	
		preferenceDTO.setPreferenceType(preference.getPreferenceType());

	
		preferenceDTO.setValue(preference.getValue());

	

		
		return preferenceDTO;
	}

	public ResultDTO updatePreference(PreferenceDTO preferenceDTO, RequestDTO requestDTO) {
		
		Preference preference = preferenceDao.getById(preferenceDTO.getPreferenceId());

		preference.setPreferenceId(ControllerUtils.setValue(preference.getPreferenceId(), preferenceDTO.getPreferenceId()));

		preference.setPreferenceType(ControllerUtils.setValue(preference.getPreferenceType(), preferenceDTO.getPreferenceType()));

		preference.setValue(ControllerUtils.setValue(preference.getValue(), preferenceDTO.getValue()));



        preference = preferenceDao.save(preference);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public PreferenceDTO getPreferenceDTOById(Integer preferenceId) {
	
		Preference preference = preferenceDao.getById(preferenceId);
			
		
		PreferenceConvertCriteriaDTO convertCriteria = new PreferenceConvertCriteriaDTO();
		return(this.convertPreferenceToPreferenceDTO(preference,convertCriteria));
	}







}
