<img src="https://github.com/user-attachments/assets/25c92839-ea33-4930-8bf1-2e6c319c0376" width="200px">

# Quarkus Hexagonal Architecture

![Quarkus](https://img.shields.io/badge/Quarkus-v3.0-blue) ![MongoDB](https://img.shields.io/badge/MongoDB-v6.0-green) ![License](https://img.shields.io/badge/License-MIT-green)

Projeto de estudo sobre a aplicação da Arquitetura Hexagonal com Quarkus, focando na criação de um CRUD de despesas utilizando MongoDB, MapStruct para mapeamentos, e boas práticas com DTOs e interfaces.

## 🚀 Tecnologias Utilizadas

- **Quarkus**: Framework Java otimizado para conteinerização.
- **MongoDB**: Banco de dados NoSQL para armazenamento dos dados.
- **MapStruct**: Biblioteca para mapeamento entre DTOs e entidades.
- **Lombok**: Simplificação de código com geração automática de getters, setters e builders.
- **Swagger**: Documentação interativa da API.

## 🛠️ Funcionalidades

- CRUD completo de despesas.
- Utilização de Arquitetura Hexagonal com separação clara entre camadas.
- Mapeamento automático de entidades com MapStruct.
- Validação de dados e testes de integração.

## 📂 Estrutura do Projeto

```
quarkus-hexagonal-architecture/
├── app/
│   ├── quarkus-application/
│   │   ├── src/
│   │   ├── main/
│   │   ├── resources/
│   │   └── pom.xml
├── pom.xml
└── README.md
```

## 🏗️ Como Rodar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/Marcoant007/quarkus-hexagonal-architecture.git
   ```
2. Navegue até a pasta do projeto:
   ```bash
   cd quarkus-hexagonal-architecture
   ```
3. Rode o Docker Compose para subir o MongoDB:
   ```bash
   docker-compose up
   ```
4. Compile e rode a aplicação:
   ```bash
   mvn clean package quarkus:dev
   ```

## 📋 Testes

Execute os testes com:
```bash
mvn test
```
