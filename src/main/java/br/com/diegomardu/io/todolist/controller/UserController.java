package br.com.diegomardu.io.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.diegomardu.io.todolist.model.User;
import br.com.diegomardu.io.todolist.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController  {
	
	@Autowired
	private UserService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity create(@RequestBody User user) {
		
		var userExists = service.buscarUsuarioPeloNome(user.getUsername());
		
		if(userExists != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
		}
		
		service.hashPassword(user);		
		service.salvar(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

}
