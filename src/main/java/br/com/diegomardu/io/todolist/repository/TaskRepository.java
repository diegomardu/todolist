package br.com.diegomardu.io.todolist.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.diegomardu.io.todolist.model.TaskModel;

public interface TaskRepository extends JpaRepository<TaskModel, UUID>{
	
	List<TaskModel> findByIdUser(UUID idUser);

}
