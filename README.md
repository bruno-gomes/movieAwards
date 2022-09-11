**# movieAwards**
 
 projeto de avaliação conceito RESTFull e teste integrado

**# Executar aplicação Spring Boot** 

mvn spring-boot:run

**# Executar Testes Integrados** 

mvn clean test

**# Modelos chamadas dos serviços disponibilizados** 

• curl --location --request POST 'localhost:8083/movie-awards/movie/uploadFile' \
--form 'file=@"/C:/Users/Path/files/movielist.csv"'

• curl --location --request GET 'localhost:8083/movie-awards/movie/listarGanhadores'

• curl --location --request DELETE 'localhost:8083/movie-awards/movie/delete/all'

**# Requisitos propostos:**

**Requisito do sistema:**
1. Ler o arquivo CSV dos filmes e inserir os dados em uma base de dados ao iniciar a
aplicação.

**Requisitos da API:**
1. Obter o produtor com maior intervalo entre dois prêmios consecutivos, e o que
obteve dois prêmios mais rápido, seguindo a especificação de formato definida confome exemplo:
{
    "min": [
        {
            "producer": "Producer 1",
            "interval": 1,
            "previousWin": 2008,
            "followingWin": 2009
        },
        {
            "producer": "Producer 2",
            "interval": 1,
            "previousWin": 2018,
            "followingWin": 2019
        }
    ],
    "max": [
        {
            "producer": "Producer 1",
            "interval": 99,
            "previousWin": 1900,
            "followingWin": 1999
        },
        {
            "producer": "Producer 2",
            "interval": 99,
            "previousWin": 2000,
            "followingWin": 2099
        }
    ]
}

**Requisitos não funcionais do sistema:**
1. O web service RESTful deve ser implementado com base no nível 2 de maturidade
de Richardson;
2. Devem ser implementados somente testes de integração. Eles devem garantir que
os dados obtidos estão de acordo com os dados fornecidos na proposta;
3. O banco de dados deve estar em memória utilizando um SGBD embarcado (por
exemplo, H2). Nenhuma instalação externa deve ser necessária;
4. A aplicação deve conter um readme com instruções para rodar o projeto e os
testes de integração.


