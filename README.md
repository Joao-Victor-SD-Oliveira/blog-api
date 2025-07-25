API de Blog com Java e Spring Boot

Descrição do Projeto

Este é um projeto de portfólio que consiste em uma API RESTful completa para a criação e gerenciamento de um sistema de blog. A aplicação foi desenvolvida com o objetivo de aplicar e aprofundar meus conhecimentos em Java, Spring Boot e as melhores práticas do desenvolvimento backend.

A API permite o gerenciamento de usuários, posts e comentários, com um sistema de autenticação e autorização baseado em Tokens JWT para proteger os endpoints.

Funcionalidades

    ✅ Autenticação de Usuários: Cadastro e Login com geração de Token JWT.

    ✅ Gerenciamento de Posts: CRUD (Criar, Ler, Atualizar, Deletar) completo para postagens.

    ✅ Sistema de Permissões:

        Rotas públicas para leitura de conteúdo.

        Rotas protegidas que exigem autenticação.

        Regras de negócio onde apenas o autor de um post pode editá-lo ou excluí-lo.

    ✅ Estrutura Organizada: O projeto segue uma arquitetura em camadas (Controller, Service, Repository) para separação de responsabilidades.

    ✅ Segurança: Senhas armazenadas com criptografia (BCrypt) e validação de acesso com Spring Security.

Tecnologias Utilizadas

Backend

    Java 17

    Spring Boot 

    Spring Web

    Spring Data JPA

    Hibernate

Segurança

    Spring Security

    JWT (JSON Web Tokens)

Banco de Dados

    PostgreSQL

Ferramentas e Dependências

    Maven

    Lombok

    Insomnia (para testes e validação dos endpoints)

    Git & GitHub

Endpoints da API

Autenticação

POST /auth/register

    Descrição: Registra um novo usuário no sistema.

    Protegido: Não ❌

POST /auth/login

    Descrição: Autentica um usuário com e-mail e senha, retornando um token JWT de acesso.

    Protegido: Não ❌

Posts

GET /posts

    Descrição: Retorna uma lista com todos os posts publicados.

    Protegido: Não ❌

GET /posts/{id}

    Descrição: Busca e retorna um post específico pelo seu ID.

    Protegido: Não ❌

POST /posts

    Descrição: Cria um novo post. Requer um token JWT de autenticação.

    Protegido: Sim ✔️

PUT /posts/{id}

    Descrição: Atualiza um post existente. Requer autenticação e que o usuário seja o autor do post.

    Protegido: Sim ✔️

DELETE /posts/{id}

    Descrição: Deleta um post. Requer autenticação e que o usuário seja o autor do post.

    Protegido: Sim ✔️

Feito por João Victor.
