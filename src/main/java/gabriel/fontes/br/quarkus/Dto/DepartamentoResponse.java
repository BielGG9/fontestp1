package gabriel.fontes.br.quarkus.Dto;


import gabriel.fontes.br.quarkus.Model.Enums.StatusDepartamento;

public record DepartamentoResponse(
    Long id,
    String sigla,
    String descricao,
    StatusDepartamento statusDepartamento
) {

    public static DepartamentoResponse fromEntity(gabriel.fontes.br.quarkus.Model.Departamento departamento) {
        return new DepartamentoResponse(
            departamento.getId(),
            departamento.getSigla(),
            departamento.getDescricao(),
            departamento.getStatusDepartamento()
        );
    }
}
