# CarQuote: Sistema de Consulta de Cotação de Veículos

<p align="center">
  <img src="https://miro.medium.com/v2/resize:fit:720/format:webp/1*wrYIcaHbd23AAd88N9yIlw.png" alt="Java Spring" width="400"/>
</p>

[![Java](https://img.shields.io/badge/Java-17-007396?logo=java&logoColor=white)](https://www.oracle.com/java/)
[![Spring](https://img.shields.io/badge/Spring-3.3.0-6DB33F?logo=spring&logoColor=white)](https://spring.io/)
[![Lambdas](https://img.shields.io/badge/Lambdas-Java-orange)](https://www.oracle.com/technical-resources/articles/java/architect-lambdas-part1.html)
[![Stream](https://img.shields.io/badge/Streams-Java-orange)](https://www.oracle.com/technical-resources/articles/java/architect-streams.html)
[![Git](https://img.shields.io/badge/Git-F05032?logo=git&logoColor=white)](https://git-scm.com/)
[![GitHub](https://img.shields.io/badge/GitHub-181717?logo=github&logoColor=white)](https://github.com/)

## Descrição

CarQuote é um sistema simples e eficiente para consultar a cotação de veículos. Foi criado como parte do projeto de Formação Java Backend ministrado pela Alura. O sistema usa a API FIPE. Esta aplicação foi desenvolvida em Java utilizando Spring Boot, com integração à API da FIPE para obter informações atualizadas sobre marcas, modelos, anos e valores de veículos.

## Funcionalidades

- Consulta de marcas de veículos
- Consulta de modelos por marca
- Consulta de anos por modelo
- Consulta de cotações detalhadas de veículos

## Estrutura do Projeto

```sh
br.org.teophilo.carquote
├── modal
│   ├── Marca.java
│   ├── Modelo.java
│   ├── Ano.java
│   ├── Veiculo.java
├── service
│   ├── IConverteDados.java
│   ├── ConverteDados.java
│   ├── ApiService.java
├── principal
│   ├── CarQuoteApplication.java
├── Main.java
└── pom.xml
```

## Exemplo de Uso

### Passos para Consultar a Cotação de um Veículo

1. **Buscando marcas de veículos:**

   O sistema irá exibir uma tabela com todas as marcas disponíveis.
   
   ```plaintext
   Buscando marcas...
   +---------+--------------+
   | Código  | Nome         |
   +---------+--------------+
   | 1       | Acura        |
   | 2       | Agrale       |
   | 3       | Alfa Romeo   |
   | ...     | ...          |
   +---------+--------------+
   ```

2. **Digite o código da marca:** `1`

   ```plaintext
   Buscando modelos...
   +---------+-----------------------------------------+
   | Código  | Nome                                    |
   +---------+-----------------------------------------+
   | 5585    | AMAROK CD2.0 16V/S CD2.0 16V TDI 4x2 Die|
   | 5586    | AMAROK CD2.0 16V/S CD2.0 16V TDI 4x4 Die|
   | 9895    | AMAROK Comfor. 3.0 V6 TDI 4x4 Dies. Aut.|
   | ...     | ...                                     |
   +---------+-----------------------------------------+
   ```

3. **Digite o código do modelo:** `5585`

   ```plaintext
   Buscando anos...
   1: 2022 Diesel
   2: 2021 Diesel
   3: 2020 Diesel
   | ... |
   ```

4. **Digite o número correspondente ao ano:** `1`

   ```plaintext
   Buscando informações do veículo...
   Informações do veículo:
   +---------------+-------------------------------------+
   | Atributo      | Valor                               |
   +---------------+-------------------------------------+
   | Marca         | VW - VolksWagen                     |
   | Modelo        | AMAROK High.CD 2.0 16V TDI 4x4 Dies.Aut |
   | Ano           | 2022                                |
   | Combustível   | Diesel                              |
   | Valor         | R$ 104.933,00                       |
   +---------------+-------------------------------------+
   ```

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.3.0
- Jackson (para manipulação de JSON)
- HTTP Client (java.net.http)
- Maven (para gerenciamento de dependências)

## Instalação e Execução

### Clone o repositório e execute a aplicação:

```sh
git clone https://github.com/theophiloweb/carquote.git
cd carquote
./mvnw spring-boot:run
```

### Se estiver no Windows, use o comando abaixo para executar o Maven Wrapper:

```sh
mvnw.cmd spring-boot:run
```

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests para melhorias ou correções.

## Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo LICENSE para mais detalhes.

## Contato

Se tiver alguma dúvida, entre em contato: [teophilo@gmail.com](mailto:teophilo@gmail.com)
