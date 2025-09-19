package gabriel.fontes.br.quarkus.Dto;

import gabriel.fontes.br.quarkus.Model.Fonte;
import gabriel.fontes.br.quarkus.Model.Enums.Certificacao;

public record FonteResponse(
    Long id,
    String nome,
    Integer potencia,
    double preco,
    String marca,
    Certificacao certificacao
) {
    public static FonteResponse fromEntity(Fonte fonte) {
        return new FonteResponse(
            fonte.getId(),
            fonte.getNome(),
            fonte.getPotencia(),
            fonte.getPreco(),
            fonte.getMarca().getNome(),
            fonte.getCertificacao()
        );
    }
}