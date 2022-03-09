// 1 - Pacote
package petstore;
// 2 - Bibliotecas

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

// 3 - Classe
public class Pet {
// 3.1 - Atributos os adjetivos
    String uri = "https://petstore.swagger.io/v2/pet"; // endereço da entidade Pet

// 3.2 - Metodos( faz e não volta retorno) e Funções fazem uma ação devolve um resultado
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    //incluir - Create - Post
   @Test // Identifica o método ou função como um teste para o TestNG
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        // Sintaxe Gherkin
        // Dado - Quando - Então
       //  Given - When - Then

       given() //Dado
               .contentType("application/json") //comum em API REST  - antigas eram "text/xml"
               .log().all()
               .body(jsonBody)
       .when() // Quando
               .post(uri)
       .then() // Então
               .log().all()
               .statusCode(200)
               .body("name", is("Lolipop"))
               .body("status", is("available"))
               .body("category.name", is("dog"))
               .body("tags.name", contains("status"))
       ;
    }


}
