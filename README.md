# AuraSkin

AuraSkin é uma API REST desenvolvida com Spring Boot para gerenciamento de clínicas de estética e cuidados com a pele. O sistema permite o cadastro e gerenciamento de clientes, profissionais, procedimentos e agendamentos, fornecendo uma solução completa para controle operacional da clínica.

O projeto foi desenvolvido seguindo boas práticas de arquitetura em camadas, utilizando Spring Boot, JPA, PostgreSQL, Docker e testes automatizados.

---

## 🚀 Funcionalidades

* **Cadastro de Clientes**
* **Cadastro de Profissionais**
* **Cadastro de Procedimentos**
* **Cadastro de Agendamentos**
* Operações CRUD completas
* Validação de dados com Bean Validation
* Tratamento global de exceções
* Documentação automática com Swagger/OpenAPI
* Persistência de dados com PostgreSQL
* Testes unitários com JUnit 5 e Mockito
* Relatórios de cobertura utilizando JaCoCo
* Deploy em ambiente cloud utilizando Render

---

## 🛠️ Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3.5**
* Spring Web
* Spring Data JPA
* PostgreSQL
* Maven
* Docker
* Swagger / OpenAPI
* JUnit 5
* Mockito
* JaCoCo
* Render

---

## 📐 Arquitetura e Estrutura

A aplicação segue a arquitetura tradicional em camadas:

Controller  ➔  Service  ➔  Repository  ➔  PostgreSQL

---

## 💻 Como Executar Localmente

### Pré-requisitos
* Java 21 ou superior
* Maven 3.9+
* PostgreSQL rodando localmente (ou use a opção do H2 abaixo)
* Git

### Passo a Passo

1. Clone o repositório:
git clone https://github.com/Joao-VictorRB/AuraSkin.git
cd AuraSkin

2. Compile o projeto:
mvn clean install

3. Execute a aplicação:
mvn spring-boot:run

A aplicação ficará disponível em: http://localhost:8080  
Swagger Local: http://localhost:8080/swagger-ui/index.html

---

## 🛢️ Execução Alternativa com H2 Database (In-Memory)

Caso deseje testar o projeto rapidamente sem configurar o PostgreSQL, substitua as propriedades do arquivo src/main/resources/application.properties por:

spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

E certifique-se de incluir a dependência do H2 no seu pom.xml:

<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>

* Console H2: http://localhost:8080/h2-console
* JDBC URL: jdbc:h2:mem:testdb
* Usuário: sa | Senha: (em branco)

---

## 🧪 Testes e Cobertura

O projeto possui testes unitários para Controllers e Services utilizando JUnit 5 e Mockito, mantendo uma cobertura superior a 90%.

Para rodar os testes:
mvn test

Para gerar o relatório de cobertura do JaCoCo:
mvn clean test
O relatório gerado estará disponível em: target/site/jacoco/index.html

---

## 🌐 Deploy em Produção

A aplicação foi implantada utilizando a plataforma Render.

* URL da API: https://auraskin-9krm.onrender.com
* Swagger em Produção: https://auraskin-9krm.onrender.com/swagger-ui/index.html

### Variáveis de Ambiente Necessárias (Render)
* SPRING_DATASOURCE_URL
* SPRING_DATASOURCE_USERNAME
* SPRING_DATASOURCE_PASSWORD
* PORT

---

## 📑 Documentação da API (Endpoints)

### Clientes
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| GET | /clients | Listar todos os clientes |
| GET | /clients/{id} | Buscar cliente por ID |
| POST | /clients | Cadastrar novo cliente |
| PUT | /clients/{id} | Atualizar dados do cliente |
| DELETE | /clients/{id} | Remover cliente |

### Profissionais
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| GET | /professionals | Listar todos os profissionais |
| GET | /professionals/{id} | Buscar profissional por ID |
| POST | /professionals | Cadastrar novo profissional |
| PUT | /professionals/{id} | Atualizar dados do profissional |
| DELETE | /professionals/{id} | Remover profissional |

### Procedimentos
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| GET | /procedures | Listar todos os procedimentos |
| GET | /procedures/{id} | Buscar procedimento por ID |
| POST | /procedures | Cadastrar novo procedimento |
| PUT | /procedures/{id} | Atualizar dados do procedimento |
| DELETE | /procedures/{id} | Remover procedimento |

### Agendamentos
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| GET | /schedulings | Listar todos os agendamentos |
| GET | /schedulings/{id} | Buscar agendamento por ID |
| POST | /schedulings | Criar novo agendamento |
| PUT | /schedulings/{id} | Atualizar agendamento |
| DELETE | /schedulings/{id} | Cancelar/Remover agendamento |

> Nota sobre Status de Agendamento: Os valores aceitos pelo sistema são SCHEDULED, COMPLETED e CANCELLED.

---

## ✉️ Exemplos de Requisições

### Criar Cliente (POST /clients)
{
  "name": "João Victor",
  "email": "joao@gmail.com",
  "phone": "11999999999"
}

### Criar Profissional (POST /professionals)
{
  "name": "Maria Silva",
  "specialty": "Estética Facial"
}

### Criar Procedimento (POST /procedures)
{
  "name": "Limpeza de Pele Profunda",
  "description": "Remoção de cravos, impurezas e hidratação final",
  "price": 150.0,
  "durationMin": 60
}

### Criar Agendamento (POST /schedulings)
{
  "date": "2026-06-10",
  "time": "14:00:00",
  "status": "SCHEDULED",
  "clientId": 1,
  "professionalId": 1,
  "procedureId": 1
}

### Testando via cURL (Exemplos)

# Buscar todos os clientes
curl -X GET http://localhost:8080/clients

# Criar um novo cliente
curl -X POST http://localhost:8080/clients \
  -H "Content-Type: application/json" \
  -d '{
    "name": "João Victor",
    "email": "joao@gmail.com",
    "phone": "11999999999"
  }'

---

## 🔮 Melhorias Futuras

- [ ] Implementação de autenticação com JWT
- [ ] Controle de permissões por perfil (Role-based security)
- [ ] Integração com serviço de envio de e-mails para confirmação
- [ ] Dashboard administrativo para a clínica
- [ ] Configuração do Docker Compose para ambiente local completo
- [ ] Criação de Pipeline CI/CD automatizada
- [ ] Monitoramento e observabilidade (Spring Actuator/Prometheus)

---

## 👥 Colaboradores

| Nome | Participação | GitHub |
| :--- | :--- | :--- |
| João Victor      | Desenvolvimento Backend, Testes, Documentação e Deploy | [@Joao-VictorRB](https://github.com/Joao-VictorRB) |
| Rafael Almeida   | Testes e Deploy          | [@RafinhaAlmeida](https://github.com/RafinhaAlmeida)                             |
| Ranyelle Melo    | Testes e Deploy          | [@ranyellemelo](https://github.com/ranyellemelo)                                 |
| Kauan Costa      | Desenvolvimento Backend  | [@kauancostasilva](https://github.com/kauancostasilva)                           |
| Isabelle Pimenta | Documentação             | [@isabellepimenta](https://github.com/isabellepimenta)                           |
| Samuel Sena      | Dto                      | [@Samuelsena9](https://github.com/Samuelsena9)                                   |




---

## 📄 Licença

Projeto desenvolvido puramente para fins acadêmicos e de aprendizado em Java, Spring Boot, APIs REST e Engenharia de Software.
