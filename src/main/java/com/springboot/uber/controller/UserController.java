package com.springboot.uber.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.uber.exception.ResourceNotFoundException;
import com.springboot.uber.model.User;
import com.springboot.uber.repository.UserRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserRepository userRepository;

//	Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/users")
	public List<User> getAllUsers() {
//		logger.error("Fetched all users from DB using GET Request");
//		logger.trace("Fetched all users from DB using GET Request");
		return userRepository.findAll();
	}

	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserbyId(@PathVariable Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No user of id: " + id));
		return ResponseEntity.ok(user);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUserbyId(@PathVariable Long id, @RequestBody User userDetails) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No user of id: " + id));
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setAddrStreet(userDetails.getAddrStreet());
		user.setAddrLocality(userDetails.getAddrLocality());
		user.setAddrCity(userDetails.getAddrCity());
		user.setAddrState(userDetails.getAddrState());
		user.setAddrPin(userDetails.getAddrPin());
		user.setPhone(userDetails.getPhone());
		user.setEmail(userDetails.getEmail());
		user.setPassword(userDetails.getPassword());
		user.setAge(userDetails.getAge());
		user.setEmerContact(userDetails.getEmerContact());
		user.setSubscription(userDetails.getSubscription());
		user.setGender(userDetails.getGender());
		user.setAadhar(userDetails.getAadhar());
		user.setPayId(userDetails.getPayId());

		User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No user of id: " + id));
		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
