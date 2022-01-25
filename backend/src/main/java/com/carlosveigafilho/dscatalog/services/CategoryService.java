package com.carlosveigafilho.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlosveigafilho.dscatalog.dto.CategoryDTO;
import com.carlosveigafilho.dscatalog.entities.Category;
import com.carlosveigafilho.dscatalog.repositories.CategoryRepository;
import com.carlosveigafilho.dscatalog.services.exceptions.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly=true)
	public List<CategoryDTO> findAll(){
		List<Category> list = repository.findAll();
		return list.stream().map(obj -> new CategoryDTO(obj)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly=true)
	public CategoryDTO findById(Long id){
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		return new CategoryDTO(entity);
// @formatter:on

	}

	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new CategoryDTO(entity);
	}
}
