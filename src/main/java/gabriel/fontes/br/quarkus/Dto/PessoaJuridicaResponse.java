package gabriel.fontes.br.quarkus.Dto;


import gabriel.fontes.br.quarkus.Model.Jpa.PessoaJuridica;

public record PessoaJuridicaResponse(
    String cnpj,
    String inscricaoEstadual
) {

    public static PessoaJuridicaResponse fromEntity(PessoaJuridica pessoaJuridica) {
        return new PessoaJuridicaResponse(
            pessoaJuridica.getCnpj(),
            pessoaJuridica.getInscricaoEstadual()
        );
    }
    
}
