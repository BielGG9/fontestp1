package gabriel.fontes.br.quarkus.Dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.Valid; 

public record ClienteRequest(
    @NotBlank(message = "O nome não pode ser vazio")
    String nome,

    @NotBlank(message = "O email não pode ser vazio")
    String email,

    LocalDateTime dataCadastro,

    @Valid 
    List<TelefoneRequest> telefones,

    @Valid 
    List<EnderecoRequest> enderecos
    
) {}