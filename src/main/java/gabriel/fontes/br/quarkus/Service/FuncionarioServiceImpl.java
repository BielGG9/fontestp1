package gabriel.fontes.br.quarkus.Service;

import gabriel.fontes.br.quarkus.Dto.FuncionarioRequest;
import gabriel.fontes.br.quarkus.Dto.FuncionarioResponse;
import gabriel.fontes.br.quarkus.Model.Departamento;
import gabriel.fontes.br.quarkus.Model.Funcionario;
import gabriel.fontes.br.quarkus.Repository.DepartamentoRepository;
import gabriel.fontes.br.quarkus.Repository.FuncionarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class FuncionarioServiceImpl implements FuncionarioService {

    @Inject
    FuncionarioRepository repository;

    @Inject
    DepartamentoRepository departamentoRepository;

    public List<FuncionarioResponse> buscarFuncionariosPorNome(String termoDeBusca) {
        List<Funcionario> funcionariosEncontrados = repository.findByNomeContendo(termoDeBusca);

        return funcionariosEncontrados.stream()
                .map(FuncionarioResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FuncionarioResponse create(FuncionarioRequest dto) {
        Funcionario novoFuncionario = new Funcionario();
        novoFuncionario.setNome(dto.nome());
        novoFuncionario.setEmail(dto.email());
        novoFuncionario.setCpf(dto.cpf());
        novoFuncionario.setRg(dto.rg());
        novoFuncionario.setCargo(dto.cargo());

        Long departamentoId;
        try {
            departamentoId = Long.parseLong(dto.departamento());
        } catch (NumberFormatException e) {
            throw new NotFoundException("ID do departamento inválido: " + dto.departamento());
        }

        Departamento departamento = departamentoRepository.findByIdOptional(departamentoId)
                .orElseThrow(() -> new NotFoundException("Departamento com ID " + departamentoId + " não encontrado."));

        novoFuncionario.setDepartamento(departamento);

        repository.persist(novoFuncionario);
        return FuncionarioResponse.fromEntity(novoFuncionario);
    }

    @Override
    @Transactional
    public FuncionarioResponse update(Long id, FuncionarioRequest dto) {
        Funcionario funcionario = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Funcionario com id " + id + " não encontrado."));

        funcionario.setNome(dto.nome());
        funcionario.setEmail(dto.email());
        funcionario.setCpf(dto.cpf());
        funcionario.setRg(dto.rg());
        funcionario.setCargo(dto.cargo());

        Long departamentoId;
         try {
            departamentoId = Long.parseLong(dto.departamento());
        } catch (NumberFormatException e) {
            throw new NotFoundException("ID do departamento inválido: " + dto.departamento());
        }
        Departamento departamento = departamentoRepository.findByIdOptional(departamentoId)
                .orElseThrow(() -> new NotFoundException("Departamento com ID " + departamentoId + " não encontrado."));
        funcionario.setDepartamento(departamento);

        return FuncionarioResponse.fromEntity(funcionario);
    }

    @Override
    @Transactional
    public FuncionarioResponse delete(Long id) {
        Funcionario funcionarioExistente = repository.findByIdOptional(id)
            .orElseThrow(() -> new NotFoundException("Funcionario com ID " + id + " não encontrado para exclusão."));

            FuncionarioResponse resposta = FuncionarioResponse.fromEntity(funcionarioExistente);
            repository.delete(funcionarioExistente);
            return resposta;
        }

    @Override
    public List<FuncionarioResponse> findAll() {
        return repository.listAll().stream()
                .map(FuncionarioResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public FuncionarioResponse findById(Long id) {
        Funcionario funcionario = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Funcionario com id " + id + " não encontrado."));
        return FuncionarioResponse.fromEntity(funcionario);
    }
}