package gabriel.fontes.br.quarkus.Dto;

import java.util.List;

import gabriel.fontes.br.quarkus.Model.Cliente;

public record ClienteResponse(
    Long id,
    String nome,
    String email,
    List<EnderecoResponse> enderecos,
    List<TelefoneResponse> telefones
) {
    public static ClienteResponse fromEntity(Cliente cliente) {
        List<TelefoneResponse> telefonesDto = cliente.getTelefones().stream()
            .map(TelefoneResponse::fromEntity)
            .toList();

        List<EnderecoResponse> enderecosDto = cliente.getEnderecos().stream()
            .map(EnderecoResponse::fromEntity)
            .toList();

        return new ClienteResponse(
            cliente.getId(),
            cliente.getNome(),
            cliente.getEmail(),
            enderecosDto,
            telefonesDto
        );
    }
}