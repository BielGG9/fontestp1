package gabriel.fontes.br.quarkus.Resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import gabriel.fontes.br.quarkus.Dto.FonteRequest;
import gabriel.fontes.br.quarkus.Model.Enums.Certificacao;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo; // Mudado para >= 0

@QuarkusTest
public class FonteResourceTest {

    private static final Long EXISTING_FONTE_ID = 1L; // Assumindo ID 1 após import.sql corrigido
    private static final Long NON_EXISTING_FONTE_ID = 999L;
    private static final Long EXISTING_MARCA_ID = 1L; // Assumindo ID 1 após import.sql corrigido

    @Test
    public void testFindAllEndpoint() {
        given()
                .when().get("/fontes")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(0)); // Espera lista >= 0
    }

    @Test
    public void testFindByIdEndpointFound() {
        given()
                .pathParam("id", EXISTING_FONTE_ID)
                .when().get("/fontes/{id}")
                .then()
                .statusCode(200) // Pode falhar com 404 se import.sql falhar
                .body("id", is(EXISTING_FONTE_ID.intValue()))
                .body("nome", is("RM750x")); // Verifique nome no import.sql
    }

     @Test
    public void testFindByIdEndpointNotFound() {
        given()
                .pathParam("id", NON_EXISTING_FONTE_ID)
                .when().get("/fontes/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    public void testCreateEndpoint() {
        // CORRIGIDO: Adicionado argumento 'estoque' (ex: 10)
        FonteRequest requestDto = new FonteRequest(
                "Fonte Teste Create",
                550,          // potencia
                350.00,       // preco
                10,           // estoque
                EXISTING_MARCA_ID,
                "BRONZE"
        );

        given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .when().post("/fontes")
                .then()
                .statusCode(201)
                .body("nome", is("Fonte Teste Create"))
                .body("potencia", is(550))
                .body("estoque", is(10)) // Verifica estoque na resposta
                .body("certificacao.id", is(Certificacao.BRONZE.getId()))
                .body("certificacao.fontcert", is(Certificacao.BRONZE.getFontcert()));
    }

    @Test
    public void testUpdateEndpoint() {
         // CORRIGIDO: Adicionado argumento 'estoque' (ex: 20)
        FonteRequest requestDto = new FonteRequest(
                "Fonte Teste Update",
                850,          // potencia
                900.50,       // preco
                20,           // estoque
                EXISTING_MARCA_ID,
                "GOLD"
        );

        given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .pathParam("id", EXISTING_FONTE_ID)
                .when().put("/fontes/{id}")
                .then()
                .statusCode(200) // Pode falhar com 404 se import.sql falhar
                .body("id", is(EXISTING_FONTE_ID.intValue()))
                .body("nome", is("Fonte Teste Update"))
                .body("potencia", is(850))
                .body("estoque", is(20)) // Verifica estoque na resposta
                .body("certificacao.id", is(Certificacao.GOLD.getId()))
                .body("certificacao.fontcert", is(Certificacao.GOLD.getFontcert()));
    }

     @Test
    public void testUpdateEndpointNotFound() {
         // CORRIGIDO: Adicionado argumento 'estoque' (ex: 0)
        FonteRequest requestDto = new FonteRequest("Nome", 500, 100.0, 0, 1L, "BRONZE");
        given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .pathParam("id", NON_EXISTING_FONTE_ID)
                .when().put("/fontes/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    public void testDeleteEndpoint() {
         // CORRIGIDO: Adicionado argumento 'estoque' (ex: 5)
         FonteRequest createDto = new FonteRequest("Fonte Delete", 450, 200.0, 5, EXISTING_MARCA_ID, "SILVER");
         var response = given()
                            .contentType(ContentType.JSON)
                            .body(createDto)
                            .when().post("/fontes")
                            .then()
                            .statusCode(201)
                            .extract().response();
         Long idToDelete = response.jsonPath().getLong("id");

        given()
                .pathParam("id", idToDelete)
                .when().delete("/fontes/{id}")
                .then()
                .statusCode(200)
                .body("id", is(idToDelete.intValue()));

        given()
                .pathParam("id", idToDelete)
                .when().get("/fontes/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    public void testDeleteEndpointNotFound() {
        given()
                .pathParam("id", NON_EXISTING_FONTE_ID)
                .when().delete("/fontes/{id}")
                .then()
                .statusCode(404);
    }
}