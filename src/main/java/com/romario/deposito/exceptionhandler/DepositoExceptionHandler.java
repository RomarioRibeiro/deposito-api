package com.romario.deposito.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DepositoExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String mesagemUsuario = messageSource.getMessage("mensagem.invalida",null, LocaleContextHolder.getLocale());
		String mensagemDesenvolverdor = Optional.ofNullable(ex.getCause()).orElse(ex).toString();
		return handleExceptionInternal(ex, new Erro(mesagemUsuario, mensagemDesenvolverdor), headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String mesagemUsuario = messageSource.getMessage("recurso.operacao-nao-permitida",null , LocaleContextHolder.getLocale());
		String mensagemDesenvolverdor = ExceptionUtils.getRootCauseMessage(ex);
		
		
		List<Erro> errors = Arrays.asList(new Erro(mesagemUsuario, mensagemDesenvolverdor));
		return handleExceptionInternal(ex, errors, headers, status, request);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
		
		String mesagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolverdor = ex.toString();
		
		
		List<Erro> erros = Arrays.asList(new Erro(mesagemUsuario, mensagemDesenvolverdor));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	public List<Erro> criarListaDeErro(BindingResult bindingResult) {
		List<Erro> errors = new ArrayList<>();
		
		for(FieldError fieldError : bindingResult.getFieldErrors()) {
			String mesagemUsuario = messageSource.getMessage(fieldError,  LocaleContextHolder.getLocale());
			String mensagemDesenvolverdor = fieldError.toString();
			errors.add(new Erro(mesagemUsuario, mensagemDesenvolverdor));
		}
		
		
		
		return errors;
	}
	
	public static class Erro {

		private String mesagemUsuario;
		private String mensagemDesenvolverdor;

		public Erro(String mesagemUsuario, String mensagemDesenvolverdor) {
			super();
			this.mesagemUsuario = mesagemUsuario;
			this.mensagemDesenvolverdor = mensagemDesenvolverdor;
		}

		public String getMesagemUsuario() {
			return mesagemUsuario;
		}

		public String getMensagemDesenvolverdor() {
			return mensagemDesenvolverdor;
		}

	}
}
