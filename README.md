# Aplicação de Fluxo de Caixa

A aplicação foi construída utilizando o conceito de *Clean Architecture*  e possui dois microserviços:

**1. lancamento**

Microserviço responsável por inserir, alterar, excluir e buscar os lançamentos no banco de dados. A cada operação, uma notificação é enviada ao Kafka. 

As operações podem ser consultadas através do swagger: http://localhost:8080/swagger-ui.html

**2. saldo**

Microserviço responsável por consumir as notificações do Kafka para sensibilizar o saldo diário consolidado. Também oferece API para consulta do saldo diário.

As operações podem ser consultadas através do swagger: http://localhost:8081/swagger-ui.html

**Execução**

Em terminais separados executar:
```shell
docker-compose up -d
mvn spring-boot:run -f lancamento/pom.xml
mvn spring-boot:run -f saldo/pom.xml
```

**Collection Postman**

O arquivo [API-Fluxo-Caixa.postman_collection.json](API-Fluxo-Caixa.postman_collection.json) contém a Collection das APIs para ser importado no Postman.