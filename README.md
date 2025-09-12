
# Room Reservation API 🏨

Bem-vindo ao repositório da **Room Reservation API**\! Este é um projeto simples em Spring Boot, projetado para gerenciar reservas de quartos. Ele oferece uma API RESTful para lidar com operações relacionadas a reservas e quartos.

-----

## 🚀 Como começar

Para colocar este projeto em funcionamento na sua máquina local, siga estes passos.

### Pré-requisitos

  * **Java Development Kit (JDK)**: A versão 17 ou superior é necessária.
  * **Maven**: Usado para gerenciamento de dependências e compilação do projeto.
    
-----

## 🛠️ Tecnologias usadas

Este projeto foi construído com as seguintes tecnologias:

  * **Java**: A linguagem de programação principal.
  * **Spring Boot**: O framework usado para criar a aplicação autônoma e de nível de produção.
  * **Spring Security**: Lida com autenticação e autorização.
  * **Maven**: A ferramenta de automação de build.

-----

## 📂 Estrutura do Projeto

O projeto segue uma estrutura de diretórios padrão do Spring Boot e Maven.

```
.
├── .mvn
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── reservation
│   │   │           └── api
│   │   │               ├── controller
│   │   │               │   └── HomeController.java  (controla as requisições)
│   │   │               ├── entity
│   │   │               │   ├── Reservation.java     (modelo de Reserva)
│   │   │               │   └── Room.java            (modelo de Quarto)
│   │   │               ├── RoomReservationApiApplication.java (classe principal)
│   │   │               └── SecurityConfig.java      (configurações de segurança)
│   └── test
│       └── java
│           └── com
│               └── reservation
│                   └── api
└── pom.xml (modelo de objeto do projeto)
```

-----

## 📄 Endpoints

Aqui está uma visão geral dos principais endpoints fornecidos pela API.

  * `GET /`: Um endpoint de boas-vindas simples.
  * `POST /reservations`: Cria uma nova reserva.
  * `GET /reservations/{id}`: Recupera uma reserva pelo seu ID.
  * `GET /rooms`: Lista todos os quartos disponíveis.
  * ... (adicione mais endpoints à medida que os criar)

-----

## 🤝 Como contribuir

Contribuições são bem-vindas\! Se você encontrar um bug ou tiver uma sugestão, sinta-se à vontade para abrir uma *issue* ou enviar um *pull request*.

-----

## 📝 Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo `LICENSE` para mais detalhes.
