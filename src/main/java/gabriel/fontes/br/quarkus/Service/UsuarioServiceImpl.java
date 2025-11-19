package gabriel.fontes.br.quarkus.Service;

import java.util.List;
import java.util.stream.Collectors;

import gabriel.fontes.br.quarkus.Dto.UsuarioRequest;
import gabriel.fontes.br.quarkus.Dto.UsuarioResponse;
import gabriel.fontes.br.quarkus.Model.Usuario;
import gabriel.fontes.br.quarkus.Model.Enums.Perfil;
import gabriel.fontes.br.quarkus.Repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository repository;

    @Inject
    HashService hashService;

    @Override
    @Transactional
    public UsuarioResponse create(UsuarioRequest dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setLogin(dto.login());

        String senhaHash = hashService.getHashSenha(dto.senha());
        usuario.setSenha(senhaHash);

        try {
            Perfil perfil = (dto.perfil() != null && !dto.perfil().isBlank()) 
                            ? Perfil.valueOf(dto.perfil()) 
                            : Perfil.USER;
            usuario.setPerfil(perfil);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException("Perfil inválido. Use ADM ou USER.");
        }

        repository.persist(usuario);
        return UsuarioResponse.fromEntity(usuario);
    }

    @Override
    public List<UsuarioResponse> findAll() {
        return repository.listAll().stream()
                .map(UsuarioResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponse findById(Long id) {
        Usuario usuario = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        
        return UsuarioResponse.fromEntity(usuario);
    }

}