package com.vicento14.api_template_spring.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vicento14.api_template_spring.models.UserAccounts;
import com.vicento14.api_template_spring.repository.UserAccountsRepository;

@CrossOrigin(origins = "*")
@RestController
public class UserAccountsController {

	@Autowired
	private UserAccountsRepository userAccountsRepository;

	@GetMapping("/")
	public String index() {
		String htmlElement = "<p>";
		htmlElement += "API TEMPLATE SPRING (localhost:8081)";
		htmlElement += "</p>";
		htmlElement += "<p>";
		htmlElement += "Developed By : Vince Dale D. Alcantara";
		htmlElement += "</p>";
		htmlElement += "<p>";
		htmlElement += "Version 1.0.0";
		htmlElement += "</p>";
		return htmlElement;
	}

	@GetMapping("/UserAccounts")
	public @ResponseBody Iterable<UserAccounts> getUserAccounts(
			@RequestParam(name = "idNumber", required = false) String idNumber,
			@RequestParam(name = "fullName", required = false) String fullName,
			@RequestParam(name = "role", required = false) String role) {
		Specification<UserAccounts> spec = Specification.where(null);

		if (idNumber != null) {
			spec = spec.and((root, query, cb) -> cb.like(root.get("idNumber"), idNumber + "%"));
		}
		if (fullName != null) {
			spec = spec.and((root, query, cb) -> cb.like(root.get("fullName"), fullName + "%"));
		}
		if (role != null) {
			spec = spec.and((root, query, cb) -> cb.equal(root.get("role"), role));
		}

		return userAccountsRepository.findAll(spec);
	}

	@SuppressWarnings("null")
	@PostMapping("/UserAccounts/Insert")
	public @ResponseBody String insertUserAccount(@RequestBody UserAccounts user_account) {
		String message = "";

		UserAccounts inserted = userAccountsRepository.save(user_account);

		if (inserted.getId() != null) {
			message = "success";
			System.out.println("User inserted successfully with ID: " + inserted.getId());
		} else {
			message = "error";
			System.out.println("Error insert user.");
		}

		return message;
	}

	@PostMapping("/UserAccounts/Insert2")
	public @ResponseBody String insertUserAccount2(@RequestParam String idNumber, @RequestParam String fullName,
			@RequestParam String username, @RequestParam String password, @RequestParam String section,
			@RequestParam String role) {
		String message = "";
		UserAccounts user_account = new UserAccounts();

		user_account.setId(0);
		user_account.setIdNumber(idNumber);
		user_account.setFullName(fullName);
		user_account.setUsername(username);
		user_account.setPassword(password);
		user_account.setSection(section);
		user_account.setRole(role);

		UserAccounts inserted = userAccountsRepository.save(user_account);

		if (inserted.getId() != null) {
			message = "success";
			System.out.println("User inserted successfully with ID: " + inserted.getId());
		} else {
			message = "error";
			System.out.println("Error insert user.");
		}

		return message;
	}

	@PostMapping("/UserAccounts/Update")
	public @ResponseBody String updateUserAccount(@RequestBody UserAccounts user_account) {
		String message = "";
		int id = user_account.getId();
		Optional<UserAccounts> user_account_optional = userAccountsRepository.findById(id);

		if (user_account_optional.isPresent()) {
			UserAccounts updated = userAccountsRepository.save(user_account);

			if (updated.getId() != null) {
				message = "success";
				System.out.println("User updated successfully with ID: " + updated.getId());
			} else {
				message = "error";
				System.out.println("Error update user.");
			}
		} else {
			System.out.println("Not Found update user.");
			message = "not found";
		}

		return message;
	}

	@PostMapping("/UserAccounts/Update2")
	public @ResponseBody String updateUserAccount2(@RequestParam int id, @RequestParam String idNumber,
			@RequestParam String fullName, @RequestParam String username, @RequestParam String password,
			@RequestParam String section, @RequestParam String role) {
		String message = "";
		Optional<UserAccounts> user_account_optional = userAccountsRepository.findById(id);

		if (user_account_optional.isPresent()) {
			UserAccounts user_account = user_account_optional.get();
			user_account.setId(id);
			user_account.setIdNumber(idNumber);
			user_account.setFullName(fullName);
			user_account.setUsername(username);
			user_account.setPassword(password);
			user_account.setSection(section);
			user_account.setRole(role);

			UserAccounts updated = userAccountsRepository.save(user_account);

			if (updated.getId() != null) {
				message = "success";
				System.out.println("User updated successfully with ID: " + updated.getId());
			} else {
				message = "error";
				System.out.println("Error update user.");
			}
		} else {
			System.out.println("Not Found update user.");
			message = "not found";
		}

		return message;
	}

	@PostMapping("/UserAccounts/Delete")
	public @ResponseBody String deleteUserAccount(@RequestBody UserAccounts user_account) {
		String message = "";
		int id = user_account.getId();
		Optional<UserAccounts> user_account_optional = userAccountsRepository.findById(id);

		if (user_account_optional.isPresent()) {
			userAccountsRepository.deleteById(id);
			message = "success";
			System.out.println("User deleted successfully with ID: " + id);

		} else {
			System.out.println("Not Found delete user.");
			message = "not found";
		}

		return message;
	}

	@PostMapping("/UserAccounts/Delete2")
	public @ResponseBody String deleteUserAccount2(@RequestParam int id) {
		String message = "";
		Optional<UserAccounts> user_account_optional = userAccountsRepository.findById(id);

		if (user_account_optional.isPresent()) {
			userAccountsRepository.deleteById(id);
			message = "success";
			System.out.println("User deleted successfully with ID: " + id);

		} else {
			System.out.println("Not Found delete user.");
			message = "not found";
		}

		return message;
	}

	@SuppressWarnings("null")
	@GetMapping("/UserAccounts/{id}")
	public @ResponseBody Optional<UserAccounts> getUserAccountsById(@PathVariable Integer id) {
		return userAccountsRepository.findById(id);
	}

}
