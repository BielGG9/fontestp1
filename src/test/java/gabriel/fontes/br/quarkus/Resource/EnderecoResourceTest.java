package gabriel.fontes.br.quarkus.Resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test; 

import gabriel.fontes.br.quarkus.Dto.EnderecoRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;

@QuarkusTest 
public class EnderecoResourceTest {

    
    private static final Long EXISTING_ENDERECO_ID = 1L;
    private static final Long NON_EXISTING_ENDERECO_ID = 999L;

     @Test
    public void testFindAllEndpoint() {
        given()
                .when().get("/enderecos")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0)); 
    }

     @Test
    public void testFindByIdEndpointFound() {
        given()
                .pathParam("id", EXISTING_ENDERECO_ID)
                .when().get("/enderecos/{id}")
                .then()
                .statusCode(200)
                .body("id", is(EXISTING_ENDERECO_ID.intValue()))
                .body("rua", is("Rua das Flores")); 
    }

      @Test
    public void testFindByIdEndpointNotFound() {
        given()
                .pathParam("id", NON_EXISTING_ENDERECO_ID)
                .when().get("/enderecos/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    public void testCreateEndpoint() {
        EnderecoRequest requestDto = new EnderecoRequest(
                "Rua Nova Teste", "100", "Apto 1", "Bairro Novo", "Cidade Nova", "NW", "99999-000"
        );

        given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .when().post("/enderecos")
                .then()
                .statusCode(201) // Created
                .body("rua", is("Rua Nova Teste"))
                .body("cep", is("99999-000"));
                
    }

    @Test
    public void testUpdateEndpoint() {
         EnderecoRequest requestDto = new EnderecoRequest(
                "Rua Atualizada Teste", "200", null, "Bairro Upd", "Cidade Upd", "UP", "54321-111"
        );

        given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .pathParam("id", EXISTING_ENDERECO_ID)
                .when().put("/enderecos/{id}")
                .then()
                .statusCode(200)
                .body("id", is(EXISTING_ENDERECO_ID.intValue()))
                .body("rua", is("Rua Atualizada Teste"))
                .body("cep", is("54321-111"));
                
    }

      @Test
    public void testUpdateEndpointNotFound() {
        EnderecoRequest requestDto = new EnderecoRequest("Rua", "Num", null, "Bairro", "Cidade", "ES", "CEP");
        given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .pathParam("id", NON_EXISTING_ENDERECO_ID)
                .when().put("/enderecos/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    public void testDeleteEndpoint() {
         EnderecoRequest createDto = new EnderecoRequest("Rua Del", "0", null, "Bairro Del", "Cidade Del", "DL", "00000-000");
         var response = given()
                            .contentType(ContentType.JSON)
                            .body(createDto)
                            .when().post("/enderecos")
                            .then()
                            .statusCode(201)
                            .extract().response();
         Long idToDelete = response.jsonPath().getLong("id");

        given()
                .pathParam("id", idToDelete)
                .when().delete("/enderecos/{id}")
                .then()
                .statusCode(200) 
                .body("id", is(idToDelete.intValue()));

        given()
                .pathParam("id", idToDelete)
                .when().get("/enderecos/{id}")
                .then()
                .statusCode(404);
    }

     @Test
    public void testDeleteEndpointNotFound() {
        given()
                .pathParam("id", NON_EXISTING_ENDERECO_ID)
                .when().delete("/enderecos/{id}")
                .then()
                .statusCode(404); 
    }
}