package gabriel.fontes.br.quarkus.Dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record PedidoRequest(
    
    @NotBlank(message = "O nome do cliente é obrigatório")
    String nomeCliente,

    @NotBlank(message = "O endereço de entrega é obrigatório")
    Long idEnderecoEntrega,

    @NotBlank(message = "Os itens do pedido são obrigatórios")
    @Valid
    List<ItemPedidoRequest> itensPedido,

    @NotBlank(message = "O tipo de pagamento é obrigatório")
    String tipoPagamento
    
    
) {

    

    
}
