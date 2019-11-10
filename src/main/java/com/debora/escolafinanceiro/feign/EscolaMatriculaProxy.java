package com.debora.escolafinanceiro.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.debora.escolafinanceiro.dto.AlunoDTO;

@FeignClient(name = "aula", url = "localhost:8080")
public interface EscolaMatriculaProxy {
	
	@GetMapping("/aluno/{id}")
	public AlunoDTO pegarDados(@PathVariable("id") Long id);
}
