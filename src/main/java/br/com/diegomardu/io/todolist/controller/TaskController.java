package br.com.diegomardu.io.todolist.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.antlr.v4.runtime.misc.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.diegomardu.io.todolist.model.TaskModel;
import br.com.diegomardu.io.todolist.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private TaskService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity create(@RequestBody TaskModel model, HttpServletRequest request) {
		var idUser = request.getAttribute("idUser");
		model.setIdUser((UUID)idUser);
		
		var currentDate = LocalDateTime.now();
		
		if(currentDate.isAfter(model.getStartAt()) || currentDate.isAfter(model.getEndAt())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data de atividade invalida! Data de inicio/final deve ser maior que a data atual.");
		}
		
		if(model.getStartAt().isAfter(model.getEndAt())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data de atividade invalida! Data de inicio deve ser maior que a data de termino.");
		}
		
		service.salvar(model);
		return ResponseEntity.status(HttpStatus.OK).body(model);
	}
	
	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public List<TaskModel> list(HttpServletRequest request) {
		var idUser = request.getAttribute("idUser");
		var tasks = service.listar((UUID) idUser);	
		
		return tasks;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity update(@RequestBody TaskModel model, HttpServletRequest request, @PathVariable UUID id) {
		var task = service.buscaPorId(id);
		
		if(task == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Tarefa não encontrada.");
		}
		
		var idUser = request.getAttribute("idUser");
		
		if(!task.getIdUser().equals(idUser)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Usuário não tem permissão para alterar  tarefa");
		}
		
		BeanUtils.copyProperties(model, task);
		
		var taskUpdated = this.service.salvar(task);
		return ResponseEntity.ok().body(this.service.salvar(taskUpdated));
	}

}
