package com.devrogerfer.jparepository.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devrogerfer.jparepository.entities.User;
import com.devrogerfer.jparepository.repositories.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	// injecting an object dependency
	@Autowired
	private UserRepository repository;

	// creating end-point '/users'
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> result = repository.findAll();
		return ResponseEntity.ok(result);
	}

	// creating end-point '/page'
	// to return a paginated list of users
	@GetMapping(value = "/page")
	public ResponseEntity<Page<User>> findAll(Pageable pageable) {
		Page<User> result = repository.findAll(pageable);
		return ResponseEntity.ok(result);
	}

	// looking for users who have salaries between
	// the minimum and maximum
	@GetMapping(value = "/search-salary")
	public ResponseEntity<Page<User>> searchBySalaryBetween(
			@RequestParam(defaultValue = "0") Double minSalary, 
			@RequestParam(defaultValue="1000000000000") Double maxSalary, Pageable pageable) {
				Page<User> result = repository.findBySalaryBetween(minSalary, maxSalary, pageable);
				return ResponseEntity.ok(result);
			}
	
	// search by name
	@GetMapping(value = "/search-name")
	public ResponseEntity<Page<User>> searchByName(
			@RequestParam(defaultValue = "") String name, Pageable pageable) {
				Page<User> result = repository.findByNameContainingIgnoreCase(name, pageable);
				return ResponseEntity.ok(result);
			}	

}
