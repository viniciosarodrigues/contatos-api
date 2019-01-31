package br.com.lampiao.contatosapi.controladores;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.lampiao.contatosapi.entidades.Contato;
import br.com.lampiao.contatosapi.repositorios.ContatoRepositorio;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

	@Autowired
	ContatoRepositorio contatoRepo;

	@PostMapping(consumes = "application/json")
	public ResponseEntity<?> adicionaContato(@RequestBody Contato contato) {
		Contato contatoSalvo = contatoRepo.save(contato);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/contatos").path("/{id}")
				.buildAndExpand(contatoSalvo.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@GetMapping
	public ResponseEntity<List<Contato>> listaContatos() {
		return ResponseEntity.ok().body(contatoRepo.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Contato> buscaPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(contatoRepo.findById(id).get());
	}

}


