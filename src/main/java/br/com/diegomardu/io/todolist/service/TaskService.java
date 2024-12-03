package br.com.diegomardu.io.todolist.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.diegomardu.io.todolist.model.TaskModel;

@Service
public interface TaskService {
	
	public TaskModel salvar(TaskModel model);
	
	public List<TaskModel> listar(UUID idUser);
	
	public TaskModel atualizar(TaskModel model);
	
	public TaskModel buscaPorId(UUID id);

}
