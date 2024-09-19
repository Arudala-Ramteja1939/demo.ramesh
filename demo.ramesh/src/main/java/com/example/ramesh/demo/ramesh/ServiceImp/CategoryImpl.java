package com.example.ramesh.demo.ramesh.ServiceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ramesh.demo.ramesh.Entity.Category;
import com.example.ramesh.demo.ramesh.Repository.CategoryRepo;
import com.example.ramesh.demo.ramesh.Service.CategoryService;

@Service
public class CategoryImpl implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public Category save(Category category) {
		// TODO Auto-generated method stub
		return categoryRepo.save(category);
	}

	@Override
	public Category update(Category category, Long id) {
		Category category1=categoryRepo.findById(id).get();

		if(category1!=null)
		{
			if(category.getName()!=null && !"".equalsIgnoreCase(category.getName()))
			{
				category1.setName(category.getName());
			}
			if(category.getDescription()!=null && !"".equalsIgnoreCase(category.getDescription()))
			{
				category1.setDescription(category.getDescription());
			}

			categoryRepo.save(category1);
		}
		return category1;
	}

	@Override
	public Category getById(Long id) {

		Category category=categoryRepo.findById(id).get();
		if(category!=null)
		{
			return categoryRepo.findById(id).get();
		}
		else
		{
			return null;
		}
	}
		@Override
		public List<Category> getAll() {
			// TODO Auto-generated method stub
			return categoryRepo.findAll();
		}

		@Override
		public String deleteById(Long id) {
			Category category=categoryRepo.findById(id).get();
			if(category!=null)
			{
				categoryRepo.deleteById(id);
			}
			return "Successfully deleted";
		}

		

	}
