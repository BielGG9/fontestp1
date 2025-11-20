package gabriel.fontes.br.quarkus.Config;

import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

@OpenAPIDefinition(
    info = @Info(
        title = "API Fontes TP1",
        version = "1.0.0",
        description = "API com autenticação via Keycloak"
    ),
    security = @SecurityRequirement(name = "jwt") 
)
@SecurityScheme(
    securitySchemeName = "jwt",
    description = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJGRWkyVV9XaDh6MWZWa09kc2dOT2JCOWlLeGhvYjZWajgtLUVMSEdsaGk0In0.eyJleHAiOjE3NjM2MDkzMjYsImlhdCI6MTc2MzYwOTAyNiwianRpIjoiNDdmZGFiODAtYWZjNS00ZGI0LWI1MTAtNmM0NWUwMmI2ZDMwIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy9tZXUtcmVhbG0iLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiMTMwODZlZDktOTZkYS00OGYzLWJmMmUtZmZiYjliNzZlZjJkIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoicXVhcmt1c3MiLCJzZXNzaW9uX3N0YXRlIjoiZDg1OTM2NTktMjgzNC00MWJiLTg3ZjctMjhhZWQ2ZDdiYzBlIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0OjgwODAiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbImRlZmF1bHQtcm9sZXMtbWV1LXJlYWxtIiwib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoiZW1haWwgcHJvZmlsZSIsInNpZCI6ImQ4NTkzNjU5LTI4MzQtNDFiYi04N2Y3LTI4YWVkNmQ3YmMwZSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJiaWVsIiwiZW1haWwiOiJnYWJyaWVsYnIxNTAzQGdtYWlsLmNvbSJ9.CayCImfpU4ByR9klUtf8T5KrEOSLocU1e3mwE-Stk3agXZDb8WED22B27xEMohlp09xxJvSoyFmlyTYNJ5AC9Ri5tqAU9Tly1bl6uw4MrLNKbTqlxtqZx0Eo4JXH5O_zEIeztru3z12ulRerg-qgcRdUn6v2v3bahs6QaJRGPAYOSbePZ9bAPSE0zABgZsGaf6h0tl575yZ2zT3YAn9ZKZhp4Y3cLkqqKTfvGAmoN1e-9_Ghla4RwGoKNBaSYLLJXQ0jM-FKTU-_sTTP6iLBpUYQxAn4gC9JgYQRSDkjb7ndXZ7LEqHPBrXwId4ETEeUCjvqZG2tgfQ0nCkZlqatww",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class SwaggerConfig extends Application {
}