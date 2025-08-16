package com.example.forumhub.dto;

import com.example.forumhub.model.Topico;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class TopicoDTO {

    // Para resposta
    private Long id;
    private LocalDateTime dataCriacao;
    private String estado;

    // Para criação (campos obrigatórios)
    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Mensagem é obrigatória")
    private String mensagem;

    @NotBlank(message = "Autor é obrigatório")
    private String autor;

    @NotBlank(message = "Curso é obrigatório")
    private String curso;

    public TopicoDTO() {}

    public static TopicoDTO fromEntity(Topico t) {
        TopicoDTO dto = new TopicoDTO();
        dto.id = t.getId();
        dto.titulo = t.getTitulo();
        dto.mensagem = t.getMensagem();
        dto.autor = t.getAutor();
        dto.curso = t.getCurso();
        dto.dataCriacao = t.getDataCriacao();
        dto.estado = t.getEstado();
        return dto;
    }

    // getters e setters
    public Long getId() { return id; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public String getEstado() { return estado; }
    public String getTitulo() { return titulo; }
    public String getMensagem() { return mensagem; }
    public String getAutor() { return autor; }
    public String getCurso() { return curso; }

    public void setId(Long id) { this.id = id; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public void setAutor(String autor) { this.autor = autor; }
    public void setCurso(String curso) { this.curso = curso; }
}
