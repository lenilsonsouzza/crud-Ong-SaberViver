package saberViver.com.appSaberviver.servicos;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saberViver.com.appSaberviver.dto.AdministradorDTO;
import saberViver.com.appSaberviver.dto.CadastroAdministradorDTO;
import saberViver.com.appSaberviver.entidades.Administrador;
import saberViver.com.appSaberviver.entidades.Aluno;
import saberViver.com.appSaberviver.entidades.user.Role;
import saberViver.com.appSaberviver.entidades.user.User;
import saberViver.com.appSaberviver.repositories.AdministradorRepositorio;
import saberViver.com.appSaberviver.repositories.UserRepositorio;
import saberViver.com.appSaberviver.servicos.exceptions.ResourceNotFoundException;

import java.util.List;


@RequiredArgsConstructor
@Service
public class AdministradorServico {


    private final AdministradorRepositorio administradorRepositorio;
    private final UserRepositorio userRepositorio;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public AdministradorDTO findyByCpf(String cpf) {
        Administrador administrador = administradorRepositorio.findByCpf((cpf)).orElseThrow(()->new ResourceNotFoundException("CPF não encontrado"));
        return new AdministradorDTO(administrador);
    }
    @Transactional(readOnly = true)
    public List<Administrador> buscarPorNome(String nome) {
        return administradorRepositorio.findByNomeContainingIgnoreCase(nome);
    }

    @Transactional(readOnly = true)
    public Page<AdministradorDTO> findALL(Pageable page) {

        Page<Administrador> result = administradorRepositorio.findAll(page);
        return result.map(x -> new AdministradorDTO(x));
    }

    @Transactional
    public AdministradorDTO cadastrarAdministrador(CadastroAdministradorDTO dto) {

        if (userRepositorio.findBylogin(dto.getLogin()) != null) {
            throw new IllegalArgumentException("Login já existente!");
        }

        User userLogado = SecurityContextHolderHelper.getUser();


        if (userLogado.getRoler() != Role.ADM_MASTER) {
            throw new AccessDeniedException("Apenas ADM_MASTER pode criar ADM");
        }

        User user = new User(dto.getLogin(), passwordEncoder.encode(dto.getSenha()), Role.ADM);
        userRepositorio.save(user);

        Administrador adm = new Administrador();
        adm.setNome(dto.getNome());
        adm.setSobreNome(dto.getSobreNome());
        adm.setCpf(dto.getCpf());
        adm.setTelefone(dto.getTelefone());
        adm.setAreaAtuacao(dto.getAreaAtuacao());
        adm.setEmail(dto.getEmail());
        adm.setDataNascimento(dto.getDataNascimento());
        adm.setUser(user);

        administradorRepositorio.save(adm);

        return new AdministradorDTO(adm);
    }



    @Transactional
    public AdministradorDTO atualizar(Long id, AdministradorDTO dto) {
        try {

            Administrador entidade = administradorRepositorio.getReferenceById(id);

            copiarAdministradorDTOParaEntidade(dto, entidade);

            entidade = administradorRepositorio.save(entidade);
            return new AdministradorDTO(entidade);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Administrador não encontrado");
        }

    }

    public void deletar(Long id) {
        Administrador administrador = administradorRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador não encontrado"));

        administradorRepositorio.delete(administrador);
    }



    private void copiarAdministradorDTOParaEntidade(AdministradorDTO dto, Administrador entidade) {
        entidade.setNome(dto.getNome());
        entidade.setSobreNome(dto.getSobreNome());
        entidade.setCpf(dto.getCpf());
        entidade.setTelefone(dto.getTelefone());
        entidade.setAreaAtuacao(dto.getAreaAtuacao());
        entidade.setEmail(dto.getEmail());
        entidade.setDataNascimento(dto.getDataNascimento());


    }

}
