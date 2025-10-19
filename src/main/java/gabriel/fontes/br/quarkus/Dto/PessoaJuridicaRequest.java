package gabriel.fontes.br.quarkus.Dto;

import jakarta.validation.constraints.NotBlank;

public record PessoaJuridicaRequest(
    @NotBlank(message = "O CNPJ é obrigatório.")
    String cnpj,

    @NotBlank(message = "A inscrição estadual é obrigatória.")
    String inscricaoEstadual
) {

    public Object razaoSocial() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'razaoSocial'");
    }}
