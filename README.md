# carstore
projeto para simular a fabricação de carros

PARA BAIXAR O PROJETO git clone https://github.com/edivaldo100/carstore.git

PASSOS PARA EXECUTAR 
2 - So executar o projeto e sua classe principal "Application" com [javaApplication ou run springBoot] 
ou executar os testes Unitarios 
ou mvn clean instal e java -jar carstore.jar

API Endpoints

GET /cars
Retorna todos os veiculos

-------------------------------

GET /cars/find?q=turbo
Retorna todos os veiculos com esse parametro

-------------------------------

GET /cars/{id}
Retorna o veiculos pelo id

-------------------------------

POST /cars/{id}
salva veiculos

body request
{
    "name": " turbo",
    "description": "2 portas 1.0",
    "model": {
        "producer": {
            "name": "RENAULT"
        },
        "brand": "NOVO CLIO 2021"
    },
    "created": "2021-01-08T01:24:19.251+0000",
    "updated": "2021-01-08T01:24:19.251+0000",
    "status": "AVAILABLE",
    "year": 1980
}
-------------------------------
PATCH /cars/{id}
Atualiza veiculos

body request
{
    "name": " turbo",
    "description": "4 portas 1.0"
}
-------------------------------

PUT /cars
atualiza veiculos

body request
{
    "name": " turbo",
    "description": "2 portas 1.0",
    "model": {
        "producer": {
            "name": "RENAULT"
        },
        "brand": "NOVO CLIO 2021"
    },
    "created": "2021-01-08T01:24:19.251+0000",
    "updated": "2021-01-08T01:24:19.251+0000",
    "status": "AVAILABLE",
    "year": 1980
}
-------------------------------
DELET /cars/{id}
remove carro
-------------------------------

USANDO
H2 como base temporaria 
RestFull para expor os serviços
springboot para gereniar/config. a parte de inversão de controle e injeção de dependência
maven para dependencias



