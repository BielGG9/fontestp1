package gabriel.fontes.br.quarkus.Dto;

import java.time.LocalDateTime;

import gabriel.fontes.br.quarkus.Model.Pix;

public record PixResponse(
        Long id,
        LocalDateTime validade,
        Double valor,
        String status
) {
    public static PixResponse fromEntity(Pix pix) {
        return new PixResponse(
                pix.getId(),
                pix.getValidade(),
                pix.getValor(),
                pix.getStatus()
        );
    }
}
