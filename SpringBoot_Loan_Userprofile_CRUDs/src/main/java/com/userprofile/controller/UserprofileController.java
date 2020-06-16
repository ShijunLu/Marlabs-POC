package com.userprofile.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.userprofile.model.Userprofile;
import com.userprofile.service.UserprofileService;

@RestController
public class UserprofileController {
	@Autowired
	private UserprofileService userprofService;

//	return userprofiles with given type
	@GetMapping("/userprofiles/type/{userype}/users")
	public List<Userprofile> retrieveByType(@PathVariable String userype) {
		return userprofService.retrieveUserprofileByType(userype);
	}
	@GetMapping("/userprofiles/users")
	public List<Userprofile> retrieveUserprofiles() {
		return userprofService.retrieveUserprofiles();
	}

	@GetMapping("/users/{name}/userprofile/{id}")
	public Userprofile retrieveUserprofile(@PathVariable String name, @PathVariable int id) {
		return userprofService.retrieveUserprofile(id);
	}

	@PostMapping("/users/{name}/userprofile")
	ResponseEntity<?> addUserprof(@PathVariable String name, @RequestBody Userprofile userprof) {
		Userprofile createdUserprof = userprofService.addUserprofile(name, userprof.getType(), userprof.getPhone(),
				userprof.getEmail(), userprof.getSsn(), userprof.getAccount(), userprof.getRecentLogin(),
				userprof.getLocation());
		if (createdUserprof == null) {
			return ResponseEntity.noContent().build();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdUserprof.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/users/{name}/userprofile/{id}")
	public ResponseEntity<Userprofile> updateUserprof(@PathVariable String name, @PathVariable int id,
			@RequestBody Userprofile userprof) {
		System.out.println(userprof);
		userprofService.updateUserprofile(userprof);
		return new ResponseEntity<Userprofile>(userprof, HttpStatus.OK);
	}

	@DeleteMapping("/users/{name}/userprofile/{id}")
	public ResponseEntity<Void> deleteUserprof(@PathVariable String name, @PathVariable int id) {
		Userprofile userprof = userprofService.deleteById(id);
		System. out.println(userprof);
		if(userprof != null)
			return ResponseEntity.noContent().build();	
		return ResponseEntity.notFound().build();  
	}
	
}
