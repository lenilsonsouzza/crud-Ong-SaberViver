package saberViver.com.appSaberviver.servicos;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saberViver.com.appSaberviver.dto.AdministradorDTO;
import saberViver.com.appSaberviver.entidades.Administrador;
import saberViver.com.appSaberviver.repositories.AdministradorRepositorio;


@RequiredArgsConstructor
@Service
public class AdministradorServico {


    private final AdministradorRepositorio administradorRepositorio;

    @Transactional(readOnly = true)
    public AdministradorDTO findyById(long id) {
        Administrador administrador = administradorRepositorio.findById((id)).get();
        return new AdministradorDTO(administrador);
    }

    @Transactional(readOnly = true)
    public Page<AdministradorDTO> findALL(Pageable page) {

        Page<Administrador> result = administradorRepositorio.findAll(page);
        return result.map(x -> new AdministradorDTO(x));
    }

    @Transactional
    public AdministradorDTO inserir(AdministradorDTO dto) {
        Administrador entidade = new Administrador();

        copiarAdministradorDTOParaEntidade(dto, entidade);

        entidade = administradorRepositorio.save(entidade);

        return new AdministradorDTO(entidade);
    }

    private void copiarAdministradorDTOParaEntidade(AdministradorDTO dto, Administrador entidade) {
        entidade.setNome(dto.getNome());
        entidade.setCpf(dto.getCpf());
        entidade.setSenha(dto.getSenha());
        entidade.setTelefone(dto.getTelefone());
        entidade.setAreaAtuacao(dto.getAreaAtuacao());
        entidade.setEmail(dto.getEmail());
        entidade.setDataNascimento(dto.getDataNascimento());


    }

    @Transactional
    public AdministradorDTO atualizar(Long id, AdministradorDTO dto) {
        Administrador entidade = administradorRepositorio.getReferenceById(id);

        copiarAdministradorDTOParaEntidade(dto, entidade);

        entidade = administradorRepositorio.save(entidade);
        return new AdministradorDTO(entidade);


    }

    public void deletar(Long id) {
        Administrador administrador = administradorRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado"));

        administradorRepositorio.delete(administrador);
    }


}
