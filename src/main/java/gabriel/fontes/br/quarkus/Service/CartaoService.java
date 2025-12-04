package gabriel.fontes.br.quarkus.Service;

import java.util.List;

import gabriel.fontes.br.quarkus.Dto.CartaoRequest;
import gabriel.fontes.br.quarkus.Dto.CartaoResponse;
import gabriel.fontes.br.quarkus.Model.Cartao;


public interface CartaoService {

    CartaoResponse create(CartaoRequest dto);
    CartaoResponse update(Long id, CartaoRequest dto);
    CartaoResponse delete(Long id);
    List<CartaoResponse> findAll();
    CartaoResponse findById(Long id);
}
