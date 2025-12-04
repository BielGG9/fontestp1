package gabriel.fontes.br.quarkus.Dto;

public record PagamentoResponse(
    String tipo,
    Double valor,
    String status,

    String chavePix,
    String codigoBarras,
    String finalCartao
) {

    public static PagamentoResponse pix(Double valor, String chavePix) {
        return new PagamentoResponse("PIX", valor, "PENDENTE", chavePix, null, null);
    }
}
    


