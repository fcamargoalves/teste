package com.br.adv.processo.services.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.br.adv.processo.services.entity.Processo;
import com.br.adv.processo.services.exceptionhandler.ProcessoExceptionHandler.Erro;
import com.br.adv.processo.services.repository.ProcessoRepository;
import com.br.adv.processo.services.service.exception.ReuInexistenteOuDuplicadoException;

@Service
public class ProcessoService {

	@Autowired
	private ProcessoRepository processoRepository;

	@Autowired
	private MessageSource messageSource;

	public Processo salvarProcesso(Processo processo) {
		Optional<Processo> reu = processoRepository.findById(processo.getReu().getId());
		if (reu == null || reu.equals(reu)) {
			throw new ReuInexistenteOuDuplicadoException();
		}

		return processoRepository.save(processo);
	}

	
	/* Método interno da classe */
	@ExceptionHandler({ ReuInexistenteOuDuplicadoException.class })
	public ResponseEntity<Object> handleReuInexistenteOuDuplicadoException(ReuInexistenteOuDuplicadoException ex) {
		String mensagemUsuario = messageSource.getMessage("reu.inexistente-ou-duplicado", null, LocaleContextHolder.getLocale());
		
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}

}
