# Sistema de Gestão de Recursos

Sistema web desenvolvido em Java com Spring Boot para gerenciamento de reservas de espaços e equipamentos.

## Funcionalidades

- Login com autenticação por sessão (HttpSession)
- Cadastro de colaboradores
- Cadastro de recursos (espaços e equipamentos)
- Cadastro de localizações dos recursos
- Cadastro de reservas
- Cancelamento de reservas
- Validações de regras de negócio
- Controle de disponibilidade de datas e horários

## Tecnologias

- Java 24
- Spring Boot
- Spring MVC
- Spring Data JPA
- Thymeleaf
- Bootstrap 5
- MySQL
- Docker
- Docker Compose

---

# Executando o projeto

## Pré-requisitos

- Docker Desktop

---

## 1. Inicie o Docker

Antes de executar o projeto, certifique-se de que o Docker está em execução.

---

## 2. Clone o repositório

```bash
git clone https://github.com/matheusLS77/gestao-recursos.git
```

Entre na pasta:

```bash
cd gestao-recursos
```

---

## 3. Execute o Docker Compose

```bash
docker compose up --build
```

O Docker irá:

- criar o banco de dados MySQL;
- construir a aplicação Spring Boot;
- iniciar os containers;
- conectar a aplicação ao banco automaticamente.

---

## 4. Acesse o sistema

Abra o navegador em:

```
http://localhost:8080
```

---

## Estrutura do projeto

```
src
├── controller
├── dto
├── entity
├── enums
├── repository
├── service
├── sessoes
└── resources
    ├── static
    │   ├── css
    │   ├── images
    │   └── js
    └── templates
```

---

## Desenvolvido por

- **Matheus L. Silva** – [GitHub](https://github.com/matheusLS77)
- **Thiago Pfiffer Koepsel** – [GitHub](https://github.com/ThiagoPK21)
