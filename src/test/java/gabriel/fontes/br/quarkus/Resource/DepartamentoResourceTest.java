package gabriel.fontes.br.quarkus.Resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import gabriel.fontes.br.quarkus.Dto.DepartamentoRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;

@QuarkusTest
public class DepartamentoResourceTest {

    private static final Long EXISTING_DEPARTAMENTO_ID = 1L; // Vendas
    private static final Long NON_EXISTING_DEPARTAMENTO_ID = 999L;

    @Test
    public void testFindAllEndpoint() {
        given()
                .when().get("/departamentos")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void testFindByIdEndpointFound() {
        given()
                .pathParam("id", EXISTING_DEPARTAMENTO_ID)
                .when().get("/departamentos/{id}")
                .then()
                .statusCode(200)
                .body("id", is(EXISTING_DEPARTAMENTO_ID.intValue()))
                .body("sigla", is("VND"));
    }

     @Test
    public void testFindByIdEndpointNotFound() {
        given()
                .pathParam("id", NON_EXISTING_DEPARTAMENTO_ID)
                .when().get("/departamentos/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    public void testCreateEndpoint() {
        DepartamentoRequest requestDto = new DepartamentoRequest(
                "MKT",
                "Departamento de Marketing",
                "ATIVO" // Ajuste se o service espera o nome do Enum ou ID
        );

        given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .when().post("/departamentos")
                .then()
                .statusCode(201)
                .body("sigla", is("MKT"))
                .body("descricao", is("Departamento de Marketing"));
                // O service original não setava o status no create, ajuste se necessário
    }

    @Test
    public void testUpdateEndpoint() {
        DepartamentoRequest requestDto = new DepartamentoRequest(
                "VND-UPD",
                "Departamento de Vendas Atualizado",
                "INATIVO" // Ajuste se o service espera o nome do Enum ou ID
        );

        given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .pathParam("id", EXISTING_DEPARTAMENTO_ID)
                .when().put("/departamentos/{id}")
                .then()
                .statusCode(200)
                .body("id", is(EXISTING_DEPARTAMENTO_ID.intValue()))
                .body("sigla", is("VND-UPD"))
                .body("descricao", is("Departamento de Vendas Atualizado"));
                // O service original não atualizava o status no update, ajuste se necessário
    }

     @Test
    public void testUpdateEndpointNotFound() {
        DepartamentoRequest requestDto = new DepartamentoRequest("SIGLA", "Desc", "ATIVO");
        given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .pathParam("id", NON_EXISTING_DEPARTAMENTO_ID)
                .when().put("/departamentos/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    public void testDeleteEndpoint() {
         // Crie um departamento para deletar
         DepartamentoRequest createDto = new DepartamentoRequest("DEL", "Deletar", "ATIVO");
         var response = given()
                            .contentType(ContentType.JSON)
                            .body(createDto)
                            .when().post("/departamentos")
                            .then()
                            .statusCode(201)
                            .extract().response();
         Long idToDelete = response.jsonPath().getLong("id");

        // Delete
        given()
                .pathParam("id", idToDelete)
                .when().delete("/departamentos/{id}")
                .then()
                .statusCode(200)
                .body("id", is(idToDelete.intValue()));

        // Verifique
        given()
                .pathParam("id", idToDelete)
                .when().get("/departamentos/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    public void testDeleteEndpointNotFound() {
        given()
                .pathParam("id", NON_EXISTING_DEPARTAMENTO_ID)
                .when().delete("/departamentos/{id}")
                .then()
                .statusCode(404);
    }
}