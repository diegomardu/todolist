package br.com.diegomardu.io.todolist.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diegomardu.io.todolist.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {
	
	User findByUsername(String username);

}
