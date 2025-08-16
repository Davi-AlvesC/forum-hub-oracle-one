package com.example.forumhub.controller;

import com.example.forumhub.dto.AtualizacaoTopicoDTO;
import com.example.forumhub.dto.TopicoDTO;
import com.example.forumhub.model.Topico;
import com.example.forumhub.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepository topicoRepository;

    public TopicoController(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    @PostMapping
    public ResponseEntity<TopicoDTO> criar(@RequestBody @Valid TopicoDTO body) {
        // Regra: todos os campos obrigatórios são validados pelo @Valid no DTO
        // Regra: não permitir duplicados (mesmo título e mensagem)
        if (topicoRepository.existsByTituloAndMensagem(body.getTitulo(), body.getMensagem())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tópico duplicado (título + mensagem).");
        }

        Topico topico = new Topico(
                body.getTitulo(),
                body.getMensagem(),
                body.getAutor(),
                body.getCurso()
        );
        // estado padrão "ABERTO" definido no construtor da entidade

        Topico salvo = topicoRepository.save(topico);

        TopicoDTO resp = TopicoDTO.fromEntity(salvo);
        return ResponseEntity.created(URI.create("/topicos/" + salvo.getId())).body(resp);
    }

    @GetMapping
    public ResponseEntity<List<TopicoDTO>> listar() {
        var lista = topicoRepository.findAll().stream().map(TopicoDTO::fromEntity).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDTO> detalhar(@PathVariable Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico não encontrado."));
        return ResponseEntity.ok(TopicoDTO.fromEntity(topico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id,
                                               @RequestBody @Valid AtualizacaoTopicoDTO body) {
        var opt = topicoRepository.findById(id);
        if (opt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico não encontrado.");

        // Regra: duplicidade também na atualização
        boolean duplicado = topicoRepository.existsByTituloAndMensagem(body.getTitulo(), body.getMensagem());
        Topico atual = opt.get();

        if (duplicado && !(atual.getTitulo().equals(body.getTitulo()) && atual.getMensagem().equals(body.getMensagem()))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tópico duplicado (título + mensagem).");
        }

        atual.setTitulo(body.getTitulo());
        atual.setMensagem(body.getMensagem());
        atual.setAutor(body.getAutor());
        atual.setCurso(body.getCurso());
        atual.setEstado(body.getEstado());

        var salvo = topicoRepository.save(atual);
        return ResponseEntity.ok(TopicoDTO.fromEntity(salvo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico não encontrado.");
        }
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
