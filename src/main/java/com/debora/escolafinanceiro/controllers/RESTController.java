package com.debora.escolafinanceiro.controllers;

 
import com.debora.escolafinanceiro.uteis.Uteis;
import lombok.extern.slf4j.Slf4j;
 
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public abstract class RESTController<T, ID extends Serializable> {
 
    private static final String SUCCESSO = "successo";
    
	private CrudRepository<T, ID> repo;
	
    public RESTController(CrudRepository<T, ID> repo) {
        this.repo = repo;
    }
    
	@GetMapping
    public @ResponseBody Collection<T> todos() {
        Iterable<T> todos = this.repo.findAll();
        return Uteis.converterInterabelParaList(todos);
    }
	

    @PostMapping(consumes={MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody Map<String, Object> salvar(@RequestBody T json) {
        log.info("criado() com body {} do tipo {}", json, json.getClass());
        T objetoCriado = this.repo.save(json);
        Map<String, Object> m =  new HashMap<>();
        m.put(SUCCESSO, true);
        m.put("criado", objetoCriado);
        return m;
    }
   
    @DeleteMapping(value="/{id}")
    public @ResponseBody Map<String, Object> apagar(@PathVariable ID id) {
        this.repo.deleteById(id);
        Map<String, Object> m = new HashMap<>();
        m.put(SUCCESSO, true);
        return m;
    }

    @SuppressWarnings("unchecked")
	@GetMapping(value="/{id}" )
    public @ResponseBody T buscar(@PathVariable ID id) {
        return (T) this.repo.findById(id);
    }

    @SuppressWarnings("unchecked")
	@PostMapping(value="/{id}", consumes={MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody Map<String, Object> atualizar(@PathVariable ID id, @RequestBody T json) {
        log.info("atualizado() id#{} com body {}", id, json);
        log.debug("T json com tipagem {}", json.getClass());
        T entity = (T) this.repo.findById(id);
        log.debug("entidade: {}", entity);
        T atualizada = this.repo.save(entity);
        log.debug("entidade atualizada: {}", atualizada);
        Map<String, Object> m = new HashMap<>();
        m.put(SUCCESSO, true);
        m.put("id", id);
        m.put("atualizada", atualizada);
        return m;
    }

}