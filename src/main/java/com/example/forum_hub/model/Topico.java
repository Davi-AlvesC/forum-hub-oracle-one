package com.example.forumhub.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos", uniqueConstraints = {
        @UniqueConstraint(name = "uk_titulo_mensagem", columnNames = {"titulo", "mensagem"})
})
public class Topico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) private String titulo;

    @Column(nullable = false, length = 4000)
    private String mensagem;

    @Column(nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Column(nullable = false)
    private String estado = "ABERTO"; // padrão

    @Column(nullable = false) private String autor;
    @Column(nullable = false) private String curso;

    public Topico() {}

    public Topico(String titulo, String mensagem, String autor, String curso) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.autor = autor;
        this.curso = curso;
    }

    // getters e setters
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getMensagem() { return mensagem; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public String getEstado() { return estado; }
    public String getAutor() { return autor; }
    public String getCurso() { return curso; }

    public void setId(Long id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setAutor(String autor) { this.autor = autor; }
    public void setCurso(String curso) { this.curso = curso; }
}
