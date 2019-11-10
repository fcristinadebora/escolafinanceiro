package com.debora.escolafinanceiro.controllers;

import com.debora.escolafinanceiro.dto.AlunoDTO;
import com.debora.escolafinanceiro.entidades.ContaRecebida;
import com.debora.escolafinanceiro.feign.EscolaMatriculaProxy;
import com.debora.escolafinanceiro.repositorios.ContaRecebidaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/conta-recebida")
public class ContaRecebidaController extends RESTController<ContaRecebida, Long> {

	public ContaRecebidaController(CrudRepository<ContaRecebida, Long> repo) {
		super(repo);
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/pegarDadosFinanceiro/{id}")
	public @ResponseBody AlunoDTO pegarDados(@PathVariable Long id) {
		RestTemplate rest = new RestTemplate();
		String url = "http://localhost:8080/aluno/" + id;
		AlunoDTO aluno = rest.getForObject(url, AlunoDTO.class);
		return aluno;
	}
	
	@Autowired
	private EscolaMatriculaProxy escolaMatriculaProxy;
	
	@GetMapping("/pegarDadosFinanceiro-feign/{id}")
	public @ResponseBody AlunoDTO pegarDadosFeign(@PathVariable Long id) {
		AlunoDTO aluno = escolaMatriculaProxy.pegarDados(id);
		return aluno;
	}
}