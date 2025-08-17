# 💬 Challenge - Fórum Hub | Programa ONE - Oracle Next Education

Este projeto é uma **API REST de fórum desenvolvida em Spring Boot**, com autenticação JWT e persistência em banco de dados relacional, desenvolvido como parte do desafio do **Programa ONE – Oracle Next Education (Grupo 8)**.  

A aplicação permite **cadastrar, listar, detalhar, atualizar e excluir tópicos**, além de controlar o acesso dos usuários por meio de autenticação segura.  

---

## ⚙️ Funcionalidades  

A API expõe endpoints para o gerenciamento de tópicos:  

- **Cadastro de tópicos** (`POST /topicos`)  
  - Criação de um novo tópico com validação de campos obrigatórios.  
  - Bloqueio de duplicados (mesmo título e mensagem).  

- **Listagem de tópicos** (`GET /topicos`)  
  - Retorna todos os tópicos cadastrados.  

- **Detalhamento de tópico** (`GET /topicos/{id}`)  
  - Retorna informações de um tópico específico.  

- **Atualização de tópico** (`PUT /topicos/{id}`)  
  - Atualiza os dados de um tópico existente.  

- **Exclusão de tópico** (`DELETE /topicos/{id}`)  
  - Remove um tópico pelo ID.  

- **Autenticação com JWT**  
  - Login via `POST /login` com usuário e senha.  
  - Geração de token JWT.  
  - Apenas usuários autenticados podem interagir com a API.  

---

## 🔑 Autenticação e Segurança  

- **Spring Security** configurado para proteger os endpoints.  
- **JWT (JSON Web Token)** para autenticação e controle de acesso.  
- Filtros para validação de tokens em cada requisição.  

### Exemplo de fluxo:  
1. Usuário envia `POST /login` com login e senha.  
2. API retorna um **token JWT**.  
3. Usuário envia esse token no header (`Authorization: Bearer <token>`) em todas as requisições subsequentes.  

---

## 🗄️ Estrutura do Banco de Dados  

Cada **tópico** possui os seguintes atributos:  

- `id` (chave primária)  
- `titulo`  
- `mensagem`  
- `dataCriacao`  
- `status` (estado do tópico)  
- `autor`  
- `curso`  

---

## 📦 Tecnologias Utilizadas  

### Configuração ao criar o projeto com Spring Initializr:  
- **Java** (versão 17 em diante)  
- **Maven** (Initializr utiliza a versão 4)  
- **Spring Boot**  
- Projeto empacotado como **JAR**  

### Dependências adicionadas:  
- **Lombok**  
- **Spring Web**  
- **Spring Boot DevTools**  
- **Spring Data JPA**  
- **Flyway Migration**  
- **MySQL Driver**  
- **Validation**  
- **Spring Security**  


---

## 👤 Autor
<p align="center"> <img src="https://avatars.githubusercontent.com/u/89622689?v=4" width="200px"><br> <strong>Davi Alves da Costa</strong><br> <a href="https://github.com/Davi-AlvesC">@Davi-AlvesC</a> </p>
