# carstore
projeto para simular a fabricação de carros<br>

PARA BAIXAR O PROJETO git clone https://github.com/edivaldo100/carstore.git<br>

PASSOS PARA EXECUTAR <br>
2 - So executar o projeto e sua classe principal "Application" com [javaApplication ou run springBoot] <br>
ou executar os testes Unitarios <br>
ou mvn clean instal e java -jar carstore.jar<br>

API Endpoints<br>

GET /cars<br>
Retorna todos os veiculos<br>

-------------------------------

GET /cars/find?q=turbo<br>
Retorna todos os veiculos com esse parametro<br>

-------------------------------

GET /cars/{id}<br>
Retorna o veiculos pelo id<br>

-------------------------------

POST /cars/{id}<br>
salva veiculos<br>

body request<br>
{<br>
    "name": " turbo",<br>
    "description": "2 portas 1.0",<br>
    "model": {<br>
        "producer": {<br>
            "name": "RENAULT"<br>
        },<br>
        "brand": "NOVO CLIO 2021"<br>
    },<br>
    "created": "2021-01-08T01:24:19.251+0000",<br>
    "updated": "2021-01-08T01:24:19.251+0000",<br>
    "status": "AVAILABLE",<br>
    "year": 1980<br>
}<br>
-------------------------------
PATCH /cars/{id}<br>
Atualiza veiculos<br>

body request<br>
{<br>
    "name": " turbo",<br>
    "description": "4 portas 1.0"<br>
}<br>
-------------------------------

PUT /cars<br>
atualiza veiculos<br>

body request<br>
{<br>
    "name": " turbo",<br>
    "description": "2 portas 1.0",<br>
    "model": {<br>
        "producer": {<br>
            "name": "RENAULT"<br>
        },<br>
        "brand": "NOVO CLIO 2021"<br>
    },<br>
    "created": "2021-01-08T01:24:19.251+0000",<br>
    "updated": "2021-01-08T01:24:19.251+0000",<br>
    "status": "AVAILABLE",<br>
    "year": 1980<br>
}<br>
-------------------------------
DELET /cars/{id}<br>
remove carro<br>
-------------------------------

USANDO<br>
H2 como base temporaria <br>
RestFull para expor os serviços<br>
springboot para gereniar/config. a parte de inversão de controle e injeção de dependência<br>
maven para dependencias<br>



