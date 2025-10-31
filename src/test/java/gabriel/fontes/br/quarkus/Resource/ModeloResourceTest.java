package gabriel.fontes.br.quarkus.Resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import gabriel.fontes.br.quarkus.Dto.ModeloRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.equalToIgnoringCase; // Import para o ignoreCase (embora não vá resolver aqui)

@QuarkusTest
public class ModeloResourceTest {

    // IDs baseados no import.sql. ID 1 = Corsair, ID 2 = Seasonic
    private static final Long EXISTING_MODELO_ID = 1L;
    private static final Long NON_EXISTING_MODELO_ID = 999L;
    
    // --- CORREÇÃO AQUI ---
    // Mudar de 1L para 2L, para usar "Seasonic" que não é modificada por outros testes.
    private static final Long MARCA_ID_PARA_TESTE = 2L; 
    private static final String NOME_MARCA_PARA_TESTE = "Seasonic";

    @Test
    public void testFindAllEndpoint() {
        given()
                .when().get("/modelos")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void testFindByIdEndpointFound() {
        given()
                .pathParam("id", EXISTING_MODELO_ID)
                .when().get("/modelos/{id}")
                .then()
                .statusCode(200)
                .body("id", is(EXISTING_MODELO_ID.intValue()))
                .body("numeracao", is(750));
    }

    @Test
    public void testFindByIdEndpointNotFound() {
        given()
                .pathParam("id", NON_EXISTING_MODELO_ID)
                .when().get("/modelos/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    public void testCreateEndpoint() {
        // Usa a Marca ID 2 ("Seasonic")
        ModeloRequest requestDto = new ModeloRequest(850, MARCA_ID_PARA_TESTE);

        given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .when().post("/modelos")
                .then()
                .statusCode(201)
                .body("numeracao", is(850))
                .body("marca", is(NOME_MARCA_PARA_TESTE)); // Verifica por "Seasonic"
    }

    @Test
    public void testUpdateEndpoint() {
        // Usa a Marca ID 2 ("Seasonic")
        ModeloRequest requestDto = new ModeloRequest(900, MARCA_ID_PARA_TESTE);

        given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .pathParam("id", EXISTING_MODELO_ID) // Atualiza o Modelo 1 (RM750)
                .when().put("/modelos/{id}")
                .then()
                .statusCode(200)
                .body("id", is(EXISTING_MODELO_ID.intValue()))
                .body("numeracao", is(900))
                .body("marca", is(NOME_MARCA_PARA_TESTE)); // Verifica se a marca foi atualizada para "Seasonic"
    }

    @Test
    public void testUpdateEndpointNotFound() {
        ModeloRequest requestDto = new ModeloRequest(100, MARCA_ID_PARA_TESTE);
        given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .pathParam("id", NON_EXISTING_MODELO_ID)
                .when().put("/modelos/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    public void testDeleteEndpoint() {
        // Cria um modelo usando a Marca ID 2 ("Seasonic")
         ModeloRequest createDto = new ModeloRequest(1200, MARCA_ID_PARA_TESTE);
         var response = given()
                            .contentType(ContentType.JSON)
                            .body(createDto)
                            .when().post("/modelos")
                            .then()
                            .statusCode(201)
                            .extract().response();
         Long idToDelete = response.jsonPath().getLong("id");

        given()
                .pathParam("id", idToDelete)
                .when().delete("/modelos/{id}")
                .then()
                .statusCode(200);

        given()
                .pathParam("id", idToDelete)
                .when().get("/modelos/{id}")
                .then()
                .statusCode(404);
    }

     @Test
    public void testDeleteEndpointNotFound() {
        given()
                .pathParam("id", NON_EXISTING_MODELO_ID)
                .when().delete("/modelos/{id}")
                .then()
                .statusCode(404);
    }
}