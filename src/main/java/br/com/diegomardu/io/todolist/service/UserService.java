package br.com.diegomardu.io.todolist.service;

import org.springframework.stereotype.Service;

import br.com.diegomardu.io.todolist.model.User;

@Service
public interface UserService {
	
	public User salvar(User user);
	
	public User buscarUsuarioPeloNome(String name);
	
	public User hashPassword(User user);

}
