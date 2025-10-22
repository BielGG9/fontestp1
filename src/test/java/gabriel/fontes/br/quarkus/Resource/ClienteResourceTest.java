    package gabriel.fontes.br.quarkus.Resource; 

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import gabriel.fontes.br.quarkus.Dto.ClienteRequest;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;

@QuarkusTest
public class ClienteResourceTest {

    
    private static final Long EXISTING_CLIENTE_ID = 1L; 
    private static final Long NON_EXISTING_CLIENTE_ID = 999L;

    @Test
    public void testFindAllEndpoint() {
        given()
                .when().get("/clientes")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void testFindByIdEndpointFound() {
        given()
                .pathParam("id", EXISTING_CLIENTE_ID)
                .when().get("/clientes/{id}")
                .then()
                .statusCode(200)
                .body("id", is(EXISTING_CLIENTE_ID.intValue()))
                .body("nome", is("João da Silva")); 
    }

    @Test
    public void testFindByIdEndpointNotFound() {
        given()
                .pathParam("id", NON_EXISTING_CLIENTE_ID)
                .when().get("/clientes/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    public void testCreateEndpoint() {
        ClienteRequest requestDto = new ClienteRequest(
                "Novo Cliente Teste",
                "teste.novo@email.com",
                "111.222.333-44", 
                "1234567 SSP/SP", 
                LocalDateTime.now()
        );

        given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .when().post("/clientes")
                .then()
                .statusCode(201) // Created
                .body("nome", is("Novo Cliente Teste"))
                .body("email", is("teste.novo@email.com"))
                .body("cpf", is("111.222.333-44"));
    }

    @Test
    public void testUpdateEndpoint() {
        ClienteRequest requestDto = new ClienteRequest(
                "Cliente Atualizado",
                "atualizado@email.com",
                "555.666.777-88", 
                "9876543 SSP/RJ",  
                LocalDateTime.now()
        );

        given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .pathParam("id", EXISTING_CLIENTE_ID) 
                .when().put("/clientes/{id}")
                .then()
                .statusCode(200) 
                .body("id", is(EXISTING_CLIENTE_ID.intValue()))
                .body("nome", is("Cliente Atualizado"))
                .body("email", is("atualizado@email.com"))
                .body("cpf", is("555.666.777-88"));
    }

    @Test
    public void testUpdateEndpointNotFound() {
        // Usa um ID que não existe
        ClienteRequest requestDto = new ClienteRequest("Nome", "email@test.com", "cpf", "rg", null);
        given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .pathParam("id", NON_EXISTING_CLIENTE_ID)
                .when().put("/clientes/{id}")
                .then()
                .statusCode(404); 
    }

    @Test
    public void testDeleteEndpoint() {
        
         ClienteRequest createDto = new ClienteRequest("Cliente Para Deletar", "delete@email.com", "000", "000", null);
         var response = given()
                            .contentType(ContentType.JSON)
                            .body(createDto)
                            .when().post("/clientes")
                            .then()
                            .statusCode(201)
                            .extract().response();
         Long idToDelete = response.jsonPath().getLong("id");

       
        given()
                .pathParam("id", idToDelete)
                .when().delete("/clientes/{id}")
                .then()
                .statusCode(200) 
                .body("id", is(idToDelete.intValue()));

        given()
                .pathParam("id", idToDelete)
                .when().get("/clientes/{id}")
                .then()
                .statusCode(404);
    }

     @Test
    public void testDeleteEndpointNotFound() {
        given()
                .pathParam("id", NON_EXISTING_CLIENTE_ID)
                .when().delete("/clientes/{id}")
                .then()
                .statusCode(404); 
    }
}