package br.com.diegomardu.io.todolist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.diegomardu.io.todolist.model.User;
import br.com.diegomardu.io.todolist.repository.UserRepository;
import br.com.diegomardu.io.todolist.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository repository;

	@Override
	public User salvar(User user) {
		return repository.save(user);
	}

	@Override
	public User buscarUsuarioPeloNome(String name) {
		var user = repository.findByUsername(name);
		return user;
	}

	@Override
	public User hashPassword(User user) {
		
		var passwordHashed =  BCrypt.withDefaults()
			.hashToString(12, user.getPassword().toCharArray());
		
		user.setPassword(passwordHashed);
		
		return user;
	}

}
