package gabriel.fontes.br.quarkus.Resource;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import gabriel.fontes.br.quarkus.Dto.DepartamentoRequest;
import gabriel.fontes.br.quarkus.Dto.FuncionarioRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
@TestSecurity(user = "testUser", roles = {"ADM", "USER"})
public class FuncionarioResourceTest {

    private static final Long EXISTING_FUNCIONARIO_ID = 2L; 
    private static final Long NON_EXISTING_FUNCIONARIO_ID = 999L;
    private static Long existingDepartamentoId;

    @BeforeAll
    static void setupDepartamento() {
        DepartamentoRequest depDto = new DepartamentoRequest("TEST", "Dep Teste Func", "ATIVO");
        var response = given()
                .contentType(ContentType.JSON)
                .body(depDto)
                .when().post("/departamentos")
                .then()
                .statusCode(201)
                .extract().response();
        existingDepartamentoId = response.jsonPath().getLong("id");
    }

    @Test
    public void testFindAllEndpoint() {
        given()
                .when().get("/funcionarios")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void testFindByIdEndpointFound() {
        given()
                .pathParam("id", EXISTING_FUNCIONARIO_ID)
                .when().get("/funcionarios/{id}")
                .then()
                .statusCode(200)
                .body("id", is(EXISTING_FUNCIONARIO_ID.intValue()))
                .body("nome", is("Carlos Andrade"));
    }

    @Test
    public void testFindByIdEndpointNotFound() {
        given()
                .pathParam("id", NON_EXISTING_FUNCIONARIO_ID)
                .when().get("/funcionarios/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    public void testCreateEndpoint() {
        FuncionarioRequest requestDto = new FuncionarioRequest(
                "Func Teste Create",
                "func.create@email.com",
                "777.888.999-00",
                "7654321 SSP/BA",
                "Analista Jr",
                existingDepartamentoId.toString() 
        );

        given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .when().post("/funcionarios")
                .then()
                .statusCode(201)
                .body("nome", is("Func Teste Create"))
                .body("email", is("func.create@email.com"))
                .body("cargo", is("Analista Jr"))
                .body("departamento", notNullValue()); 
    }

    @Test
    public void testUpdateEndpoint() {
        FuncionarioRequest createDto = new FuncionarioRequest("Antes Update", "antes@func.com", "111", "111", "Cargo Antigo", existingDepartamentoId.toString());
        var postResponse = given().contentType(ContentType.JSON).body(createDto).when().post("/funcionarios").then().statusCode(201).extract().response();
        Long idToUpdate = postResponse.jsonPath().getLong("id");

        FuncionarioRequest updateDto = new FuncionarioRequest(
                "Func Teste Update",
                "func.update@email.com",
                "222.333.444-55",
                "1231231 SSP/MG",
                "Analista Pleno",
                existingDepartamentoId.toString() 
        );

        given()
                .contentType(ContentType.JSON)
                .body(updateDto)
                .pathParam("id", idToUpdate)
                .when().put("/funcionarios/{id}")
                .then()
                .statusCode(200)
                .body("id", is(idToUpdate.intValue()))
                .body("nome", is("Func Teste Update"));
    }

    @Test
    public void testUpdateEndpointNotFound() {
        FuncionarioRequest requestDto = new FuncionarioRequest("Nome", "email", "cpf", "rg", "cargo", "1");
        given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .pathParam("id", NON_EXISTING_FUNCIONARIO_ID)
                .when().put("/funcionarios/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    public void testDeleteEndpoint() {
         FuncionarioRequest createDto = new FuncionarioRequest("Func Delete", "delete@func.com", "333", "333", "Cargo Delete", existingDepartamentoId.toString());
         var response = given()
                            .contentType(ContentType.JSON)
                            .body(createDto)
                            .when().post("/funcionarios")
                            .then()
                            .statusCode(201)
                            .extract().response();
         Long idToDelete = response.jsonPath().getLong("id");

        given()
                .pathParam("id", idToDelete)
                .when().delete("/funcionarios/{id}")
                .then()
                .statusCode(200)
                .body("id", is(idToDelete.intValue()));

        given()
                .pathParam("id", idToDelete)
                .when().get("/funcionarios/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    public void testDeleteEndpointNotFound() {
        given()
                .pathParam("id", NON_EXISTING_FUNCIONARIO_ID)
                .when().delete("/funcionarios/{id}")
                .then()
                .statusCode(404);
    }
}