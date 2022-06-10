# API RESTful _filtered movies per mood_
Api desenvolvida para sugerir filmes que estão em cartaz para quem for usa-lá com base na temperatura atual do lugar onde a pessoa está. Qualquer lugar do mundo! Com isso sua lógica segue: <br>
Se o local onde a pessoa está estiver fazendo mais de **40°C** ou mais serão sugeridos filmes com o gênero **ação** <br>
Se estiver fazendo entre **36°C e 40**, então serão sugeridos filmes com o gênero **comédia** <br>
Se estiver fazendo entre **20°C e 35**, então serão sugeridos filmes com o gênero **animações** <br>
Se estiver fazendo entre **0°C e 20**, então serão sugeridos filmes com o gênero **suspence** <br
E estiver fazendo menos de **0°C**, então serão sugeridos filmes com o gênero **documentários** <br>
Para o desenvolvimento foram usadas duas APIs externas que são elas: [openweathermap](https://openweathermap.org/) & [themoviedb](https://www.themoviedb.org/)
## Objetivo ✔
- Desenvolver a API conforme os requisitos pedidos
- Usar boas práticas
- Aprender e desenvolver novas técnicas
- Praticar
- Consumir APIs externas
---
## Tecnologias / Bibliotecas usadas 👨🏿‍💻
- Spring Boot
- Bean Validation
- Lombok
- Maven
- Git
- JPA
- H2
- **Postman** - para testar a api com uma ambiente mais agradável 
---
## Objetivos futuros 💡
- [ ] Implementar um banco de dados através de docker
- [ ] Realizar testes unitários
- [ ] Documentar API
---
## Baixe-a na sua máquina para testar ou até mesmo melhora-lá 🤝
### **_REQUISITOS NECESSÁRIOS_**
- Java - 11+ version
- Maven - 3.8.5 version <br>
**_Necessário ter instalado na sua máquina pelo menos esses dois caras de cima_**
## E agora aqui vai o passo a passo para baixar a api e testar:

```
# Copie a url do repositório no botão verde lá em cima ou esta que está aqui em baixo:
$ https://github.com/wpmello/filtered-movie-per-mood.git

# Crie uma pasta em um ambiente de preferência na sua máquina
$ Dentro da pasta abra qualquer teminal.

# Pelo terminal, clone o repositório com o seguinte comando:
$ git clone https://github.com/wpmello/filtered-movie-per-mood.git

Abra a pasta do repositório e se voce instalou certinho o Java e o Maven na sua máquina agora basta iniciar a aplicação com o comando:
$$$ mvn spring-boot:run

A aplicação vai subir e voce vai ver pelo seu terminal

Caso não tenha certeza que baixou o Java ou o Maven corretamente basta ir no terminal (com a aplicação parada) e digitar

$ mvn -v
Para verificar o Maven
$ java -version
Para verificar o Java 

Agora finalmente para testar a api e ver seus resultado basta ir no navegador e digitar:

$ localhost:8080/app/movie/on-playing
Para ver todos os filmes em cartaz em formato Json

$ localhost:8080/app/movie/on-playing/sao paulo
Para ver os filmes sugeridos com base na temperatura atual da cidade de São Paulo. Você pode testar com qualquer cidade.

----> Para as funções abaixo é recomendados que baixe o Postman para fazer a inserção de dados no  <----
----> banco e assim conseguir testar melhor as outras funções. Link logo abaixo depois desse bloco <----

$ localhost:8080/app/movie/db
Para mostrar todas os filmes savos no banco de dados

$ localhost:8080/app/movie/db/1
Para buscar no banco de dados o filme com o id 1

$ localhost:8080/app/movie/db/
Para inserir dados no banco você pode simplesmente usar o dado 
{
    "title": "Code Geass"
}

$ localhost:8080/app/movie/db/1
Para artualizar, passe o id de qual filme deseja atualizar

$ localhost:8080/app/movie/db/1
Para deletar, passe o id de qual filme deseja deletar

# Caso queira verificar o banco de dados h2 basta ir no navegador e inserir a url:
$ localhost:8080/h2-console

As configurações do mesmo se encontram no arquivo 'application.properties'.
url: jdbc:h2:mem:filtered-movie-per-mood
user name: sa
```
---
### Recomendação:
- Você pode usar o [Postman](https://www.postman.com/downloads/) para testar as funcionalidades da API.
- Você pode usar o [IntelliJ](https://www.jetbrains.com/pt-br/idea/download/#section=windows) para desenvolver melhoria ou pegar como base para um novo projeto.