package gabriel.fontes.br.quarkus.Dto;

import java.time.LocalDateTime;
import gabriel.fontes.br.quarkus.Model.Cliente;

public record ClienteResponse(
    Long id,
    String nome,
    String email,
    String cpf,
    String rg,
    LocalDateTime dataCadastro
) {

    public static ClienteResponse fromEntity(Cliente cliente) {
        return new ClienteResponse(
            cliente.getId(),
            cliente.getNome(),
            cliente.getEmail(),
            cliente.getCpf(),
            cliente.getRg(),
            cliente.getDataCadastro()
        );
    }
}