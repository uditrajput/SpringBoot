package com.mypack.restfullwebservice.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService userDaoService;

	@GetMapping("/users")
	public List<User> reterveAllUser(){
		return userDaoService.findAll();
	}
	
	@GetMapping("/users/{id}")
	private Resource<User> reterveUser(@PathVariable int id) {
		User user = userDaoService.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("Id - "+id);
		}
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkto = linkTo(methodOn(this.getClass()).reterveAllUser());
		resource.add(linkto.withRel("all-users"));
		return resource;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User saveUser = userDaoService.save(user);
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest().path("{/id}")
		.buildAndExpand(saveUser.getId())
		.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	private ResponseEntity<Object> deleteUser(@PathVariable int id) {
		User user = userDaoService.deleteById(id);
		if(user == null) {
			throw new UserNotFoundException("Id - "+id);
		}
		return ResponseEntity.noContent().build();
	}
	
}
