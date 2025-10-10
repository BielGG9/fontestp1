package gabriel.fontes.br.quarkus.Dto;

public record PessoaFisicaResponse( 
    String cpf,
    String rg
    )
{
    public static PessoaFisicaResponse fromEntity(gabriel.fontes.br.quarkus.Model.PessoaFisica pessoaFisica) {
        return new PessoaFisicaResponse(
            pessoaFisica.getCpf(),
            pessoaFisica.getRg()
        );
    }
}
