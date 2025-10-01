package gabriel.fontes.br.quarkus.Dto;

import gabriel.fontes.br.quarkus.Model.Funcionario;
import gabriel.fontes.br.quarkus.Model.Enums.Cargo;

public record FuncionarioResponse(
    String nome,
    Cargo cargo
) {

    public static FuncionarioResponse fromModel(gabriel.fontes.br.quarkus.Model.Funcionario funcionario) {
        return new FuncionarioResponse(
            funcionario.getNome(),
            funcionario.getCargo()
        );
    }

    public static FuncionarioResponse fromEntity(Funcionario novoFuncionario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromEntity'");
    }
}
