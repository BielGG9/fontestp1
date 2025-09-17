package gabriel.fontes.br.quarkus.Dto;

import gabriel.fontes.br.quarkus.Model.Fonte;

public record FonteResponse(
    Long id,
    String nome,
    Integer potencia,
    double preco,
    String marca,
    String certificacao
) {
    public static FonteResponse fromEntity(Fonte fonte) {
        return new FonteResponse(
            fonte.getId(),
            fonte.getNome(),
            fonte.getPotencia(),
            fonte.getPreco(),
            fonte.getMarca().getNome(),
            fonte.getCertificacao().toString()
        );
    }
}