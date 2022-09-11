# movieAwards
projeto de avaliação conceito RESTFull e teste integrado

## Executar aplicação Spring Boot
mvn spring-boot:run

• curl --location --request POST 'localhost:8083/uploadFile' \
--form 'file=@"/C:/Users/Path/files/movielist.csv"'


• curl --location --request GET 'localhost:8083/listarGanhadores'


Verificar console banco de dados:
http://localhost:8083/h2-console

jdbc:h2:mem:movieAward





