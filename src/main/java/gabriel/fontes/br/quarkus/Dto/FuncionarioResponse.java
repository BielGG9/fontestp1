package gabriel.fontes.br.quarkus.Dto;

import gabriel.fontes.br.quarkus.Model.Funcionario;

public record FuncionarioResponse(
    String nome
) {

    public static FuncionarioResponse fromModel(gabriel.fontes.br.quarkus.Model.Funcionario funcionario) {
        return new FuncionarioResponse(
            funcionario.getNome()
        );
    }

    public static FuncionarioResponse fromEntity(Funcionario novoFuncionario) {
        throw new UnsupportedOperationException("Unimplemented method 'fromEntity'");
    }
}
