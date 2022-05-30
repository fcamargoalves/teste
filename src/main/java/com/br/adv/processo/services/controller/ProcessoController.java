package com.br.adv.processo.services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.adv.processo.services.entity.Processo;
import com.br.adv.processo.services.repository.ProcessoRepository;
import com.br.adv.processo.services.service.ProcessoService;

@RestController
@RequestMapping("/processos")
public class ProcessoController {

	@Autowired
	private ProcessoService processoService;
	
	@Autowired
	private ProcessoRepository processoRepository;
	
	
	/* Método salvar */
	@PostMapping("/")
	public Processo salvar(@RequestBody Processo processo) {
		return  processoService.salvarProcesso(processo);		
	}
		
	
	/* Método buscar por id */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Processo> buscarPorId(@PathVariable("id") Long id){
		Processo obj = processoRepository.findById(id).get();
		return ResponseEntity.ok(obj);
		
	}
	
	/* Método lista todos */
	@GetMapping
	public ResponseEntity<List<Processo>> findAll() {
		List<Processo> list = processoRepository.findAll();
		return ResponseEntity.ok(list);
	}
	
	/* Método deltar por id */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable("id") Long id) {
		processoRepository.deleteById(id);		
	}
}
