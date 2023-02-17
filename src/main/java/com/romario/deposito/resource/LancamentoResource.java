package com.romario.deposito.resource;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.romario.deposito.modelo.Lancamento;
import com.romario.deposito.repository.LancamentoRepository;
import com.romario.deposito.repository.filter.LancamentoFilter;
import com.romario.deposito.repository.projection.ResumoLancamento;
import com.romario.deposito.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
@CrossOrigin(origins = "http://localhost:4200/")
public class LancamentoResource {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	
	@GetMapping(params = "resumo")
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable){
		return lancamentoRepository.resumo(lancamentoFilter, pageable);
	}
	
	
	@GetMapping
	public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable){
		return lancamentoRepository.filter(lancamentoFilter, pageable);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> buscarPorCodigo(@PathVariable Long codigo) {
		
		
		return lancamentoRepository.findById(codigo)
				.map(ResponseEntity::ok)
					.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletRequest request){
		Lancamento lancamentoSalvar = lancamentoRepository.save(lancamento);
		
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
			.buildAndExpand("Location" ).toUri();
		
		return ResponseEntity.created(uri).body(lancamentoSalvar);
		
	}
	
	@PutMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Lancamento atualizar(@PathVariable Long codigo,@Valid @RequestBody Lancamento lancamento) {
		
		
		return lancamentoService.atualizar(codigo, lancamento) ;
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long codigo) {
		this.lancamentoRepository.deleteById(codigo);
	}
	
	
	@PutMapping("/{codigo}/pago")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarLancamentoPago(@PathVariable Long codigo,@RequestBody Boolean pago) {
		lancamentoService.atualizarLancamentoAtivo(codigo, pago);
		
	}
	
	
}
