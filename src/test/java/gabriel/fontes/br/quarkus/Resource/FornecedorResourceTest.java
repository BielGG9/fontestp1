package gabriel.fontes.br.quarkus.Resource;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import gabriel.fontes.br.quarkus.Dto.FornecedorRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;

@QuarkusTest
@TestSecurity(user = "testUser", roles = {"ADM", "USER"})
public class FornecedorResourceTest {

    private static final Long EXISTING_FORNECEDOR_ID = 2L;
    private static final Long NON_EXISTING_FORNECEDOR_ID = 999L;

    @Test
    public void testFindAllEndpoint() {
        given()
                .when().get("/fornecedores")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void testFindByIdEndpointFound() {
        given()
                .pathParam("id", EXISTING_FORNECEDOR_ID)
                .when().get("/fornecedores/{id}")
                .then()
                .statusCode(200)
                .body("id", is(EXISTING_FORNECEDOR_ID.intValue()))
                .body("nome", is("Tech Distribuidora"));
    }

    @Test
    public void testFindByIdEndpointNotFound() {
        given()
                .pathParam("id", NON_EXISTING_FORNECEDOR_ID)
                .when().get("/fornecedores/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    public void testCreateEndpoint() {
        FornecedorRequest requestDto = new FornecedorRequest(
                "Fornecedor Teste Create",
                "contato@testecreate.com",
                "Teste Create Ltda",
                "12.345.678/0001-99",
                "123.456.789.111"
        );

        given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .when().post("/fornecedores")
                .then()
                .statusCode(201)
                .body("nome", is("Fornecedor Teste Create"))
                .body("email", is("contato@testecreate.com"))
                .body("razaoSocial", is("Teste Create Ltda"))
                .body("cnpj", is("12.345.678/0001-99"));
    }

     @Test
    public void testUpdateEndpoint() {
        FornecedorRequest createDto = new FornecedorRequest("Antes Update", "antes@upd.com", "Antes UPD Ltda", "001", "100");
        var postResponse = given()
                .contentType(ContentType.JSON)
                .body(createDto)
                .when().post("/fornecedores")
                .then().statusCode(201).extract().response();
        Long idToUpdate = postResponse.jsonPath().getLong("id");

        FornecedorRequest updateDto = new FornecedorRequest(
                "Fornecedor Teste Update",
                "contato@testeupdate.com",
                "Teste Update S/A",
                "98.765.432/0001-11",
                "987.654.321.999"
        );

        given()
                .contentType(ContentType.JSON)
                .body(updateDto)
                .pathParam("id", idToUpdate)
                .when().put("/fornecedores/{id}")
                .then()
                .statusCode(200)
                .body("id", is(idToUpdate.intValue()))
                .body("nome", is("Fornecedor Teste Update")) 
                .body("email", is("contato@testeupdate.com"));
    }

    @Test
    public void testUpdateEndpointNotFound() {
        FornecedorRequest requestDto = new FornecedorRequest("Nome", "email@t.com", "Razao", "cnpj", "ie");
        given()
                .contentType(ContentType.JSON)
                .body(requestDto)
                .pathParam("id", NON_EXISTING_FORNECEDOR_ID)
                .when().put("/fornecedores/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    public void testDeleteEndpoint() {
         FornecedorRequest createDto = new FornecedorRequest("Fornecedor Delete", "delete@f.com", "Delete Ltda", "002", "200");
         var response = given()
                            .contentType(ContentType.JSON)
                            .body(createDto)
                            .when().post("/fornecedores")
                            .then()
                            .statusCode(201)
                            .extract().response();
         Long idToDelete = response.jsonPath().getLong("id");

        given()
                .pathParam("id", idToDelete)
                .when().delete("/fornecedores/{id}")
                .then()
                .statusCode(200)
                .body("id", is(idToDelete.intValue()));

        given()
                .pathParam("id", idToDelete)
                .when().get("/fornecedores/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    public void testDeleteEndpointNotFound() {
        given()
                .pathParam("id", NON_EXISTING_FORNECEDOR_ID)
                .when().delete("/fornecedores/{id}")
                .then()
                .statusCode(404);
    }
}