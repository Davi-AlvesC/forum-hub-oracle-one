package com.example.forumhub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AtualizacaoTopicoDTO {

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Mensagem é obrigatória")
    private String mensagem;

    @NotBlank(message = "Autor é obrigatório")
    private String autor;

    @NotBlank(message = "Curso é obrigatório")
    private String curso;

    // Defina os estados aceitos conforme sua regra; aqui usamos ABERTO | FECHADO | ARQUIVADO
    @NotBlank(message = "Estado é obrigatório")
    @Pattern(regexp = "ABERTO|FECHADO|ARQUIVADO", message = "Estado deve ser ABERTO, FECHADO ou ARQUIVADO")
    private String estado;

    public String getTitulo() { return titulo; }
    public String getMensagem() { return mensagem; }
    public String getAutor() { return autor; }
    public String getCurso() { return curso; }
    public String getEstado() { return estado; }

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public void setAutor(String autor) { this.autor = autor; }
    public void setCurso(String curso) { this.curso = curso; }
    public void setEstado(String estado) { this.estado = estado; }
}
