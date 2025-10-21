package gabriel.fontes.br.quarkus.Resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test; // Removido @Disabled

import gabriel.fontes.br.quarkus.Dto.TelefoneRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;

@QuarkusTest // Teste habilitado
public class TelefoneResourceTest {

    // IDs baseados no import.sql para a tabela 'telefones'
    private static final Long EXISTING_TELEFONE_ID = 1L;
    private static final Long NON_EXISTING_TELEFONE_ID = 999L;

     @Test
    public void testFindAllEndpoint() {
        given()
                .when().get("/telefones")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0)); // Agora esperamos que haja telefones
    }

     @Test
    public void testFindByIdEndpointFound() {
        given()
                .pathParam("id", EXISTING_TELEFONE_ID)
                .when().get("/telefones/{id}")
                .then()
                .statusCode(200)
                .body("id", is(EXISTING_TELEFONE_ID.intValue()))
                .body("ddd", is("11")) // Verifique o dado do import.sql
                .body("numero", is("98765-4321")); // Verifique o dado do import.sql
    }

      @Test
    public void testFindByIdEndpointNotFound() {
        given()
                .pathParam("id", NON_EXISTING_TELEFONE_ID)
                .when().get("/telefones/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    public void testCreateEndpoint() {
        TelefoneRequest requestDto = new TelefoneRequest("71", "98888-7777");

        given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .when().post("/telefones")
                .then()
                .statusCode(201) // Created
                .body("ddd", is("71"))
                .body("numero", is("98888-7777"));
    }

    @Test
    public void testUpdateEndpoint() {
         TelefoneRequest requestDto = new TelefoneRequest("61", "3333-4444");

        given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .pathParam("id", EXISTING_TELEFONE_ID)
                .when().put("/telefones/{id}")
                .then()
                .statusCode(200)
                .body("id", is(EXISTING_TELEFONE_ID.intValue()))
                .body("ddd", is("61"))
                .body("numero", is("3333-4444"));
    }

      @Test
    public void testUpdateEndpointNotFound() {
        TelefoneRequest requestDto = new TelefoneRequest("00", "0000");
        given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .pathParam("id", NON_EXISTING_TELEFONE_ID)
                .when().put("/telefones/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    public void testDeleteEndpoint() {
          // Crie um telefone para deletar
         TelefoneRequest createDto = new TelefoneRequest("99", "1234-5678");
         var response = given()
                            .contentType(ContentType.JSON)
                            .body(createDto)
                            .when().post("/telefones")
                            .then()
                            .statusCode(201)
                            .extract().response();
        // Use o ID retornado pelo POST
         Long idToDelete = response.jsonPath().getLong("id");

        // Delete
        given()
                .pathParam("id", idToDelete)
                .when().delete("/telefones/{id}")
                .then()
                .statusCode(200) // OK, pois retorna o objeto deletado
                .body("id", is(idToDelete.intValue()));

        // Verifique se foi realmente deletado
        given()
                .pathParam("id", idToDelete)
                .when().get("/telefones/{id}")
                .then()
                .statusCode(404);
    }

     @Test
    public void testDeleteEndpointNotFound() {
        given()
                .pathParam("id", NON_EXISTING_TELEFONE_ID)
                .when().delete("/telefones/{id}")
                .then()
                .statusCode(404); // O Service lança NotFoundException
    }
}