package br.com.diegomardu.io.todolist.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.diegomardu.io.todolist.model.TaskModel;
import br.com.diegomardu.io.todolist.repository.TaskRepository;
import br.com.diegomardu.io.todolist.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	
	private TaskRepository repository;
	
	public TaskServiceImpl(TaskRepository repository) {
		this.repository = repository;
	}

	@Override
	public TaskModel salvar(TaskModel model) {
		
		return repository.save(model);
	}

	@Override
	public List<TaskModel> listar(UUID idUser) {
		return this.repository.findByIdUser(idUser);
	
	}

	@Override
	public TaskModel atualizar(TaskModel model) {
		return repository.save(model);
	}

	@Override
	public TaskModel buscaPorId(UUID id) {
		return this.repository.findById(id).orElse(null);
		
	}

}
