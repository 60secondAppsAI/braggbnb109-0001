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
import com.braggbnb109.dao.ExperienceDAO;
import com.braggbnb109.domain.Experience;
import com.braggbnb109.dto.ExperienceDTO;
import com.braggbnb109.dto.ExperienceSearchDTO;
import com.braggbnb109.dto.ExperiencePageDTO;
import com.braggbnb109.dto.ExperienceConvertCriteriaDTO;
import com.braggbnb109.dto.common.RequestDTO;
import com.braggbnb109.dto.common.ResultDTO;
import com.braggbnb109.service.ExperienceService;
import com.braggbnb109.util.ControllerUtils;





@Service
public class ExperienceServiceImpl extends GenericServiceImpl<Experience, Integer> implements ExperienceService {

    private final static Logger logger = LoggerFactory.getLogger(ExperienceServiceImpl.class);

	@Autowired
	ExperienceDAO experienceDao;

	


	@Override
	public GenericDAO<Experience, Integer> getDAO() {
		return (GenericDAO<Experience, Integer>) experienceDao;
	}
	
	public List<Experience> findAll () {
		List<Experience> experiences = experienceDao.findAll();
		
		return experiences;	
		
	}

	public ResultDTO addExperience(ExperienceDTO experienceDTO, RequestDTO requestDTO) {

		Experience experience = new Experience();

		experience.setExperienceId(experienceDTO.getExperienceId());


		experience.setTitle(experienceDTO.getTitle());


		experience.setDescription(experienceDTO.getDescription());


		experience.setPrice(experienceDTO.getPrice());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		experience = experienceDao.save(experience);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Experience> getAllExperiences(Pageable pageable) {
		return experienceDao.findAll(pageable);
	}

	public Page<Experience> getAllExperiences(Specification<Experience> spec, Pageable pageable) {
		return experienceDao.findAll(spec, pageable);
	}

	public ResponseEntity<ExperiencePageDTO> getExperiences(ExperienceSearchDTO experienceSearchDTO) {
	
			Integer experienceId = experienceSearchDTO.getExperienceId(); 
 			String title = experienceSearchDTO.getTitle(); 
 			String description = experienceSearchDTO.getDescription(); 
  			String sortBy = experienceSearchDTO.getSortBy();
			String sortOrder = experienceSearchDTO.getSortOrder();
			String searchQuery = experienceSearchDTO.getSearchQuery();
			Integer page = experienceSearchDTO.getPage();
			Integer size = experienceSearchDTO.getSize();

	        Specification<Experience> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, experienceId, "experienceId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, title, "title"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("title")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Experience> experiences = this.getAllExperiences(spec, pageable);
		
		//System.out.println(String.valueOf(experiences.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(experiences.getTotalPages()));
		
		List<Experience> experiencesList = experiences.getContent();
		
		ExperienceConvertCriteriaDTO convertCriteria = new ExperienceConvertCriteriaDTO();
		List<ExperienceDTO> experienceDTOs = this.convertExperiencesToExperienceDTOs(experiencesList,convertCriteria);
		
		ExperiencePageDTO experiencePageDTO = new ExperiencePageDTO();
		experiencePageDTO.setExperiences(experienceDTOs);
		experiencePageDTO.setTotalElements(experiences.getTotalElements());
		return ResponseEntity.ok(experiencePageDTO);
	}

	public List<ExperienceDTO> convertExperiencesToExperienceDTOs(List<Experience> experiences, ExperienceConvertCriteriaDTO convertCriteria) {
		
		List<ExperienceDTO> experienceDTOs = new ArrayList<ExperienceDTO>();
		
		for (Experience experience : experiences) {
			experienceDTOs.add(convertExperienceToExperienceDTO(experience,convertCriteria));
		}
		
		return experienceDTOs;

	}
	
	public ExperienceDTO convertExperienceToExperienceDTO(Experience experience, ExperienceConvertCriteriaDTO convertCriteria) {
		
		ExperienceDTO experienceDTO = new ExperienceDTO();
		
		experienceDTO.setExperienceId(experience.getExperienceId());

	
		experienceDTO.setTitle(experience.getTitle());

	
		experienceDTO.setDescription(experience.getDescription());

	
		experienceDTO.setPrice(experience.getPrice());

	

		
		return experienceDTO;
	}

	public ResultDTO updateExperience(ExperienceDTO experienceDTO, RequestDTO requestDTO) {
		
		Experience experience = experienceDao.getById(experienceDTO.getExperienceId());

		experience.setExperienceId(ControllerUtils.setValue(experience.getExperienceId(), experienceDTO.getExperienceId()));

		experience.setTitle(ControllerUtils.setValue(experience.getTitle(), experienceDTO.getTitle()));

		experience.setDescription(ControllerUtils.setValue(experience.getDescription(), experienceDTO.getDescription()));

		experience.setPrice(ControllerUtils.setValue(experience.getPrice(), experienceDTO.getPrice()));



        experience = experienceDao.save(experience);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ExperienceDTO getExperienceDTOById(Integer experienceId) {
	
		Experience experience = experienceDao.getById(experienceId);
			
		
		ExperienceConvertCriteriaDTO convertCriteria = new ExperienceConvertCriteriaDTO();
		return(this.convertExperienceToExperienceDTO(experience,convertCriteria));
	}







}
