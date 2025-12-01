package gabriel.fontes.br.quarkus.Resource;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import gabriel.fontes.br.quarkus.Dto.MarcaRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;

@QuarkusTest
@TestSecurity(user = "testUser", roles = {"ADM", "USER"})
public class MarcaResourceTest {

    private static final Long EXISTING_MARCA_ID = 1L;
    private static final Long NON_EXISTING_MARCA_ID = 999L;

    @Test
    public void testFindAllEndpoint() {
        given()
                .when().get("/marca")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void testFindByIdEndpointFound() {
        given()
                .pathParam("id", EXISTING_MARCA_ID)
                .when().get("/marca/{id}")
                .then()
                .statusCode(200)
                .body("id", is(EXISTING_MARCA_ID.intValue()))
                .body("nome", is("Corsair"));
    }

     @Test
    public void testFindByIdEndpointNotFound() {
        given()
                .pathParam("id", NON_EXISTING_MARCA_ID)
                .when().get("/marca/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    public void testCreateEndpoint() {
        MarcaRequest requestDto = new MarcaRequest("Nova Marca Teste");

        given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .when().post("/marca")
                .then()
                .statusCode(201)
                .body("nome", is("Nova Marca Teste"));
    }

    @Test
    public void testUpdateEndpoint() {
        MarcaRequest requestDto = new MarcaRequest("Marca Atualizada");

        given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .pathParam("id", EXISTING_MARCA_ID)
                .when().put("/marca/{id}")
                .then()
                .statusCode(200)
                .body("id", is(EXISTING_MARCA_ID.intValue()))
                .body("nome", is("Marca Atualizada"));
    }

    @Test
    public void testUpdateEndpointNotFound() {
         MarcaRequest requestDto = new MarcaRequest("Nome");
         given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .pathParam("id", NON_EXISTING_MARCA_ID)
                .when().put("/marca/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    public void testDeleteEndpoint() {
         // Crie uma marca para deletar
         MarcaRequest createDto = new MarcaRequest("Marca Para Deletar");
         var response = given()
                            .contentType(ContentType.JSON)
                            .body(createDto)
                            .when().post("/marca")
                            .then()
                            .statusCode(201)
                            .extract().response();
         Long idToDelete = response.jsonPath().getLong("id");

         // Delete
         given()
                .pathParam("id", idToDelete)
                .when().delete("/marca/{id}")
                .then()
                .statusCode(200)
                .body("id", is(idToDelete.intValue()));

         // Verifique
         given()
                .pathParam("id", idToDelete)
                .when().get("/marca/{id}")
                .then()
                .statusCode(404);
    }

     @Test
    public void testDeleteEndpointNotFound() {
        given()
                .pathParam("id", NON_EXISTING_MARCA_ID)
                .when().delete("/marca/{id}")
                .then()
                .statusCode(404);
    }
}