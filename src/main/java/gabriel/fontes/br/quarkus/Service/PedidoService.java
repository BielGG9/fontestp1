package gabriel.fontes.br.quarkus.Service;

import java.util.List;

import gabriel.fontes.br.quarkus.Dto.PedidoRequest;
import gabriel.fontes.br.quarkus.Dto.PedidoResponse;

public interface PedidoService {
    
    PedidoResponse create(PedidoRequest dto);
    PedidoResponse update(Long id, PedidoRequest dto);
    PedidoResponse delete(Long id);
    List<PedidoResponse> findAll();
    PedidoResponse findById(Long id);
    List<PedidoResponse> buscarHistoricoPedido(String nomeCliente, Long fonteId, Long ItensPedidoId);

}
