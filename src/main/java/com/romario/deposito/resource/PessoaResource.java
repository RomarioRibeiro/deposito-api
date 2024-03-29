package com.romario.deposito.resource;

import java.net.URI;
import java.util.List;

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

import com.romario.deposito.modelo.Pessoa;
import com.romario.deposito.repository.PessoaRepository;
import com.romario.deposito.repository.filter.PessoaFilter;
import com.romario.deposito.repository.projection.ResumoPessoa;
import com.romario.deposito.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin(origins = "http://localhost:4200/")
public class PessoaResource {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	
	@GetMapping
	public List<Pessoa> lista(){
		return pessoaRepository.findAll();
	}
	
	@GetMapping(params = "resumo")
	public Page<ResumoPessoa> resumir(PessoaFilter pessoaFilter, Pageable pageable){
		return pessoaRepository.resumo(pessoaFilter, pageable);
	}
	
//	@GetMapping
//	public Page<Pessoa> pesquisar(PessoaFilter pessaFilter, Pageable pageable){
//		return pessoaRepository.filter(pessaFilter, pageable);
//	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> buscarPorCodigo(@PathVariable Long codigo) {
		return pessoaRepository.findById(codigo)
					.map(ResponseEntity::ok)
						.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletRequest request) {
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
			.buildAndExpand("Location").toUri();
		
		
		return ResponseEntity.created(uri).body(pessoaSalva);
	}
	
	@PutMapping("/{codigo}")
	
	public Pessoa atualizar(@PathVariable Long codigo,@Valid @RequestBody Pessoa pessoa) {
		return pessoaService.atualizar(codigo, pessoa);
	}
	
	@DeleteMapping("/{codigo}")
	public void excluir(@PathVariable Long codigo) {
		this.pessoaRepository.deleteById(codigo);
	}
	
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPessoaAtiva(@PathVariable Long codigo,@RequestBody Boolean ativo) {
		pessoaService.atualizarPessoaAtiva(codigo, ativo);
	}
	
	
}
